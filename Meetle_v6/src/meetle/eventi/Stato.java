package meetle.eventi;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Stato implements Serializable {
    public static final int NONVALIDO = 0, VALIDO = 1, APERTO = 2, CHIUSO = 3, CONCLUSO = 4, FALLITO = 5, RITIRATO = 6;
    
    private int indiceStato;
    private LocalDateTime dataora; // data in cui lo indiceStato è cambiato

    public Stato(int indiceStato, LocalDateTime dataora) {
        this.indiceStato = indiceStato;
        this.dataora = dataora;
    }
    
    public Stato(int indiceStato) {
        this(indiceStato, LocalDateTime.now());
    }
    
    public Stato() {
        this(NONVALIDO);  
    }
    
    public int getIndiceStato() {
        return indiceStato;
    }
    
}
