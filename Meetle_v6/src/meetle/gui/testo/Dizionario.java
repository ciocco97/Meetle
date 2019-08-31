package meetle.gui.testo;

public class Dizionario {
    
    public static final int 
            NOME_TITOLO = 0, 
            NOME_NUM_PARTECIPANTI = NOME_TITOLO + 1, 
            NOME_TERMINE_ISCRIZIONE = NOME_NUM_PARTECIPANTI + 1, 
            NOME_LUOGO = NOME_TERMINE_ISCRIZIONE + 1, 
            NOME_DATA = NOME_LUOGO + 1, 
            NOME_ORA = NOME_DATA + 1, 
            NOME_DURATA = NOME_ORA + 1, 
            NOME_QUOTA_INDIVIDUALE = NOME_DURATA + 1, 
            NOME_COMPRESO_QUOTA = NOME_QUOTA_INDIVIDUALE + 1, 
            NOME_DATA_CONCLUSIVA = NOME_COMPRESO_QUOTA + 1, 
            NOME_ORA_CONCLUSIVA = NOME_DATA_CONCLUSIVA + 1, 
            NOME_NOTE = NOME_ORA_CONCLUSIVA + 1,
            NOME_TOLLERANZA_PARTECIPANTI = NOME_NOTE + 1, 
            NOME_DATA_RITIRO_ISCRIZIONE = NOME_TOLLERANZA_PARTECIPANTI + 1, 
            NOME_GENERE_GIOCATORI = NOME_DATA_RITIRO_ISCRIZIONE + 1, 
            NOME_ETA_MIN_MAX = NOME_GENERE_GIOCATORI + 1, 
            NOME_SPESA = NOME_ETA_MIN_MAX + 1, 
            NOME_MOTORIZZAZIONE = NOME_SPESA + 1, 
            NOME_POTENZA = NOME_MOTORIZZAZIONE + 1, 
            NOME_SPESA_CASCO = NOME_POTENZA + 1, 
            NOME_SPESA_TUTA = NOME_SPESA_CASCO + 1, 
            
            NOME_NO_DESCRIZIONE = NOME_SPESA_TUTA + 1, 
            
            DESC_TITOLO = NOME_NO_DESCRIZIONE + 1, // Evento
            DESC_NUM_PARTECIPANTI = DESC_TITOLO + 1, 
            DESC_TERMINE_ISCRIZIONE = DESC_NUM_PARTECIPANTI + 1, 
            DESC_LUOGO = DESC_TERMINE_ISCRIZIONE + 1, 
            DESC_DATA = DESC_LUOGO + 1, 
            DESC_ORA = DESC_DATA + 1, 
            DESC_DURATA = DESC_ORA + 1, 
            DESC_QUOTA_INDIVIDUALE = DESC_DURATA + 1, 
            DESC_COMPRESO_QUOTA = DESC_QUOTA_INDIVIDUALE + 1, 
            DESC_DATA_CONCLUSIVA = DESC_COMPRESO_QUOTA + 1, 
            DESC_ORA_CONCLUSIVA = DESC_DATA_CONCLUSIVA + 1, 
            DESC_NOTE = DESC_ORA_CONCLUSIVA + 1,
            DESC_TOLLERANZA_PARTECIPANTI = DESC_NOTE + 1, 
            DESC_DATA_RITIRO_ISCRIZIONE = DESC_TOLLERANZA_PARTECIPANTI + 1, 
            DESC_PARTITA_CALCIO = DESC_DATA_RITIRO_ISCRIZIONE + 1, // Partita di calcio
            DESC_GENERE_GIOCATORI = DESC_PARTITA_CALCIO + 1, 
            DESC_ETA_MIN_MAX = DESC_GENERE_GIOCATORI + 1, 
            DESC_SPESA = DESC_ETA_MIN_MAX + 1, 
            DESC_GOKART = DESC_SPESA + 1, 
            DESC_SPESA_CASCO = DESC_GOKART + 1, 
            DESC_SPESA_TUTA = DESC_SPESA_CASCO + 1, 
            
            NOTIFICA_PREAMBOLO = DESC_SPESA_TUTA + 1,
            NOTIFICA_EVENTO_CHIUSO = NOTIFICA_PREAMBOLO + 1, 
            NOTIFICA_EVENTO_FALLITO = NOTIFICA_EVENTO_CHIUSO + 1, 
            NOTIFICA_EVENTO_APERTO = NOTIFICA_EVENTO_FALLITO + 1,
            NOTIFICA_EVENTO_RITIRATO = NOTIFICA_EVENTO_APERTO + 1, 
            NOTIFICA_EVENTO_CONCLUSO = NOTIFICA_EVENTO_RITIRATO + 1, 
            NOTIFICA_INVITO_TITOLO = NOTIFICA_EVENTO_CONCLUSO + 1, 
            NOTIFICA_INVITO_MESSAGGIO = NOTIFICA_INVITO_TITOLO + 1, 
            
            MESSAGGIO_NO_UTENTI_INVITABILI = NOTIFICA_INVITO_MESSAGGIO + 1, 
            MESSAGGIO_ERR_INS_ETA = MESSAGGIO_NO_UTENTI_INVITABILI + 1, 
    
            BOTTONE_INVIA_INVITI = MESSAGGIO_NO_UTENTI_INVITABILI + 1, 
            
            CHECK_GOKARTS = BOTTONE_INVIA_INVITI + 1, 
            
            TOOL_ETA_MAX = CHECK_GOKARTS + 1;
            
    public static final String[] parole = 
    {
        // Nomi dei campi e eventi
            "Titolo", 
            "N° Partecipanti", 
            "Termine Iscrizione", 
            "Luogo", 
            "Data", 
            "Ora", 
            "Durata", 
            "Quota individuale", 
            "Compreso quota", 
            "Data conclusiva", 
            "Ora conclusiva", 
            "Note", 
            "Tolleranza partecipanti",
            "Data ritiro iscrizione",
            "Genere", 
            "Fascia di età", 
            "Spesa campo", 
            "Motorizzazione", 
            "Potenza", 
            "Costo del casco", 
            "Costo della tuta", 
        //
            "Nessuna descrizione presente",
        // Descrizioni
            "Titolo dell'evento", 
            "Numero massimo di partecipanti all'evento (almeno 2)", 
            "Data di scadenza per l'iscrizione", 
            "Luogo dove si terrà l'evento", 
            "Data di inizio dell'evento", 
            "Ora di inizio dell'evento (formato hh:mm)", 
            "Durata stimata dell'evento (formato gg:hh:mm)", 
            "Quota di denaro richiesta per partecipare", 
            "Quota di denaro già compresa (?)", 
            "Data di fine dell'evento", 
            "Ora di fine dell'evento", 
            "Note aggiuntive", 
            "Numero di partecipanti accettabili in più rispetto a num partecipanti (non negativo)", 
            "Data entro cui ci si può disiscrivere da un evento", 
            "Sport per lo più maschile o comunque sappiamo guidare ecco ...", 
            "Genere dei giocatori", 
            "Età minima e massima per partecipare (formato min-max)", 
            "Spesa per l'affitto per il campo da calcio", 
            "un giro in compagnia sui kart :)", 
            "Spesa per l'affitto del casco se l'utente non lo possiede", 
            "Spesa per l'affitto della tuta se l'utente non la possiede", 
            
        // Notifiche
            "Aggiornamento stato: ", 
            "Le iscrizioni sono chiuse: l'evento è confermato!\nIl tuo importo dovuto è di €", 
            "Evento fallito purtroppo: non ha raggiunto in tempo il minimo di partecipanti", 
            "L'evento è ora aperto alle iscrizioni",
            "L'evento è stato ritirato dal propositore", 
            "Evento concluso! hope you enjoyed =D",
        // Invito
            "Nuovo invito!", 
            "Sei stato invitato a questo evento!", 
        // Messaggi
            "Nessun utente da invitare", 
            "Errore nell'inserimento dell'età", 
        // Bottoni
            "Invia gli inviti", 
        // Checkbox
            "Go Karts", 
        // ToolTipText
            "Età massima"
    };
    
    public static String get(int indice) {
        return parole[indice];
    }
    
}
