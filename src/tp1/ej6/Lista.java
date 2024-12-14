package tp1.ej6;

import java.util.Comparator;

public class Lista<T> implements Iterable<T>{

    private Nodo<T> raiz;
    private Nodo<T> ultimo;
    private int size;

    public Lista() {
        this.size = 0;
    }

    private void setRaiz(T t) {
        this.raiz = new Nodo<T>(t);
    }

    public T get(int pos){

        if(this.size == 0 || pos < 0 || pos >= this.size)
            return null;

        //Iterativa
        //return this.getIterativo(pos);

        //Recursiva
        return this.getRecursivo(this.raiz, pos);
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
        return this.getRecursivo(puntero.getNext(), pos - 1);
    }

    public void insertLast(T t){
        if(this.size == 0)
            this.insertFront(t);
        else{
            Nodo<T> nodo = new Nodo<>(t);
            this.ultimo.setNext(nodo);
            this.ultimo = this.ultimo.getNext();
            this.size++;
        }
    }
    public T getUltimo(){
        return this.ultimo.getContent();
    }

    public void reordenar(Comparator<T> comp){
        if(this.size == 0 || this.size == 1 || comp == null)
            return;
        Nodo<T> puntero = this.raiz;
        Nodo<T> punteroAux;
        for(int i = 0; i < this.size; i++){
            punteroAux = puntero;
            for(int j = i; j <= this.size; j++){
                if (comp.compare(punteroAux.getContent(), puntero.getContent()) <= 0){
                    //this.insertFront(punteroAux.getContent());
                    //Tiene que fijarse en el siguiente. Mas adelante lo hare
                }
                if(punteroAux != null)
                    punteroAux = punteroAux.getNext();
            }
            puntero = puntero.getNext();
        }
    }

    public void insertFront(T t) {
        if(this.raiz == null){
            this.setRaiz(t);
            this.ultimo = this.raiz;
        }
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
        this.size--;
        if(this.size == 0)
            this.ultimo = null;
        return content;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public int size() {
        return this.size;
    }

    public int indexOf(T elem){
        if(this.size == 0 || elem == null)
            return -1;
        Nodo<T> puntero = this.raiz;
        int pos = 0;
        while(puntero != null){
            if(elem.equals(puntero.getContent()))
                return pos;
            puntero = puntero.getNext();
            pos++;
        }
        return -1;
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

    @Override
    public Iterator iterator() {
        return new Iterator(this.raiz);
    }

    private class Iterator implements java.util.Iterator{

        private Nodo<T> puntero;

        public Iterator(Nodo<T> puntero){ this.puntero = puntero; }

        @Override
        public boolean hasNext() {
            return this.puntero != null;
        }

        @Override
        public T next() {
            T content = this.puntero.getContent();
            this.puntero = puntero.getNext();
            return content;
        }
    }

    public static void main(String[] args) {
        Lista<Integer> l = new Lista<Integer>();
        l.insertLast(1);
        l.insertLast(4);
        l.insertLast(6);
        l.insertLast(8);
        l.insertLast(12);
        l.insertLast(16);
        l.insertLast(20);
        l.insertLast(21);
        l.insertLast(27);
        l.insertLast(37);

        System.out.println(l);
        l.reordenar(Integer::compare);
        System.out.println(l);
        /*System.out.println("--Sig lista--");

        Lista<Integer> l2 = new Lista<Integer>();
        l2.insertLast(4);
        l2.insertLast(6);
        l2.insertLast(10);
        l2.insertLast(21);
        l2.insertLast(32);
        System.out.println(l2);

        Lista<Integer> l3 = new Lista<Integer>();*/
        /*int f = 0;
        for (Integer num : l){
            for (int i = f; i < l2.size(); i++){
                if (num.equals(l2.get(i))){
                    f = i;
                    l3.insertLast(num);
                    break;
                }
                if(num < l2.get(i))
                    break;
            }
        }*/
        /*
        int i = 0, j = 0;
        while (i < l.size() && j < l2.size()) {
            if (l.get(i).equals(l2.get(j))) {
                l3.insertLast(l.get(i));
                i++;
                j++;
            } else if (l.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println(l3);*/
    }
}
