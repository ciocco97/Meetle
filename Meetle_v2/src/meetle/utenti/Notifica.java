package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notifica implements Serializable {
    private String messaggio;
    private LocalDateTime dataora;

    public Notifica(String messaggio, LocalDateTime dataora) {
        this.messaggio = messaggio;
        this.dataora = dataora;
    }
    
    public Notifica(String messaggio) {
        this(messaggio, LocalDateTime.now());
    }
    
    
}
