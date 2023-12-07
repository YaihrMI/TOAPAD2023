package multihilos;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Reproductor extends javax.swing.JFrame {

    private Clip clip;
    private File file;
    private String audio;
    private int posPausa;
    
    public Reproductor() {
        initComponents();
        setLocationRelativeTo(null);
        JFileChooser fileChooser = new JFileChooser();
        int opt = fileChooser.showOpenDialog(this);

        if (opt == JFileChooser.APPROVE_OPTION) {
            audio = fileChooser.getSelectedFile().getAbsolutePath();
            file = new File(audio);
            Audio.setText(file.getName());
            
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        Audio = new javax.swing.JTextField();
        bar = new javax.swing.JProgressBar();
        btnReproducir = new javax.swing.JButton();
        btnContinuar = new javax.swing.JButton();
        btnPausar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bar.setStringPainted(true);

        btnReproducir.setText("Reproducir");
        btnReproducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReproducirActionPerformed(evt);
            }
        });

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnPausar.setText("Pausar");
        btnPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausarActionPerformed(evt);
            }
        });

        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(btnReproducir)
                .addGap(53, 53, 53)
                .addComponent(btnContinuar)
                .addGap(67, 67, 67)
                .addComponent(btnPausar)
                .addGap(57, 57, 57)
                .addComponent(btnReiniciar)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Audio, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(Audio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReproducir)
                    .addComponent(btnContinuar)
                    .addComponent(btnPausar)
                    .addComponent(btnReiniciar))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>                        

    private void btnReproducirActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Thread hilo = new Thread(() -> {
            try {
                
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
                
                clip.start();
                int total = (int) clip.getMicrosecondLength() / 1000;
                int acc = 0;
                while (acc < total) {
                    acc = (int) clip.getMicrosecondPosition() / 1000;
                    int progress = (int) ((float) acc / total * 100);
                    bar.setValue(progress);     
                    Thread.sleep(100);
                }
                
                bar.setValue(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        hilo.start();
    }                                             

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {                                             
         if(clip != null){
            clip.setFramePosition(posPausa);
            clip.start();
        }
    }                                            

    private void btnPausarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if(clip != null){
            posPausa = clip.getFramePosition();
            clip.stop();
        }
    }                                         

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }                                            

    
    
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
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reproductor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reproductor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField Audio;
    private javax.swing.JProgressBar bar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnPausar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnReproducir;
    // End of variables declaration                   
}
