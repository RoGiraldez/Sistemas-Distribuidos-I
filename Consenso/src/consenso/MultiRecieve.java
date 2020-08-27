package consenso;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author murilo erhardt
 */
public class MultiRecieve implements Runnable {

    public static String group = "228.5.6.7";
    public static Integer port = 5865;
    public static int timeOut = 0;

    public MultiRecieve() throws IOException {

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
                String data = new String(pkg.getData(), 0, pkg.getLength()).trim();
                System.out.println("Dados recebidos:" + data);               
                DecodeMessage decodificaMensaje = new DecodeMessage(data);
                decodificaMensaje.start();
                
                        
                        //decodeMessage(data);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public static void decodeMessage(String data) {
        String messageDiv[] = data.split("->");
        if (messageDiv[0].equals("WIC")) {
            if (!(messageDiv[1].equals(NodoB.myPort))) {
                if (NodoB.isCoord == true) {
                    UniSender.sendMessage("IAC->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(messageDiv[1]));
                }
            }
        }

        if (messageDiv[0].equals("ELECTION")) {
            if (!(messageDiv[1].equals(NodoB.myPort))) {
                if (NodoB.myId > Integer.parseInt(messageDiv[2])) {
                    UniSender.sendMessage("OK->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(messageDiv[1]));
                }
            }
        }
        
        if (messageDiv[0].equals("ESTAMOS_CONSENSUANDO")) {
           NodoB.estamosConsensuando = true;
           UniSender.sendMessage("ENVIOX->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(NodoB.portCoord));           
        }
        if (messageDiv[0].equals("FIN_CONSENSO")) {
           NodoB.estamosConsensuando = false;
          
        }
        
        if (messageDiv[0].equals("VALORX")) { //Se quedaran con el menor valor de x
           if (NodoB.x > Integer.parseInt(messageDiv[1]))
           {
               NodoB.x = Integer.parseInt(messageDiv[1]);
           }
           if (NodoB.isCoord ){
               NodoB.contX--;
               NodoB.semaphore.release();
               if (NodoB.contX == 0){
                   NodoB.estamosConsensuando = false;
                   try {
                       MultiSender.sendMessage("FIN_CONSENSO->");
                   } catch (IOException ex) {
                       Logger.getLogger(MultiRecieve.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           }
                      
        }
    }
    
    public static class DecodeMessage extends Thread{
    
        String data;
        DecodeMessage(String cadena){
            data = cadena;
        }
        @Override
        public void run(){
        {
            String messageDiv[] = data.split("->");
            if (messageDiv[0].equals("WIC")) {
                if (!(messageDiv[1].equals(NodoB.myPort))) {
                    if (NodoB.isCoord == true) {
                        UniSender.sendMessage("IAC->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(messageDiv[1]));
                    }
                }
            }

            if (messageDiv[0].equals("ELECTION")) {
                if (!(messageDiv[1].equals(NodoB.myPort))) {
                    if (NodoB.myId > Integer.parseInt(messageDiv[2])) {
                        UniSender.sendMessage("OK->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(messageDiv[1]));
                    }
                }
            }

            if (messageDiv[0].equals("ESTAMOS_CONSENSUANDO")) {
               NodoB.estamosConsensuando = true;
               UniSender.sendMessage("ENVIOX->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(NodoB.portCoord));           
            }
            if (messageDiv[0].equals("FIN_CONSENSO")) {
               NodoB.estamosConsensuando = false;

            }

            if (messageDiv[0].equals("VALORX")) { //Se quedaran con el menor valor de x
               if (NodoB.x > Integer.parseInt(messageDiv[2]))
               {
                   NodoB.x = Integer.parseInt(messageDiv[2]);
               }
               if (NodoB.isCoord ){
                   NodoB.contX--;
                   //NodoB.lock.unlock();
                   NodoB.semaphore.release();
                   if (NodoB.contX == 0){
                       NodoB.estamosConsensuando = false;
                       try {
                           MultiSender.sendMessage("FIN_CONSENSO->");
                       } catch (IOException ex) {
                           Logger.getLogger(MultiRecieve.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
               }

            }
        }

        
        }
    
    }
}