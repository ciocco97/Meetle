package meetle.eventi.stati;

import java.io.Serializable;
import java.time.LocalDateTime;
import meetle.eventi.Evento;

public abstract class Stato implements Serializable {
    public static final int NONVALIDO = 0, VALIDO = NONVALIDO+1, APERTO = VALIDO+1, 
            CHIUSO = APERTO+1, CONCLUSO = CHIUSO+1, FALLITO = CONCLUSO+1, RITIRATO = FALLITO+1;
    
    protected final Evento evento;
    private final LocalDateTime dataora;

    public Stato(Evento evento) {
        this.evento = evento;
        this.dataora = LocalDateTime.now();
    }

    public Stato(Evento evento, LocalDateTime dataora) {
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
