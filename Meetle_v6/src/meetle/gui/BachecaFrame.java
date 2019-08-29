package meetle.gui;

import java.awt.Component;
import javax.swing.JOptionPane;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.GoKarts;
import meetle.eventi.PartitaDiCalcio;

public class BachecaFrame extends javax.swing.JFrame {

    private final Meetle meetle;
    
    public BachecaFrame(Meetle meetle) {
        this.meetle = meetle;
        initComponents();        
//        jBtnAreaPrivata.setText(this.meetle.getUtenteLoggatoID() + "");
        jLabelUserID.setText(this.meetle.getUtenteLoggatoID());
        jScrollPaneBacheca.getVerticalScrollBar().setUnitIncrement(20);
        inizializza();
    }
    
    /**
     * rimuove tutti i contenuti del pannello principale e lo riempie con nuovi pannelli
     */
    public final void inizializza() {
        jPanelBacheca.removeAll();
        jPanelBacheca.repaint();
        jPanelBacheca.validate();
        
        this.meetle.getBacheca().getEventi().stream()
                .filter((e) -> {
                    switch(jComboBoxCategorie.getSelectedIndex()) {
                        case 0: return true;
                        case 1: return e instanceof PartitaDiCalcio;
                        case 2: return e instanceof GoKarts;
                        default: throw new UnsupportedOperationException("selezione da implementare");
                    }})
                .forEach((e) -> jPanelBacheca.add(new EventoPanel(this.meetle, e.getID(), EventoPanel.POS_BACHECA)));
        pack();
    }
    
    /**
     * aggiorna i contenuti dei pannelli degli eventi
     */
    public void aggiorna() {
        Component contenuti[] = jPanelBacheca.getComponents();
        for (Component componente : contenuti) 
            ((EventoPanel) componente).aggiorna();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jLabelTitolo = new javax.swing.JLabel();
        jComboBoxCategorie = new javax.swing.JComboBox<>();
        jBtnAreaPrivata = new javax.swing.JButton();
        jLabelUserID = new javax.swing.JLabel();
        jScrollPaneBacheca = new javax.swing.JScrollPane();
        jPanelBacheca = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meetle");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("BachecaFrame"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelHeader.setBackground(new java.awt.Color(0, 110, 155));

        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setText("Meetle");

        jComboBoxCategorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tutte le Categorie", "Partite di Calcio", "GoKart" }));
        jComboBoxCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategorieActionPerformed(evt);
            }
        });

        jBtnAreaPrivata.setText("Area Personale");
        jBtnAreaPrivata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAreaPrivataActionPerformed(evt);
            }
        });

        jLabelUserID.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabelUserID.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUserID.setText("userID");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabelTitolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                        .addComponent(jLabelUserID))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnAreaPrivata)))
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitolo)
                    .addComponent(jLabelUserID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAreaPrivata))
                .addContainerGap())
        );

        getContentPane().add(jPanelHeader, java.awt.BorderLayout.NORTH);

        jScrollPaneBacheca.setBorder(null);
        jScrollPaneBacheca.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelBacheca.setBackground(new java.awt.Color(0, 150, 155));
        jPanelBacheca.setLayout(new javax.swing.BoxLayout(jPanelBacheca, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneBacheca.setViewportView(jPanelBacheca);

        getContentPane().add(jScrollPaneBacheca, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAreaPrivataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAreaPrivataActionPerformed
        this.meetle.mostraAreaPersonale();
    }//GEN-LAST:event_jBtnAreaPrivataActionPerformed

    private void jComboBoxCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategorieActionPerformed
        inizializza();
        aggiorna();
    }//GEN-LAST:event_jComboBoxCategorieActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.meetle.salva();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAreaPrivata;
    private javax.swing.JComboBox<String> jComboBoxCategorie;
    private javax.swing.JLabel jLabelTitolo;
    private javax.swing.JLabel jLabelUserID;
    private javax.swing.JPanel jPanelBacheca;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JScrollPane jScrollPaneBacheca;
    // End of variables declaration//GEN-END:variables
}
