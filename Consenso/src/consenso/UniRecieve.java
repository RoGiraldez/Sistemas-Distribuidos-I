
package consenso;


//import static bullyalgorithm.MultiRecieve.timeOut;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
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
        if (messageDiv[0].equals("IAC")) {            
            if (!(messageDiv[1].equals(NodoB.myPort))) {
                NodoB.portCoord = messageDiv[1];
                NodoB.instanceStatus = "El proceso " + NodoB.myId
                        + " Declaro la computadora " + messageDiv[2] + " puerto " + messageDiv[1] + " como legitimo coordinador";
            }
        }

        if (messageDiv[0].equals("AYA")) {    //ARE YOU ALIVE
            if (!(messageDiv[1].equals(NodoB.myPort))) {
                UniSender.sendMessage("IAA->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(messageDiv[1]));
            }
        }

        if (messageDiv[0].equals("IAA")) { //I AM ALIVE
            if (!(messageDiv[1].equals(NodoB.myPort))) {
                NodoB.isCoordAlive = true;
                NodoB.instanceStatus = "Proceso coordinador está vivo!";
            }
        }

        if (messageDiv[0].equals("OK")) {
            if (!(messageDiv[1].equals(NodoB.myPort))) {
                NodoB.okMessage = true;
                NodoB.instanceStatus = "No soy el coordinador";               
            }
        }
        
        if (messageDiv[0].equals("LLAMAR_CONSENSO")) { //no puede estar con el recurso asignado
            if (NodoB.isCoord && !NodoB.recursoAsignado && !NodoB.estamosConsensuando) {
                try {
                    MultiSender.sendMessage("ESTAMOS_CONSENSUANDO->" + NodoB.myId);
                } catch (IOException ex) {
                    Logger.getLogger(UniRecieve.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (messageDiv[0].equals("PROCESOX")) { //no puede estar con el recurso asignado
            NodoB.x = EscribeArchivo.sorteoLineas();
            try {
                MultiSender.sendMessage("VALORX->" + NodoB.x + "->" + NodoB.myPort );
            } catch (IOException ex) {
                Logger.getLogger(UniRecieve.class.getName()).log(Level.SEVERE, null, ex);
            }

            }
        
        
                
        if (messageDiv[0].equals("ENVIOX")) { //no puede estar con el recurso asignado
            if (NodoB.isCoord) {
                Consenso.organizaConsenso(messageDiv[1], messageDiv[2]);
            }
        }
        
        
        if (messageDiv[0].equals("YES")) {    //puede escribir en el archivo
            //escribir y cuando termina enviar mensaje LIBERO
            System.out.println("ACA");
            NodoB.tengoPermiso = "YES";
        }
        
        if (messageDiv[0].equals("NO")) {    // no puede escribir en el archivo
            NodoB.tengoPermiso = "WAIT"; //volver a sortear
        }
        
        if (messageDiv[0].equals("PERMISO")) {    // CUANDO SOY COORDINADOR RECIBO ESTOS MSJ
/// 0 permiso 1 puerto proceso con el permiso
           if(NodoB.isCoord && !NodoB.estamosConsensuando){
                if (NodoB.recursoAsignado == false){
                    NodoB.recursoAsignado = true;
                    NodoB.procesoConPermiso = messageDiv[1];                  
                    UniSender.sendMessage("YES->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(messageDiv[1]));
                } else {
                    UniSender.sendMessage("NO->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(messageDiv[1]));
                }
            }
        }
        
        if (messageDiv[0].equals("LIBERO")) {    // Libera el recurso el proceso que lo estaba utilizando

           if(NodoB.isCoord){
                if (NodoB.recursoAsignado == true){
                    NodoB.recursoAsignado = false;
                    NodoB.procesoConPermiso = "Nadie";                  
                }
        }

    }

    }
}