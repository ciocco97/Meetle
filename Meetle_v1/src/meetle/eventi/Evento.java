package meetle.eventi;

import meetle.eventi.campi.*;
import java.util.Arrays;
import java.util.stream.Stream;

public abstract class Evento {
    protected final static String NO_DESCRIPTION = "Nessuna descrizione presente";    
    
    public static final int NUM_CAMPI_FISSI = 12;
    public static final int I_TITOLO = 0, I_NUM_PARTECIPANTI = 1, I_TERMINE_ISCRIZIONE = 2, 
            I_LUOGO = 3, I_DATA = 4, I_ORA = 5, I_DURATA = 6, I_QUOTA_INDIVIDUALE = 7, 
            I_COMPRESO_QUOTA = 8, I_DATA_CONCLUSIVA = 9, I_ORA_CONCLUSIVA = 10, I_NOTE = 11;
    public final static String SEPARATORE_CAMPI = "\n";
    
    protected String nome, descrizione;    
    protected Campo[] campi, campiExtra;    
    
    public Evento() {
                
        campi = new Campo[NUM_CAMPI_FISSI];
        campi[I_TITOLO] = new CampoString("Titolo", "Titolo dell'evento");
        campi[I_NUM_PARTECIPANTI] = new CampoInt("Numero partecipanti", "Numero massimo di partecipanti all'evento");
        campi[I_TERMINE_ISCRIZIONE] = new CampoData("Termine iscrizione", "Data di scadenza per l'iscrizione");
        campi[I_LUOGO] = new CampoString("Luogo", "Luogo dove si terrà l'evento");
        campi[I_DATA] = new CampoData("Data", "Data di inizio dell'evento");
        campi[I_ORA] = new CampoOra("Ora", "Ora di inizio dell'evento");
        campi[I_DURATA] = new CampoDurata("Durata", "Durata stimata dell'evento");
        campi[I_QUOTA_INDIVIDUALE] = new CampoInt("Quota individuale",  "Quota di denaro richiesta a ognuno per partecipare");
        campi[I_COMPRESO_QUOTA] = new CampoInt("Compreso quota",  "Quota di denaro già compresa (?)");
        campi[I_DATA_CONCLUSIVA] = new CampoData("Data conclusiva",  "Data di fine dell'evento");
        campi[I_ORA_CONCLUSIVA] = new CampoOra("Ora conclusiva", "Ora di fine dell'evento");
        campi[I_NOTE] = new CampoString("Note", "Note aggiuntive");      
                        
        setFacoltativi();
    }   
    
    public void setValoreDaString(int indice, String valore){
        if(indice < NUM_CAMPI_FISSI)
            campi[indice].setValoreDaString(valore);
        else
            campiExtra[indice-NUM_CAMPI_FISSI].setValoreDaString(valore);
    }

    private void setFacoltativi() {
        campi[I_TITOLO].setFacoltativo();
        campi[I_DURATA].setFacoltativo();
        campi[I_COMPRESO_QUOTA].setFacoltativo();
        campi[I_DATA_CONCLUSIVA].setFacoltativo();
        campi[I_ORA_CONCLUSIVA].setFacoltativo();
        campi[I_NOTE].setFacoltativo();
    } 
    
    public String toDescrizione() {
        return "\t"+nome.toUpperCase() + "\n" +Stream.concat(Arrays.stream(campi), Arrays.stream(campiExtra))
                .map(c -> "\t" + c.getNome() +": "+ c.getDescrizione() + SEPARATORE_CAMPI)
                .reduce("", String::concat);   
    }

    @Override
    public String toString() {
        return Stream.concat(Arrays.stream(campi), Arrays.stream(campiExtra))
                .filter(c -> !c.toString().equals(""))
                .map(c -> "\t" + c + SEPARATORE_CAMPI)
                .reduce("", String::concat);     
    }   
    
    
    // Getters e Setters
    public String getNome() { return nome; }
    public String getTitolo() { return campi[I_TITOLO].toString(); }   
    public String getLuogo() { return campi[I_LUOGO].toString(); }    
    public String getData() { return campi[I_DATA].toString(); }    
    public String getOra() { return campi[I_ORA].toString(); }    
    public String getNumeroPartecipanti() { return campi[I_NUM_PARTECIPANTI].toString(); }    
    public String getTermineUltimoDIscrizione() { return campi[I_TERMINE_ISCRIZIONE].toString(); }
    
}
