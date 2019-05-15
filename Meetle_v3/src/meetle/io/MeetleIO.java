package meetle.io;

import java.io.*;
import java.util.ArrayList;
import meetle.Meetle;
import meetle.eventi.Evento;
import meetle.utenti.Utente;

public class MeetleIO {
    private static final String NOME_FILE_EVENTI = "saves/eventi.sav",
            NOME_FILE_UTENTI = "saves/utenti.sav";
    
    private final Meetle meetle;
    
    private final File fileEventi, fileUtenti;
    

    public MeetleIO(Meetle meetle) throws IOException {
        this.meetle = meetle;
        
        File cartella = new File("saves");
        if (!cartella.exists()) cartella.mkdir();
        
        fileEventi = new File(NOME_FILE_EVENTI);
        fileUtenti = new File(NOME_FILE_UTENTI);
    }       
    
    public void salvaEventi() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileEventi));
        oos.writeObject(meetle.getBacheca());
    }
    
    public ArrayList<Evento> caricaEventi() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileEventi));
        return (ArrayList<Evento>) ois.readObject();  
    }
    
    public void salvaUtenti() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileUtenti));
        oos.writeObject(meetle.getUtenti());
    }
    
    public ArrayList<Utente> caricaUtenti() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileUtenti));
        return (ArrayList<Utente>) ois.readObject();            
    }
}
