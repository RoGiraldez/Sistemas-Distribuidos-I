
//Utilizare un grupo multicast de fondo

package algoritmoanillo;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Nodo extends javax.swing.JFrame {

    public MultiSender ms;
    public static ReentrantLock lock = new ReentrantLock();
    public static String instanceStatus;
    
    //Almaceno mis datos
    public static String myPort = null;
    public static String myIP = null;
    public static String myID = null;
    //Almaceno el del siguiente para no buscarlo en la lista cada vez que le quiero enviar algo. Podria almacenar el indice. Podria almacenar el elemento de la clase InfoNodos
    public static String portNext = null;
    public static String IDnext = null;
    public static String ipNext = null;
    
    
    public static Boolean isTokenAlive = false;
    public static Boolean tengoToken = false;
    public final static Token token = new Token();
    //ServerSocket
    public static ServerSocket server;
    
    public static ArrayList<InfoNodos> listaNodos;
    
    public Nodo() {
        initComponents();
        listaNodos = new ArrayList();
        //createId();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tf_myPort = new javax.swing.JTextField();
        tf_myIP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tf_ipGrupo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_puertoGrupo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        b_iniciar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_area = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        l_ipNext = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        l_portNext = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración "));

        tf_myPort.setText("1234");

        tf_myIP.setText("127.0.0.1");

        jLabel1.setText("IP:");

        jLabel2.setText("Puerto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_myPort, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_myIP, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_myIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_myPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo"));

        jLabel4.setText("IP:");

        tf_ipGrupo.setText("228.5.6.7");
        tf_ipGrupo.setToolTipText("");
        tf_ipGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ipGrupoActionPerformed(evt);
            }
        });

        jLabel3.setText("Puerto:");

        tf_puertoGrupo.setText("5865");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_ipGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_puertoGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_ipGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_puertoGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setText("Generar Token");

        b_iniciar.setBackground(new java.awt.Color(255, 255, 204));
        b_iniciar.setText("Iniciar");
        b_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_iniciarActionPerformed(evt);
            }
        });

        jButton3.setText("Crash");

        txt_area.setColumns(20);
        txt_area.setRows(5);
        jScrollPane1.setViewportView(txt_area);

        jLabel5.setText("Nodo siguiente:");

        jLabel6.setText("Puerto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_ipNext, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(l_portNext, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(b_iniciar))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(l_portNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(b_iniciar)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addComponent(l_ipNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(22, 22, 22))
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

    private void tf_ipGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ipGrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_ipGrupoActionPerformed

    private void b_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_iniciarActionPerformed
        try {
            createId();
            this.setTitle("ID: " + myID);
            txt_area.setText("");
            b_iniciar.setEnabled(false);
            myIP = tf_myIP.getText();
            myPort = tf_myPort.getText();
            
            
            ms = new MultiSender(tf_ipGrupo.getText(), Integer.parseInt(tf_puertoGrupo.getText()));
            
            creatServerSocket();
            
            Thread threadMultiRecieve = new Thread(new MultiRecieve(tf_ipGrupo.getText(), Integer.parseInt(tf_puertoGrupo.getText())));
            threadMultiRecieve.start();
            Thread threadUniRecieve = new Thread(new UniRecieve(server));
            threadUniRecieve.start();
            
            Thread.sleep(1000);
            whereIsMyPlace();
            
            Thread manejoToken = new Thread (new RecibeToken());
            manejoToken.start();
                    
            isTokenAlive();
            
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
           txt_area.append("Error al iniciar el socket con los datos ingresados, revíselos");
            b_iniciar.setEnabled(true);
        } catch (IOException ex) {
            txt_area.append("Error al iniciar el socket con los datos ingresados, revíselos");
            b_iniciar.setEnabled(true);
        } catch (InterruptedException ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b_iniciarActionPerformed


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
            java.util.logging.Logger.getLogger(Nodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nodo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nodo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_iniciar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel l_ipNext;
    private static javax.swing.JLabel l_portNext;
    public static javax.swing.JTextField tf_ipGrupo;
    private javax.swing.JTextField tf_myIP;
    private javax.swing.JTextField tf_myPort;
    private javax.swing.JTextField tf_puertoGrupo;
    public static javax.swing.JTextArea txt_area;
    // End of variables declaration//GEN-END:variables

    public void createId() {
        Random rand;
        rand = new Random();
        myID = Integer.toString(rand.nextInt(1000) + 1);
        txt_area.setCaretPosition(txt_area.getDocument().getLength());
        txt_area.append("Id generado: " + myID + "\n");
    }
    
    
    public void creatServerSocket() throws IOException  {
     
            server = new ServerSocket(Integer.parseInt(myPort));
            //myPort = Integer.toString(server.getLocalPort());
            txt_area.append("Puerto Servidor: " + myPort + "\n");

    }
    
    
    
      public void whereIsMyPlace() throws IOException, InterruptedException {

           // createId();
       // if (okMessage == true) {
            portNext = null;
            String message = "WIMP->" + myID + "->" + myPort + "->" + myIP ;
            System.out.println(myPort);
            ms.sendMessage(message);
            System.out.println("¿Donde voy?"); 
            boolean foundPlace = false;
        
                //ms.sendMessage(message);
                long startTime = System.currentTimeMillis(); //fetch starting time
                txt_area.append("Se esperará hasta 10s para que algun nodo se comunique \n");
                scrollDown();
                while (false || (System.currentTimeMillis() - startTime) < 10000) {
                    Thread.sleep(10); //Cada 10 ms vuelve a intentar mandar la señal para ver si hay algun proceso activo
                    //ms.sendMessage(message);
                    if ( portNext != null ) { /// Desde multirecieve o unirecieve se modificara si alguien me responde
                        //System.out.println(instanceStatus);
                        foundPlace = true;
                        //txt_area.append( instanceStatus + "\n");
                        break;
                    }
                }                
            
            // si despues de esperar los 10 s y continuo con el puerto Next en NULL significa que soy el primerp en llegar y generare el token
            if ( (portNext == null) && (isTokenAlive == false )){
                token.setToken(Token.generateToken());
                isTokenAlive = true;
                tengoToken = true;
                lock.lock();
                try {
                    listaNodos.add(new InfoNodos(myID, myPort, myIP));
                } finally {
                    lock.unlock();
                }
                tengoToken(true);
            }

        //}

//        if (okMessage == false) {
//            portCoord = null;
//            String message = "WIC->" + myPort + "->" + myId; //WHERE IS COORDINATOR
//            MultiSender ms = new MultiSender();
//            System.out.println("¿Quién esta a cargo?");
//            boolean foundCoord = false;
//            ms.sendMessage(message);
//
//            long startTime = System.currentTimeMillis(); //fetch starting time
//            while (false || (System.currentTimeMillis() - startTime) < 10000) {
//                Thread.sleep(10); //Não apagar
//                if (portCoord != null && portCoord != myPort) {
//                    System.out.println(instanceStatus);
//                    foundCoord = true;
//                    break;
//                }
//            }
//            if (foundCoord == false) {
//                System.out.println("Me convertí en coordinador!!!");
//                isCoord = true;
//                portCoord = myPort;
//            }
//        }

    }
      
      public int encuentroNodoInactivo(){
         int elemento= -1;
         InfoNodos nodo;
         
         for(int i = 0; i <listaNodos.size(); i++){
             instanceStatus = "FALSE";
             nodo = listaNodos.get(i);
             UniSender.sendMessage("ALIVE->" + myPort + myIP, Integer.parseInt(nodo.puerto), nodo.ip);
             //Espera hasta 10s a q responda
              long startTime = System.currentTimeMillis(); //fetch starting time
              while (false || (System.currentTimeMillis() - startTime) < 10000) {  //se dormirá 10 s, al despertar primero
               try {                                                           // chequea la bandera de si esta vivo
                         Thread.sleep(10);                                          // si es true significa que le ha contestado el coord y no hay que llamar a eleccion                                  
                    } catch (InterruptedException ex) {
                          Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                     }
                if (instanceStatus.equals("ALIVE")) {
                                //System.out.println(instanceStatus);                               
                     break;
                 }
              
               }
              if(instanceStatus.equals("FALSE")){
                  encuentraIdNodo(nodo.id);
              }
         
        }
         return elemento;
      }
      
    
        public void isTokenAlive() {
        new Thread() { // }.start();

            @Override
            public void run() {
                while (true) {                              // bucle infinito                    
                    if ( tengoToken == false) {
                        System.out.println("Alguien tiene el token?");
                        try {
                            ms.sendMessage("WIT->" + myPort + "->" + myIP + "->"+ myID);
                        } catch (IOException ex) {
                            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        isTokenAlive = false;
                        long startTime = System.currentTimeMillis(); //fetch starting time
                        while (false || (System.currentTimeMillis() - startTime) < 10000) {  //se dormirá 10 s, al despertar primero
                            try {                                                           // chequea la bandera de si esta vivo
                                Thread.sleep(10);                                          // si es true significa que le ha contestado el coord y no hay que llamar a eleccion                                  
                            } catch (InterruptedException ex) {
                                Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                            }
                            if (isTokenAlive == true) {
                                //System.out.println(instanceStatus);                               
                                break;
                            }
                        }
                        if (isTokenAlive == false && tengoToken == false) {
                                                        
                                synchronized(token){
                                    //Averiguar que nodo se cayo----------------------------------->preguntar 1 x 1
                                    token.setToken(Token.generateToken());
                                    token.notify();
                                    try {
                                        MultiSender.sendMessage("NUEVOTOKEN->" + "->" + myID + "->" + myPort + "->" + myIP);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                txt_area.append("Se ha generado un nuevo token \n");
                                scrollDown();
                                // generar token                        Que pasa si otro justo essta en lo mismo
                        }
                    } else {
                        System.out.println("Yo tengo el token ahora\n");
                    }
                    actualizaInterfaz();
                    Random rand;
                    rand = new Random();
                    int sleep = rand.nextInt(10000) + 5000;
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }.start();
    }
        
     public static void sendList( int portToSend, String ip, int id) throws IOException {   //Este metodo ayuda a configurar al nuevo nodo
      new Thread(
              new Runnable() {
                        @Override
                        public void run()  {   
                            txt_area.append("Se enviará la lista de nodos al nuevo nodo siguiente: id-"+ id + " puerto- " + portToSend + "\n");
                            scrollDown();
                            String message = "";                              
                            /// Por si un nodo se cae mientras se esta pasando la lista pido el lock y lo libero
                            int i = 0;
                            for(InfoNodos nodo: listaNodos){           
                                
                                       Nodo.lock.lock();
                                try {
                                    message = "NODO->" + nodo.id + "->" + nodo.ip + "->" + nodo.puerto;
                                } finally {
                                    Nodo.lock.unlock();
                                }
                                       UniSender.sendMessage(message, portToSend, ip);
                                       message = "";
                                       i++;
                                   }
                            if (portNext == null && ipNext == null && IDnext == null){ // Solo se cumplia cuando llegue el 2do nodo al anillo
                                message = "NEXT->" + myID + "->" + myPort + "->" + myIP;
                            } else {
                              message = "NEXT->" + IDnext + "->" + portNext + "->" + ipNext;
                            }                           
                            UniSender.sendMessage(message, portToSend, ip);
                            IDnext = Integer.toString(id);
                            portNext = Integer.toString(portToSend);
                            ipNext = ip;
                            actualizaInterfaz();
                        }
                    }).start();     
    }
     
     public static void AcomodaNodo(int id, String portToSend, String ip){
         System.out.println("Estamos en acomoda Nodo: id " + id + ", portosend: "+ portToSend + " ip: " + ip);
         Nodo.lock.lock();        
         int lugar = 0;
         int tamInicial = listaNodos.size();
        try {
            
            if (listaNodos.isEmpty()){
                listaNodos.add(lugar, new InfoNodos(Integer.toString(id), portToSend, ip ));
            } else {
                for(InfoNodos nodo: listaNodos){ //(String id, String puerto, String ip)
                    if (Integer.parseInt(nodo.id) > id){
                        listaNodos.add(lugar, new InfoNodos(Integer.toString(id), portToSend, ip ));
                        break;
                    }
                 lugar ++;                  
                }
                if (listaNodos.size() == tamInicial){
                    listaNodos.add(lugar, new InfoNodos(Integer.toString(id), portToSend, ip )); //Lo agrego, significa que va de ultimo               
                }               
            }
            System.out.println("Tamaño final listaNodos: " + listaNodos.size() );
        } finally {
            Nodo.lock.unlock();
        }
     }
     
    public void tengoToken(Boolean activo){
            if(activo){
             
                b_iniciar.setEnabled(true);
                b_iniciar.setBackground(Color.GREEN);
                b_iniciar.setText("ACTIVO");
            
            } else{
            
                 b_iniciar.setBackground(Color.BLACK);
                 b_iniciar.setEnabled(false);
                 b_iniciar.setText("INACTIVO");
            }
            
    }
    
   public static void actualizaInterfaz(){
        l_ipNext.setText(ipNext);
        l_portNext.setText(portNext);
    }
   
   //-------------------------------------///
   public class RecibeToken extends Thread{

        public void run(){
            while (true){
            
               
                        if(!tengoToken){
                            
                            synchronized(Nodo.token){
                                try {
                                    txt_area.append("No tengo el token, entonces lo esperaré\n");
                                    Nodo.token.wait();
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        }
                        tengoToken(true);                       
                        txt_area.append("DORMIRE 5S CON EL TOKEN\n");
                        scrollDown();
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            if(portNext!= null && ipNext != null){
                                UniSender.sendMessage("TOKEN->" + myPort + "->" + myIP , Integer.parseInt(portNext), ipNext);
                                long startTime = System.currentTimeMillis();
                                while (false || (System.currentTimeMillis() - startTime) < 10000) {  //se dormirá 10 s, al despertar primero
                                    try {                                                           // chequea la bandera de si esta vivo
                                        Thread.sleep(10);                                          // si es true significa que le ha contestado el coord y no hay que llamar a eleccion                                  
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (tengoToken == false) { //Significa que la otra parte me contesto q lo recibio
                                        //System.out.println(instanceStatus);
                                        
                                        break;
                                    }
                                }
                                if(tengoToken == true){
                                    ///-------------------------------------------SE CAYO EL SIGUIENTE --------------> HACER FUNCION DE ARREGLO D ESTA SITUACION
                                    token.setToken(Token.generateToken());
                                    txt_area.append("Nuevo Token: " + token);
                                    encuentraIdNodo(IDnext);
                                    
                                } 
                                tengoToken(tengoToken);
                            }
            }
        }
            
        
    }
   
    public static void encuentraIdNodo(String idNodo) {
        int elemento = -1;

        for (int i = 0; i < listaNodos.size(); i++) {
            if (listaNodos.get(i).id.equals(idNodo)) {
                elemento = i;
                break;
            }
        }
        try {
            MultiSender.sendMessage("REMOVE->" + elemento);
        } catch (IOException ex) {
            Logger.getLogger(Nodo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public static void removeElement(int i){
       InfoNodos nodo = listaNodos.get(i);
       if (nodo.getId().equals(IDnext)){
            try{
                InfoNodos nuevoNext = listaNodos.get(i+1); 
                
                IDnext = nuevoNext.getId();
                ipNext = nuevoNext.getIp();
                portNext = nuevoNext.getPuerto();
                
           }
            catch(IndexOutOfBoundsException e){ ///Cuando justo se cae el ultimo, será el primero en la lista el nuevo siguiente
                   InfoNodos nuevoNext = listaNodos.get(0);
                   IDnext = nuevoNext.getId();
                   ipNext = nuevoNext.getIp();
                   portNext = nuevoNext.getPuerto();
            } 
            finally {
                listaNodos.remove(i);
            }
       
       }
   }
   
      public class EnvioToken extends Thread{

        public void run(){
            while (true){
            
                while(isTokenAlive){
                    if (!tengoToken) {
			synchronized (token) {
				try {                                       
					token.wait();             //esperara a que otro le pase el token si no es el servidor inicial
                                           //`proceso y envio
                                } catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
                
                }
            }
        
        }
   
   }
   
   
}
      
   public static void scrollDown(){
    txt_area.setCaretPosition(txt_area.getText().length());
}
      
}
