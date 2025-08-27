package tp3.ej1;

public class Arco<T> {
    
    private Integer origen;
    private Integer destino;
    private T etiqueta;


    public Arco(Integer origen, Integer destino, T etiqueta){
        this.origen = origen;
        this.destino = destino;
        this.etiqueta = etiqueta;
    }

    public void setOrigen(Integer origen){
        this.origen = origen;
    }
    public Integer getOrigen(){
        return this.origen;
    }
    public void setDestino(Integer destino){
        this.destino = destino;
    }
    public Integer getDestino(){
        return this.destino;
    }
    public void setContent(T etiqueta){
        this.etiqueta = etiqueta;
    }
    public T getContent(){
        return this.etiqueta;
    }
    public String toString(){
        return "| " + this.getOrigen() + " <--> " + this.getDestino() + " |";
    }

    public boolean equals(Object o){
        try{
            Arco<T> a = (Arco<T>) o;
            if (this.getOrigen().equals(a.getOrigen()) && 
                this.getDestino().equals(a.getDestino()))
                return true;
            return false;
        }
        catch(Exception e){
            System.out.println("error en equals de arco");
            System.out.println(e);
            return false;
        }
    }
    public int hashCode(){
        return ((this.origen == null) ? 0 : this.origen.hashCode()) +
                ((this.destino == null) ? 0 : this.destino.hashCode());
    }
}
