package meetle.io;

import java.io.*;
import java.util.ArrayList;
import meetle.Meetle;
import meetle.eventi.Bacheca;
import meetle.eventi.Evento;
import meetle.utenti.Utente;
import meetle.utenti.Utenti;

public class MeetleIO {
    private static final String 
            PATH_FILE_EVENTI = "saves/eventi.sav",
            PATH_FILE_UTENTI = "saves/utenti.sav";
    
    private final Meetle meetle;
    private final File fileEventi, fileUtenti;    

    public MeetleIO(Meetle meetle) throws IOException {
        this.meetle = meetle;
        new File("saves").mkdir();
        
        fileEventi = new File(PATH_FILE_EVENTI);
        fileUtenti = new File(PATH_FILE_UTENTI);        
    } 
    
    public void salvaEventi() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileEventi));
        oos.writeObject(this.meetle.getBacheca());
    }
    
    public Bacheca caricaEventi() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileEventi));
        return (Bacheca) ois.readObject();  
    }
    
    public void salvaUtenti() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileUtenti));
        oos.writeObject(this.meetle.getUtenti());
    }
    
    public Utenti caricaUtenti() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileUtenti));
        return (Utenti) ois.readObject();            
    }
}
