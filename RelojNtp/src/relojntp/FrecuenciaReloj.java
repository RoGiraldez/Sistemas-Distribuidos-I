
package relojntp;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;



    
    //Actualiza el reloj se
class FrecuenciaReloj extends Thread{
        
        @Override
        public void run(){
            
            if (reloj.frecuencia > 0){
                while(true){
                    String[] parts = reloj.hora.split( "\\:" );
//                    int hora = Integer.parseInt(parts[0]);
//                    int min  = Integer.parseInt(parts[1]);
//                    int sec  = Integer.parseInt(parts[2]);
//                    c        = new GregorianCalendar();
//                    c.set(Calendar.HOUR_OF_DAY,hora);
//                    c.set(Calendar.MINUTE,min);
//                    c.set(Calendar.SECOND,sec);
                    modificaHora(reloj.horaActual);
                    try {
                        Thread.sleep(reloj.frecuencia); //Frecuencia en seg
                    } catch (InterruptedException ex) {
                       
                    }
                }
            }
        }
        
        void modificaHora(Calendar c) {
            c.add(Calendar.SECOND, 1);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);

            String time = hour + ":" + min + ":" + sec;
            reloj.hora = time;           
        }
        
        
            public static Date horaToTimestamp (Calendar c, String cadena){

            String[] parts = cadena.split( "\\:" );
            int hora = Integer.parseInt(parts[0]);
            int min  = Integer.parseInt(parts[1]);
            int sec  = Integer.parseInt(parts[2]);
            c        = new GregorianCalendar();
            c.set(Calendar.HOUR_OF_DAY,hora);
            c.set(Calendar.MINUTE,min);
            c.set(Calendar.SECOND,sec);
            
            Date tiempo = c.getTime();
            return tiempo;
          
    }
            
 
    
  }


