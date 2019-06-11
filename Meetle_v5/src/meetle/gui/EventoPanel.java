package meetle.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.eventi.Stato;

public class EventoPanel extends javax.swing.JPanel {
    public static final int POS_BACHECA = 0, POS_ISCRIZIONI = 1, POS_POSSEDUTI = 2;
    private int posizione, eID;
    
    public EventoPanel(int eID, int pos) {
        this.eID = eID;
        this.posizione = pos;
        
        initComponents();
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        aggiorna();
    }
    
    public final void aggiorna() {
        Evento evento = Meetle.getIstanza().getBacheca().getByID(eID);
        if (evento == null) {
            elimina();
            return;
        }
         switch(evento.getIndiceStatoCorrente()) {
            case Stato.APERTO: jLbTitolo.setForeground(Color.green); break;
            case Stato.CHIUSO: jLbTitolo.setForeground(Color.blue); break;
            case Stato.FALLITO: jLbTitolo.setForeground(Color.red); break;
            case Stato.VALIDO: jLbTitolo.setForeground(Color.white); break;
            case Stato.CONCLUSO: jLbTitolo.setForeground(Color.ORANGE); break;
            default: jLbTitolo.setForeground(Color.BLACK);
        }
        boolean proprietario = evento.getCreatoreID().equals(Meetle.getIstanza().getUtenteLoggatoID());
//        boolean condRitiro = evento.isRitirabile(); // tolta perché se lo stato non era valido (valore data null) dava nullpointer
        int stE = evento.getIndiceStatoCorrente(); //stato evento
        switch(posizione) {
            case POS_BACHECA:
                if (stE == Stato.APERTO){
                    if (!proprietario)
                        addIscrizione(evento);
                    addVisualizza();
                }
                else elimina();
                break;
            case POS_ISCRIZIONI:
                addVisualizza();
                if (stE == Stato.APERTO)
                    if (!proprietario)
                        addIscrizione(evento);
                    else if (evento.isRitirabile())
                        addRitira(evento);
                    else removeRitira();
                if (stE == Stato.VALIDO || stE == Stato.NONVALIDO) //TO DO eliminato
                    elimina();
                break;
            case POS_POSSEDUTI:
                if (stE == Stato.APERTO || stE == Stato.CHIUSO || stE == Stato.FALLITO || stE == Stato.CONCLUSO || stE == Stato.RITIRATO) {
                    if (evento.isRitirabile())
                        addRitira(evento);
                    else removeRitira();
                    if(evento.isInvitoInviabile())
                        addInvita(evento);
                    else removeInvita();
                    addVisualizza();
                    removeModifica();
                }
                else if (stE == Stato.VALIDO || stE == Stato.NONVALIDO){
                    addModifica();
                    addElimina();
                    if (stE == Stato.VALIDO)
                        addApri(evento);
                }
                break;
        }
        aggiornaLabels();
    }
    
    private void addModifica() {
        jButton2.setText("Modifica");
        jButton2.setVisible(true);
        rimuoviListener(jButton2);
        jButton2.addActionListener((ActionEvent e) -> { // pulsante modifica
                    java.awt.EventQueue.invokeLater(() -> new EventoFrame(eID, EventoFrame.MODIFICA).setVisible(true));
                });
    }
    
    private void removeModifica() {
        jButton2.setVisible(false);
        rimuoviListener(jButton2);
    }
    
    private void addVisualizza() {
        jButton1.setText("Visualizza");
        jButton1.setVisible(true);
        rimuoviListener(jButton1);
        jButton1.addActionListener((ActionEvent e) -> { // pulsante modifica
                    java.awt.EventQueue.invokeLater(() -> new EventoFrame(eID, EventoFrame.VISUALIZZA).setVisible(true));
                });
    }
    
    private void addIscrizione(Evento evento) {
        if(evento.isUtenteIscritto(Meetle.getIstanza().getUtenteLoggatoID())) {
            jButton2.setText("Disiscriviti");
            jButton2.setEnabled(evento.isIscrivibile());
        }
        else {
            jButton2.setText("Iscriviti");
            if(evento.getNumIscritti() == evento.getNumIscrittiMax())
                jButton2.setEnabled(false);
        }
        jButton2.setVisible(true);
        rimuoviListener(jButton2);
        jButton2.addActionListener((ActionEvent e) -> {
                    if (evento.isUtenteIscritto(Meetle.getIstanza().getUtenteLoggatoID())){
                        evento.switchIscrizione(Meetle.getIstanza().getUtenteLoggatoID(), null);
                    }
                    else
                        java.awt.EventQueue.invokeLater(() -> new IscrizioneFrame(eID).setVisible(true));
                    aggiorna();
                });
    }
    
    private void addElimina() {
        jButton1.setVisible(true);
        jButton1.setText("Elimina");
        rimuoviListener(jButton1);
        jButton1.addActionListener((ActionEvent e) -> { // pulsante elimina
                    Meetle.getIstanza().getBacheca().rimuoviByID(eID); 
                });
    }
    
    private void addApri(Evento evento) {
        jButton3.setText("Apri in bacheca");
        jButton3.setVisible(true);
        rimuoviListener(jButton3);
        jButton3.addActionListener((ActionEvent e) -> { // pusante apri in bacheca
                        evento.apriEvento();
                        jButton3.setVisible(false);
                    });
    }
    
    private void addRitira(Evento evento) {
        jButton3.setText("Ritira evento");
        jButton3.setVisible(true);
        rimuoviListener(jButton3);
        jButton3.addActionListener((ActionEvent e) -> { // pusante ritira evento
                        evento.ritiraEvento();
                    });
    }
    
    private void removeRitira() {
        jButton3.setVisible(false);
        rimuoviListener(jButton3);
    }
    
    private void addInvita(Evento evento) {
        jButton4.setText("Invita");
        jButton4.setVisible(true);
        rimuoviListener(jButton4);
        jButton4.addActionListener((ActionEvent e) -> { // pusante per invitare altri fruitori :)
            new SelezioneUtentiFrame(eID);
        });
    }
    
    private void removeInvita() {
        jButton4.setVisible(false);
        rimuoviListener(jButton4);
    }

    private void elimina() {
        removeAll();
        setVisible(false);
    }
    
    private void rimuoviListener(JButton button) {
        ActionListener ls[] = button.getActionListeners();
        for (ActionListener l : ls)
            button.removeActionListener(l);
    }
    
    private void aggiornaLabels(){
        Evento evento = Meetle.getIstanza().getBacheca().getByID(eID);
        String mancante = evento.getProssimoCampoObbligatorioMancante();
        if (mancante!=null){
            jLbTitolo.setText("Evento incompleto");
            jLbInformazioni.setText("Prossimo campo mancante: " + mancante);
            jLbNumPartecipanti.setText("");
            return;
        }
        jLbTitolo.setText((String)evento.getTuttiCampi()[Evento.I_TITOLO].getValore());        
        jLbNumPartecipanti.setText("("+evento.getNumIscritti() +"/"+ evento.getTuttiCampi()[Evento.I_NUM_PARTECIPANTI].getValore()+")");
        String info = "" + evento.getTuttiCampi()[Evento.I_LUOGO].getValore();
        info += ", il " + evento.getTuttiCampi()[Evento.I_DATA].getValore();
        info += " alle ore " + evento.getTuttiCampi()[Evento.I_ORA].getValore();
        info += " Stato: ";
        switch (evento.getIndiceStatoCorrente()) {
            case Stato.VALIDO: info+="Valido"; break;
            case Stato.NONVALIDO: info+="Non Valido"; break;
            case Stato.APERTO: info+="Aperto"; break;
            case Stato.CHIUSO: info+="Chiuso"; break;
            case Stato.CONCLUSO: info+="Concluso"; break;
            case Stato.FALLITO: info+="Fallito"; break;
            case Stato.RITIRATO: info+="Ritirato";break;
        }
        jLbInformazioni.setText(info);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbTitolo = new javax.swing.JLabel();
        jLbNumPartecipanti = new javax.swing.JLabel();
        jLbInformazioni = new javax.swing.JLabel();
        jPanelBottoni = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 150, 155));

        jLbTitolo.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLbTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLbTitolo.setText("Titolo");

        jLbNumPartecipanti.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLbNumPartecipanti.setForeground(new java.awt.Color(255, 255, 255));
        jLbNumPartecipanti.setText("# iscritti");

        jLbInformazioni.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLbInformazioni.setForeground(new java.awt.Color(255, 255, 255));
        jLbInformazioni.setText("informazioni");

        jPanelBottoni.setBackground(getBackground());
        jPanelBottoni.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton4.setText("jButton4");
        jPanelBottoni.add(jButton4);

        jButton3.setText("jButton3");
        jPanelBottoni.add(jButton3);

        jButton2.setText("jButton2");
        jPanelBottoni.add(jButton2);

        jButton1.setText("jButton1");
        jPanelBottoni.add(jButton1);

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
                        .addComponent(jPanelBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbInformazioni)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLbTitolo)
                        .addComponent(jLbNumPartecipanti))
                    .addComponent(jPanelBottoni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbInformazioni)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLbInformazioni;
    private javax.swing.JLabel jLbNumPartecipanti;
    private javax.swing.JLabel jLbTitolo;
    private javax.swing.JPanel jPanelBottoni;
    // End of variables declaration//GEN-END:variables
}
