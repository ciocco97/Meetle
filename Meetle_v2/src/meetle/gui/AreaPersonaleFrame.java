package meetle.gui;

import java.awt.HeadlessException;
import javax.swing.DefaultListModel;

public class AreaPersonaleFrame extends javax.swing.JFrame {
    private DefaultListModel model;

    public AreaPersonaleFrame() throws HeadlessException {
        initComponents();
        setLocationRelativeTo(null);
        listaNotifiche.setVisible(false);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaNotifiche = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        selettore = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Area Utente");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listaNotifiche.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(listaNotifiche);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 760, 450));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome del personaggio");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 60));

        selettore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Eventi a cui sei iscritto", "Eventi creati da te", "Le tue notifiche" }));
        selettore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selettoreActionPerformed(evt);
            }
        });
        getContentPane().add(selettore, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 150, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selettoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selettoreActionPerformed
        togliTutto();
        int selezionato = selettore.getSelectedIndex();
        switch (selezionato) {
            case 0:
                System.out.println("Eventi a cui sei iscritto");
                break;
            case 1:
                System.out.println("Eventi creati da te");
                break;
            case 2:
                System.out.println("Le tue notifiche");
                model.addElement(new String("ciao"));
                listaNotifiche.setModel(model);
                listaNotifiche.setVisible(true);
                break;
            default:
                System.out.println("What?");
                break;
        }
    }//GEN-LAST:event_selettoreActionPerformed

    private void togliTutto() {
        listaNotifiche.setVisible(false);
    }
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Setting the Windows look and feel (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AreaPersonaleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AreaPersonaleFrame().setVisible(true);
        });        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaNotifiche;
    private javax.swing.JComboBox<String> selettore;
    // End of variables declaration//GEN-END:variables
}
