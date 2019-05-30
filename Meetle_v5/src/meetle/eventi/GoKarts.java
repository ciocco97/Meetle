package meetle.eventi;

import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoInt;
import meetle.eventi.campi.CampoString;

public class GoKarts extends Evento {

    public static final String NOME = "Go Karts", DESCRIZIONE = "un giro in compagnia sui kart :)";
    public static final String N_CAMPO1 = "Motorizzazione", N_CAMPO2 = "Cilindrata"; 
    
    public static final int NUM_CAMPI_EXTRA = 2;
    public static final int I_MOTORIZZAZIONE = 14, I_CILINDRATA = 15;
    
    public GoKarts(String creatoreID) {
        super(creatoreID, NOME);
        
        this.nome = NOME;
        this.descrizione = DESCRIZIONE;
        
        campiExtra = new Campo[NUM_CAMPI_EXTRA];
        campiExtra[I_MOTORIZZAZIONE-NUM_CAMPI_FISSI] = new CampoString(N_CAMPO1, "");
        campiExtra[I_CILINDRATA-NUM_CAMPI_FISSI] = new CampoInt(N_CAMPO2, "");
        
    }
    
    
}
