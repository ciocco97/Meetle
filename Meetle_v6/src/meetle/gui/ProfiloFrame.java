package meetle.gui;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import meetle.Meetle;
import meetle.eventi.GoKarts;
import meetle.eventi.PartitaDiCalcio;
import meetle.utenti.Utente;

public class ProfiloFrame extends javax.swing.JFrame {

    private final String uID;
    
    private final Meetle meetle;
    
    public ProfiloFrame(Meetle meetle) {
        this.meetle = meetle;
        initComponents();
        uID = this.meetle.getUtenteLoggatoID();
        
        Utente u = getUtente();        
        jLabelUserID.setText(uID);
        jTextFieldEta.setText(""+(u.getEta()>0 ? u.getEta():""));
        
        if(u.getCategoriePreferite()!=null) {
            if(u.getCategoriePreferite().contains(PartitaDiCalcio.NOME))
                jCheckBoxPartite.setSelected(true);
            if (u.getCategoriePreferite().contains(GoKarts.NOME))
                jCheckBoxGoKarts.setSelected(true);
        }

        pack();
        
    }
    
    private Utente getUtente() {
        return this.meetle.getUtenti().getByID(uID);
    }
    
//    private void aggiornaValori() {
//        Utente u = this.meetle.getUtenti().getByID(uID);
//        
//        jLabelUserID.setText(uID);
//        if(u.getNomignoloPercheNicknameEraTroppoMainStream() == null)
//            setNickname();
//        jTextFieldNick.setText(u.getNomignoloPercheNicknameEraTroppoMainStream()==null? "" : u.getNomignoloPercheNicknameEraTroppoMainStream());
//        jTextFieldFascia.setText(u.getFasciaEtaVals());
//        
//        pack();
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelUserID = new javax.swing.JLabel();
        jLabelFasciaEta = new javax.swing.JLabel();
        jLabelCategorie = new javax.swing.JLabel();
        jCheckBoxTutti = new javax.swing.JCheckBox();
        jCheckBoxPartite = new javax.swing.JCheckBox();
        jButtonSalva = new javax.swing.JButton();
        jTextFieldEta = new javax.swing.JTextField();
        jCheckBoxGoKarts = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configura il tuo Profilo");
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(0, 110, 155));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabelUserID.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabelUserID.setForeground(jPanel1.getForeground());
        jLabelUserID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUserID.setText("userID");
        jLabelUserID.setMinimumSize(new java.awt.Dimension(300, 45));

        jLabelFasciaEta.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabelFasciaEta.setForeground(jPanel1.getForeground());
        jLabelFasciaEta.setText("La tua età:");
        jLabelFasciaEta.setToolTipText("");

        jLabelCategorie.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabelCategorie.setForeground(jPanel1.getForeground());
        jLabelCategorie.setText("Le categorie che ti interessano:");
        jLabelCategorie.setToolTipText("Riceverai una notifica per ogni nuovo evento aperto di una delle categorie selezionate");

        jCheckBoxTutti.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        jCheckBoxTutti.setForeground(jPanel1.getForeground());
        jCheckBoxTutti.setText("Seleziona tutti");
        jCheckBoxTutti.setOpaque(false);
        jCheckBoxTutti.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxTuttiItemStateChanged(evt);
            }
        });

        jCheckBoxPartite.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        jCheckBoxPartite.setForeground(jPanel1.getForeground());
        jCheckBoxPartite.setText("Partite di calcio");
        jCheckBoxPartite.setOpaque(false);

        jButtonSalva.setText("Salva");
        jButtonSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvaActionPerformed(evt);
            }
        });

        jTextFieldEta.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jTextFieldEta.setToolTipText("Età massima");
        jTextFieldEta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextFieldEta.setPreferredSize(new java.awt.Dimension(30, 20));

        jCheckBoxGoKarts.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        jCheckBoxGoKarts.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxGoKarts.setText("Go Karts");
        jCheckBoxGoKarts.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUserID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelFasciaEta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldEta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalva))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxPartite)
                                    .addComponent(jCheckBoxTutti)
                                    .addComponent(jCheckBoxGoKarts)))
                            .addComponent(jLabelCategorie))
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFasciaEta)
                    .addComponent(jTextFieldEta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelCategorie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxTutti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxPartite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxGoKarts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalva)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvaActionPerformed

        if(!jTextFieldEta.getText().equals(""))
        try {
            int eta = Integer.parseInt(jTextFieldEta.getText());
            getUtente().setEta(eta);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore nell'inserimento dell'età");
            return;
        }

        ArrayList<String> categoriePreferite = new ArrayList<>();
        if(jCheckBoxPartite.isSelected())
            categoriePreferite.add(PartitaDiCalcio.NOME);
        if(jCheckBoxGoKarts.isSelected())
            categoriePreferite.add(GoKarts.NOME);
        getUtente().setCategoriePreferite(categoriePreferite);

        synchronized(this.meetle) {
            this.meetle.notify();
        }

        dispose();
    }//GEN-LAST:event_jButtonSalvaActionPerformed

    private void jCheckBoxTuttiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxTuttiItemStateChanged
        jCheckBoxPartite.setSelected(jCheckBoxTutti.isSelected());
        jCheckBoxGoKarts.setSelected(jCheckBoxTutti.isSelected());
    }//GEN-LAST:event_jCheckBoxTuttiItemStateChanged

//    private void setNickname() {
//        String tNick = null; 
//        while (tNick  == null || tNick.equals(""))
//            tNick = JOptionPane.showInputDialog("Inserisci il tuo nickname (obbligatorio)");
//        
//        this.meetle.getUtenti().getByID(uID).setNickname(tNick);
//    }
//    
//    public static void main(String[] args) {
//        new ProfiloFrame().setVisible(true);
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalva;
    private javax.swing.JCheckBox jCheckBoxGoKarts;
    private javax.swing.JCheckBox jCheckBoxPartite;
    private javax.swing.JCheckBox jCheckBoxTutti;
    private javax.swing.JLabel jLabelCategorie;
    private javax.swing.JLabel jLabelFasciaEta;
    private javax.swing.JLabel jLabelUserID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldEta;
    // End of variables declaration//GEN-END:variables
}