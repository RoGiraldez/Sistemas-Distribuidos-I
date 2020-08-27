package consenso;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class UniSender {

    public UniSender() {
    }

    public static void sendMessage(String message, int portToSend) {   //Mensaje tcp a el proceso coordinador
        Socket clientSocket;
        try {
            clientSocket = new Socket("localhost", portToSend); //////////////////////////////////////DEBERIA MODIFICAR ESTO SI LOS PROCESOS ESTAN EN DISTINTAS COMPUS
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeUTF(message);
            System.out.println("Enviado un " + message);
        } catch (IOException ex) {
            System.out.println("incapaz de enviar unicast: " + ex);
        }
    }

}
