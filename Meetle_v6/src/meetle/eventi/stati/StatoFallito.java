package meetle.eventi.stati;

import meetle.eventi.Evento;

public class StatoFallito extends Stato {

    public StatoFallito(Evento evento) {
        super(evento);
    }

    @Override
    public void aggiornaStato() {
    }

    @Override
    public int getIndiceStato() {
        return Stato.FALLITO;
    }
    
}
