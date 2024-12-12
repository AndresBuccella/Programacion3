package tp1.ej3;

public class Pila<T> extends Lista<T>{

    public Pila(T primerObjeto) {
        super(primerObjeto);
    }

    public Nodo<T> reverseRecursivo() {
        if (this.isEmpty())
            return null;
        T content = this.pop();
        Nodo<T> nodo = new Nodo(content);
        Nodo<T> nuevaRaiz = null;

        Nodo<T> nuevoNodo = this.reverseRecursivo();
        if (nuevoNodo == null)
            this.insertFront(nodo.getContent());
        else
            nuevoNodo.setNext(nodo);
        return nodo;
    }
    public void reverse(){
        //Recursivo
        this.reverseRecursivo();
        this.insertFront(this.get(0));



        //Iterativo no funciona
        /*while(nodo != null){
            this.insertFront(nodo.getContent());
            nodo = nodo.getNext();
        }
        Nodo<T> antNodo = null;
        if(sigNodo != null)
            antNodo = new Nodo(super.extractFront());
        antNodo.setNext(sigNodo);*/
    }

    public T top(){
        return super.get(0);
    }

    public T pop(){
        return super.extractFront();
    }

    public void push(T nuevoObjeto){
        super.insertFront(nuevoObjeto);
    }

    public static void main(String[] args) {
        Pila<String> p = new Pila("hola");
        p.push("que tal");
        p.push("adios");
        p.reverse();
        System.out.println(p);
    }
}
