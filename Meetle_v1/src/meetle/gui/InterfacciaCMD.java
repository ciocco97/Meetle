package meetle.gui;

import java.util.List;
import meetle.Meetle;

public class InterfacciaCMD extends javax.swing.JFrame {
    private int stato;
    
    private final String TITOLO_MENU_PRINCIPALE = "---MENU PRINCIPALE---";
    private final String SCELTE_MENU_PRINCIPALE = "1)Visualizza eventi presenti\n2)Esci da Meetle";
    
    private final String TITOLO_MENU_EVENTI = "---EVENTI---";
    private final String SCELTE_MENU_EVENTI = "1)Torna al menù principale\n2)Esci da Meetle";
    
    private final String ETICHETTA_SCELTE = "Scelte disponibili per questo menù:";
    
    private final String ERRORE_COMANDO = "SCELTA IMMESSA NON VALIDA";
    
    private Meetle meetle;
    
    public InterfacciaCMD(Meetle meetle) {
        initComponents();
        this.meetle = meetle;
        menuPrincipale();
    }
    
    public void mostraEventi() {   
        stato = 1;
        jTextArea.setText(TITOLO_MENU_EVENTI);
        List<List<NomeValore>> datiEventi = meetle.getDatiEventi();
        String eventiToString = "\nEventi disponibili:\n";
        for(List<NomeValore> datiEvento: datiEventi) {
            for(NomeValore nv: datiEvento)
                eventiToString += "\t"+ nv +"\n";
            eventiToString += "\n";
        }
        jTextArea.append(eventiToString); 
        jTextArea.append("\n"+ETICHETTA_SCELTE+"\n"); 
        jTextArea.append(SCELTE_MENU_EVENTI);
        
    }
    
    private void menuPrincipale()
    {
        stato = 0;
        jTextArea.setText(TITOLO_MENU_PRINCIPALE);
        jTextArea.append("\n"+ETICHETTA_SCELTE+"\n");
        jTextArea.append(SCELTE_MENU_PRINCIPALE);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jTextFieldInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setPreferredSize(new java.awt.Dimension(800, 600));

        jScrollPane1.setBorder(null);

        jTextArea.setEditable(false);
        jTextArea.setBackground(new java.awt.Color(0, 32, 64));
        jTextArea.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jTextArea.setForeground(new java.awt.Color(240, 240, 240));
        jTextArea.setLineWrap(true);
        jTextArea.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        jTextArea.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTextFieldInput.setBackground(jTextArea.getBackground());
        jTextFieldInput.setFont(jTextArea.getFont());
        jTextFieldInput.setForeground(jTextArea.getForeground());
        jTextFieldInput.setBorder(jTextArea.getBorder());
        jTextFieldInput.setCaretColor(jTextArea.getForeground());
        jTextFieldInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldInputKeyPressed(evt);
            }
        });
        getContentPane().add(jTextFieldInput, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldInputKeyPressed
        if(evt.getKeyChar() == '\n') {
            String comando = jTextFieldInput.getText();
            switch (stato)
            {
                case 0: //MENU PRINCIPALE
                    switch (comando)
                    {
                        case "1": mostraEventi(); break;
                        case "2": System.exit(0); 
                        default: jTextArea.append("\n"+ERRORE_COMANDO); 
                    }break;
                case 1: //MENU EVENTI
                    switch (comando)
                    {
                        case "1": menuPrincipale(); break;
                        case "2": System.exit(0);
                        default: jTextArea.append("\n"+ERRORE_COMANDO); 
                    }
            }
        }
        jTextFieldInput.setText("");
    }//GEN-LAST:event_jTextFieldInputKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldInput;
    // End of variables declaration//GEN-END:variables
}
