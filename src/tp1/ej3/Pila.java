package tp1.ej3;

public class Pila<T> extends Lista<T>{

    public Pila(){ super(); }

    public void reverse(){
        //Recursivo
        if (this.top() == null || size() == 1) {
            return;
        }
        //Iterativo
        //this.reverseIterativo();

        //recursivo1
        this.reverseRecursivo1();
    }

    private void reverseRecursivo1() {
        if(this.top() == null){
            return;
        }
        T content = this.pop();

        this.reverseRecursivo1();
        this.insertLast(content);
    }
    private void insertLast(T content){
        if(this.top() == null){
            this.insertFront(content);
        }
        else{
            T contentAux = this.pop();
            this.insertLast(content);
            this.push(contentAux);
        }
    }

    public void reverseIterativo(){
        Pila p1 = new Pila();
        Pila p2 = new Pila();
        while(this.top() != null){
            p1.push(this.pop());
        }
        while(p1.top() != null){
            p2.push(p1.pop());
        }
        while(p2.top() != null){
            T content = (T) p2.pop();
            this.push(content);
        }
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
        Pila<String> p = new Pila<>();
        p.push("hola");
        p.push("que tal");
        p.push("adios");
        System.out.println(p);
        System.out.println("---");
        p.reverse();
        System.out.println(p);
    }
}
