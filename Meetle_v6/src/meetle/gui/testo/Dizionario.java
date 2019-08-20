package meetle.gui.testo;

public class Dizionario {
    
    public static final int TITOLO_NOME = 0, NUM_PARTECIPANTI_NOME = 1, TERMINE_ISCRIZIONE_NOME = 2, 
            LUOGO_NOME = 3, DATA_NOME = 4, ORA_NOME = 5, DURATA_NOME = 6, QUOTA_INDIVIDUALE_NOME = 7, 
            COMPRESO_QUOTA_NOME = 8, DATA_CONCLUSIVA_NOME = 9, ORA_CONCLUSIVA_NOME = 10, NOTE_NOME = 11,
            TOLLERANZA_PARTECIPANTI_NOME = 12, DATA_RITIRO_ISCRIZIONE_NOME = 13, NO_DESCRIZIONE = 14, 
            TITOLO_DESC = 15, NUM_PARTECIPANTI_DESC = 16, TERMINE_ISCRIZIONE_DESC = 17, 
            LUOGO_DESC = 18, DATA_DESC = 19, ORA_DESC = 20, DURATA_DESC = 21, QUOTA_INDIVIDUALE_DESC = 22, 
            COMPRESO_QUOTA_DESC = 23, DATA_CONCLUSIVA_DESC = 24, ORA_CONCLUSIVA_DESC = 25, NOTE_DESC = 26,
            TOLLERANZA_PARTECIPANTI_DESC = 27, DATA_RITIRO_ISCRIZIONE_DESC = 28;
    
    private final String[] dizionario = 
    {
        // Nomi dei campi
            "Titolo", "N° Partecipanti", 
            "Termine Iscrizione", "Luogo", "Data", "Ora", 
            "Durata", "Quota individuale", 
            "Compreso quota", "Data conclusiva", 
            "Ora conclusiva", "Note", "Tolleranza partecipanti",
            "Data ritiro iscrizione",
        // Bho
            "Nessuna descrizione presente",
        // Descrizioni
            "Titolo dell'evento", 
            "Numero massimo di partecipanti all'evento (almeno 2)", 
            "Data di scadenza per l'iscrizione", "Luogo dove si terrà l'evento", 
            "Data di inizio dell'evento", "Ora di inizio dell'evento (formato hh:mm)", 
            "Durata stimata dell'evento (formato gg:hh:mm)", 
            "Quota di denaro richiesta per partecipare", "Quota di denaro già compresa (?)", 
            "Data di fine dell'evento", "Ora di fine dell'evento", "Note aggiuntive", 
            "Numero di partecipanti accettabili in più rispetto a num partecipanti (non negativo)", 
            "Data entro cui ci si può disiscrivere da un evento"
    };
    
}


