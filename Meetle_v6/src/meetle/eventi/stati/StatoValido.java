package meetle.eventi.stati;

import meetle.eventi.Evento;

public class StatoValido extends StatoEvento {

    public StatoValido(Evento evento) {
        super(evento);
    }

    @Override
    public boolean apriEvento() {
        evento.nuovoStato(new StatoAperto(evento));
        return true;
    }


    @Override
    public void aggiornaStato() {
        if(!evento.checkValidita())
            evento.nuovoStato(new StatoNonValido(evento));
    }

    @Override
    public int getIndiceStato() {
        return StatoEvento.VALIDO;
    }

    
}
