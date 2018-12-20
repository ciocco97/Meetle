package meetle;

import meetle.campi.CampoString;
import meetle.campi.CampoRange;
import meetle.campi.CampoGenere;

public class PartitaDiCalcio extends Evento {
    private CampoGenere genere;
    private CampoRange fasciaEta;

//    public PartitaDiCalcio(int genere, int fasciaEtal, int fasciaEtah, String nome, 
//            String descrizione, String titolo, String luogo, String note, 
//            int numPartecipanti, int quotaIndividuale, int compresoQuota, 
//            CampoData termineIscrizione, CampoData data, CampoData dataConclusiva, CampoOra ora, 
//            CampoOra oraConclusiva, CampoDurata durata) {
//        super(nome, descrizione, titolo, luogo, note, numPartecipanti, quotaIndividuale, 
//                compresoQuota, termineIscrizione, data, dataConclusiva, ora, oraConclusiva, durata);
//        
//        campiAggiuntivi.add(genere);
//        campiAggiuntivi.add(fasciaEta);        
//        
//    }
    
    public PartitaDiCalcio(String titolo){
        super();
        campiFissi[I_TITOLO] = new CampoString(titolo);
        this.nome = "Partita di calcio";
    }
    
    
    
}
