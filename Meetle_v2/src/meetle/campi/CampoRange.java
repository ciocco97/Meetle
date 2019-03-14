package meetle.campi;

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
       
//    public CampoRange(String nome, String descrizione) {
//        super(nome, descrizione);
//    }
    
    private CampoRange(String nome, String descrizione, Range range) {
        super(nome, descrizione, range);
    }
    
    public CampoRange(String nome, String descrizione, int min, int max) {
        this(nome, descrizione, new Range(min, max));
    }
    
    private CampoRange(Range range){
        this("Campo Range", NO_DESCRIPTION, range);
    }
    
    public CampoRange(int min, int max) {
        this(new Range(min, max));
    }
       
    public CampoRange() {
        this(null);
    }

    @Override
    public void setValoreDaString(String stringa) {
        this.valore = new Range(stringa);
    }
    
}
