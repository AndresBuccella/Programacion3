package tp21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArbolBinario<T extends Comparable<T>>{
    
    private Nodo<T> raiz;

    public ArbolBinario(){
        this.raiz = null;
    }
    public T getRoot(){return this.raiz.getContent();}
    public boolean hasElem(T i){
        Nodo<T> puntero = this.raiz;
        while(puntero!=null){
            if (puntero.getContent().equals(i))
                return true;
            if (puntero.getContent().compareTo(i) > 0) {
                puntero = puntero.getLeft();
            }else
            if (puntero.getContent().compareTo(i) < 0) {
                puntero = puntero.getRight();
            }
        }
        return false;
    }

    public boolean isEmpty(){ return this.raiz == null;}
    public boolean esHoja(Nodo<T> nodo){ return nodo.getLeft()==null && nodo.getRight()==null;}
    public boolean delete(T i){
        if (this.isEmpty()) return false;
        Nodo<T> nodoPadre = this.raiz;
        Nodo<T> nodoABorrar = this.raiz;
        if (nodoABorrar.getContent().equals(i)) {
            if (esHoja(nodoABorrar)) {this.raiz = null; return true;}
            if (nodoABorrar.getLeft() == null) {
                this.raiz = nodoABorrar.getRight();
                return true;
            }
            if (nodoABorrar.getRight() == null)
                this.raiz = nodoABorrar.getLeft();
            else
                deleteNodo(nodoPadre, nodoABorrar);
            
            return true;
        }
        while(nodoABorrar!=null){
            if (nodoABorrar.getContent().compareTo(i) > 0) {
                if (nodoABorrar.getLeft() == null) return false;
                nodoABorrar = nodoABorrar.getLeft();
            }else if (nodoABorrar.getContent().compareTo(i) < 0) {
                if (nodoABorrar.getRight() == null) return false;
                nodoABorrar = nodoABorrar.getRight();
            }
            if (nodoABorrar.getContent().equals(i)) {
                deleteNodo(nodoPadre, nodoABorrar);
                return true;
            }
            nodoPadre = nodoABorrar;
        }
        
        return false;
    }
    private void deleteNodo(Nodo<T> nodoPadre, Nodo<T> nodoABorrar){
        //Hoja
        if (esHoja(nodoABorrar)) {
            if (nodoPadre.getLeft()==nodoABorrar) {
                nodoPadre.setLeft(null);
            }else
                nodoPadre.setRight(null);
            return;
        }

        //sin hijos a la derecha
        if (nodoABorrar.getLeft() == null || nodoABorrar.getRight() == null){
            Nodo<T> hijo = (nodoABorrar.getLeft() != null) ? nodoABorrar.getLeft(): nodoABorrar.getRight();
            if (nodoPadre.getLeft() == nodoABorrar){
                nodoPadre.setLeft(hijo);
            }else{
                nodoPadre.setRight(hijo);
            }
            return;
        }
    
        Nodo<T> padreSuces = nodoABorrar;
        Nodo<T> hijoSuces = nodoABorrar.getLeft();
        while (hijoSuces.getRight() != null) {
            padreSuces = hijoSuces;
            hijoSuces = hijoSuces.getRight();
        }
        nodoABorrar.setContent(hijoSuces.getContent());
        if (padreSuces.getRight() == hijoSuces) {
            padreSuces.setRight(hijoSuces.getLeft());
        }else
            padreSuces.setLeft(hijoSuces.getLeft());
    }
    
    public void add(T elem){
        if (this.raiz != null){
            addPriv(this.raiz, elem);
        }else{
            Nodo<T> nn = new Nodo<T>(elem);
            this.raiz = nn;
        }
    }
    private void addPriv(Nodo<T> puntero, T elem){
        if(puntero.getContent().compareTo(elem) > 0){
            if (puntero.getLeft() == null){
                Nodo<T> nodo = new Nodo<T>(elem);
                puntero.setLeft(nodo);
            }
            else
                addPriv(puntero.getLeft(), elem);
        }else if (puntero.getContent().compareTo(elem) < 0) {
            if (puntero.getRight() == null){
                Nodo<T> nodo = new Nodo<T>(elem);
                puntero.setRight(nodo);                
            }
            else
                addPriv(puntero.getRight(), elem);
            }
    }
    
    public String toString(){
        if (this.raiz == null)
        return "";
        StringBuilder sb = new StringBuilder();
        toStringPriv(raiz, sb);
        return sb.toString().trim();
    }
    private void toStringPriv(Nodo<T> puntero, StringBuilder sb){
        sb.append(puntero.getContent().toString()).append(" ");
        if (puntero.getLeft()!=null) {
            toStringPriv(puntero.getLeft(), sb);
        }
        if (puntero.getRight()!=null) {
            toStringPriv(puntero.getRight(), sb);
        }
    }
   
    public int getHeight(){
        if (this.raiz == null) return 0;
        if (this.esHoja(this.raiz)) return 1;
        return getHeightRecursivo(this.raiz, 0);
    }
    private int getHeightRecursivo(Nodo<T> puntero, int nivelActual){

        if (esHoja(puntero)) return nivelActual;

        int maxNivel = nivelActual;

        if (puntero.getLeft()!=null) {
            int nivelIzquierda = getHeightRecursivo(puntero.getLeft(), nivelActual+1);
            if (nivelIzquierda > maxNivel) {
                maxNivel = nivelIzquierda;
            }
        }
        if (puntero.getRight()!=null) {
            int nivelDerecha = getHeightRecursivo(puntero.getRight(), nivelActual+1);
            maxNivel = Math.max(maxNivel, nivelDerecha);
        }

        return maxNivel;
    }
    
    public List<Nodo<T>> getLongestBranch(){
        //metodo void funcionando (mas claro pero mas ineficiente)
        /*LinkedList<Nodo<Integer>> ll = new LinkedList<>();
        LinkedList<Nodo<Integer>> resultList = new LinkedList<>();
        getLongestBranchPriv(this.raiz, ll, resultList);
        return resultList;*/

        //function funcionando (menos claro pero más eficiente)
        return getLongestBranchPrivRt(this.raiz);
    }

    private List<Nodo<T>> getLongestBranchPrivRt(Nodo<T> nodo){
        if (nodo == null) return new LinkedList<>();

        List<Nodo<T>> izquierda = getLongestBranchPrivRt(nodo.getLeft());
        List<Nodo<T>> derecha = getLongestBranchPrivRt(nodo.getRight());

        List<Nodo<T>> masLarga = (izquierda.size() > derecha.size()) ? izquierda : derecha;

        masLarga.add(0, nodo);

        return masLarga;
    }

/*
    public void getLongestBranchPriv(Nodo<Integer> puntero, List<Nodo<Integer>> ll, List<Nodo<Integer>> resultList){
        if (esHoja(puntero)) {
            ll.add(puntero);
            if (resultList.size()<ll.size()) {
                resultList.clear();
                resultList.addAll(ll);
            }
        }
        if(puntero.getLeft()!= null){
            if (!ll.contains(puntero))
                ll.add(puntero);
            getLongestBranchPriv(puntero.getLeft(), ll, resultList);
            ll.removeLast();
        }
        if(puntero.getRight()!= null){
            if (!ll.contains(puntero))
                ll.add(puntero);
            getLongestBranchPriv(puntero.getRight(), ll, resultList);
            ll.removeLast();
        }      
    }
*/

    public List<Nodo<T>> getFrontera(){
        LinkedList<Nodo<T>> frontera = new LinkedList<>();
        return getFronteraPriv(this.raiz, frontera);
    }
    private List<Nodo<T>> getFronteraPriv(Nodo<T> nodo, LinkedList<Nodo<T>> frontera){
        if (esHoja(nodo)) {
            frontera.add(nodo);
            return frontera;
        }
        if (nodo.getLeft()!=null) {
            getFronteraPriv(nodo.getLeft(), frontera);
        }
        if (nodo.getRight()!=null) {
            getFronteraPriv(nodo.getRight(), frontera);
        }
        return frontera;
    }
    public T getMaxElem(){
        return getMaxElemPriv(this.raiz);
    } 
    private T getMaxElemPriv(Nodo<T> nodo){
        while(nodo.getRight()!=null) 
            nodo = nodo.getRight();

        return nodo.getContent();
    }
    public List<Nodo<T>> getElemAtLevel(int level){
        if(level < 0) return new LinkedList<>();
        LinkedList<Nodo<T>> itemsAtLevel = new LinkedList<>();
        return getElemAtLevelPriv(this.raiz, itemsAtLevel, level, 0);
    }
    private List<Nodo<T>> getElemAtLevelPriv(Nodo<T> nodo, LinkedList<Nodo<T>> itemsAtLevel, int level, int pos){

        if (level == pos) {
            itemsAtLevel.add(nodo);
        }
        if (level > pos) {
            if (nodo.getLeft()!=null) {
                getElemAtLevelPriv(nodo.getLeft(), itemsAtLevel, level, pos+1);
            }
            if (nodo.getRight()!=null) {
                getElemAtLevelPriv(nodo.getRight(), itemsAtLevel, level, pos+1);
            }
        }
        return itemsAtLevel;
    }

    //EJERCICIO 2 Dado un árbol binario de búsquedas que almacena números enteros, 
    //implementar un algoritmo que retorne la suma de todos los nodos internos del árbol.
    public int pesoTotal(){
        return pesoTotalPriv(this.raiz);
    }
    private int pesoTotalPriv(Nodo<T> nodo){
        if (nodo == null) return 0;

        return ((int) nodo.getContent()) + 
            pesoTotalPriv(nodo.getLeft()) + 
            pesoTotalPriv(nodo.getRight());
    }

    /*Ejercicio 3
    Dado un árbol binario de búsqueda que almacena
    números enteros y un valor de entrada K, implementar un
    algoritmo que permita obtener un listado con los valores
    de todas las hojas cuyo valor supere K. Por ejemplo, para
    el árbol de la derecha, con un valor K = 8, el resultado
    debería ser [9, 11]. */

    public List<Nodo<T>> getHojasFiltro(int k){
        //funcional
        ArrayList<Nodo<T>> result = new ArrayList<>();
        getHojasFiltroPriv(this.raiz, result, k);
        //return result;

        //con streams
        return getFrontera().stream()
                .filter(n -> k < (int) n.getContent())
                .toList();
    }
    private void getHojasFiltroPriv(Nodo<T> nodo, ArrayList<Nodo<T>> result, int k){
        
        if (nodo == null) return;
        
        int content = (int) nodo.getContent();
        if(esHoja(nodo) && content > k) result.add(nodo);

        getHojasFiltroPriv(nodo.getLeft(), result, k);
        getHojasFiltroPriv(nodo.getRight(), result, k);
    }

    /*
     * Ejercicio 4
        Se posee un árbol binario (no de búsqueda), donde los nodos internos están vacíos, mientras
        que las hojas tienen valores enteros. Se debe implementar un método que recorra el árbol y
        coloque valores en los nodos vacíos (los nodos internos). El valor de cada nodo interno debe
        ser igual al valor de su hijo derecho, menos el valor de su hijo izquierdo. En caso de que el
        nodo tenga un solo hijo, el valor del hijo faltante se reemplaza por un 0. Por ejemplo, tomando
        como entrada el árbol de la izquierda, el árbol resultante debería quedar con los mismos
        valores que el de la derecha.
     */

    public void rellenarArbol(){

        rellenarArbolRec(this.raiz);
    }
    private int rellenarArbolRec(Nodo<T> nodo){
        
        if (nodo == null) return 0;
        if(esHoja(nodo)) return (int) nodo.getContent();
        
        int izq = rellenarArbolRec(nodo.getLeft());
        int der = rellenarArbolRec(nodo.getRight());

        Integer valor = der - izq;
        nodo.setContent((T) valor);
        return valor;
    }
    /*private Nodo<Integer> rellenarArbolRec(Nodo<Integer> nodo){
        
        if (nodo == null) return new Nodo<>(0);
        
        Nodo<Integer> izq = rellenarArbolRec(nodo.getLeft());
        Nodo<Integer> der = rellenarArbolRec(nodo.getRight());

        if (!esHoja(nodo)) {
            nodo.setContent(der.getContent() - izq.getContent());
        }
        return nodo;
    }*/

    /*Ejercicio 5
        Dado un árbol binario donde todos los nodos poseen un carácter, de manera que cada rama del
        árbol contiene una palabra, implementar un algoritmo que busque y retorne todas las palabras
        que posea exactamente N vocales (ni más ni menos). Por ejemplo, para el siguiente árbol, con
        una entrada de N = 1, el algoritmo debería retornar [“MAL”]. En cambio, para un N = 2, debería
        retornar [“MANA”, “MANO”, “MISA”].
    */

    public List<String> getWords(int n){
        //n == cantVocales
        List<String> resultList = new ArrayList<>();
        getWordsPriv(this.raiz, resultList, "", 0, n);
        return resultList;
    }
    private void getWordsPriv(Nodo<T> nodo, List<String> resultList, String palabra, int cantVocales, int n){
        if (nodo == null) {
            return;
        }
        String letra = nodo.getContent().toString();
        palabra += letra;
        if (esVocal(letra)) cantVocales++;
        if (esHoja(nodo)){
            if(cantVocales == n){
                resultList.add(palabra);
            }
            return;
        }
            
        if (cantVocales <= n) {
            getWordsPriv(nodo.getLeft(), resultList, palabra, cantVocales, n);
            getWordsPriv(nodo.getRight(), resultList, palabra, cantVocales, n);
        }
    }
    private boolean esVocal(String letra){
        return "aeiouAEIOU".contains(letra);
    }

    /*Ejercicio 6
    Se desea desarrollar una aplicación para mejorar la atención de una biblioteca en cuanto a la
    búsqueda de libros dentro del catálogo disponible. Cada libro estará compuesto por un
    identificador único y datos propios de los libros (título, autor, géneros, año de publicación,
    cantidad de ejemplares, etc.)
    Se sabe, además, que los libros nuevos se agregan al catálogo en horarios fuera de la atención
    al público.
    Se desean proveer los siguientes servicios:
    ● Obtener la cantidad de ejemplares de un libro dado su identificador único.
    ● Obtener todos los libros de un género dado.
    ● Obtener todos los libros publicados entre dos años de publicación dados.
    Responda y justifique:
    1) ¿Qué estructura de datos utilizaría para almacenar todos los libros en memoria dentro
    de la aplicación?
    2) ¿Cómo resolvería cada uno de los servicios solicitados? ¿Utilizaría alguna estructura
    adicional de acceso para mejorar el costo de respuesta de cada servicio? */
    public static void main(String[] args) {
        ArbolBinario<Integer> ab = new ArbolBinario<>();
        ab.add(100);
        ab.add(50);
        ab.add(80);
        ab.add(60);
        ab.add(70);
        ab.add(65);
        ab.add(55);
        ab.add(90);
        ab.add(40);
        ab.add(10);
        ab.add(140);
        ab.add(130);
        ab.add(20);
        ab.add(180);
        ab.add(110);
        ab.add(200);

        limpiarConsola();
        //System.out.println(ab);
        System.out.println(ab.getHojasFiltro(60));

    }
    public static void limpiarConsola() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
