package meetle.GUI;

import meetle.PartitaDiCalcio;
import meetle.campi.*;

public class MainFinestra extends javax.swing.JFrame {

    /**
     * Creates new form MainFinestra
     */
    public MainFinestra() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void aggiornaBacheca(int tab)
    {
        PartitaDiCalcio Eil = new PartitaDiCalcio(new CampoGenere(1), new CampoRange(10,15), "partita bimbe nude", "astenersi perditempo", new CampoString("PBN"), new CampoString("Brescia"), new CampoString("tutti hanno un debole per le bimbe"),new CampoInt(50000),new CampoInt(1),new CampoInt(20),new CampoData(2018, 02, 15), new CampoData (2018, 02, 18), new CampoData(2019,02,04),new CampoOra(12,45), new CampoOra(22,30), new CampoDurata(100,100,100));
        PannelloEventoSingolo p = new PannelloEventoSingolo(Eil);
        jPanelEventi.add(p);
        jPanelEventi.updateUI();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanelCategorie = new javax.swing.JPanel();
        jButtonPartitaCalcio = new javax.swing.JButton();
        jScrollPaneEventi = new javax.swing.JScrollPane();
        jPanelEventi = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        jButtonPartitaCalcio.setText("Partita di Calcio");
        jButtonPartitaCalcio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPartitaCalcioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCategorieLayout = new javax.swing.GroupLayout(jPanelCategorie);
        jPanelCategorie.setLayout(jPanelCategorieLayout);
        jPanelCategorieLayout.setHorizontalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPartitaCalcio)
                .addContainerGap(639, Short.MAX_VALUE))
        );
        jPanelCategorieLayout.setVerticalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jButtonPartitaCalcio)
                .addContainerGap())
        );

        getContentPane().add(jPanelCategorie, java.awt.BorderLayout.PAGE_START);

        jScrollPaneEventi.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelEventi.setLayout(new javax.swing.BoxLayout(jPanelEventi, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneEventi.setViewportView(jPanelEventi);

        getContentPane().add(jScrollPaneEventi, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPartitaCalcioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPartitaCalcioActionPerformed
        aggiornaBacheca(7);
    }//GEN-LAST:event_jButtonPartitaCalcioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFinestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFinestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFinestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFinestra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFinestra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPartitaCalcio;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCategorie;
    private javax.swing.JPanel jPanelEventi;
    private javax.swing.JScrollPane jScrollPaneEventi;
    // End of variables declaration//GEN-END:variables
}
