package meetle.io;

import java.io.*;
import meetle.Meetle;

public class MeetleIO {
    private static final String 
            PATH_FILE_EVENTI = "saves/eventi.sav",
            PATH_FILE_UTENTI = "saves/utenti.sav",
            PATH_FILE = "saves/meetle.sav";
    
//    private final Meetle meetle;
    private final File file;  
    
    private static MeetleIO istanza;

    private MeetleIO() throws IOException {
//        this.meetle = meetle;
        new File("saves").mkdir();
        
//        fileEventi = new File(PATH_FILE_EVENTI);
//        fileUtenti = new File(PATH_FILE_UTENTI);  
        file = new File(PATH_FILE);        
    } 
    
    public static MeetleIO getIstanza() {
        if (istanza==null)
            try {
                istanza = new MeetleIO();
            } catch (IOException ex) { System.err.println("Errore istanza MeetleIO"); }
        return istanza;
    }
    
    public void scrivi(Meetle meetle) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(meetle);
    }
    
    public Meetle leggi() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        return (Meetle) ois.readObject();  
    }

//    public void salvaEventi() throws IOException {
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileEventi));
//        oos.writeObject(this.meetle.getBacheca());
//    }
//    
//    public Bacheca caricaEventi() throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileEventi));
//        return (Bacheca) ois.readObject();  
//    }
//    
//    public void salvaUtenti() throws IOException {
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileUtenti));
//        oos.writeObject(this.meetle.getUtenti());
//    }
//    
//    public Utenti caricaUtenti() throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileUtenti));
//        return (Utenti) ois.readObject();            
//    }
}
