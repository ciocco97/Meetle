package meetle.gui;

import java.awt.event.ActionEvent;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.Stato;

public class EventoPanel extends javax.swing.JPanel {
    
    public static final int MODE_MODIFICA=0, MODE_EVENTO_APERTO=1, MODE_VISUALIZZA=2;
    
    private final int eventoID; // ID dell'evento di riferimento
    private final int mode; // true se siamo in modalità modifica
    
    
    public EventoPanel(int eID, int mode) {
        
        initComponents();
        this.eventoID = eID;
        this.mode = mode;
        
        Evento evento = Meetle.getIstanza().getBacheca().getByID(eID);
        
        // Configurazione testo e azione dei pulsanti
        switch(mode) {
            case MODE_MODIFICA:                
                jButton1.setText("Elimina");
                jButton2.setText("Modifica");
                jButton3.setText("Apri in Bacheca");
                
                jButton1.addActionListener((ActionEvent e) -> { // pulsante elimina
                    Meetle.getIstanza().getBacheca().rimuoviByID(eventoID);
                    aggiorna();
                });
                jButton2.addActionListener((ActionEvent e) -> { // pulsante modifica
                    java.awt.EventQueue.invokeLater(() -> new EventoFrame(eventoID, EventoFrame.MODIFICA).setVisible(true));
                    aggiorna();
                });
                if(evento.getIndiceStatoCorrente()==Stato.VALIDO)
                    jButton3.addActionListener((ActionEvent e) -> { // pusante apri in bacheca
                        evento.apriEvento();
                        aggiorna();
                    });
                else jButton3.setVisible(false);
                break;     
                
            case MODE_VISUALIZZA:                
                jButton2.setVisible(false);       
                
            case MODE_EVENTO_APERTO:
                jButton1.setText("Visualizza");
                
                jButton1.addActionListener((ActionEvent e) -> { // pulsante visualizza
                    java.awt.EventQueue.invokeLater(() -> new EventoFrame(eventoID, EventoFrame.VISUALIZZA).setVisible(true));
                });                
                jButton2.addActionListener((ActionEvent e) -> { // pulsante iscrivi/disiscrivi
                    evento.switchIscrizione(Meetle.getIstanza().getUtenteLoggatoID());
                    aggiorna();
                });
                
                jButton3.setVisible(false);
                break;
                
        }
        
        aggiorna();
    }   
    
    /**
     * aggiorna la grafica del pannello con le informazioni dell'evento
     */
    private void aggiorna() {
        Evento evento = Meetle.getIstanza().getBacheca().getByID(eventoID);
        if(evento==null) {
            removeAll();
            setVisible(false);
            return;
        }
        if (evento.getIndiceStatoCorrente() == Stato.NONVALIDO)
            jLbTitolo.setText("non valido");
        else riempiPannello(evento);
        if(mode==MODE_EVENTO_APERTO) 
            if(Meetle.getIstanza().getUtenteLoggatoID().equals(evento.getCreatoreID()))
                jButton2.setVisible(false);
            else if(!evento.isUtenteIscritto(Meetle.getIstanza().getUtenteLoggatoID())) 
                jButton2.setText("Iscriviti");                     
            else jButton2.setText("Disiscriviti");        
        
    }
    
    private void riempiPannello(Evento evento){
        jLbTitolo.setText((String)evento.getTuttiCampi()[Evento.I_TITOLO].getValore());        
        jLbNumPartecipanti.setText("("+evento.getNumIscritti() +"/"+ evento.getTuttiCampi()[Evento.I_NUM_PARTECIPANTI].getValore()+")");
        String info = "" + evento.getTuttiCampi()[Evento.I_LUOGO].getValore();
        info += ", il " + evento.getTuttiCampi()[Evento.I_DATA].getValore();
        info += " alle ore " + evento.getTuttiCampi()[Evento.I_ORA].getValore();
        info += "                    Stato: ";
        switch (evento.getIndiceStatoCorrente())
        {
            case Stato.APERTO: info+="Aperto"; break;
            case Stato.CHIUSO: info+="Chiuso"; break;
            case Stato.CONCLUSO: info+="Concluso"; break;
            case Stato.FALLITO: info+="Fallito"; break;
            case Stato.NONVALIDO: info+="Non valido"; break;
            case Stato.VALIDO: info+="Valido"; break;
        }
        jLbInformazioni.setText(info);
    }
    
//    /**
//     * aggiorna testo e visibilità di tutti i campi e dei pulsanti
//     */
//    private void aggiorna() {
//        Evento ev=null;
//        
//        if(godMode) { // Modalità GOD (modifica dal creatore)
//            
//            jButton1.setText("Elimina");
//            jButton2.setText("Modifica");
//            if(ev.getIndiceStatoCorrente()!=Stato.VALIDO)
//                jButton3.setVisible(false);
//            else
//                jButton3.setText("Apri in Bacheca");
//            
//        } else { // Modalità BASE (finestra bacheca)
//            
//            jButton1.setText("Visualizza");
//            if(Meetle.getIstanza().getUtenteLoggatoID().equals(ev.getCreatoreID()))
//                jButton2.setVisible(false);
//            else if(!ev.isUtenteIscritto(Meetle.getIstanza().getUtenteLoggatoID())) 
//                jButton2.setText("Iscriviti");                     
//            else     
//                jButton2.setText("Disiscriviti");  
//            
//            jButton3.setVisible(false);            
//            
//        }
//    }
//    
//    /**
//     * imposta i listener sui pulsanti in base alla godMode
//     */
//    private void setListeners() { 
//        
//        Evento ev = Meetle.getIstanza().getBacheca().getByID(eventoID);
//        
//        // poi settiamo le azioni che devono fare i pulsanti
//        if(this.godMode) { // modalità GOD (modifica dal creatore)
//            
//            jButton1.addActionListener((ActionEvent e) -> { // pulsante elimina
//                Meetle.getIstanza().getBacheca().rimuoviByID(eventoID);
//                Meetle.getIstanza().salvaEventi();
//                aggiorna();
//            });
//            
//            jButton2.addActionListener((ActionEvent e) -> { // pulsante modifica
//                java.awt.EventQueue.invokeLater(() -> { new EventoFrame(eventoID, EventoFrame.MODIFICA).setVisible(true); });
//            });
//            
//            jButton3.addActionListener((ActionEvent e) -> { // pusante apri in bacheca
//                Meetle.getIstanza().getBacheca().rendiApertoEvento(eventoID);
//                aggiorna();
//            });            
//            
//            
//        } else { // modalità BASE (finestra bacheca)
//            
//            jButton1.addActionListener((ActionEvent e) -> { // pulsante visualizza
//                java.awt.EventQueue.invokeLater(() -> { new EventoFrame(eventoID, EventoFrame.VISUALIZZA).setVisible(true); });
//            });
//            
//            jButton2.addActionListener((ActionEvent e) -> { // pulsante iscrivi/disiscrivi
//                ev.switchIscrizione(Meetle.getIstanza().getUtenteLoggatoID());
//                Meetle.getIstanza().salvaEventi();
//                aggiorna();
//            });
//        }
//        
//    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbTitolo = new javax.swing.JLabel();
        jLbNumPartecipanti = new javax.swing.JLabel();
        jLbInformazioni = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 150, 155));

        jLbTitolo.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLbTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLbTitolo.setText("Titolo");

        jLbNumPartecipanti.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLbNumPartecipanti.setForeground(new java.awt.Color(255, 255, 255));

        jLbInformazioni.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLbInformazioni.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addGap(18, 18, 18)
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
