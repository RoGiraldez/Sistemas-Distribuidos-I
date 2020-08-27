/*
Data consistency. All update requests will be forwarded to the leader, 
and then the leader will broadcast data to all active servers and then returns the update status
 */
package consenso;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NodoB extends javax.swing.JFrame {

   

    //private int myPort        = 0;
    private String serverHost = null;
    boolean isServer          = false;
    int myID;
    public static String filename           = "C:\\Users\\Rocío\\Documents\\Sistemas Distribuidos\\U5\\ExclusionMutuaBully\\salida.txt";
    ArrayList listaNodos;
   // public static Lock lock = new ReentrantLock();
    //public static MyLock lock;
    
    public static Semaphore semaphore = new Semaphore(1);
    
     MulticastSocket s ;
     InetAddress group ;
     
     
    //Flag para identificar si el proceso es coord
    public static boolean isCoord;
    //Almacena el puerto en la red del coordinador.
    public static String portCoord = null;
    //Almacena el identificador del proceso utilizado para la elección en la red
    public static int myId;
    //Almacena el puerto de instancia
    public static String myPort = null;
    //Flag para identificar si esta instancia ya está haciendo una elección
    public static boolean doingElection = false;   // No la uso?
    //ServerSocket
    public static ServerSocket server;
    //Almacena una cadena que representa el estado del programa
    public static String instanceStatus;
    public static boolean isCoordAlive = false;
    public static boolean okMessage = false;
    
    public static boolean recursoAsignado = false;
    public static String procesoConPermiso;
    //Flag para determinar si me han concedido el permiso o no
    public static String tengoPermiso = "NO";
    
    public static boolean estamosConsensuando = false;
    public static boolean enviandoX = false;
    public static int x;
    public static int contX = 0;
    

    
    
        //****************************************************************//
    
    public NodoB() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_area = new javax.swing.JTextArea();
        b_iniciar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txt_ip = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_puerto = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txt_ipMulticast = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_puertoMulticast = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        txt_area.setColumns(20);
        txt_area.setRows(5);
        jScrollPane1.setViewportView(txt_area);

        b_iniciar.setText("INICIAR");
        b_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_iniciarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración Coordinador"));

        txt_ip.setText("127.0.0.1");
        txt_ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ipActionPerformed(evt);
            }
        });

        jLabel1.setText("IP");

        jLabel2.setText("Puerto");

        txt_puerto.setText("1234");
        txt_puerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_puertoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración Grupo"));

        txt_ipMulticast.setText("228.5.6.7");

        jLabel3.setText("IP");

        jLabel4.setText("Puerto");

        txt_puertoMulticast.setText("6789");
        txt_puertoMulticast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_puertoMulticastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_puertoMulticast, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ipMulticast, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ipMulticast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_puertoMulticast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(b_iniciar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_iniciar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_iniciarActionPerformed
        try {
            createId();
            creatServerSocket();
            
            Thread threadMultiRecieve = new Thread(new MultiRecieve());
            threadMultiRecieve.start();
            Thread threadUniRecieve = new Thread(new UniRecieve(server));
            threadUniRecieve.start();

            
            whoIsTheCoordinator();     
            areYouAlive();
            Consenso.consenso();
            Thread threadDoSomething = new Thread(new Procesamiento());
            threadDoSomething.start();
        } catch (IOException ex) {
            Logger.getLogger(NodoB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(NodoB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_iniciarActionPerformed

    
    
    private void txt_puertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_puertoActionPerformed
       
    }//GEN-LAST:event_txt_puertoActionPerformed

    private void txt_puertoMulticastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_puertoMulticastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_puertoMulticastActionPerformed

    private void txt_ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ipActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NodoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NodoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NodoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NodoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NodoB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_iniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea txt_area;
    private javax.swing.JTextField txt_ip;
    private javax.swing.JTextField txt_ipMulticast;
    private javax.swing.JTextField txt_puerto;
    private javax.swing.JTextField txt_puertoMulticast;
    // End of variables declaration//GEN-END:variables

//----------------------------------------------------------------------------//

    //Genera un id para la instancia -------------->INTERFAZ PODRIA SER OTRA OPCION
    public void createId() {
        Random rand;
        rand = new Random();
        myId = rand.nextInt(1000) + 1;
        txt_area.setCaretPosition(txt_area.getDocument().getLength());
        txt_area.append("Id generado: " + myId + "\n");
    }
    
    
    public void creatServerSocket()  {
        try {
            server = new ServerSocket(0);
            myPort = Integer.toString(server.getLocalPort());
            txt_area.append("Puerto Servidor: " + myPort + "\n");
        } catch (IOException ex) {
            Logger.getLogger(NodoB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void whoIsTheCoordinator() throws IOException, InterruptedException {

        if (okMessage == true) {
            portCoord = null;
            String message = "WIC->" + myPort + "->" + myId;
            MultiSender ms = new MultiSender();
            System.out.println("¿Quién esta a cargo?");
            boolean foundCoord = false;
            while (portCoord == null) {
                ms.sendMessage(message);
                long startTime = System.currentTimeMillis(); //fetch starting time
                txt_area.append("Se esperará hasta 10s para que aparezca el coordinador \n");
                while (false || (System.currentTimeMillis() - startTime) < 10000) {
                    Thread.sleep(10); //Não apagar
                    if (portCoord != null && portCoord != myPort) {
                        //System.out.println(instanceStatus);
                        foundCoord = true;
                        txt_area.append( instanceStatus + "\n");
                        break;
                    }
                }
            }

        }

        if (okMessage == false) {
            portCoord = null;
            String message = "WIC->" + myPort + "->" + myId; //WHERE IS COORDINATOR
            MultiSender ms = new MultiSender();
            System.out.println("¿Quién esta a cargo?");
            boolean foundCoord = false;
            ms.sendMessage(message);

            long startTime = System.currentTimeMillis(); //fetch starting time
            while (false || (System.currentTimeMillis() - startTime) < 10000) {
                Thread.sleep(10); //Não apagar
                if (portCoord != null && portCoord != myPort) {
                    System.out.println(instanceStatus);
                    foundCoord = true;
                    break;
                }
            }
            if (foundCoord == false) {
                System.out.println("Me convertí en coordinador!!!");
                isCoord = true;
                portCoord = myPort;
            }
        }

    }
    
        public void areYouAlive() {
        new Thread() {

            @Override
            public void run() {
                while (true) {                              // bucle infinito
                    Random rand;
                    rand = new Random();
                    int sleep = rand.nextInt(10000) + 5000;
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(NodoB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (isCoord == false) {
                        System.out.println("Estas vivo proceso coordinador?");
                        UniSender.sendMessage("AYA->" + myPort + "->" + myId, Integer.parseInt(portCoord));

                        isCoordAlive = false;
                        long startTime = System.currentTimeMillis(); //fetch starting time
                        while (false || (System.currentTimeMillis() - startTime) < 10000) {  //se dormirá 10 s, al despertar primero
                            try {                                                           // chequea la bandera de si esta vivo
                                Thread.sleep(10);                                          // si es true significa que le ha contestado el coord y no hay que llamar a eleccion                                  
                            } catch (InterruptedException ex) {
                                Logger.getLogger(NodoB.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (isCoordAlive == true) {
                                System.out.println(instanceStatus);

                                break;
                            }
                        }
                        if (isCoordAlive == false) {
                            try {
                                election();   // si no le contesta el coordinador llama a leccion
                            } catch (IOException ex) {
                                System.out.println(ex);
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                        }
                    } else {
                        System.out.println("Sigo siendo coordinador");
                    }

                }

            }
        }.start();
    }
        
    
    public void election() throws IOException, InterruptedException {
        System.out.println("----------INICIANDO ELECCIÓN---------");
        long startTime = System.currentTimeMillis(); //fetch starting time
        okMessage = false;
        MultiSender.sendMessage("ELECTION->" + myPort + "->" + myId); // Envia el mensaje de ELECCION a todos los del grupo (sera levantado por los portId mayores)
        while (false || (System.currentTimeMillis() - startTime) < 10000) {
            try {
                Thread.sleep(10); 
            } catch (InterruptedException ex) {
                Logger.getLogger(NodoB.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (okMessage == true) {
                System.out.println(instanceStatus);

                break;
            }
        }
        if (okMessage == true) {
            System.out.println("sleep 15s");  //No le toco ser coordinador
            Thread.sleep(15000);  // esperar a que se termine la eleccion
            whoIsTheCoordinator();
        }
        if (okMessage == false) {
            whoIsTheCoordinator();
        }

    }
    
    
   public class Procesamiento extends Thread{
      
       
       @Override
    public void run(){
        while(true){
            try {
                changeB();
                EscribeArchivo.createFile(filename);
                EscribeArchivo.sorteoTurno();
                if (!estamosConsensuando){
                UniSender.sendMessage("PERMISO->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(NodoB.portCoord)); //pido permiso
                while(NodoB.tengoPermiso.equals("NO")){Thread.sleep(5); }
                System.out.println(tengoPermiso);
                if (NodoB.tengoPermiso.equals("YES")){
                    changeB();                   
                    String cadena;
                    for(int i= 1; i <= x; i++ ){
                        cadena = EscribeArchivo.handlerEscritura(i,x);
                        txt_area.append(cadena + " ");
                        EscribeArchivo.writeFile(cadena);
                    }
                     txt_area.append("\n");
                }
                UniSender.sendMessage("LIBERO->" + NodoB.myPort + "->" + NodoB.myId, Integer.parseInt(NodoB.portCoord)); //pido permiso
                NodoB.tengoPermiso = "NO";
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(EscribeArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }
          
    }
   
   }
   
   void changeB(){
      if (tengoPermiso.equals("YES")){
          b_iniciar.setEnabled(true);
          b_iniciar.setBackground(Color.GREEN);
          b_iniciar.setText("ACTIVO");
      } else{
          b_iniciar.setEnabled(false);
          b_iniciar.setBackground(Color.BLACK);
          b_iniciar.setText("INACTIVO");
      
      }
   }
   

}
