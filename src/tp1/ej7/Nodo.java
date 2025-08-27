package tp1.ej7;

public class Nodo<T> {

    private T content;
    private Nodo<T> next;
    public Nodo(T content) {
        this.content = content;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }

    public Nodo<T> getNext() {
        return this.next;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content){
        this.content = content;
    }

    public String toString(){
        return this.content.toString();
    }
    public boolean equals(Object o){
        try {
            Nodo<T> nodoNuevo = (Nodo<T>) o;
            return this.content.equals(nodoNuevo.getContent());
        }catch (ClassCastException e){
            return false;
        }
    }
}
