package meetle.gui;

import java.awt.event.ActionEvent;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.Stato;

public class EventoPanel extends javax.swing.JPanel {
    
    private final Meetle meetle;

    private final int eventoID; // ID dell'evento di riferimento
    private final boolean godMode; // true se siamo in modalità modifica
    
    
    public EventoPanel(int eID, boolean godMode, Meetle meetle) {
        
        initComponents();
        this.eventoID = eID;
        this.godMode = godMode;
        this.meetle = meetle;
        
        
        aggiorna();
        setListeners();
    }   
    
    /**
     * aggiorna testo e visibilità di tutti i campi e dei pulsanti
     * @param ev 
     */
    private void aggiorna() { 
        
        Evento ev = meetle.getBacheca().getByID(eventoID);
        if(ev==null) { removeAll(); setVisible(false); return; }
        
        jLbTitolo.setText((String)ev.getCampi()[Evento.I_TITOLO].getValore());
        jLbNumPartecipanti.setText("iscritti: "+ev.getNumIscritti() +"/"+ ev.getCampi()[Evento.I_NUM_PARTECIPANTI].getValore());
        String info = "" + ev.getCampi()[Evento.I_LUOGO].getValore();
        info += ", il " + ev.getCampi()[Evento.I_DATA].getValore();
        info += " alle " + ev.getCampi()[Evento.I_ORA].getValore();
        info += "S" + ev.getIndiceStatoCorrente();
        jLbInformazioni.setText(info);
//        Random rand = new Random();
//        setBackground(new Color(rand.nextInt(70)+180, rand.nextInt(70)+180, rand.nextInt(70)+180));
        
        if(godMode) { // Modalità GOD (modifica dal creatore)
            
            jButton1.setText("Elimina");
            jButton2.setText("Modifica");
            if(ev.getIndiceStatoCorrente()!=Stato.VALIDO)
                jButton3.setVisible(false);
            else
                jButton3.setText("Apri in Bacheca");
            
        } else { // Modalità BASE (finestra bacheca)
            
            jButton1.setText("Visualizza");
            if(!ev.isUtenteIscritto(meetle.getUtenteLoggatoID())) {
                jButton2.setText("Iscriviti");                     
            } else {           
                jButton2.setText("Disiscriviti");  
            }
            jButton3.setVisible(false);            
            
        }
    }
    
    /**
     * imposta i listener sui pulsanti in base alla godMode
     */
    private void setListeners() {        
        
        Evento ev = meetle.getBacheca().getByID(eventoID);
        
        // poi settiamo le azioni che devono fare i pulsanti
        if(this.godMode) { // modalità GOD (modifica dal creatore)
            
            jButton1.addActionListener((ActionEvent e) -> { // pulsante elimina
                meetle.getBacheca().rimuoviEventiByID(eventoID);
                meetle.salvaEventi();
                aggiorna();
            });
            
            jButton2.addActionListener((ActionEvent e) -> { // pulsante modifica
                java.awt.EventQueue.invokeLater(() -> { new EventoFrame(ev, true).setVisible(true); });
            });
            
            jButton3.addActionListener((ActionEvent e) -> { // pusante apri in bacheca
                meetle.getBacheca().rendiApertoEvento(eventoID);
                aggiorna();
            });            
            
            
        } else { // modalità BASE (finestra bacheca)
            
            jButton1.addActionListener((ActionEvent e) -> { // pulsante visualizza
                boolean godmode;
                godmode = ev.getCreatore().equals(meetle.getUtenteLoggatoID()) ? true : false;
                java.awt.EventQueue.invokeLater(() -> { new EventoFrame(ev, godmode).setVisible(true); });
            });
            
            jButton2.addActionListener((ActionEvent e) -> { // pulsante iscrivi/disiscrivi
                ev.switchIscrizione(meetle.getUtenteLoggatoID());
                meetle.salvaEventi();
                aggiorna();
            });
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbTitolo = new javax.swing.JLabel();
        jLbNumPartecipanti = new javax.swing.JLabel();
        jLbInformazioni = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLbTitolo.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLbTitolo.setText("Titolo");

        jLbNumPartecipanti.setText("N iscritti");

        jLbInformazioni.setText("Data, Ora, Luogo");

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbTitolo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLbNumPartecipanti)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbInformazioni)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbTitolo)
                    .addComponent(jLbNumPartecipanti)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbInformazioni)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLbInformazioni;
    private javax.swing.JLabel jLbNumPartecipanti;
    private javax.swing.JLabel jLbTitolo;
    // End of variables declaration//GEN-END:variables
}
