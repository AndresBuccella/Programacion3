package tp2;

import java.util.ArrayList;
import java.util.Comparator;

public class Lista<T> implements Iterable<T> {

    private Nodo<T> raiz;
    private Nodo<T> ultimo;
    private int size;

    public Lista() {
        this.size = 0;
    }

    protected void setRaiz(T t) {
        Nodo<T> puntero = this.raiz;
        this.setRaiz(t);
        this.raiz.setNext(puntero);
    }
    protected Nodo<T> getRaiz() { return this.raiz; }

    protected void setUltimo(T t) {
        Nodo<T> puntero = this.ultimo;
        this.ultimo = new Nodo<T>(t);
        puntero.setNext(this.ultimo);
    }
    protected Nodo<T> getUltimo() { return this.ultimo; }

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
            this.setUltimo(t);
            this.size++;
        }
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
            this.setRaiz(t);
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
    public void setSize(int size) { this.size = size; }

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

    //1.a
    public static void seleccion(ArrayList<Integer> arr, int frontera){
        if(frontera == arr.size())
            return;

        int posMin = frontera;
        for (int i = frontera; i < arr.size(); i++){
            if (arr.get(i).compareTo(arr.get(frontera)) < 0){
                posMin = i;
            }
        }

        int aux = arr.get(frontera);
        arr.set(frontera, arr.get(posMin));
        arr.set(posMin, aux);

        seleccion(arr, ++frontera);
    }

    //1.b Estos metodos deberian llamarse en otro y este ser privado
    //asi quien lo consume no tiene que preocuparse por la frontera que nada tiene que ver y puede
    //romper el programa.
    //Tienen complejidad O(n^2) donde n es la cantidad de elementos y se eleva al cuadrado
    //porque por cada llamada se recorre todo el arreglo
    public static void burbujeo(ArrayList<Integer> arr, int frontera){

        if (frontera == arr.size() - 1)
            return;

        boolean cambio = false;

        for(int pos = arr.size() - 1; pos > frontera; pos--){
            if(arr.get(pos - 1).compareTo(arr.get(pos)) > 0){
                int aux = arr.get(pos - 1);
                arr.set(pos - 1, arr.get(pos));
                arr.set(pos, aux);
                cambio = true;
            }
        }

        if(cambio)
            return;

        burbujeo(arr, ++frontera);
    }

    //2.a
    private static void mergesort(ArrayList<Integer> arr, ArrayList<Integer> tempArray, int start, int end){
        if(start < end){
            int middle = (start + end)/2;
            mergesort(arr, tempArray, start, middle);
            mergesort(arr, tempArray, middle + 1, end);
            merge(arr, tempArray, start, middle, end);
        }
    }
    private static void merge(ArrayList<Integer> arr, ArrayList<Integer> tempArray, int start, int middle, int end){
        tempArray.clear();
        tempArray.addAll(arr);

        int leftIndex = start;
        int rightIndex = middle + 1;
        int currentIndex = start;

        while( leftIndex <= middle && rightIndex <= end){
            if(tempArray.get(leftIndex) <= tempArray.get(rightIndex)){
                arr.set(currentIndex, tempArray.get(leftIndex));
                leftIndex++;
            }else{
                arr.set(currentIndex, tempArray.get(rightIndex));
                rightIndex++;
            }
            currentIndex++;
        }

        while(leftIndex <= middle){
            arr.set(currentIndex, tempArray.get(leftIndex));
            currentIndex++;
            leftIndex++;
        }
        while(rightIndex <= end){
            arr.set(currentIndex, tempArray.get(rightIndex));
            currentIndex++;
            rightIndex++;
        }
    }

    //2.b
    public static void sort(ArrayList<Integer> arr) {
        quickSort(arr, 0, arr.size() - 1);

        //Mergesort
        //ArrayList<Integer> tempArray = new ArrayList<>();
        //mergesort(arr, tempArray, 0, arr.size() - 1);

        //burbujeo(arr, 0);

        //seleccion(arr, 0);
    }
    public static void quickSort (ArrayList<Integer> arr, int start, int end) {
        if (start < end) {
            // Particionar el arreglo y obtener la posición del pivote
            int pivotIndex = partition(arr, start, end);

            // Ordenar recursivamente las subpartes izquierda y derecha
            quickSort(arr, start, pivotIndex - 1);  // Subarreglo izquierdo
            quickSort(arr, pivotIndex + 1, end); // Subarreglo derecho
        }
    }

    private static int partition(ArrayList<Integer> arr, int start, int end) {
        int pivot = arr.get(end);  // Elegir el último elemento como pivote
        int indiceMenores = start - 1;        // Índice de elementos menores que el pivote

        for (int j = start; j < end; j++) {
            if (arr.get(j) <= pivot) {
                indiceMenores++;
                // Intercambiar arr[i] con arr[j]
                swap(arr, indiceMenores, j);
            }
        }

        // Colocar el pivote en su posición final
        swap(arr,indiceMenores + 1 ,end);

        return indiceMenores + 1; // Retornar la posición del pivote
    }
    private static void swap(ArrayList<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    public static void main(String[] args) {

        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(1);
        l.add(12);
        l.add(14);
        l.add(15);
        l.add(25);
        l.add(30);
        l.add(4);
        l.add(5);
        l.add(10);
        l.add(17);
        l.add(45);
        l.add(20);
        //seleccionEnUnaFuncion(l, 0);
        //seleccion(l);
        //burbujeo(l, 0);
        mergesort(l);
        System.out.println(l);
/*
        Lista<Integer> l2 = new Lista<Integer>();
        l2.insertLast(4);
        l2.insertLast(6);
        l2.insertLast(10);
        l2.insertLast(21);
        l2.insertLast(32);
        System.out.println(l2);
        System.out.println(l.getListaDeLaPrimeraPeroNoDeLaSegunda(l, l2));*/
/*
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
