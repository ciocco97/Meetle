package meetle.eventi.stati;

import meetle.eventi.Evento;

public class StatoConcluso extends StatoEvento {

    public StatoConcluso(Evento evento) {
        super(evento);
    }

    @Override
    public void aggiornaStato() {
    }

    @Override
    public int getIndiceStato() {
        return StatoEvento.CONCLUSO;
    }
    
}
