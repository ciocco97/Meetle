package meetle.io;

import java.io.*;
import java.util.ArrayList;
import meetle.Meetle;
import meetle.eventi.Evento;

public class MeetleIO {
    private static final String NOME_FILE_EVENTI = "saves/eventi.sav";
    
    private Meetle meetle;
    
    private File fileEventi;
    

    public MeetleIO(Meetle meetle) {
        this.meetle = meetle;
        fileEventi = new File(NOME_FILE_EVENTI);
    }       
    
    public void salvaEventi() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileEventi));
        oos.writeObject(meetle.getBacheca().getEventi());
    }
    
    public ArrayList<Evento> caricaEventi() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileEventi));
        return (ArrayList<Evento>) ois.readObject();            
    }
}
