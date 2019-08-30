package meetle.eventi.stati;

import meetle.eventi.Evento;

public class StatoFallito extends StatoEvento {

    public StatoFallito(Evento evento) {
        super(evento);
    }

    @Override
    public void aggiornaStato() {
    }

    @Override
    public int getIndiceStato() {
        return StatoEvento.FALLITO;
    }
    
}
