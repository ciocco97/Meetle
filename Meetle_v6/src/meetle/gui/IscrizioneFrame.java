package meetle.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.campi.Campo;

public class IscrizioneFrame extends javax.swing.JFrame {
    private Evento evento;
    private final Meetle meetle;

    public IscrizioneFrame(Meetle meetle, int eID) {
        this.meetle = meetle;
        initComponents();
        setLocationRelativeTo(null);
        this.evento = this.meetle.getBacheca().getByID(eID);
        for(Campo campo: evento.getCampiSpesa()) {
            JCheckBox cb = new JCheckBox(campo.getNome());
            cb.setOpaque(false);
            cb.setForeground(Color.white);
            cb.setFont(jSpesePanel.getFont());
            jSpesePanel.add(cb);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTitoloLabel = new javax.swing.JLabel();
        jSpesePanel = new javax.swing.JPanel();
        jBottoniPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selezione spese incluse");
        setMinimumSize(new java.awt.Dimension(400, 60));
        setSize(new java.awt.Dimension(400, 60));

        jTitoloLabel.setBackground(new java.awt.Color(0, 110, 155));
        jTitoloLabel.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jTitoloLabel.setForeground(new java.awt.Color(255, 255, 255));
        jTitoloLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTitoloLabel.setText("Spese Incluse");
        jTitoloLabel.setMaximumSize(new java.awt.Dimension(10000, 10000));
        jTitoloLabel.setOpaque(true);
        jTitoloLabel.setPreferredSize(new java.awt.Dimension(400, 60));
        getContentPane().add(jTitoloLabel, java.awt.BorderLayout.PAGE_START);
        jTitoloLabel.getAccessibleContext().setAccessibleName("jTitolo");

        jSpesePanel.setBackground(new java.awt.Color(0, 150, 155));
        jSpesePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jSpesePanel.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        jSpesePanel.setMaximumSize(new java.awt.Dimension(400, 60));
        jSpesePanel.setMinimumSize(new java.awt.Dimension(400, 60));
        jSpesePanel.setPreferredSize(new java.awt.Dimension(400, 60));
        jSpesePanel.setLayout(new javax.swing.BoxLayout(jSpesePanel, javax.swing.BoxLayout.Y_AXIS));
        getContentPane().add(jSpesePanel, java.awt.BorderLayout.CENTER);

        jBottoniPanel.setBackground(new java.awt.Color(0, 150, 155));

        jButton1.setText("Iscriviti");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jBottoniPanelLayout = new javax.swing.GroupLayout(jBottoniPanel);
        jBottoniPanel.setLayout(jBottoniPanelLayout);
        jBottoniPanelLayout.setHorizontalGroup(
            jBottoniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBottoniPanelLayout.createSequentialGroup()
                .addContainerGap(302, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jBottoniPanelLayout.setVerticalGroup(
            jBottoniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jBottoniPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        getContentPane().add(jBottoniPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       evento.iscrivi(meetle.getUtenti().getByID(meetle.getUtenteLoggatoID()), codificaSpese());
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private String codificaSpese()
    {
        String spese = "";
        for (Component cb:jSpesePanel.getComponents())
        {
            JCheckBox box = (JCheckBox) cb;
            spese += box.isSelected()? "t" : "f";    
        }
        return spese;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jBottoniPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jSpesePanel;
    private javax.swing.JLabel jTitoloLabel;
    // End of variables declaration//GEN-END:variables
}
