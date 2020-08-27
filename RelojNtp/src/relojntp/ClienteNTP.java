package relojntp;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;


public class ClienteNTP {
    
    private static String SERVER_NAME;

    private volatile TimeInfo timeInfo;
    private volatile Long offset;
    
    NTPUDPClient client;
    
    InetAddress inetAddress;

  public ClienteNTP(String server) throws IOException {

    SERVER_NAME = server;
    client = new NTPUDPClient();
    // Hace timeout si una respuesta tarda mas de 10s
    client.setDefaultTimeout(10_000);
    inetAddress = InetAddress.getByName(SERVER_NAME); //devuelve la IP de SERVER_NAME

  }

  //no sense
  void tiempo (){
     // This system NTP time
     
    TimeStamp systemNtpTime = TimeStamp.getCurrentTime();
    System.out.println("System time:\t" + systemNtpTime + "  " + systemNtpTime.toDateString());

    // Calculate the remote server NTP time
    long currentTime = System.currentTimeMillis();
    TimeStamp atomicNtpTime = TimeStamp.getNtpTime(currentTime + offset);

    System.out.println("Atomic time:\t" + atomicNtpTime + "  " + atomicNtpTime.toDateString());
  
  }

  

  public boolean isComputed()
  {
      return timeInfo != null && offset != null;
  }
  
  public long obtenerOffset(Calendar c, String cadena){
    try {
                client.open();
                InetAddress hostAddr = InetAddress.getByName(SERVER_NAME);
                System.out.println("> " + hostAddr.getHostName() + "/" + hostAddr.getHostAddress());
                TimeInfo info = client.getTime(hostAddr);
                
                //originateTimeStamp
                Date date = FrecuenciaReloj.horaToTimestamp(c, cadena);
                info.getMessage().setTransmitTime(new TimeStamp(date));
               
                System.out.println(date);

                info.computeDetails();
                
                return( info.getOffset() );
              //  System.out.println(offset);

             //   int value = (int)(Math.min(Math.abs(offset / 5), 100) * Math.signum(offset)); //divide por 5 el offset
               // this.jSpinnerFrecuencia.setValue(1000 + value);  //cambiar generico!!

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                client.close();
            }
   return offset;
  }
  
  
  void ObtenerInfo ( NTPUDPClient client ){
    try {
        client.open();       
        
        //Retrieves the time information from the specified server on the default NTP port and returns it.
        timeInfo = client.getTime(inetAddress);
        
        
        NtpV3Packet message = timeInfo.getMessage(); //Devuelve el mensaje NTP
        // Hora del servidor
        TimeStamp referenceTimeStamp = message.getReferenceTimeStamp();
        System.out.println("Reference Timestamp: " + referenceTimeStamp.toDateString());
        
        // Hora del cliente
        TimeStamp originateTimeStamp = message.getOriginateTimeStamp();
        System.out.println("Originate Timestamp:" + originateTimeStamp.toDateString());
        
        // Hora en que el servidor recibió la solicitud
        TimeStamp receiveTimeStamp = message.getReceiveTimeStamp();
        System.out.println("Receive Timestamp: " + receiveTimeStamp.toDateString());
        
        // Hora en que el servidor envió la respuesta
        TimeStamp transmitTimeStamp = message.getTransmitTimeStamp();
        System.out.println("Transmit Timestamp: " + transmitTimeStamp.toDateString());
        
        // Hora en que se recibió la respuesta
        TimeStamp returnTime = TimeStamp.getNtpTime(timeInfo.getReturnTime());
        System.out.println("Return Timestamp:" + returnTime.toDateString());
        
        timeInfo.computeDetails();
        offset = timeInfo.getOffset();
        System.out.println("Delay (ms)=" + timeInfo.getDelay());
        System.out.println("Offset (ms)=" + offset);
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        client.close();
    }
}
  
  long obtenerOffset(){
      if (!isComputed()){
        timeInfo.computeDetails();
        offset = timeInfo.getOffset();
          
      }
      return offset;
      
  }
}




