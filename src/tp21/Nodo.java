package tp21;

public class Nodo<T>{

    private T content;
    private Nodo<T> right;
    private Nodo<T> left;

    public Nodo(T content) {
        this.content = content;
    }

    public void setRight(Nodo<T> right) {
        this.right = right;
    }
    public void setLeft(Nodo<T> left) {
        this.left = left;
    }

    public Nodo<T> getRight() {
        return this.right;
    }
    public Nodo<T> getLeft() {
        return this.left;
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
