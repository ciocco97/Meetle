package meetle.eventi;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Stato implements Serializable {
    public static final int NONVALIDO = 0, VALIDO = 1, APERTO = 2, CHIUSO = 3, FALLITO = 4;
    
    private int stato;
    private LocalDateTime dataora; // data in cui lo stato è cambiato

    public Stato(int stato, LocalDateTime dataora) {
        this.stato = stato;
        this.dataora = dataora;
    }
    
    public Stato(int stato) {
        this(stato, LocalDateTime.now());
    }
    
    public Stato() {
        this(NONVALIDO);  
    }
    
    
    
}
