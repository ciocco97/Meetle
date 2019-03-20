package meetle.GUI;

import meetle.eventi.*;

public class InterfacciaCMD extends javax.swing.JFrame {

    private Bacheca bacheca;
    
    public InterfacciaCMD(Bacheca bacheca) {
        initComponents();
        
        this.bacheca = bacheca;
        mostraEventi();       
    }
    
    
    public void mostra(){ 
        java.awt.EventQueue.invokeLater(() -> { setVisible(true); }); 
    }
    
    public void mostraEventi(){
        String tuttiEventi = "Lista di tutti gli eventi:\n\n";
        for(int i=0; i < bacheca.getEventi().size(); i++)
            tuttiEventi += (i+1) +") "+ bacheca.getEventi().get(i).toString() +"\n\n";
        jTextArea.setText(tuttiEventi);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jTextFieldInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setResizable(false);

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
        if(evt.getKeyChar() == '\n'){
            jTextFieldInput.setText("");
        }
    }//GEN-LAST:event_jTextFieldInputKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldInput;
    // End of variables declaration//GEN-END:variables
}
