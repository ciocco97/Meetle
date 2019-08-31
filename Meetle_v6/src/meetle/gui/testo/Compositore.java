package meetle.gui.testo;

public class Compositore {
    
    public Compositore() {
        
    }
   
   public String componiNotificaEventoAperto() {
       return Dizionario.get(Dizionario.NOTIFICA_EVENTO_APERTO);
   }
   
   public String componiNotificaEventoChiuso() {
       return Dizionario.get(Dizionario.NOTIFICA_EVENTO_CHIUSO);
   }
   
   public String componiNotificaEventoConcluso() {
       return Dizionario.get(Dizionario.NOTIFICA_EVENTO_CONCLUSO);
   }
   
   public String componiNotificaEventoFallito() {
       return Dizionario.get(Dizionario.NOTIFICA_EVENTO_FALLITO);
   }
   
   public String componiNotificaEventoRitirato() {
       return Dizionario.get(Dizionario.NOTIFICA_EVENTO_RITIRATO);
   }
   
   public String componiInvitoTitolo() {
       return Dizionario.get(Dizionario.NOTIFICA_INVITO_TITOLO);
   }
   
   public String componiInvitoMessaggio() {
       return Dizionario.get(Dizionario.NOTIFICA_INVITO_MESSAGGIO);
   }
   
   public String componiMessaggioNoUtentiInvitabili() {
       return Dizionario.get(Dizionario.MESSAGGIO_NO_UTENTI_INVITABILI);
   }
}
