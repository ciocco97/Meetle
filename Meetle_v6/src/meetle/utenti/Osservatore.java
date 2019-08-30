package meetle.utenti;

import meetle.eventi.Osservabile;

public interface Osservatore {
    
    void aggiorna(Osservabile bile);
    
}
