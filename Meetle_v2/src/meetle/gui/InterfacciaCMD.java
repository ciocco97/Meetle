package meetle.gui;

import meetle.Meetle;
import meetle.eventi.PartitaDiCalcio;

public class InterfacciaCMD extends javax.swing.JFrame {
    
    private static final String ERRORE_COMANDO = "SCELTA IMMESSA NON VALIDA\n";
    
    private Meetle meetle;
    private int stato = 0; // stato del menu, leggere dalle stringhe qui sotto per sapere i vari menu
    private final String[] titoliMenu = {"MENU PRINCIPALE", "SCEGLI CATEGORIA", "SCEGLI CATEGORIA"};
    private final String[][] vociMenu = {
        {"Mostra categorie", "Mostra eventi", "Mostra eventi per categoria", "Aggiungi Evento"},
        {PartitaDiCalcio.NOME},
        {PartitaDiCalcio.NOME}
    };
    
    // Attributi per aggiungi evento
    String[] campi; int indice;
    
    public InterfacciaCMD(Meetle meetle) {
        initComponents();
        this.meetle = meetle;
        stampaMenu();
    }
    
    private void println(String s) {
        jTextArea.append(s+'\n');
    }
    
    private void stampaMenu() {
        println("----"+titoliMenu[stato]+"----");
        for(int i=0; i<vociMenu[stato].length; i++ )
            println((i+1)+") "+vociMenu[stato][i]);
        println(vociMenu[stato].length+1 +") Esci");
    }
    
    private int indexOf(Object a, Object[] list) {
        for(int i = 0; i < list.length; i++)
            if(list[i].equals(a)) return i;
        return -1;
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
        jTextArea.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
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
            jTextArea.setText("");
            int comando = Integer.parseInt(jTextFieldInput.getText());
            if(comando == vociMenu[stato].length+1) System.exit(0);
            else if (comando > vociMenu[stato].length+1) println(ERRORE_COMANDO);
            switch (stato) {
                case 0: //MENU PRINCIPALE
                    switch(comando) {
                        case 1:
                            println("----LISTA DESCRIZIONE CATEGORIE----");
                            println(meetle.getDescrizioneCategorie());
                            break;
                        case 2:
                            println("----LISTA EVENTI----");
                            println(meetle.stampaBacheca());  
                            break;
                        case 3:
                            stato = 1;
                            break; 
                        case 4:
                            stato = 2;
                            break;
                    }
                    break;
                    
                case 1: // Mostra eventi per categoria
                    println("----LISTA EVENTI----");
                    println(meetle.stampaBacheca(vociMenu[stato][comando-1]));
                    stato = 0;
                    break;
                case 2: // Aggiungi evento
                    if((comando - 1) == indexOf(PartitaDiCalcio.NOME, vociMenu[stato])) {
                        println("----NUOVA PARTITA DI CALCIO----");
                        campi = meetle.istanziaEvento(PartitaDiCalcio.NOME);
                    }
                    indice = 0;
                    println("Invio per iniziare");
                    stato = 3;
                    break;
                case 3: //Aggiungi campo
                    if(indice >= campi.length) {
                        meetle.salvaEvento();
                        campi = null; indice = 0;
                        println("Evento creato");
                        stato = 0;
                    } else {;
                    }
            }
            stampaMenu();
            jTextFieldInput.setText("");
        }
    }//GEN-LAST:event_jTextFieldInputKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldInput;
    // End of variables declaration//GEN-END:variables

    
}
