package meetle.eventi.stati;

import java.io.Serializable;
import java.time.LocalDateTime;
import meetle.eventi.Evento;

public abstract class StatoEvento implements Serializable {
    public static final int NONVALIDO = 0, VALIDO = 1, APERTO = 2, CHIUSO = 3, CONCLUSO = 4, FALLITO = 5, RITIRATO = 6;
    
    protected final Evento evento;
    private final LocalDateTime dataora;

    public StatoEvento(Evento evento) {
        this.evento = evento;
        this.dataora = LocalDateTime.now();
    }

    public StatoEvento(Evento evento, LocalDateTime dataora) {
        this.evento = evento;
        this.dataora = dataora;
    }
    
    public abstract void aggiornaStato();
    
    public boolean apriEvento() { return false; };
    
    public boolean ritiraEvento() { return false; };

    public abstract int getIndiceStato();
    
    public final LocalDateTime getDataOra() {
        return dataora;
    }
    
}
