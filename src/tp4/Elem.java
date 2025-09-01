package tp4;

public class Elem {

    private int valor;
    private boolean norte;
    private boolean sur;
    private boolean este;
    private boolean oeste;

    public Elem(int valor, boolean norte, boolean sur, boolean este, boolean oeste){
        this.valor = valor;
        this.norte = norte;
        this.sur = sur;
        this.este = este;
        this.oeste = oeste;
    }
    public int getValor(){
        return this.valor;
    }
    public boolean getNorte(){
        return this.norte;
    }
    public boolean getSur(){
        return this.sur;
    }
    public boolean getEste(){
        return this.este;
    }
    public boolean getOeste(){
        return this.oeste;
    }
    
}
