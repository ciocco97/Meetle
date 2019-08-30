package meetle.eventi.stati;

import meetle.eventi.Evento;

public class StatoRitirato extends Stato {

    public StatoRitirato(Evento evento) {
        super(evento);
    }

    @Override
    public void aggiornaStato() {
    }

    @Override
    public int getIndiceStato() {
        return Stato.RITIRATO;
    }
    
}
