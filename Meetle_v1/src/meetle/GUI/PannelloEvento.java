package meetle.GUI;

import java.awt.Color;
import java.util.Random;
import meetle.Evento;

public class PannelloEvento extends javax.swing.JPanel {
    private final String NP = "Numero partecipanti: ";
    private final String TD = "Termine ultimo d'iscrizione";
    private final String LG = "Luogo: ";
    private final String DT = "Data: ";
    private final String OA = "Ora: ";
    private final String DR = "Durata: ";
    private final String QI = "Quota individuale: ";
    private final String CQ = "Compreso nella quota: ";
    private final String DC = "Data conclusiva: ";
    private final String OC = "Ora conclusiva: ";
    private final String NT = "Note: ";

    private Evento evento;
    
    public PannelloEvento(Evento evento) {
        initComponents();
        this.evento = evento;
        
        if (evento.getTitolo() != null)
            jLbTitolo.setText(evento.getTitolo());
        else
            jLbTitolo.setText(evento.getNome());
        
        jLbNumPartecipanti.setText(NP + evento.getNumeroPartecipanti());
//        jLbTermineUltimoDIscrizione.setText(TD + evento.getTermineUltimoDIscrizione());
        jLbLuogo.setText(LG + evento.getLuogo());
//        jLbData.setText(DT + evento.getData());
//        jLbOra.setText(OA + evento.getOra());
        Random rand = new Random();
        this.setBackground(new Color(rand.nextInt(70)+180, rand.nextInt(70)+180, rand.nextInt(70)+180));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbTitolo = new javax.swing.JLabel();
        jLbNumPartecipanti = new javax.swing.JLabel();
        jLbTermineUltimoDIscrizione = new javax.swing.JLabel();
        jLbLuogo = new javax.swing.JLabel();
        jLbData = new javax.swing.JLabel();
        jLbOra = new javax.swing.JLabel();

        jLbTitolo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLbTitolo.setText("Partita di calcio");

        jLbNumPartecipanti.setText("Numero partecipanti");

        jLbTermineUltimoDIscrizione.setText("Termine ultimo d'iscrizione");

        jLbLuogo.setText("Luogo");

        jLbData.setText("Data");

        jLbOra.setText("Ora");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLbTermineUltimoDIscrizione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbTitolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbNumPartecipanti))
                    .addComponent(jLbData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbLuogo, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbOra, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbTitolo)
                    .addComponent(jLbNumPartecipanti))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbTermineUltimoDIscrizione)
                    .addComponent(jLbLuogo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbData)
                    .addComponent(jLbOra))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLbData;
    private javax.swing.JLabel jLbLuogo;
    private javax.swing.JLabel jLbNumPartecipanti;
    private javax.swing.JLabel jLbOra;
    private javax.swing.JLabel jLbTermineUltimoDIscrizione;
    private javax.swing.JLabel jLbTitolo;
    // End of variables declaration//GEN-END:variables
}
