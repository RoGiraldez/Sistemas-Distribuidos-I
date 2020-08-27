
package relojntp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;


public class reloj extends javax.swing.JFrame {

    public static int      frecuencia;
    public static String   hora;
    public        Boolean  inicio  = false;
    public        Boolean  inicio2 = false;
    public static String   serverNTP;
    public static int      tActualizacion;
    public static Calendar horaActual;
    
    

    public reloj() {
        initComponents();
        horaActual = new GregorianCalendar();
        
        int hour = horaActual.get(Calendar.HOUR_OF_DAY);
        int min  = horaActual.get(Calendar.MINUTE);
        int sec  = horaActual.get(Calendar.SECOND);
         
        
        String time = hour + ":" + min + ":" + sec;
        hora = time;
        l_hora.setText(hora);
        
        SpinnerNumberModel nm = new SpinnerNumberModel();
        nm.setMinimum(0);
        nm.setStepSize(10);      //No se podran valores negativos de frecuencia y el paso cada vez que haces click en una flechita es de 10 hz
        s_frecuencia.setModel(nm);
        JFormattedTextField txt = ((JSpinner.NumberEditor) s_frecuencia.getEditor()).getTextField();
       ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
        SpinnerNumberModel nm2 = new SpinnerNumberModel();
        nm2.setMinimum(0);
        nm2.setStepSize(1);      //No se podran valores negativos de frecuencia y el paso cada vez que haces click en una flechita es de 10 hz
        s_tiempoActualizacion.setModel(nm2);
        
        //b_iniciar.setEnabled(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        b_iniciar = new javax.swing.JButton();
        l_hora = new javax.swing.JLabel();
        s_frecuencia = new javax.swing.JSpinner();
        tf_serverNTP = new javax.swing.JTextField();
        s_tiempoActualizacion = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));

        jLabel1.setText("Hora:");

        jLabel2.setText("Frecuencia:");

        jLabel3.setText("Servidor:");

        jLabel4.setText("Tiempo actualización:");

        b_iniciar.setBackground(new java.awt.Color(0, 153, 153));
        b_iniciar.setForeground(new java.awt.Color(255, 255, 255));
        b_iniciar.setText("INICIAR");
        b_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_iniciarActionPerformed(evt);
            }
        });

        l_hora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        s_frecuencia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s_frecuenciaStateChanged(evt);
            }
        });

        tf_serverNTP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tf_serverNTP.setText("time.windows.com");
        tf_serverNTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_serverNTPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(s_frecuencia)
                            .addComponent(tf_serverNTP, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(s_tiempoActualizacion)
                            .addComponent(l_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(b_iniciar)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(l_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addComponent(s_frecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tf_serverNTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(s_tiempoActualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(b_iniciar)
                .addGap(26, 26, 26))
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

    private void s_frecuenciaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s_frecuenciaStateChanged
        
        frecuencia = (Integer)s_frecuencia.getValue();
        if (!inicio){
            Thread actualiza = new Thread (new ActualizaInterfaz());
            actualiza.start();

            Thread actualizaFrecuencia = new Thread (new FrecuenciaReloj());
            actualizaFrecuencia.start();
            b_iniciar.setEnabled(true);
            inicio = true;
        }
    }//GEN-LAST:event_s_frecuenciaStateChanged

    private void b_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_iniciarActionPerformed
       
        serverNTP = tf_serverNTP.getText();
        tActualizacion = (Integer)(s_tiempoActualizacion.getValue());
        if(!inicio2){
            s_frecuencia.setEnabled(false);
            //falta arrancar hilo conector con servidor
            Thread correctClock = new Thread(new RelojNtp());
            correctClock.start();
            inicio2 = true;
        }
    }//GEN-LAST:event_b_iniciarActionPerformed

    private void tf_serverNTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_serverNTPActionPerformed
        
    }//GEN-LAST:event_tf_serverNTPActionPerformed


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
            java.util.logging.Logger.getLogger(reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reloj.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new reloj().setVisible(true);
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
    private javax.swing.JLabel l_hora;
    private javax.swing.JSpinner s_frecuencia;
    private javax.swing.JSpinner s_tiempoActualizacion;
    private javax.swing.JTextField tf_serverNTP;
    // End of variables declaration//GEN-END:variables

    public class ActualizaInterfaz extends Thread{
            
        @Override
        public void run(){
            while(true){
                l_hora.setText(hora);  
                if (inicio2){
                    s_frecuencia.setValue(frecuencia);
                }
            }
            
        }

    }


}
