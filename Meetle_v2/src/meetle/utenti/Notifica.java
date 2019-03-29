package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notifica implements Serializable {
    private String messaggio;
    private LocalDateTime data;

    public Notifica(String messaggio, LocalDateTime data) {
        this.messaggio = messaggio;
        this.data = data;
    }
    
    
    
}
