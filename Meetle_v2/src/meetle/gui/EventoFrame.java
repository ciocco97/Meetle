package meetle.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.campi.Campo;

public class EventoFrame extends javax.swing.JFrame {
    public static final int CREA = 0, MODIFICA = 1, VISUALIZZA = 2;
    
    private Meetle meetle;
    
    private int mode; // modalità d'uso
    private Evento evento;

    
    public EventoFrame(Meetle meetle, Evento evento, int mode) {
        initComponents();
        this.meetle = meetle;
        this.evento = evento;
        this.mode = mode;
        
        for(Campo campoT: evento.getTuttiCampi()) 
            jCampiPanel.add(new CampoPanel(campoT, true));
        
        
        switch(mode){
            case CREA:
            case MODIFICA:
                jButton1.addActionListener((ActionEvent e) -> salvaEvento());
                break;
            case VISUALIZZA:
                jButton1.setVisible(false);
                
        }
        
        jButton2.setText("Chiudi");
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
    
    public boolean salvaEvento() {
        for(Component c: jCampiPanel.getComponents())
            if(c instanceof CampoPanel && !((CampoPanel) c).checkValido()) {
                JOptionPane.showMessageDialog(this, "Errore inserimento campi");
                return false;
            } else {
                ((CampoPanel) c).salvaValore();
            }
        meetle.salvaEventi();
        dispose();
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titoloLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jCampiPanel = new javax.swing.JPanel();
        jPanelBottoni = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        titoloLabel.setBackground(new java.awt.Color(0, 115, 150));
        titoloLabel.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        titoloLabel.setForeground(new java.awt.Color(255, 255, 255));
        titoloLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titoloLabel.setText("EventoFrame");
        titoloLabel.setOpaque(true);
        getContentPane().add(titoloLabel, java.awt.BorderLayout.NORTH);
        titoloLabel.getAccessibleContext().setAccessibleName("titolo");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 300));

        jCampiPanel.setBackground(new java.awt.Color(0, 148, 157));
        jCampiPanel.setLayout(new javax.swing.BoxLayout(jCampiPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jCampiPanel);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelBottoni.setBackground(new java.awt.Color(0, 115, 150));

        jButton2.setText("jButton2");

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanelBottoniLayout = new javax.swing.GroupLayout(jPanelBottoni);
        jPanelBottoni.setLayout(jPanelBottoniLayout);
        jPanelBottoniLayout.setHorizontalGroup(
            jPanelBottoniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBottoniLayout.createSequentialGroup()
                .addContainerGap(326, Short.MAX_VALUE)
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

        getContentPane().add(jPanelBottoni, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jCampiPanel;
    private javax.swing.JPanel jPanelBottoni;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel titoloLabel;
    // End of variables declaration//GEN-END:variables
}
