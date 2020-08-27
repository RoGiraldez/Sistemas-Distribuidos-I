
package consenso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EscribeArchivo extends Thread {

    
    @Override
    public void run(){
        while(true){
            try {
                sorteoTurno();
                UniSender.sendMessage("PERMISO->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(NodoB.portCoord)); //pido permiso
                while(NodoB.tengoPermiso.equals("NO")){Thread.sleep(5);}
                if (NodoB.tengoPermiso.equals("YES")){
                    int x = sorteoLineas();
                    for(int i= 1; i <= x; i++ ){
                    
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(EscribeArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }
          
    }
    
      
    public static void sorteoTurno() throws InterruptedException {
        
        Random rand = new Random();
        int n = rand.nextInt(25);
        n += 5;       
        System.out.println("Ahora esperare " + n + "s para tratar de adquirir el lock");
        Thread.sleep(n*1000);
    }
    
    public static int  sorteoLineas() {
        
        Random rand = new Random();
        int n = (new Random()).nextInt(5) + 5;
        return n;
    }
    
     /// A ejecutar por el cliente
     void handlerEscritura (int x) throws InterruptedException{
         
        String linea;
        Calendar now = GregorianCalendar.getInstance();        
         for(int i = 1; i <= x; i++){
            now = GregorianCalendar.getInstance();
            linea = "";
            linea = NodoB.myId + "|" + now.get(Calendar.HOUR_OF_DAY) + ":" 
                    + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "|"
                    + i + "/" + x + "\n";
            System.out.println("Iteracion: " + i);

            // o unisend mensaje para que lo escriba en el archivo o escribirlo directamente
            
            //Una vez que escribe una linea, el hilo dormirá 1 s
            Thread.sleep(1000);

         }
     
     }
     
    public static String handlerEscritura ( int i, int x) {
         
        try {
            String linea;
            Calendar now = GregorianCalendar.getInstance();
            now = GregorianCalendar.getInstance();
            linea = ""; 
            linea = NodoB.myId + "|" + now.get(Calendar.HOUR_OF_DAY) + ":"
                    + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + "|"
                    + i + "/" + x ;
            
            // o unisend mensaje para que lo escriba en el archivo o escribirlo directamente
            
            //Una vez que escribe una linea, el hilo dormirá 1 s
            Thread.sleep(1000);
            
            return linea;
        } catch (InterruptedException ex) {
            Logger.getLogger(EscribeArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
         
     
     }
    
    
     //SERVIDOR
    public static void createFile(String name ) {
        
          try {
            File myObj = new File(name);
            if (!myObj.exists()) {
                myObj.delete(); //you might want to check if delete was successfull
           
            myObj.createNewFile();
            if (myObj.createNewFile()) {
              System.out.println("Archivo creado: " + myObj.getName());
            }
            }
          } catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            e.printStackTrace();
          }
        
      }
   
    //SERVIDOR
    public static void writeFile (String linea){
        
        try {
          FileWriter myWriter = new FileWriter(NodoB.filename, true);
          myWriter.write(linea + "\n");
          myWriter.close();
          System.out.println("Se ha escrito una linea en el archivo y luego se ha cerrado");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    
    
}
