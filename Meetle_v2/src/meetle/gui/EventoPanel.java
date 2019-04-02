package meetle.gui;

import java.awt.Color;
import java.util.Random;
import meetle.eventi.Evento;

public class EventoPanel extends javax.swing.JPanel {

    private boolean godMode;
    
    private final int eventoID;
    
    public EventoPanel(Evento evento, boolean godMode) {
        initComponents();
        eventoID = evento.getID();
        this.godMode = godMode;
        jLbTitolo.setText((String)evento.getCampi()[Evento.I_TITOLO].getValore());
        jLbNumPartecipanti.setText(evento.getNumIscritti()+"/"+evento.getCampi()[Evento.I_NUM_PARTECIPANTI].getValore()+" iscritti");
        String info = "" + evento.getCampi()[Evento.I_LUOGO].getValore();
        info += ", il " + evento.getCampi()[Evento.I_DATA].getValore();
        info += " alle " + evento.getCampi()[Evento.I_ORA].getValore();
        jLbInformazioni.setText(info);
        
        if(godMode) { // modalita modifica dal creatore
            jButton1.setText("Elimina");
            jButton2.setText("Modifica");
            jButton3.setText("make open");
        } else { // modalita base
            jButton1.setText("Visualizza");
            if(evento.isIscritto("ciao")) {
                jButton2.setText("Iscriviti");                     
            } else {           
                jButton2.setText("Disiscriviti");  
            }
            jButton3.setVisible(false);
        }
//        Random rand = new Random();
//        this.setBackground(new Color(rand.nextInt(70)+180, rand.nextInt(70)+180, rand.nextInt(70)+180));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbTitolo = new javax.swing.JLabel();
        jLbNumPartecipanti = new javax.swing.JLabel();
        jLbInformazioni = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(60, 75));

        jLbTitolo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLbTitolo.setText("Titolo");

        jLbNumPartecipanti.setText("N iscritti");

        jLbInformazioni.setText("Data, Ora, Luogo");

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbTitolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbNumPartecipanti)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbInformazioni)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbTitolo)
                    .addComponent(jLbNumPartecipanti)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbInformazioni)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLbInformazioni;
    private javax.swing.JLabel jLbNumPartecipanti;
    private javax.swing.JLabel jLbTitolo;
    // End of variables declaration//GEN-END:variables
}
