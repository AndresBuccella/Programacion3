package tp1.ej3;

public class Lista<T> {

    private Nodo<T> raiz;
    private int size;

    public Lista() {
        this.size = 0;
    }

    private void setRaiz(T t) {
        this.raiz = new Nodo<T>(t);
    }

    public T get(int index){

        if(this.size == 0 || index < 0 || index > this.size)
            return null;

        //Iterativa
        //return this.getIterativo(index);

        //Recursiva
        return this.getRecursivo(this.raiz, index);
    }
    private T getIterativo(int pos){
        Nodo<T> puntero = this.raiz;
        for(int i = 0; i <= this.size; i++){
            if (i == pos){
                return puntero.getContent();
            }
            if(i < this.size)
                puntero = puntero.getNext();
        }
        return null;
    }

    private T getRecursivo(Nodo<T> puntero, int pos){
        if(pos == 0) {
            return puntero.getContent();
        }
        this.getRecursivo(puntero.getNext(), pos-1);
        return null;
    }

    public void insertFront(T t) {
        if(this.raiz == null)
            this.setRaiz(t);
        else{
            Nodo<T> puntero = this.raiz;
            this.setRaiz(t);
            this.raiz.setNext(puntero);
        }
        this.size++;
    }
    public T extractFront() {
        if(this.raiz == null)
            return null;

        Nodo<T> puntero = this.raiz;
        this.raiz = this.raiz.getNext();
        puntero.setNext(null);
        T content = puntero.getContent();
        puntero = null;
        this.size--;
        return content;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public int size() {
        return this.size;
    }

    private String toStringIterativo(){
        StringBuilder s = new StringBuilder();
        Nodo<T> puntero = this.raiz;
        for(int i = 0; i < this.size; i++){
            s.append(puntero.toString()).append(" ");
            puntero = puntero.getNext();
        }
        return s.toString().trim();
    }

    private String toStringRecursivo(Nodo<T> puntero){
        if(puntero.getNext() == null)
            return puntero.toString();

        return puntero.toString() + " " + this.toStringRecursivo(puntero.getNext());
    }

    public String toString() {
        if (this.size == 0)
            return "[]";

        //Recursiva
        return "[ " + this.toStringRecursivo(this.raiz) + " ]";

        //Iterativa
        //return this.toStringIterativo();
    }

    public static void main(String[] args) {
        Lista<String> l = new Lista<>();
        l.insertFront("hola");
        l.insertFront("que tal");
        l.insertFront("adios");
        System.out.println(l);
    }
}
