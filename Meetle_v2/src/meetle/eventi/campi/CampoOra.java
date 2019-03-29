package meetle.eventi.campi;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CampoOra extends Campo<LocalTime> {
   
    public CampoOra(String nome, String descrizione, LocalTime ora) {
        super(nome, descrizione, ora);
    }
    
    public CampoOra(String nome, String descrizione, int ore, int minuti) {
        this(nome, descrizione, LocalTime.of(ore, minuti));
    }
    
    public CampoOra(String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringaOra) {
        this.valore = LocalTime.parse(stringaOra, DateTimeFormatter.ISO_TIME);
    }
    
}
