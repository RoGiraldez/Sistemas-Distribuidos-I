
package relojntp;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RelojNtp extends Thread{

     @Override
     public void run (){
         while(true){
            try {
                ClienteNTP cliente = new ClienteNTP(reloj.serverNTP);
                long offset = cliente.obtenerOffset(reloj.horaActual, reloj.hora);
                 // offset < 0 --> reloj cliente atrasado
                // offset > 0 --> reloj cliente adelantado
                if(Math.abs(offset) > 8000){ //Atrasado o adelantado mas de 8 s
                    if (Math.signum(offset) > 0 ){
                         reloj.frecuencia = 1000*2; 
                    } else{
                        reloj.frecuencia = 1000/2; //Pongo como tope adelantar 2 s cada 1 s reales. Es mi extremo.                
                    } 
                }else{
                     int value = (int)(Math.min(Math.abs(offset / 5), 100) * Math.signum(offset)); //La máxima correccion que se podrá
                                                                                        // hacer es de 100 ms 
                System.out.println("Offset: " + offset + "   Value: " + value);
           
                // Lo máximo que puedo atrazar por segundo si esta adelantado será 1.1 s cada 1s. Osea si está 1s adelantado tardará 10 s en corregirse
               // Lo máximo que puedo adelantar por segundo si está atrazado será de 500 ms osea 0.5 s y tardo 2 s en corregir 1 s atrazado 
                  if ( value >= 0 ){ reloj.frecuencia = 1000 + value; }
                  else if (value < 0 ) { reloj.frecuencia = 500; }                   
                }
               
              Thread.sleep(reloj.tActualizacion*1000);
            } catch (IOException ex) {
                Logger.getLogger(FrecuenciaReloj.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {  
                Logger.getLogger(RelojNtp.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
     }
    
}
