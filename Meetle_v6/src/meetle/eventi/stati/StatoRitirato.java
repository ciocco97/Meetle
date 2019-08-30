package meetle.eventi.stati;

import meetle.eventi.Evento;

public class StatoRitirato extends StatoEvento {

    public StatoRitirato(Evento evento) {
        super(evento);
    }

    @Override
    public void aggiornaStato() {
    }

    @Override
    public int getIndiceStato() {
        return StatoEvento.RITIRATO;
    }
    
}
