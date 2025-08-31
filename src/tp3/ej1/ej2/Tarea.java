package tp3.ej1.ej2;

public class Tarea {

    private String nombre;
    private String descrip;
    private int duracion;

    public Tarea(String nombre, String descrip, int duracion){
        this.nombre = nombre;
        this.descrip = descrip;
        this.duracion = duracion;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getDescrip(){
        return this.descrip;
    }
    public int getDuracion(){
        return this.duracion;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setDescrip(String descrip){
        this.nombre = descrip;
    }
    public void setDuracion(int duracion){
        this.duracion = duracion;
    }
    
}
