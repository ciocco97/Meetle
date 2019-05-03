package meetle.gui;

import java.util.ArrayList;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.Stato;
import meetle.utenti.Notifica;

public class AreaPersonaleFrame extends javax.swing.JFrame {
    
    private static final int ISCRIZIONI=0, CREAZIONI=1, NOTIFICHE=2; 
    
    private String uID;

    public AreaPersonaleFrame() {
        initComponents();
        
        uID = Meetle.getIstanza().getUtenteLoggatoID();
        jLabelTitolo.setText(uID);
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        
        aggiorna();
    }    
    
    public void aggiorna() {
        jPanelMadre.removeAll();
        switch (selettore.getSelectedIndex()) {
            case ISCRIZIONI:
                {
                    ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByIscrittoID(uID);
                    for(Evento e : l) {
                        if(e.getIndiceStatoCorrente() == Stato.APERTO)
                            jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_EVENTO_APERTO));
                        else if(e.getIndiceStatoCorrente() == Stato.CHIUSO)
                            jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_VISUALIZZA));
                    }
                    break;
                }
            case CREAZIONI:
                {
                    ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID);
                    for(Evento e: l) {
                        int stato = e.getIndiceStatoCorrente();
                        
                        if(stato == Stato.VALIDO || stato == Stato.NONVALIDO)
                            jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_MODIFICA));
                        if(stato == Stato.APERTO || stato == Stato.CHIUSO || stato == Stato.CONCLUSO)
                            jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_VISUALIZZA));
                    }
//                    Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID).stream()
//                            .forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_MODIFICA)); });
                    break;
                }
            case NOTIFICHE:
                for(Notifica n: Meetle.getIstanza().getNotifiche()) {
                    jPanelMadre.add(new NotificaPanel(n));
                }   
                break;
        }      
        repaint();  
        pack();
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
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(selettore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jLabelTitolo)
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(selettore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelTitolo))
                .addContainerGap(25, Short.MAX_VALUE))
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
        aggiorna();
    }//GEN-LAST:event_selettoreActionPerformed

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
