package meetle.gui;

import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.PartitaDiCalcio;

public class BachecaFrame extends javax.swing.JFrame {

    private Meetle meetle;

    public BachecaFrame(Meetle meetle) {
        initComponents();
        this.meetle = meetle;
        
        jBtnAreaPrivata.setText(meetle.getUtenteLoggatoID());
        
        aggiornaEventi();
    }
    
    private void aggiornaEventi() {
        jPanelBacheca.removeAll();
        for(Evento e: meetle.getBacheca()) 
            jPanelBacheca.add(new EventoPanel(e.getID(), false, meetle));
        pack();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCategorie = new javax.swing.JPanel();
        jComboBoxCategorie = new javax.swing.JComboBox<>();
        jBtnAreaPrivata = new javax.swing.JButton();
        jButtonTemporaneo = new javax.swing.JButton();
        jButtonAggiungiEvento = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPaneBacheca = new javax.swing.JScrollPane();
        jPanelBacheca = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meetle");

        jPanelCategorie.setBackground(new java.awt.Color(0, 115, 150));
        jPanelCategorie.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelCategorie.setFont(new java.awt.Font("Segoe UI Semilight", 0, 48)); // NOI18N
        jPanelCategorie.setPreferredSize(new java.awt.Dimension(600, 100));

        jComboBoxCategorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tutte le Categorie", "Partite di Calcio" }));
        jComboBoxCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategorieActionPerformed(evt);
            }
        });

        jBtnAreaPrivata.setText("userID");
        jBtnAreaPrivata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAreaPrivataActionPerformed(evt);
            }
        });

        jButtonTemporaneo.setText("metodoTemporaneo()");
        jButtonTemporaneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTemporaneoActionPerformed(evt);
            }
        });

        jButtonAggiungiEvento.setText("Aggiungi Evento");
        jButtonAggiungiEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAggiungiEventoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Meetle");

        javax.swing.GroupLayout jPanelCategorieLayout = new javax.swing.GroupLayout(jPanelCategorie);
        jPanelCategorie.setLayout(jPanelCategorieLayout);
        jPanelCategorieLayout.setHorizontalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCategorieLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 467, Short.MAX_VALUE))
                    .addGroup(jPanelCategorieLayout.createSequentialGroup()
                        .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTemporaneo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAggiungiEvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnAreaPrivata)))
                .addContainerGap())
        );
        jPanelCategorieLayout.setVerticalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addGroup(jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTemporaneo)
                    .addComponent(jButtonAggiungiEvento)
                    .addComponent(jBtnAreaPrivata))
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanelCategorie, java.awt.BorderLayout.NORTH);

        jScrollPaneBacheca.setBorder(null);
        jScrollPaneBacheca.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneBacheca.setPreferredSize(new java.awt.Dimension(600, 400));

        jPanelBacheca.setLayout(new javax.swing.BoxLayout(jPanelBacheca, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneBacheca.setViewportView(jPanelBacheca);

        getContentPane().add(jScrollPaneBacheca, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTemporaneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTemporaneoActionPerformed
        meetle.getBacheca().metodoTemporaneo(meetle.getUtenteLoggatoID());
        meetle.salvaEventi();
        aggiornaEventi();
    }//GEN-LAST:event_jButtonTemporaneoActionPerformed

    private void jBtnAreaPrivataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAreaPrivataActionPerformed
        meetle.areaPersonale();
    }//GEN-LAST:event_jBtnAreaPrivataActionPerformed

    private void jComboBoxCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategorieActionPerformed
        switch(jComboBoxCategorie.getSelectedIndex()) {
            case 0:
                System.out.println("Tutte le categorie");
                break;
            case 1:
                System.out.println("Partite di calcio");
                break;
        }
    }//GEN-LAST:event_jComboBoxCategorieActionPerformed

    private void jButtonAggiungiEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAggiungiEventoActionPerformed
        Evento e = new PartitaDiCalcio(meetle.getUtenteLoggatoID());
        meetle.getBacheca().add(e);
        meetle.salvaEventi();
        aggiornaEventi();
        (new EventoFrame(meetle, e, EventoFrame.CREA)).setVisible(true);
        
    }//GEN-LAST:event_jButtonAggiungiEventoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAreaPrivata;
    private javax.swing.JButton jButtonAggiungiEvento;
    private javax.swing.JButton jButtonTemporaneo;
    private javax.swing.JComboBox<String> jComboBoxCategorie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelBacheca;
    private javax.swing.JPanel jPanelCategorie;
    private javax.swing.JScrollPane jScrollPaneBacheca;
    // End of variables declaration//GEN-END:variables
}
