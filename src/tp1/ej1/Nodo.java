package tp1.ej1;

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
}
