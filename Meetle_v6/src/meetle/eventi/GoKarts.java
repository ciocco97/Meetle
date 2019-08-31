package meetle.eventi;

import meetle.Meetle;
import meetle.eventi.campi.Campo;
import meetle.eventi.campi.CampoInt;
import meetle.eventi.campi.CampoString;
import meetle.gui.testo.Dizionario;
import meetle.utenti.Utente;

public class GoKarts extends Evento {

    public static final String NOME = "Go Karts";
    
    public static final int NUM_CAMPI_EXTRA = 2;
    public static final int  NUM_CAMPI_SPESA = 2;
    public static final int I_MOTORIZZAZIONE = 14, I_POTENZA = 15;
    public static final int I_SPESA_CASCO = 16, I_SPESA_TUTA = 17;
    
    public GoKarts(Utente tenteCreatore) {
        super(tenteCreatore, NOME);
        this.nome = NOME;
        this.descrizione = Dizionario.get(Dizionario.DESC_GOKART);
        campiExtra = new Campo[NUM_CAMPI_EXTRA];
        campiSpesa = new Campo[NUM_CAMPI_SPESA];
        campiExtra[I_MOTORIZZAZIONE-NUM_CAMPI_FISSI] = new CampoString(Dizionario.get(Dizionario.NOME_MOTORIZZAZIONE), "");
        campiExtra[I_POTENZA-NUM_CAMPI_FISSI] = new CampoInt(Dizionario.get(Dizionario.NOME_POTENZA), "");
        campiSpesa[I_SPESA_CASCO-NUM_CAMPI_FISSI-NUM_CAMPI_EXTRA] = new CampoInt(Dizionario.get(Dizionario.NOME_SPESA_CASCO), Dizionario.get(Dizionario.DESC_SPESA_CASCO));
        campiSpesa[I_SPESA_TUTA-NUM_CAMPI_FISSI-NUM_CAMPI_EXTRA] = new CampoInt(Dizionario.get(Dizionario.NOME_SPESA_TUTA), Dizionario.get(Dizionario.DESC_SPESA_TUTA));
  
    }
    
    
}
