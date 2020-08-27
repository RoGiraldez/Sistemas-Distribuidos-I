
package consenso;

///En esta clase use un semaforo. Esto es para que al adquirirlo este hilo lo pueda liberar el hilo de recepcion de msj multiRecieve!

import static consenso.NodoB.isCoord;
import static consenso.NodoB.isCoordAlive;
import static consenso.NodoB.myId;
import static consenso.NodoB.myPort;
import static consenso.NodoB.portCoord;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consenso {
 
public static void consenso(){
    new Thread() {

                @Override
                public void run() {
                    while (true) {   
                        if (NodoB.x == 0 ){
                            NodoB.x = EscribeArchivo.sorteoLineas();
                            UniSender.sendMessage("LLAMAR_CONSENSO->" + myPort + "->" + myId, Integer.parseInt(portCoord));
                        }
                        while(NodoB.estamosConsensuando){
                          try {
                            Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Consenso.class.getName()).log(Level.SEVERE, null, ex);
                            }
}
                    // bucle infinito
                        Random rand;
                        rand = new Random();
                        int sleep = rand.nextInt(10000) + 5000;
                        try {
                            Thread.sleep(sleep);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(NodoB.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (isCoordAlive){
                            UniSender.sendMessage("LLAMAR_CONSENSO->" + myPort + "->" + myId, Integer.parseInt(portCoord));
                            
                        
                        }
                    }

                }
            }.start();
        
}

public static void organizaConsenso(String portProceso, String id){

    new Thread() {

                @Override
                public void run() {
                    try {
                        System.out.println("Nuevo pedido de envio x " + id + "\n");
                        NodoB.contX ++;
                        Thread.sleep(1000);
                    
                        NodoB.semaphore.acquire();
                        UniSender.sendMessage("PROCESOX->", Integer.parseInt(portProceso));
                        //Ahora se liberara el lock cuando el servidor reciba el mensaje con el valor de x
                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consenso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                
                
                }
            }.start();


}
    
}
