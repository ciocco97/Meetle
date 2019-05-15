/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetle.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import meetle.Meetle;
import meetle.eventi.Evento;
import static meetle.eventi.Evento.I_DATA_RITIRO_ISCRIZIONE;
import meetle.eventi.Stato;

/**
 *
 * @author Alessandro
 */
public class EventoPanel extends javax.swing.JPanel {
    public static final int POS_BACHECA = 0, POS_ISCRIZIONI = 1, POS_POSSEDUTI = 2;
    private int posizione, eID;
    
    /**
     * Creates new form EventoPanelNew
     */
    public EventoPanel(int eID, int pos) {
        //System.out.println("EventoPanel.costruttore -> ID dell'evento: " + eID);
        initComponents();
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        this.posizione = pos;
        this.eID = eID;
        riempiPannello(eID);
        aggiorna();
    }
    
    public void aggiorna()
    {
        Evento evento = Meetle.getIstanza().getBacheca().getByID(eID);
        if (evento == null){
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
        switch(posizione)
        {
            case POS_BACHECA:
                if (stE == Stato.APERTO){
                    if (!proprietario)
                        addIscrizione(evento);
                    else if (evento.isRitirabile())
                        addRitira(evento);
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
                if (stE == Stato.VALIDO || stE == Stato.NONVALIDO) //TO DO eliminato
                    elimina();
            break;
            case POS_POSSEDUTI:
                if (stE == Stato.APERTO || stE == Stato.CHIUSO || stE == Stato.FALLITO || stE == Stato.CONCLUSO || stE == Stato.RITIRATO) {
                    if (evento.isRitirabile()) {
                        addRitira(evento);
                    }
                    addVisualizza();
                    removeModifica();
                    if (stE == Stato.RITIRATO) //Se è ritirato elimina sovrascrive visualizza (solo in POSSEDUTI)
                        addElimina();
                }
                else if (stE == Stato.VALIDO || stE == Stato.NONVALIDO){
                    addModifica();
                    addElimina();
                    if (stE == Stato.VALIDO)
                        addApri(evento);
                }
            break;
        }
        riempiPannello(eID);
        
    }
    
    private void addModifica()
    {
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
    
    private void addVisualizza()
    {
        jButton1.setText("Visualizza");
        jButton1.setVisible(true);
        rimuoviListener(jButton1);
        jButton1.addActionListener((ActionEvent e) -> { // pulsante modifica
                    java.awt.EventQueue.invokeLater(() -> new EventoFrame(eID, EventoFrame.VISUALIZZA).setVisible(true));
                });
    }
    
    private void addIscrizione(Evento evento)
    {
        if(evento.isUtenteIscritto(Meetle.getIstanza().getUtenteLoggatoID())) {
            jButton2.setText("Disiscriviti");
            jButton2.setEnabled(evento.isIscrivibile());
        }
        else {
            jButton2.setText("Iscriviti");
            if(evento.getNumIscritti() == evento.getMaxNumIscritti())
                jButton2.setEnabled(false);
        }
        jButton2.setVisible(true);
        rimuoviListener(jButton2);
        jButton2.addActionListener((ActionEvent e) -> {
                    evento.switchIscrizione(Meetle.getIstanza().getUtenteLoggatoID());
                    aggiorna();
                });
    }
    private void addElimina()
    {
        jButton1.setVisible(true);
        jButton1.setText("Elimina");
        rimuoviListener(jButton1);
        jButton1.addActionListener((ActionEvent e) -> { // pulsante elimina
                    Meetle.getIstanza().getBacheca().rimuoviByID(eID); 
                });
    }
    private void addApri(Evento evento)
    {
        jButton3.setText("Apri in bacheca");
        jButton3.setVisible(true);
        rimuoviListener(jButton3);
        jButton3.addActionListener((ActionEvent e) -> { // pusante apri in bacheca
                        evento.apriEvento();
                        jButton3.setVisible(false);
                    });
    }
    private void addRitira(Evento evento)
    {
        jButton3.setText("Ritira evento");
        jButton3.setVisible(true);
        rimuoviListener(jButton3);
        jButton3.addActionListener((ActionEvent e) -> { // pusante ritira evento
                        evento.ritiraEvento();
                    });
    }
    private void elimina()
    {
        setVisible(false);
    }
    
    private void rimuoviListener(JButton button) {
        ActionListener ls[] = button.getActionListeners();
        for (ActionListener l : ls)
            button.removeActionListener(l);
    }
    
    private void riempiPannello(int eID){
        Evento evento = Meetle.getIstanza().getBacheca().getByID(eID);
        if (evento.getIndiceStatoCorrente() == Stato.NONVALIDO){
             jLbTitolo.setText("non valido");
             return;
        }
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
            case Stato.VALIDO: info+="Valido"; break;
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
        jLbInformazioni = new javax.swing.JLabel();
        jLbNumPartecipanti = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 150, 155));

        jLbTitolo.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLbTitolo.setForeground(new java.awt.Color(255, 255, 255));
        jLbTitolo.setText("Titolo");

        jLbInformazioni.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLbInformazioni.setForeground(new java.awt.Color(255, 255, 255));

        jLbNumPartecipanti.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLbNumPartecipanti.setForeground(new java.awt.Color(255, 255, 255));

        jButton3.setText("jButton3");

        jButton2.setText("jButton2");

        jButton1.setText("jButton1");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLbInformazioni)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbTitolo)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton2)
                                .addComponent(jButton3))
                            .addComponent(jLbNumPartecipanti))))
                .addGap(0, 0, 0)
                .addComponent(jLbInformazioni))
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
