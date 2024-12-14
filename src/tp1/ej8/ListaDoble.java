package tp1.ej8;

import java.util.ArrayList;
import java.util.Comparator;

public class ListaDoble<T> extends Lista<T> {
    public ListaDoble() { super(); }

    @Override
    protected void setRaiz(T t) {
        super.setRaiz(t);
        Nodo<T> raiz = super.getRaiz();
        raiz.getNext().setPrev(raiz);
    }

    @Override
    protected void setUltimo(T t) {
        Nodo<T> puntero = super.getUltimo();
        super.setUltimo(t);
        super.getUltimo().setPrev(puntero);
    }

    public void insertLast(T t){
        if(this.size() == 0)
            this.insertFront(t);
        else{
            this.setRaiz(t);
            this.setSize(this.size() + 1);
        }
    }

    public void reordenar(Comparator<T> comp){
        int size = this.size();
        if(size == 0 || size == 1 || comp == null)
            return;

        //Poco eficiente
        Nodo<T> puntero = this.getRaiz();
        Nodo<T> puntero2;
        Nodo<T> punteroMayor = puntero;
        for(int i = 0; i < size; i++){
            puntero2 = puntero;
            for(int j = i; j < size; j++){
                if (comp.compare(puntero2.getContent(), puntero.getContent()) > 0){
                    punteroMayor = puntero2;
                }
                puntero2 = puntero2.getNext();
            }
            Nodo<T> puntPrev = punteroMayor.getPrev();
            Nodo<T> puntNext = punteroMayor.getNext();
            if (puntPrev != null)
                puntPrev.setNext(puntNext);
            if(puntNext != null)
                puntNext.setPrev(puntPrev);
            punteroMayor.setNext(null);
            punteroMayor.setPrev(null);
            this.setRaiz(punteroMayor.getContent());
            punteroMayor = null;
            puntero = puntero.getNext();
        }
    }

    public void insertFront(T t) {
        Nodo<T> raiz = this.getRaiz();
        if(raiz == null){
            this.setRaiz(t);
            this.setUltimo(raiz.getContent());
        }
        else{
            this.setRaiz(t);
        }
        this.setSize(this.size() + 1);
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

    public ArrayList<T> getListaDeLaPrimeraPeroNoDeLaSegunda(Lista<T> lista1, Lista<T> lista2){
        ArrayList<T> result = new ArrayList<>();
        for(int i = 0; i < lista1.size(); i++){
            if(lista2.indexOf(lista1.get(i)) == -1)
                result.add(lista1.get(i));
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator(this.raiz);
    }
}
