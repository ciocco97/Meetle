package meetle.eventi;

import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoInt;
import meetle.eventi.campi.CampoString;

public class GoKarts extends Evento {

    public static final String NOME = "Go Karts", DESCRIZIONE = "un giro in compagnia sui kart :)";
    public static final String N_MOTORIZZAZIONE = "Motorizzazione", N_POTENZA = "Potenza", N_COSTO_CASCO = "Costo del Casco"; 
    
    public static final int NUM_CAMPI_EXTRA = 3;
    public static final int I_MOTORIZZAZIONE = 14, I_POTENZA = 15, I_COSTO_CASCO = 16;
    
    public GoKarts(String creatoreID) {
        super(creatoreID, NOME);
        
        this.nome = NOME;
        this.descrizione = DESCRIZIONE;
        
        campiExtra = new Campo[NUM_CAMPI_EXTRA];
        campiExtra[I_MOTORIZZAZIONE-NUM_CAMPI_FISSI] = new CampoString(N_MOTORIZZAZIONE, "");
        campiExtra[I_POTENZA-NUM_CAMPI_FISSI] = new CampoInt(N_POTENZA, "");
        campiExtra[I_COSTO_CASCO-NUM_CAMPI_FISSI] = new CampoInt(N_COSTO_CASCO, "");
        
    }
    
    
}
