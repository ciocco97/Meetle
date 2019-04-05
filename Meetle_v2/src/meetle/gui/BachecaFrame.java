package meetle.gui;

import meetle.Meetle;
import meetle.eventi.Bacheca;
import meetle.eventi.Evento;

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
        jButton1 = new javax.swing.JButton();
        jScrollPaneBacheca = new javax.swing.JScrollPane();
        jPanelBacheca = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelCategorie.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelCategorie.setPreferredSize(new java.awt.Dimension(600, 40));

        jComboBoxCategorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tutte le Categorie", "Partite di Calcio" }));
        jComboBoxCategorie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCategorieItemStateChanged(evt);
            }
        });

        jBtnAreaPrivata.setText("userID");

        jButton1.setText("metodoTemporaneo()");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCategorieLayout = new javax.swing.GroupLayout(jPanelCategorie);
        jPanelCategorie.setLayout(jPanelCategorieLayout);
        jPanelCategorieLayout.setHorizontalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(jBtnAreaPrivata)
                .addContainerGap())
        );
        jPanelCategorieLayout.setVerticalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAreaPrivata)
                    .addComponent(jButton1))
                .addContainerGap())
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

    private void jComboBoxCategorieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCategorieItemStateChanged
        switch(jComboBoxCategorie.getSelectedIndex()) {
            case 0:
                System.out.println("b");
                break;
            case 1:
                System.out.println("a");
                break;
        }
    }//GEN-LAST:event_jComboBoxCategorieItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        meetle.getBacheca().metodoTemporaneo();
        meetle.salvaEventi();
        aggiornaEventi();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAreaPrivata;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxCategorie;
    private javax.swing.JPanel jPanelBacheca;
    private javax.swing.JPanel jPanelCategorie;
    private javax.swing.JScrollPane jScrollPaneBacheca;
    // End of variables declaration//GEN-END:variables
}
