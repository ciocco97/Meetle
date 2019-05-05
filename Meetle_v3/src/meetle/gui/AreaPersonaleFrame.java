package meetle.gui;

import java.awt.Component;
import java.util.ArrayList;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.utenti.Notifica;

public class AreaPersonaleFrame extends javax.swing.JFrame {

    private static final int ISCRIZIONI = 0, CREAZIONI = 1, NOTIFICHE = 2;

    private final String uID;

    public AreaPersonaleFrame() {
        initComponents();

        uID = Meetle.getIstanza().getUtenteLoggatoID();
        jLabelTitolo.setText(uID);
        inizializza();
        aggiorna();
    }

    public void inizializza() {
        jPanelMadre.removeAll();
        jPanelMadre.repaint();
        jPanelMadre.validate();
        
        switch (selettore.getSelectedIndex()) {
            case ISCRIZIONI: {
                ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByIscrittoID(uID);
                for (Evento e : l) 
                    jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.POS_ISCRIZIONI));
                
                break;
            }
            case CREAZIONI: {
                ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID);
                for (Evento e : l) 
                    jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.POS_POSSEDUTI));
                
//                    Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID).stream()
//                            .forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_MODIFICA)); });
                break;
            }
            case NOTIFICHE:
                for (Notifica n : Meetle.getIstanza().getNotifiche()) {
                    jPanelMadre.add(new NotificaPanel(n));
                }
                break;
        }
    }

    public void aggiorna() {
        Component comp[] = jPanelMadre.getComponents();
//        System.out.println("Agggiorna -> comp.lenght: " + comp.length);
        for (Component comp1 : comp) {
            try {
                EventoPanel pannello = (EventoPanel) comp1;
                pannello.aggiorna();
            }catch (Exception e) {;}
        }
        jPanelMadre.validate();
    }

    @Override
    public void setVisible(boolean b) {
        aggiorna();
        super.setVisible(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jLabelTitolo = new javax.swing.JLabel();
        selettore = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jPanelMadre = new javax.swing.JPanel();

        setTitle("Area Utente");
        setBackground(new java.awt.Color(0, 115, 150));
        setMinimumSize(new java.awt.Dimension(600, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelHeader.setBackground(new java.awt.Color(0, 115, 150));
        jPanelHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setText("AreaPersonale");

        selettore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Eventi a cui sei iscritto", "Eventi creati da te", "Le tue notifiche" }));
        selettore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selettoreActionPerformed(evt);
            }
        });

        jButton1.setText("Torna a bacheca");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selettore, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jLabelTitolo)
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selettore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelTitolo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.NORTH);

        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelMadre.setBackground(new java.awt.Color(0, 150, 156));
        jPanelMadre.setLayout(new javax.swing.BoxLayout(jPanelMadre, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane.setViewportView(jPanelMadre);

        getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selettoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selettoreActionPerformed
        inizializza();
        aggiorna();
    }//GEN-LAST:event_selettoreActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Meetle.getIstanza().mostraBacheca();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Meetle.getIstanza().mostraBacheca();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelTitolo;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelMadre;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JComboBox<String> selettore;
    // End of variables declaration//GEN-END:variables
}
