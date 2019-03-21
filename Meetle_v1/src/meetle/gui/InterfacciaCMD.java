package meetle.gui;

import java.util.List;
import meetle.Meetle;

public class InterfacciaCMD extends javax.swing.JFrame {

    private Meetle meetle;
    
    public InterfacciaCMD(Meetle meetle) {
        initComponents();
        
        this.meetle = meetle;
    }
    
    public void mostraEventi() {        
        List<List<NomeValore>> datiEventi = meetle.getDatiEventi();
        
        String eventiToString = "Lista di tutti gli eventi:\n\n";
        for(List<NomeValore> datiEvento: datiEventi) {
            for(NomeValore nv: datiEvento)
                eventiToString += nv +"\t";
            eventiToString += "\n";
        }
        jTextArea.setText(eventiToString);  
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jTextFieldInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jTextArea.setLineWrap(true);
        jTextArea.setRows(5);
        jTextArea.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTextFieldInput.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
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
            switch(comando){
                case "ls":
                    mostraEventi();
                    break;
                    
            }
            jTextFieldInput.setText("");
        }
    }//GEN-LAST:event_jTextFieldInputKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldInput;
    // End of variables declaration//GEN-END:variables
}
