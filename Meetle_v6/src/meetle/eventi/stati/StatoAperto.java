package meetle.eventi.stati;

import java.time.LocalDate;
import meetle.eventi.Evento;

public class StatoAperto extends Stato {

    public StatoAperto(Evento evento) {
        super(evento);
    }

    @Override
    public boolean ritiraEvento() {
        evento.nuovoStato(new StatoRitirato(evento));
        return true;
    }

    @Override
    public void aggiornaStato() {
        int numMinPartecipanti = evento.getNumIscrittiMin(), 
                numMaxPartecipanti = evento.getNumIscrittiMax();
            // Condizione 1 per passare da aperto a chiuso
            if(LocalDate.now().compareTo(evento.getTermineIscrizione()) > 0 &&
                    evento.getNumIscrittiCorrente() >= numMinPartecipanti)
                evento.nuovoStato(new StatoChiuso(evento));
            // Condizione 2 per passare da aperto a chiuso
            else if(LocalDate.now().compareTo(evento.getTermineIscrizione()) <= 0 &&
                    LocalDate.now().compareTo(evento.getDataRitiroIscrizione()) > 0 &&
                    evento.getNumIscrittiCorrente() == numMaxPartecipanti)
                evento.nuovoStato(new StatoChiuso(evento));
            else if (LocalDate.now().compareTo(evento.getTermineIscrizione()) > 0) // la data attuale supera la data termine iscrizione
                evento.nuovoStato(new StatoFallito(evento));
    }

    @Override
    public int getIndiceStato() {
        return Stato.APERTO;
    }

    
}