package meetle_v1;

import meetle_v1.campi.*;

public class PartitaDiCalcio extends Evento {
    private CampoGenere genere;
    private CampoRange fasciaEta;

    public PartitaDiCalcio(CampoGenere genere, CampoRange fasciaEta, String nome, 
            String descrizione, CampoString titolo, CampoString luogo, CampoString note, 
            CampoInt numPartecipanti, CampoInt quotaIndividuale, CampoInt compresoQuota, 
            CampoData termineIscrizione, CampoData data, CampoData dataConclusiva, CampoOra ora, 
            CampoOra oraConclusiva, CampoDurata durata) {
        super(nome, descrizione, titolo, luogo, note, numPartecipanti, quotaIndividuale, 
                compresoQuota, termineIscrizione, data, dataConclusiva, ora, oraConclusiva, durata);
        
        campiAggiuntivi.add(genere);
        campiAggiuntivi.add(fasciaEta);        
        
    }
    
    
    
}
