package meetle;

import meetle.gui.InterfacciaCMD;
import meetle.eventi.Bacheca;
import meetle.eventi.Evento;

public class Meetle {
    private InterfacciaCMD interfaccia;
    private Bacheca bacheca;

    public Meetle() {
        interfaccia = new InterfacciaCMD(this);
        bacheca = new Bacheca(this);
    }  
    
    public void start() {
        java.awt.EventQueue.invokeLater(() -> { interfaccia.setVisible(true); });
    }
    
    public String stampaBacheca() {
        return bacheca.toString();
    }
    
    public String stampaBacheca(String nome){
        String stampa = "";
        for (Evento e:bacheca.getEventi())
        {
            if (e.getNome().equals(nome))
                stampa += e + "\n";
        }
        return stampa;
    }
    
//    public List<List<NomeValore>> getDatiEventi(){
//        
//        List<List<NomeValore>> listaEventi = new LinkedList<>();
//        for(String evento: bacheca.toString().split(Bacheca.SEPARATORE_EVENTI)) {
//            List<NomeValore> listaCampi = new LinkedList<>();
//            for (String campo: evento.split(Evento.SEPARATORE_CAMPI))                
//                listaCampi.add(new NomeValore(campo.split(Campo.SEPARATORE_NOME_VALORE)));
//            listaEventi.add(listaCampi);
//        }
//        return listaEventi;
//    }

//    public String getUtente() {  return utente.toString(); }
//    public void setUtente(String username) { utente = new Utente(username); }
    
    
    public static void main(String[] args) {
        Meetle meetle = new Meetle();        
        meetle.start();
        
    }
        
}
