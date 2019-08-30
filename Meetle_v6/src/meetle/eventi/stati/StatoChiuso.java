package meetle.eventi.stati;

import java.time.LocalDate;
import meetle.eventi.Evento;

public class StatoChiuso extends Stato {

    public StatoChiuso(Evento evento) {
        super(evento);
    }

    @Override
    public void aggiornaStato() {
        if (evento.getDataConlusiva() != null && LocalDate.now().compareTo(evento.getDataConlusiva()) > 0)                
            evento.nuovoStato(new StatoConcluso(evento));
        else if (LocalDate.now().compareTo(evento.getData()) > 0)
            evento.nuovoStato(new StatoConcluso(evento));
    }

    @Override
    public int getIndiceStato() {
        return Stato.CHIUSO;
    }
    
}
