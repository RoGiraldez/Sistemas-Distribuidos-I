
package algoritmoanillo;


//import static bullyalgorithm.MultiRecieve.timeOut;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UniRecieve implements Runnable {

    public static ServerSocket server;
    public static DataInputStream inputStream;
    public static Socket socketClient = null;

    public UniRecieve(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            try {
                recieveMessage();
            } catch (IOException ex) {
                Logger.getLogger(UniRecieve.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(UniRecieve.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void recieveMessage() throws IOException, InterruptedException {
        String message = null;
        socketClient = server.accept();
        inputStream = new DataInputStream(socketClient.getInputStream());
        message = inputStream.readUTF();

        decodeMessage(message);
    }

    public static void decodeMessage(String data)  {
        System.out.println("Mensaje UNI RECIBIDO: " + data);
        //Thread.sleep(100);
        String messageDiv[] = data.split("->");

        //InfoNodos(String id, String puerto, String ip)
        //message = "NODO->" + nodo.id + "->" + nodo.ip + "->" + nodo.puerto;
        if (messageDiv[0].equals("NODO")){        
            Nodo.lock.lock();
            try {
                Nodo.listaNodos.add(new InfoNodos(messageDiv[1], messageDiv[3], messageDiv[2]));
            } finally {
                Nodo.lock.unlock();
            }
        }
        
        if (messageDiv[0].equals("NEXT")){              
            Nodo.IDnext   = messageDiv[1];
            Nodo.portNext = messageDiv[2];
            Nodo.ipNext   = messageDiv[3];   
            Nodo.actualizaInterfaz();
        }

        if (messageDiv[0].equals("IHT")) {
              Nodo.isTokenAlive = true;
              Nodo.instanceStatus = "El token está en el proceso " + messageDiv[3];
        }
        
        if (messageDiv[0].equals("TOKEN")) {
              Nodo.isTokenAlive = true;
              Nodo.tengoToken   = true;
              Nodo.instanceStatus = "Me ha llegado el token";
              synchronized(Nodo.token) {
                 Nodo.txt_area.append("NOTIFY\n");
                 Nodo.token.notify();
               }
              UniSender.sendMessage("TOKENRECIBIDO", Integer.parseInt(messageDiv[1]), messageDiv[2]);
              
        }
        
        if (messageDiv[0].equals("TOKENRECIBIDO")) {
              Nodo.isTokenAlive = true;
              Nodo.tengoToken   = false;
              Nodo.instanceStatus = "Token pasado al nodo siguiente";
                           
        }
        //UniSender.sendMessage("ALIVE->" + myPort + myIP, Integer.parseInt(nodo.puerto), nodo.ip);
            if (messageDiv[0].equals("ALIVE")) {
              UniSender.sendMessage("ACA->", Integer.parseInt(messageDiv[1]), messageDiv[2]);                         
        }
          
                    //UniSender.sendMessage("ALIVE->" + myPort + myIP, Integer.parseInt(nodo.puerto), nodo.ip);
            if (messageDiv[0].equals("ALIVE")) {
             Nodo.instanceStatus = "ALIVE";
        }
 

    }
}