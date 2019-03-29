package meetle.eventi;

import java.io.Serializable;
import java.time.*;

public class Stato implements Serializable {
    public static final int NONVALIDA = 0, VALIDA = 1, APERTA = 2, CHIUSA = 3, FALLITA = 4;
    
    private int stato;
    private LocalDateTime data; // data in cui lo stato è cambiato

    public Stato() {
        stato = NONVALIDA;
        data = LocalDate.now().atTime(LocalTime.now());        
    }
    
    
}
