package meetle.eventi.campi;

public class CampoRange extends Campo<CampoRange.Range> {
       
    static class Range {
        private int min, max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }
        
        public Range(String range){
            String[] numeri = range.split("-");
            min = Integer.parseInt(numeri[0]);
            max = Integer.parseInt(numeri[1]);
        }

        @Override
        public String toString() { return min + "-" + max; }
    }
       
    public CampoRange(String nome, String descrizione, Range range) {
        super(nome, descrizione, range);
    }
    
    public CampoRange(String nome, String descrizione, int min, int max) {
        this(nome, descrizione, new Range(min, max));
    }
       
    public CampoRange(String nome, String descrizione) {
        this(nome, descrizione, null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = new Range(stringa);
    }
    
}