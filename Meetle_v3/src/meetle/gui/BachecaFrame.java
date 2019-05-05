package meetle.gui;

import java.awt.Component;
import javax.swing.JOptionPane;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.PartitaDiCalcio;
import meetle.eventi.Stato;

public class BachecaFrame extends javax.swing.JFrame {

    public BachecaFrame() {
        initComponents();        
        jBtnAreaPrivata.setText(Meetle.getIstanza().getUtenteLoggatoID());
        inizializza();
    }
    public void inizializza() {
        jPanelBacheca.removeAll();
        jPanelBacheca.repaint();
        jPanelBacheca.validate();
        
        Meetle.getIstanza().getBacheca().stream()
                .filter((e) -> {
                    switch(jComboBoxCategorie.getSelectedIndex()) {
                        case 1: return e instanceof PartitaDiCalcio;
                        default: return true;
                    }})
                .forEach((e) -> jPanelBacheca.add(new EventoPanel(e.getID(), EventoPanel.POS_BACHECA)));
        pack();
    }
    public void aggiorna()
    {
        Component comp[] = jPanelBacheca.getComponents();
        for (int i = 0; i<comp.length; i++)
        {
             EventoPanel pannello = (EventoPanel) comp[i];
             pannello.aggiorna();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCategorie = new javax.swing.JPanel();
        jLabelTitolo = new javax.swing.JLabel();
        jComboBoxCategorie = new javax.swing.JComboBox<>();
        jButtonAggiungiEvento = new javax.swing.JButton();
        jBtnAreaPrivata = new javax.swing.JButton();
        jScrollPaneBacheca = new javax.swing.JScrollPane();
        jPanelBacheca = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meetle");
        setMinimumSize(new java.awt.Dimension(600, 400));

        jPanelCategorie.setBackground(new java.awt.Color(0, 115, 150));
        jPanelCategorie.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setText("Meetle");

        jComboBoxCategorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tutte le Categorie", "Partite di Calcio" }));
        jComboBoxCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategorieActionPerformed(evt);
            }
        });

        jButtonAggiungiEvento.setText("Aggiungi Evento");
        jButtonAggiungiEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAggiungiEventoActionPerformed(evt);
            }
        });

        jBtnAreaPrivata.setText("userID");
        jBtnAreaPrivata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAreaPrivataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCategorieLayout = new javax.swing.GroupLayout(jPanelCategorie);
        jPanelCategorie.setLayout(jPanelCategorieLayout);
        jPanelCategorieLayout.setHorizontalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCategorieLayout.createSequentialGroup()
                        .addComponent(jLabelTitolo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelCategorieLayout.createSequentialGroup()
                        .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAggiungiEvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                        .addComponent(jBtnAreaPrivata)))
                .addContainerGap())
        );
        jPanelCategorieLayout.setVerticalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitolo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAggiungiEvento)
                    .addComponent(jBtnAreaPrivata))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelCategorie, java.awt.BorderLayout.NORTH);

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
        Meetle.getIstanza().mostraAreaPersonale();
    }//GEN-LAST:event_jBtnAreaPrivataActionPerformed

    private void jComboBoxCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategorieActionPerformed
        inizializza();
        aggiorna();
    }//GEN-LAST:event_jComboBoxCategorieActionPerformed

    private void jButtonAggiungiEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAggiungiEventoActionPerformed
        switch(jComboBoxCategorie.getSelectedIndex()) {
            case 0:
                JOptionPane.showMessageDialog(this, "Seleziona (dal combo box) la categoria di cui vuoi creare un evento");
                break;
            case 1:
                Evento eventoTemp = new PartitaDiCalcio(Meetle.getIstanza().getUtenteLoggatoID());
                Meetle.getIstanza().getBacheca().add(eventoTemp);
                new EventoFrame(eventoTemp.getID(), EventoFrame.CREA).setVisible(true);
                break;
            default:
                throw new UnsupportedOperationException("Creazione non supportata");
        }        
        
    }//GEN-LAST:event_jButtonAggiungiEventoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAreaPrivata;
    private javax.swing.JButton jButtonAggiungiEvento;
    private javax.swing.JComboBox<String> jComboBoxCategorie;
    private javax.swing.JLabel jLabelTitolo;
    private javax.swing.JPanel jPanelBacheca;
    private javax.swing.JPanel jPanelCategorie;
    private javax.swing.JScrollPane jScrollPaneBacheca;
    // End of variables declaration//GEN-END:variables
}
