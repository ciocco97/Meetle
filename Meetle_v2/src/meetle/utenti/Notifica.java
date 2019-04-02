package meetle.utenti;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Notifica implements Serializable {
    private boolean visualizzata;
    private String messaggio;
    private LocalDateTime dataora;
    private int eventoID;

    public Notifica(String messaggio, LocalDateTime dataora) {
        this.messaggio = messaggio;
        this.dataora = dataora;
        this.visualizzata = false;
    }
    
    public Notifica(String messaggio) {
        this(messaggio, LocalDateTime.now());
    }
    
    public void setVisualizzata()
    {
        this.visualizzata = true;
    }
    
}
