package tp1.ej3;

public class Lista<T> {

    private Nodo<T> raiz;
    private Nodo<T> puntero;
    private int size;

    public Lista(T primerObjeto) {
        this.setRaiz(primerObjeto);
        this.size = 0;
    }

    private void setRaiz(T t) {
        this.raiz = new Nodo<T>(t);
    }

    private void setPuntero(Nodo<T> n) {
        this.puntero = n;
    }

    public T get(int index){
        //return lista.get(index);
        //Iterativa
        /*this.setPuntero(this.raiz);
        if(index >= 0 && index < this.size)
            for(int i = 0; i <= this.size; i++){
                if (i == index){
                    return this.puntero.getContent();
                }
                if(i < this.size)
                    this.setPuntero(this.puntero.getNext());
            }
        return null;*/

        //Recursiva
        if(this.size == 0 || index < 0 || index > this.size)
            return null;

        this.setPuntero(this.raiz);
        return this.getRecursivo(index);
    }
    private T getRecursivo(int pos){
        if(pos == 0) {
            return this.puntero.getContent();
        }
        this.getRecursivo(pos-1);
        return null;
    }

    public void insertFront(T t) {
        if(this.raiz == null)
            return;
        this.setPuntero(this.raiz);
        this.setRaiz(t);
        this.raiz.setNext(this.puntero);
        this.size++;
    }
    public T extractFront() {
        if(this.raiz == null)
            return null;

        this.setPuntero(this.raiz);
        this.raiz = this.raiz.getNext();
        this.puntero.setNext(null);
        this.size--;
        return this.puntero.getContent();
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public int size() {
        return this.size;
    }

    private String toStringRecursivo(Nodo<T> puntero){
        if(puntero.getNext() == null)
            return puntero.toString();
        return puntero + " " + this.toStringRecursivo(puntero.getNext());
    }

    public String toString() {
        if (this.size == 0)
            return "[]";
        //Recursiva
        this.setPuntero(this.raiz);
        return this.toStringRecursivo(this.puntero);
        //Iterativa
        /*
        String s = "";
        this.setPuntero(this.raiz);
        for(int i = 0; i <= this.size; i++){
            s += this.puntero.toString() + " ";
            if (this.puntero.getNext() != null)
                this.setPuntero(this.puntero.getNext());
        }
        return s;*/
    }

    public static void main(String[] args) {
        Lista<String> l = new Lista<String>("hola");
        l.insertFront("que tal");
        l.insertFront("adios");
        System.out.println(l);
    }
}
