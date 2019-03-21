package meetle.gui;

public class NomeValore {

    String nome, valore;

    public NomeValore(String nome, String valore) {
        this.nome = nome;
        this.valore = valore;
    }
    
    public NomeValore(String[] nomevalore) {
        this.nome = nomevalore[0];
        this.valore = nomevalore[1];
    }

    @Override
    public String toString() {
        return nome + ":" + valore;
    }
}
