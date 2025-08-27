package tp1.ej8;

public class Nodo<T> {

    private T content;
    private Nodo<T> next;
    private Nodo<T> prev;
    public Nodo(T content) {
        this.content = content;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }
    public void setPrev(Nodo<T> prev) {
        this.prev = prev;
    }

    public Nodo<T> getNext() {
        return this.next;
    }
    public Nodo<T> getPrev() {
        return this.prev;
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
