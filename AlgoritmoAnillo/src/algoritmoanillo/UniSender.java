package algoritmoanillo;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UniSender {

    public UniSender() {
    }

    public static void sendMessage(String message, int portToSend, String ip) {   
        //Mensaje tcp a el proceso coordinador
        Socket clientSocket;
        try {
            clientSocket = new Socket(ip, portToSend); //////////////////////////////////////DEBERIA MODIFICAR ESTO SI LOS PROCESOS ESTAN EN DISTINTAS COMPUS
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeUTF(message);
            outToServer.flush();
            System.out.println("Enviado un " + message);
        } catch(ConnectException ex){ //Cuando se cae el nodo siguiente sale esta excepcion. Me doy cuenta al comunicarme con este para pasarle el token. 
                                        // Entonces reacomodo primero mi nuevo nodo siguiente y luego procedo a enviar el token al nuevo siguiente
            Nodo.encuentraIdNodo(Nodo.IDnext);
            UniSender.sendMessage("TOKEN->" + Nodo.myPort + "->" + Nodo.myIP , Integer.parseInt(Nodo.portNext), Nodo.ipNext);
        } catch (IOException ex) {
          
            System.out.println("Imposible de enviar msj uni port: " + portToSend + " ip: " + ip);
          
        }
    }
    
}
