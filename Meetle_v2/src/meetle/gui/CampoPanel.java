package meetle.gui;

import java.awt.Color;
import meetle.eventi.campi.Campo;

public class CampoPanel extends javax.swing.JPanel {
    
    private final Campo campo;
    
    public CampoPanel(Campo campo, boolean godmode) {
        initComponents();
        
        this.campo = campo;
        nomeLabel.setText(campo.getNome());
        valoreField.setText(campo.getValore()==null ? "" : campo.getValore().toString());
        valoreField.setEditable(godmode);
        if(!campo.isFacoltativo()) nomeLabel.setText(nomeLabel.getText()+"*");
    }
    
    public boolean checkCompilato() {
        return campo.isFacoltativo() || !valoreField.getText().equals("");
    }
    
    public boolean salvaValore() {
        try {
            if(!valoreField.getText().equals(""))
                campo.setValoreDaString(valoreField.getText());
        } catch (Exception ex) { 
            System.out.println("Errore compilazione "+campo.getNome()+"\n"+ex.getMessage());
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeLabel = new javax.swing.JLabel();
        valoreField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 150, 155));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setLayout(new java.awt.GridLayout());

        nomeLabel.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        nomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        nomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeLabel.setText("nome");
        nomeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(nomeLabel);

        valoreField.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        valoreField.setForeground(new java.awt.Color(30, 98, 255));
        valoreField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        valoreField.setText("valore");
        valoreField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(valoreField);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField valoreField;
    // End of variables declaration//GEN-END:variables
}
