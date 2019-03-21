package meetle.utenti;

public class Utente {
    private String username;

    public Utente(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }
    
    
}
