package algoritmoanillo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MultiRecieve implements Runnable {

    public static String group;
    public static Integer port;
    public static int timeOut = 0;

    public MultiRecieve(String ipgrupo, int puerto) throws IOException {
            group = ipgrupo;
            port  = puerto;
    }

    @Override
    public void run() {
        try {
            recieveMessage();
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public static void recieveMessage() throws IOException, InterruptedException {
        MulticastSocket mcs = new MulticastSocket(port);
        InetAddress grp = InetAddress.getByName(group);
        mcs.joinGroup(grp);
        byte rec[] = new byte[256];
        DatagramPacket pkg = new DatagramPacket(rec, rec.length);

        while (true) {
            try {
                mcs.receive(pkg);
                String data = new String(pkg.getData(), 0, pkg.getLength());
//String data = new String(pkg.getData()).trim();
                System.out.println("Datos recebidos:" + data);
                decodeMessage(data);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public static void decodeMessage(String data) {
        String messageDiv[] = data.split("->");
        //     ms.sendMessage("WIT->" + myPort + "->" + myIP + "->"+ myID);
        if (messageDiv[0].equals("WIT")) {
                if (Nodo.tengoToken == true) {
                    UniSender.sendMessage(("IHT->" + Nodo.myPort + "->" + Nodo.myIP + "->" + Nodo.myID), Integer.parseInt(messageDiv[1]),messageDiv[2] ); // I Have the Token
                }
        }
        // RPTA A "WIMP->" + myID + "->" + myPort + "->" +  myIP; 
      //            WIMP->465->1234->127.0.0.1
        //           0     1      2        3
        if (messageDiv[0].equals("WIMP")) {
            if (!messageDiv[1].equals(Nodo.myID)){ 
                Nodo.AcomodaNodo(Integer.parseInt(messageDiv[1]), messageDiv[2], messageDiv[3]); // 1 ro Todos agregaran el nodo en el lugar EXCEPTO UNO MISMo
            }
            //Luego uno solo (el que ahora se conectara con el, le enviara la lista de nodos  (en el cual estará incluido el!)
            if ((Nodo.portNext) != null && !messageDiv[2].equals(Nodo.myPort)){  //No puede haber dos nodos con el mismo id! No hay iguales en las condiciones
                /** CONDICIONES **/
                if ((Integer.parseInt(Nodo.myID) < Integer.parseInt(messageDiv[1])) && (Integer.parseInt(Nodo.IDnext) > Integer.parseInt(messageDiv[1])) ){
                    try {
                        //public static void sendList( int portToSend, String ip, int id)    //Este metodo ayuda a configurar al nuevo nodo
                        Nodo.sendList(Integer.parseInt(messageDiv[2]), messageDiv[3], Integer.parseInt(messageDiv[1]));
                    } catch (IOException ex) {
                        Logger.getLogger(MultiRecieve.class.getName()).log(Level.SEVERE, null, ex);
                    }                                                                                                                                                           // -2 PORQUE YA LO COLOCO AL FINAL!!!
                } else if ((Integer.parseInt(Nodo.myID) < Integer.parseInt(messageDiv[1])) && (Integer.parseInt(Nodo.IDnext) < Integer.parseInt(messageDiv[1])) && Nodo.listaNodos.get(Nodo.listaNodos.size() - 2).id.equals(Nodo.myID) /*** para q solo me responda 1**/ ){  try {
                    /// si soy el ultimo y el siguiente es el primero y viene un nuevo ultimo
                    Nodo.sendList(Integer.parseInt(messageDiv[2]), messageDiv[3], Integer.parseInt(messageDiv[1]));
                    } catch (IOException ex) {
                        Logger.getLogger(MultiRecieve.class.getName()).log(Level.SEVERE, null, ex);
                    }                                                                                                                                                       // El ultimo de la lista contendra la información del siguiente cuando entra un nuevo Nodo inicial                                                                           
                }else if ((Integer.parseInt(Nodo.myID) > Integer.parseInt(messageDiv[1])) && (Integer.parseInt(Nodo.IDnext) > Integer.parseInt(messageDiv[1])) && Nodo.listaNodos.get(Nodo.listaNodos.size()-1).id.equals(Nodo.myID) /*** para q solo me responda 1**/ ){  try {
                    /// si soy el ultimo y el siguiente es el primero y viene un nuevo ultimo
                    Nodo.sendList(Integer.parseInt(messageDiv[2]), messageDiv[3], Integer.parseInt(messageDiv[1]));
                    } catch (IOException ex) {
                        Logger.getLogger(MultiRecieve.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
                
            } if (Nodo.portNext == null && Nodo.tengoToken == true && !messageDiv[2].equals(Nodo.myPort)){ try {
                //es el segundo en llegar
                Nodo.sendList(Integer.parseInt(messageDiv[2]), messageDiv[3], Integer.parseInt(messageDiv[1]));
                } catch (IOException ex) {
                    Logger.getLogger(MultiRecieve.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
      
        }
        
         if (messageDiv[0].equals("REMOVE")) {
                int elemento = Integer.parseInt(messageDiv[1]);
                Nodo.removeElement(elemento);
        }
         
         if (messageDiv[0].equals("NUEVOTOKEN")){
             Nodo.isTokenAlive = true;
         }
   }
}