package meetle.eventi.campi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CampoData extends Campo<LocalDate> {
    
    public CampoData(String nome, String descrizione, LocalDate data) {
        super(nome, descrizione, data);
    }
    
    public CampoData(String nome, String descrizione, int anno, int mese, int giorno) {
        this(nome, descrizione, LocalDate.of(anno, mese, giorno));
    }   
    
    public CampoData(String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringaData) {
        this.valore = LocalDate.parse(stringaData, DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
    
}