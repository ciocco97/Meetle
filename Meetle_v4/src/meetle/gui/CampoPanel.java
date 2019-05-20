package meetle.gui;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoData;

public class CampoPanel extends javax.swing.JPanel {
    
    private final Campo campo;
    
    private static final int M_TESTO=0, M_DATA=1;
    private int modo;
    
    
    public CampoPanel(Campo campo, boolean godmode) {
        initComponents();
        this.campo = campo;
        
        nomeLabel.setText(campo.getNome());
        if(!campo.isFacoltativo()) 
            nomeLabel.setText(nomeLabel.getText()+"*");
        
        if(campo instanceof CampoData)
            modo = M_DATA;
        else 
            modo = M_TESTO;
            
        switch (modo) {
            case M_DATA:
                if (campo.getValore() != null)
                    jDateChooser.setDate(Date.from(((LocalDate)campo.getValore()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                jDateChooser.setEnabled(godmode);
                add(jDateChooser);
                break;
            default:
                if (campo.getNome() == "Titolo"){
                    valoreField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) { 
                            if (valoreField.getText().length() >= 18 ) // limit textfield to 3 characters
                                e.consume(); 
                        }
                    });
                }
                valoreField.setText(campo.getValore()==null ? "" : campo.getValore().toString());
                valoreField.setEditable(godmode);
                add(valoreField);
        }
    }
    
    public boolean checkCompilato() {
        if (campo.isFacoltativo()) return true;
        switch (modo) {
            case M_DATA:
                return jDateChooser.getDate() != null;
            default:
                return !valoreField.getText().equals("");
        }
    }
    
    public boolean salvaValore() {
        try {
            switch (modo) {
                case M_DATA:
                    if(jDateChooser.getDate()!=null)
                        campo.setValoreDaString(jDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
                    else
                        campo.svuota();
                    break;
                default:
                    if(!valoreField.getText().equals(""))
                        campo.setValoreDaString(valoreField.getText());
                    else 
                        campo.svuota();
                    
            }
        } catch (Exception ex) { 
            System.out.println("Errore compilazione "+campo.getNome()+"\n"+ex.getMessage());
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valoreField = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        nomeLabel = new javax.swing.JLabel();

        valoreField.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        valoreField.setForeground(new java.awt.Color(30, 98, 255));
        valoreField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        valoreField.setText("valore");
        valoreField.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser.setForeground(new java.awt.Color(30, 98, 255));
        jDateChooser.setToolTipText("");
        jDateChooser.setDateFormatString("yyyy-MM-dd");
        jDateChooser.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N

        setBackground(new java.awt.Color(0, 150, 155));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setLayout(new java.awt.GridLayout(1, 0));

        nomeLabel.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        nomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        nomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nomeLabel.setText("nome");
        nomeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(nomeLabel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField valoreField;
    // End of variables declaration//GEN-END:variables
}
