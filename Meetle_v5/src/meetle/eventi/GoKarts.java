package meetle.eventi;

import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoInt;
import meetle.eventi.campi.CampoString;

public class GoKarts extends Evento {

    public static final String NOME = "Go Karts", DESCRIZIONE = "un giro in compagnia sui kart :)";
    public static final String N_MOTORIZZAZIONE = "Motorizzazione", N_POTENZA = "Potenza", N_SPESA_CASCO = "Costo del Casco", N_SPESA_TUTA = "Costo della tuta"; 
    
    public static final int NUM_CAMPI_EXTRA = 2;
    public static final int  NUM_CAMPI_SPESA = 2;
    public static final int I_MOTORIZZAZIONE = 14, I_POTENZA = 15;
    public static final int I_SPESA_CASCO = 16, I_SPESA_TUTA = 17;
    
    public GoKarts(String creatoreID) {
        super(creatoreID, NOME);
        
        this.nome = NOME;
        this.descrizione = DESCRIZIONE;
        
        campiExtra = new Campo[NUM_CAMPI_EXTRA];
        campiSpesa = new Campo[NUM_CAMPI_SPESA];
        campiExtra[I_MOTORIZZAZIONE-NUM_CAMPI_FISSI] = new CampoString(N_MOTORIZZAZIONE, "");
        campiExtra[I_POTENZA-NUM_CAMPI_FISSI] = new CampoInt(N_POTENZA, "");
        campiSpesa[I_SPESA_CASCO-NUM_CAMPI_FISSI-NUM_CAMPI_EXTRA] = new CampoInt(N_SPESA_CASCO, "Spesa per l'affitto del casco se l'utente non lo possiede");
        campiSpesa[I_SPESA_TUTA-NUM_CAMPI_FISSI-NUM_CAMPI_EXTRA] = new CampoInt(N_SPESA_TUTA, "Spesa per l'affitto della tuta se l'utente non la possiede");
  
    }
    
    
}
