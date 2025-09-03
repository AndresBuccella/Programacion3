package elResto;

public class Tarea {
    private String nombre;
    private int tiempo;
    public Tarea(String nombre, int tiempo){
        this.nombre = nombre;
        this.tiempo = tiempo;
    }
    public int getTiempo(){
        return this.tiempo;
    }
    public String toString(){
        return this.nombre;
    }
}
