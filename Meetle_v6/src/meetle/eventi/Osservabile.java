package meetle.eventi;

import meetle.utenti.Osservatore;

public interface Osservabile {
    
    boolean registra(Osservatore tore);
    
    boolean deregistra(Osservatore tore);
    
    void notifica();
    
}
