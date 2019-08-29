package meetle.gui;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.GoKarts;
import meetle.eventi.PartitaDiCalcio;

public class AreaPersonaleFrame extends javax.swing.JFrame {

    private static final int ISCRIZIONI = 0, CREAZIONI = 1, NOTIFICHE = 2;

    private final String uID;

    public AreaPersonaleFrame() {
        uID = Meetle.getIstanza().getUtenteLoggatoID();
        
        initComponents();
        jButton1.setVisible(false);
        jLabelUserID.setText(Meetle.getIstanza().getUtenteLoggatoID());
        jScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        inizializza();
        aggiorna();
    }

    /**
     * rimuove tutti i componenti nel pannello principale e lo riempie nuovamente con nuovi componenti
     */
    public final void inizializza() {
        jPanelMadre.removeAll();
        jPanelMadre.repaint();
        jPanelMadre.validate();
        
        switch (selettore.getSelectedIndex()) {
            case ISCRIZIONI: {
                ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByIscrittoID(uID);
                l.forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.POS_ISCRIZIONI)); });
                break;
            }
            case CREAZIONI: {
                ArrayList<Evento> l = Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID);
                l.forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.POS_POSSEDUTI)); });
                
//                    Meetle.getIstanza().getBacheca().getEventiByCreatoreID(uID).stream()
//                            .forEach((e) -> { jPanelMadre.add(new EventoPanel(e.getID(), EventoPanel.MODE_MODIFICA)); });
                break;
            }
            case NOTIFICHE:
                Meetle.getIstanza().getNotifiche().forEach((n) -> { jPanelMadre.add(new NotificaPanel(n)); });
                break;
        }
        
    }
    
    /**
     * aggiorna il contenuto dei pannelli presenti nel pannello principale
     */
    public final void aggiorna() {
        jButtonAggiungi.setVisible(selettore.getSelectedIndex()==1);
        jButtonNotificheNonLette.setVisible(Meetle.getIstanza().getUtenti().getUtenteDaID(uID).haNotificheNonLette());
        
        Component componenti[] = jPanelMadre.getComponents();
//        System.out.println("Agggiorna -> comp.lenght: " + comp.length);
        for (Component componente : componenti) 
            try { ((EventoPanel) componente).aggiorna(); } catch (Exception e) {}
        jPanelMadre.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanelHeader = new javax.swing.JPanel();
        jLabelTitolo = new javax.swing.JLabel();
        selettore = new javax.swing.JComboBox<>();
        jButtonProfilo = new javax.swing.JButton();
        jLabelUserID = new javax.swing.JLabel();
        jButtonAggiungi = new javax.swing.JButton();
        jButtonNotificheNonLette = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jPanelMadre = new javax.swing.JPanel();

        jButton1.setText("Torna a bacheca");

        setTitle("Area Utente");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(getMinimumSize());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelHeader.setBackground(new java.awt.Color(0, 115, 150));
        jPanelHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setText("Area Personale");

        selettore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Eventi a cui sei iscritto", "Eventi creati da te", "Le tue notifiche" }));
        selettore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selettoreActionPerformed(evt);
            }
        });

        jButtonProfilo.setText("Configura il Profilo");
        jButtonProfilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProfiloActionPerformed(evt);
            }
        });

        jLabelUserID.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabelUserID.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUserID.setText("userID");

        jButtonAggiungi.setText("Aggiungi Evento");
        jButtonAggiungi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAggiungiActionPerformed(evt);
            }
        });

        jButtonNotificheNonLette.setText("Hai delle notifiche non lette!");
        jButtonNotificheNonLette.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNotificheNonLetteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabelTitolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                        .addComponent(jLabelUserID))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(selettore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAggiungi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNotificheNonLette)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonProfilo)))
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitolo)
                    .addComponent(jLabelUserID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selettore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProfilo)
                    .addComponent(jButtonAggiungi)
                    .addComponent(jButtonNotificheNonLette))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.NORTH);

        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelMadre.setBackground(new java.awt.Color(0, 150, 155));
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

    private void jButtonProfiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProfiloActionPerformed
        new ProfiloFrame().setVisible(true);
    }//GEN-LAST:event_jButtonProfiloActionPerformed

    private void jButtonAggiungiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAggiungiActionPerformed
        String[] opzioni = {PartitaDiCalcio.NOME, GoKarts.NOME};
        String risposta = (String) JOptionPane.showInputDialog(this, "Seleziona la categoria di cui vuoi creare l'evento", 
                "Selezione Categoria", JOptionPane.QUESTION_MESSAGE, null, opzioni, null);
        if(risposta==null)
            return;
        Evento eventoTemp = null;
        switch(risposta) {
            case PartitaDiCalcio.NOME:
                eventoTemp = new PartitaDiCalcio(Meetle.getIstanza(), Meetle.getIstanza().getUtenteLoggatoID());
                break;
            case GoKarts.NOME:
                eventoTemp = new GoKarts(Meetle.getIstanza(), Meetle.getIstanza().getUtenteLoggatoID());
                break;
//            case "temporaneo":
//                Meetle.getIstanza().getBacheca().metodoTemporaneo(Meetle.getIstanza().getUtenteLoggatoID());
//                return;
            default:
                throw new UnsupportedOperationException("Selezione non valida");
        }
        Meetle.getIstanza().getBacheca().add(eventoTemp);
        new EventoFrame(eventoTemp.getID(), EventoFrame.CREA).setVisible(true);
        inizializza();
        aggiorna();
    }//GEN-LAST:event_jButtonAggiungiActionPerformed

    private void jButtonNotificheNonLetteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNotificheNonLetteActionPerformed
        selettore.setSelectedIndex(2);
    }//GEN-LAST:event_jButtonNotificheNonLetteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAggiungi;
    private javax.swing.JButton jButtonNotificheNonLette;
    private javax.swing.JButton jButtonProfilo;
    private javax.swing.JLabel jLabelTitolo;
    private javax.swing.JLabel jLabelUserID;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelMadre;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JComboBox<String> selettore;
    // End of variables declaration//GEN-END:variables
}
