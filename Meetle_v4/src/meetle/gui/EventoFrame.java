package meetle.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import meetle.Meetle;
import meetle.eventi.campi.Campo;

public class EventoFrame extends javax.swing.JFrame {
    public static final int CREA = 0, MODIFICA = 1, VISUALIZZA = 2;
        
    private int mode; // modalità d'uso
    private int eventoID;

    
    public EventoFrame(int eventoID, int mode) {
        initComponents();
        this.eventoID = eventoID;
        this.mode = mode;
        for(Campo campoT: Meetle.getIstanza().getBacheca().getByID(eventoID).getTuttiCampi()) // qualche altro metodo?
            jPanelCampi.add(new CampoPanel(campoT, mode!=VISUALIZZA));
        String titolo = Meetle.getIstanza().getBacheca().getByID(eventoID).getTitolo();
        if (titolo == null)
            jLabelTitolo.setText(mode == CREA ? "Nuovo Evento" : "evento senza nome");
        else
            jLabelTitolo.setText(!titolo.equals("") ? titolo : "evento senza nome");
        
        pack();
        setLocationRelativeTo(null);
        
        jButton1.setText("Salva");
        switch(mode){
            case CREA:
            case MODIFICA:jButton2.setText("Elimina");
                jButton2.addActionListener((ActionEvent e) -> Meetle.getIstanza().getBacheca().rimuoviByID(eventoID));
                jButton1.addActionListener((ActionEvent e) -> salvaEventoEChiudi());
                break;
            case VISUALIZZA:
                jButton1.setVisible(false);
                jButton2.setText("Chiudi");
        }
        
        jButton2.addActionListener((ActionEvent e) -> dispose());
        
//        Campo campi[] = ev.getTuttiCampi();
//        for (int i = 0; i<campi.length; i++){
//            JLabel label = new JLabel();
//            if (!array[i].toString().equals("")){
//            CampoPanel campoPanel = new CampoPanel(campi[i].getNome(), ""+campi[i].getValore(), true);
//            jCampiPanel.add(campoPanel);
//            }
//        }
        
    }
    
    public boolean salvaEventoEChiudi() {
        for(Component c: jPanelCampi.getComponents())
            if(!((CampoPanel)c).salvaValore()) {
                JOptionPane.showMessageDialog(this, "Errore compilazione campo");
                return false;
            }
        Meetle.getIstanza().getBacheca().getByID(eventoID).aggiornaStato();
        dispose();
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitolo = new javax.swing.JLabel();
        jPanelCampi = new javax.swing.JPanel();
        jPanelBottoni = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Evento");
        setMinimumSize(new java.awt.Dimension(400, 400));

        jLabelTitolo.setBackground(new java.awt.Color(0, 115, 150));
        jLabelTitolo.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabelTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitolo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitolo.setText("EventoFrame");
        jLabelTitolo.setOpaque(true);
        getContentPane().add(jLabelTitolo, java.awt.BorderLayout.NORTH);

        jPanelCampi.setBackground(new java.awt.Color(0, 148, 157));
        jPanelCampi.setLayout(new javax.swing.BoxLayout(jPanelCampi, javax.swing.BoxLayout.Y_AXIS));
        getContentPane().add(jPanelCampi, java.awt.BorderLayout.CENTER);

        jPanelBottoni.setBackground(new java.awt.Color(0, 115, 150));

        jButton2.setText("jButton2");

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanelBottoniLayout = new javax.swing.GroupLayout(jPanelBottoni);
        jPanelBottoni.setLayout(jPanelBottoniLayout);
        jPanelBottoniLayout.setHorizontalGroup(
            jPanelBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBottoniLayout.createSequentialGroup()
                .addContainerGap(278, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanelBottoniLayout.setVerticalGroup(
            jPanelBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBottoniLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        getContentPane().add(jPanelBottoni, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabelTitolo;
    private javax.swing.JPanel jPanelBottoni;
    private javax.swing.JPanel jPanelCampi;
    // End of variables declaration//GEN-END:variables
}
