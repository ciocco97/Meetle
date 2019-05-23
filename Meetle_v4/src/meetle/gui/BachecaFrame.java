package meetle.gui;

import java.awt.Component;
import javax.swing.JOptionPane;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.PartitaDiCalcio;

public class BachecaFrame extends javax.swing.JFrame {

    public BachecaFrame() {
        initComponents();        
        jBtnAreaPrivata.setText(Meetle.getIstanza().getUtenti().getUtenteDaID(Meetle.getIstanza().getUtenteLoggatoID()).getNomignoloPercheNicknameEraTroppoMainStream());
        jScrollPaneBacheca.getVerticalScrollBar().setUnitIncrement(20);
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
    
    public void aggiorna() {
        Component contenuti[] = jPanelBacheca.getComponents();
        for (Component componente : contenuti) {
            ((EventoPanel) componente).aggiorna();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jLabelTitolo = new javax.swing.JLabel();
        jComboBoxCategorie = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButtonAggiungiEvento = new javax.swing.JButton();
        jBtnAreaPrivata = new javax.swing.JButton();
        jScrollPaneBacheca = new javax.swing.JScrollPane();
        jPanelBacheca = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meetle");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("BachecaFrame"); // NOI18N
        setPreferredSize(getMinimumSize());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanelHeader.setBackground(new java.awt.Color(0, 115, 150));

        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setText("Meetle");

        jComboBoxCategorie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tutte le Categorie", "Partite di Calcio" }));
        jComboBoxCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategorieActionPerformed(evt);
            }
        });

        jButton1.setText("temporaneo()");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jLabelTitolo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelHeaderLayout.createSequentialGroup()
                        .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAggiungiEvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                        .addComponent(jBtnAreaPrivata)))
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitolo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAggiungiEvento)
                    .addComponent(jButton1)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Meetle.getIstanza().getBacheca().metodoTemporaneo(Meetle.getIstanza().getUtenteLoggatoID());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Meetle.getIstanza().salva();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAreaPrivata;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAggiungiEvento;
    private javax.swing.JComboBox<String> jComboBoxCategorie;
    private javax.swing.JLabel jLabelTitolo;
    private javax.swing.JPanel jPanelBacheca;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JScrollPane jScrollPaneBacheca;
    // End of variables declaration//GEN-END:variables
}
