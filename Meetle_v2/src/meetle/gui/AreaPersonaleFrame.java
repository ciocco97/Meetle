package meetle.gui;

import java.awt.HeadlessException;
import java.util.ArrayList;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.utenti.Notifica;

public class AreaPersonaleFrame extends javax.swing.JFrame {
    private Meetle meetle;
    private enum Stato {ISCRIZIONI, CREAZIONI, NOTIFICHE};
    private Stato stato;
    private String ID;
    
    //DA togliere
    ArrayList<String> notifiche;

    public AreaPersonaleFrame(Meetle meetle) throws HeadlessException {
        initComponents();
        setLocationRelativeTo(null);
        this.meetle = meetle;
        ID = meetle.getUtenteLoggatoID();
        stato = Stato.NOTIFICHE;
        this.jLabel1.setText(meetle.getUtenteLoggatoID());
        
        //da togliere
        notifiche = new ArrayList<>();
        for(int i = 0; i< 10; i++)
            notifiche.add("Notifica numero: " + i);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelMadre = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        selettore = new javax.swing.JComboBox<>();

        setTitle("Area Utente");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelMadre.setLayout(new javax.swing.BoxLayout(jPanelMadre, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanelMadre);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 760, 450));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
        int selezionato = selettore.getSelectedIndex();
        switch (selezionato) {
            case 0:
                System.out.println("Eventi a cui sei iscritto");
                stato = Stato.ISCRIZIONI;
                break;
            case 1:
                System.out.println("Eventi creati da te");
                stato = Stato.CREAZIONI;
                break;
            case 2:
                System.out.println("Le tue notifiche");
                stato = Stato.NOTIFICHE;
                break;
        }
        aggiorna();
    }//GEN-LAST:event_selettoreActionPerformed

    private void aggiorna() {
        jPanelMadre.removeAll();
        if(stato.equals(Stato.ISCRIZIONI)) {
            ArrayList<Evento> list = meetle.getEventiIscritti(ID);
            list.forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), false, meetle)); });
            pack();
        }
        else if(stato.equals(Stato.CREAZIONI)) {
            ArrayList<Evento> list = meetle.getEventiByCreatoreID(ID);
            list.forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), true, meetle)); });
            pack();
        }
        else {
            for(String s: notifiche)
                jPanelMadre.add(new NotificaPanel(s));
            pack();
        }
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
            new AreaPersonaleFrame(new Meetle()).setVisible(true);
        });        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelMadre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> selettore;
    // End of variables declaration//GEN-END:variables
}
