package meetle.gui;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JLabel;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.utenti.Notifica;

public class AreaPersonaleFrame extends javax.swing.JFrame {
    private Meetle meetle;
    private enum Stato {ISCRIZIONI, CREAZIONI, NOTIFICHE}; 
    private Stato stato;
    private String ID;

    public AreaPersonaleFrame(Meetle meetle) throws HeadlessException {
        initComponents();
        setLocationRelativeTo(null);
        this.meetle = meetle;
        ID = meetle.getUtenteLoggatoID();
        stato = Stato.NOTIFICHE;
        this.jLabel1.setText(meetle.getUtenteLoggatoID());
        aggiorna();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        selettore = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelMadre = new javax.swing.JPanel();

        setTitle("Area Utente");
        setBackground(new java.awt.Color(0, 115, 150));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 115, 150));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel2");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, -1));

        selettore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Eventi a cui sei iscritto", "Eventi creati da te", "Le tue notifiche" }));
        selettore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selettoreActionPerformed(evt);
            }
        });
        jPanel1.add(selettore, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 210, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelMadre.setLayout(new javax.swing.BoxLayout(jPanelMadre, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanelMadre);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selettoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selettoreActionPerformed
        int selezionato = selettore.getSelectedIndex();
        switch (selezionato) {
            case 0:
//                System.out.println("Eventi a cui sei iscritto");
                stato = Stato.ISCRIZIONI;
                break;
            case 1:
//                System.out.println("Eventi creati da te");
                stato = Stato.CREAZIONI;
                break;
            case 2:
//                System.out.println("Le tue notifiche");
                stato = Stato.NOTIFICHE;
                break;
        }
        aggiorna();
    }//GEN-LAST:event_selettoreActionPerformed

    private void aggiorna() {
        jPanelMadre.removeAll();
        switch (stato) {
            case ISCRIZIONI:
                {
                    ArrayList<Evento> list = meetle.getEventiIscritti(ID);
                    list.forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), false, meetle)); });
                    break;
                }
            case CREAZIONI:
                {
                    ArrayList<Evento> list = meetle.getEventiByCreatoreID(ID);
                    list.forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), true, meetle)); });
                    break;
                }
            default:
                for(Notifica n: meetle.getNotifiche()) {
                    jPanelMadre.add(new NotificaPanel(meetle, n));
                }   break;
        }
        pack();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelMadre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> selettore;
    // End of variables declaration//GEN-END:variables
}
