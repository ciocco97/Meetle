/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetle.gui;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import meetle.Meetle;
import meetle.utenti.Notifica;

/**
 *
 * @author Alessandro
 */
public class NotificaPanel extends javax.swing.JPanel {
    private int IDEvento;
    private int IDNotifica;

   public NotificaPanel(Notifica notifica) {
        initComponents();
        IDEvento = notifica.getEventoID();
        IDNotifica = notifica.getID();
        jLabelTitolo.setText(notifica.getTitolo());
        jLabelTestoNotifica.setText(notifica.getMessaggio());
        jLabelOrario.setText(" "+ notifica.getDataora().format(DateTimeFormatter.ofPattern("dd-MM-yyyy   hh:mm")));
        if(notifica.isVisualizzata()) {
//            jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 20));
            jButtonSegnaLetto.setText("visualizzata");
            jButtonSegnaLetto.setEnabled(false);
        } else { jButtonSegnaLetto.setText("Segna come già letto"); }
        String IDUtenteLoggato = Meetle.getIstanza().getUtenteLoggatoID();
        if(notifica.isInvito()) {
            jButtonAccettaInvito.setVisible(true);
            if (!Meetle.getIstanza().getBacheca().getByID(IDEvento).isUtenteIscritto(IDUtenteLoggato))
                jButtonAccettaInvito.setEnabled(true);
            else {
                jButtonAccettaInvito.setEnabled(false);
                if(Meetle.getIstanza().getBacheca().getByID(IDEvento).isIscrivibile())
                    jButtonAccettaInvito.setText("Sei già iscritto all'evento");
                else
                    jButtonAccettaInvito.setText("Invito non più disponibile");
            }
        } else jButtonAccettaInvito.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitolo = new javax.swing.JLabel();
        jLabelOrario = new javax.swing.JLabel();
        jButtonElimina = new javax.swing.JButton();
        jButtonSegnaLetto = new javax.swing.JButton();
        jButtonVaiEvento = new javax.swing.JButton();
        jButtonAccettaInvito = new javax.swing.JButton();
        jLabelTestoNotifica = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 150, 155));

        jLabelTitolo.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setText("titolo evento");

        jLabelOrario.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOrario.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        jLabelOrario.setForeground(new java.awt.Color(240, 240, 240));
        jLabelOrario.setText("Data e ora");

        jButtonElimina.setText("Elimina");
        jButtonElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminaActionPerformed(evt);
            }
        });

        jButtonSegnaLetto.setText("Segna come già letto");
        jButtonSegnaLetto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSegnaLettoActionPerformed(evt);
            }
        });

        jButtonVaiEvento.setText("Vedi evento");
        jButtonVaiEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVaiEventoActionPerformed(evt);
            }
        });

        jButtonAccettaInvito.setText("Accetta invito");
        jButtonAccettaInvito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccettaInvitoActionPerformed(evt);
            }
        });

        jLabelTestoNotifica.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabelTestoNotifica.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTestoNotifica.setText("Testo notifica");
        jLabelTestoNotifica.setPreferredSize(new java.awt.Dimension(400, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAccettaInvito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonVaiEvento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSegnaLetto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonElimina))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTestoNotifica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelOrario)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTitolo)
                    .addComponent(jButtonElimina)
                    .addComponent(jButtonSegnaLetto)
                    .addComponent(jButtonVaiEvento)
                    .addComponent(jButtonAccettaInvito))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOrario)
                    .addComponent(jLabelTestoNotifica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVaiEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVaiEventoActionPerformed
        java.awt.EventQueue.invokeLater(() -> { new EventoFrame(IDEvento, EventoFrame.VISUALIZZA).setVisible(true); });
    }//GEN-LAST:event_jButtonVaiEventoActionPerformed

    private void jButtonSegnaLettoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSegnaLettoActionPerformed
        Meetle.getIstanza().setNotificaLetta(Meetle.getIstanza().getUtenteLoggatoID(), IDNotifica);
//        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 20));
        jLabelTitolo.setForeground(Color.GRAY);
        jButtonSegnaLetto.setText("visualizzata");
        jButtonSegnaLetto.setEnabled(false);
    }//GEN-LAST:event_jButtonSegnaLettoActionPerformed

    private void jButtonEliminaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminaActionPerformed
        Meetle.getIstanza().rimuoviNotifica(Meetle.getIstanza().getUtenteLoggatoID(), IDNotifica);
        removeAll();
        setVisible(false);
    }//GEN-LAST:event_jButtonEliminaActionPerformed

    private void jButtonAccettaInvitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccettaInvitoActionPerformed
        Meetle.getIstanza().getBacheca().getByID(IDEvento).switchIscrizione(Meetle.getIstanza().getUtenteLoggatoID());
        jButtonAccettaInvito.setEnabled(false);
        jButtonAccettaInvito.setText("Invito accettato");
    }//GEN-LAST:event_jButtonAccettaInvitoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAccettaInvito;
    private javax.swing.JButton jButtonElimina;
    private javax.swing.JButton jButtonSegnaLetto;
    private javax.swing.JButton jButtonVaiEvento;
    private javax.swing.JLabel jLabelOrario;
    private javax.swing.JLabel jLabelTestoNotifica;
    private javax.swing.JLabel jLabelTitolo;
    // End of variables declaration//GEN-END:variables
}
