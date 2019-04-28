package meetle.eventi.campi;

public class CampoRange extends Campo<CampoRange.Range> {
       
    static class Range {
        private int min, max;

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }
        
        public Range(String stringaRange){
            System.out.println(stringaRange);
            String[] numeri = stringaRange.split("-");
            min = Integer.parseInt(numeri[0]);
            max = Integer.parseInt(numeri[1]);
            System.out.println(min+" e "+max);
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
    public void setValoreDaString(String stringaRange) {
        this.valore = new Range(stringaRange);
    }
    
}
