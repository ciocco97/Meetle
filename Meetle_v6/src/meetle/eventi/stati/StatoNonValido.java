package meetle.eventi.stati;

import meetle.eventi.Evento;

public class StatoNonValido extends Stato {

    public StatoNonValido(Evento evento) {
        super(evento);
    }

    @Override
    public void aggiornaStato() {
        if(evento.checkValidita())
            evento.nuovoStato(new StatoValido(evento));
    }

    @Override
    public int getIndiceStato() {
        return Stato.NONVALIDO;
    }
    
}
