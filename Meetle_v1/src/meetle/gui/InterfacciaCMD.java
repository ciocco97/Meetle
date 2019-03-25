package meetle.gui;

import meetle.Meetle;
import meetle.eventi.PartitaDiCalcio;

public class InterfacciaCMD extends javax.swing.JFrame {
    private int stato = 0;
    
    private final String ERRORE_COMANDO = "SCELTA IMMESSA NON VALIDA\n";
    
    private Meetle meetle;
    String[] titoliMenu = {"MENU PRINCIPALE", "MENU CATEGORIE", "MENU EVENTI"};
    String[][] vociMenu = {
        {"Visualizza categorie", "Visualizza eventi", "Visualizza evento per categoria"},
        {"Partita di Calcio"},
        {"Torna a MENU PRINCIPALE"}};
    
    public InterfacciaCMD(Meetle meetle) {
        initComponents();
        this.meetle = meetle;
        stampaMenu();
    }
    
    public void println(String s) {
        jTextArea.append(s+'\n');
    }
    
    public void stampaMenu() {
        println("----"+titoliMenu[stato]+"----");
        for(int i=1; i<=vociMenu[stato].length; i++ )
            println(i+") "+vociMenu[stato][i-1]);
        println(vociMenu[stato].length+1 +") Esci");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jTextFieldInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meetle");
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
            int comando = Integer.parseInt(jTextFieldInput.getText());
            jTextArea.setText("");
            switch (stato) {
                case 0: //MENU PRINCIPALE
                    switch(comando) {
                        case 1:
                            println("----LISTA DESCRIZIONE CATEGORIE----");
                            println(new PartitaDiCalcio().toDescrizione());
                            break;
                        case 2:
                            println("----LISTA EVENTI----");
                            println(meetle.stampaBacheca()); 
                            break;
                        case 3:
                            stato = 1;
                            break;
                        case 4:
                            System.exit(0);
                        default:
                            println(ERRORE_COMANDO);   
                            
                    }
                    break;
                    
                case 1: 
                    if(comando == vociMenu[stato].length+1) System.exit(0);
                    println("----LISTA EVENTI----");
                    println(meetle.stampaBacheca(vociMenu[stato][comando-1]));
                    stato = 0;
                    break;                 
            }
            stampaMenu();
        }
        jTextFieldInput.setText("");
    }//GEN-LAST:event_jTextFieldInputKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldInput;
    // End of variables declaration//GEN-END:variables

    
}
