package elResto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Backtracking {

	private CSVReader dataset;
    private Grafo<Integer> grafo;
    private Estado e;
    private int tiempo;

    public Backtracking(String path){
        this.grafo = new GrafoDirigido<>();
        this.tiempo = 0;
        this.dataset = new CSVReader(path);
        this.setGrafo(this.dataset);
    }

    private void setGrafo(CSVReader dataset){
        Iterator<Arco<Integer>> it = this.dataset.getTuneles();

        while (it.hasNext()) {
            Arco<Integer> arco = it.next();

            this.grafo.agregarVertice(arco.getOrigen());
            this.grafo.agregarVertice(arco.getDestino());
            
            this.grafo.agregarArco(arco.getOrigen(), arco.getDestino(), arco.getEtiqueta());
        }
    }
    
    //Implemente algoritmos por la técnica Backtracking para los siguientes problemas.
    /*
    Ejercicio 1.
        Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se abren solamente
        en un sentido. Una de las salas se denomina entrada y la otra salida. Construir un algoritmo que
        permita ir desde la entrada a la salida atravesando la máxima cantidad de salas. Idea: podría
        representar el problema mediante un grafo dirigido, donde cada nodo es una habitación, y cada
        puerta es un arco dirigido hacia otra habitación.
    */

    public List<Integer> getCaminoMasLargo(Integer entrada, Integer salida){
        List<Integer> caminoMasLargo = new LinkedList<>();
        List<Integer> camino = new LinkedList<>();
        HashSet<Integer> visitados = new HashSet<>();

        getCaminoMasLargo(entrada, salida, caminoMasLargo, camino, visitados);
        return caminoMasLargo;
    }

    public void getCaminoMasLargo(Integer actual, Integer salida, List<Integer> caminoMasLargo, List<Integer> camino, HashSet<Integer> visitados){
        visitados.add(actual);
        camino.add(actual);
        if (actual.equals(salida)) {
            if (caminoMasLargo.size() < camino.size()) {
                caminoMasLargo.clear();
                caminoMasLargo.addAll(camino);
            }
        }else{
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(actual);
            while (adyacentes.hasNext()) {
                Integer adyacente = adyacentes.next();
                if (!visitados.contains(adyacente)) {
                    getCaminoMasLargo(adyacente, salida, caminoMasLargo, camino, visitados);
                }
            }
        }
        visitados.remove(actual);
        camino.removeLast();
    }

    /*
    Ejercicio 2.
        Dado un laberinto consistente en una matriz cuadrada que tiene en cada posición un valor natural
        y cuatro valores booleanos, indicando estos últimos si desde esa casilla se puede ir al norte, este,
        sur y oeste, encontrar un camino de longitud mínima entre dos casillas dadas, siendo la longitud
        de un camino la suma de los valores naturales de las casillas por las que pasa. Idea: podría
        representarse el laberinto como una matriz, de objetos, donde cada objeto tiene el valor natural, y
        cuatro booleanos, uno para cada dirección a la que se permite ir desde allí.
    */
/* //Funcionaria si estuviesen todos los metodos implementados
    Elem[][] matElems = new Elem[10][10];
    public List<int[]> caminoMasCortoEnMatriz(Integer f1, Integer c1, Integer f2, Integer c2){
        //Acá se comprueba imaginariamente que están en rango
        List<int[]> camino = new LinkedList<>();
        List<int[]> caminoAux = new LinkedList<>();
        int[] origen = {f1, c1};
        int[] destino = {f2, c2};
        caminoMasCortoEnMatriz(origen, destino, camino, caminoAux);
        return camino;
    }

    public void caminoMasCortoEnMatriz (int[] actual, int[] destino, List<int[]> camino, List<int[]> caminoAux){
        caminoAux.add(actual);
        if (actual[0] == destino[0] && actual[1] == destino[1]) {
            int totalCamino = 0;
            int totalCaminoAux = 0;
            for (int[] coo1 : caminoAux) {
                totalCaminoAux += matElems[coo1[0]][coo1[1]].getValor();
            }
            for (int[] coo2 : camino) {
                totalCamino += matElems[coo2[0]][coo2[1]].getValor();
            }
            if (camino.isEmpty() || totalCamino > totalCaminoAux) {
                camino.clear();
                camino.addAll(caminoAux);
            }
        }else{
            int[] siguiente = new int[2];
            if (matElems[actual[0]][actual[1]].getNorte()) {
                siguiente[0] = actual[actual[0]-1];
                siguiente[1] = actual[actual[1]];
                if (!this.estaEnRango(siguiente[0], siguiente[1])) return;
                if (!matElems[siguiente[0]][siguiente[1]].getSur()) {
                    return;
                }
                boolean originalNorte = matElems[actual[0]][actual[1]].getNorte();
                boolean originalSur = matElems[siguiente[0]][siguiente[1]].getSur();
                matElems[actual[0]][actual[1]].setNorte(false);
                matElems[siguiente[0]][siguiente[1]].setSur(false);
                caminoMasCortoEnMatriz(siguiente, destino, camino, caminoAux);
                matElems[actual[0]][actual[1]].setNorte(originalNorte);
                matElems[siguiente[0]][siguiente[1]].setSur(originalSur);
            }
            if (matElems[actual[0]][actual[1]].getSur()) {
                siguiente[0] = actual[actual[0]+1];
                siguiente[1] = actual[actual[1]];
                if (!this.estaEnRango(siguiente[0], siguiente[1])) return;
                if (!matElems[siguiente[0]][siguiente[1]].getNorte()) {
                    return;
                }
                boolean originalSur = matElems[actual[0]][actual[1]].getSur();
                boolean originalNorte = matElems[siguiente[0]][siguiente[1]].getNorte();
                matElems[actual[0]][actual[1]].setSur(false);
                matElems[siguiente[0]][siguiente[1]].setNorte(false);
                caminoMasCortoEnMatriz(siguiente, destino, camino, caminoAux);
                matElems[actual[0]][actual[1]].setSur(true);
                matElems[siguiente[0]][siguiente[1]].setNorte(true);
            }
            if (matElems[actual[0]][actual[1]].getEste()) {
                siguiente[0] = actual[actual[0]];
                siguiente[1] = actual[actual[1]+1];
                if (!this.estaEnRango(siguiente[0], siguiente[1])) return;
                if (!matElems[siguiente[0]][siguiente[1]].getOeste()) {
                    return;
                }
                boolean originalEste = matElems[actual[0]][actual[1]].getEste();
                boolean originalOeste = matElems[siguiente[0]][siguiente[1]].getOeste();
                matElems[actual[0]][actual[1]].setEste(false);
                matElems[siguiente[0]][siguiente[1]].setOeste(false);
                caminoMasCortoEnMatriz(siguiente, destino, camino, caminoAux);
                matElems[actual[0]][actual[1]].setEste(originalEste);
                matElems[siguiente[0]][siguiente[1]].setOeste(originalOeste);
            }
            if (matElems[actual[0]][actual[1]].getOeste()) {
                siguiente[0] = actual[actual[0]];
                siguiente[1] = actual[actual[1]-1];
                if (!this.estaEnRango(siguiente[0], siguiente[1])) return;
                if (!matElems[siguiente[0]][siguiente[1]].getEste()) {
                    return;
                }
                boolean originalOeste = matElems[actual[0]][actual[1]].getOeste();
                boolean originalEste = matElems[siguiente[0]][siguiente[1]].getEste();
                matElems[actual[0]][actual[1]].setOeste(false);
                matElems[siguiente[0]][siguiente[1]].setEste(false);
                caminoMasCortoEnMatriz(siguiente, destino, camino, caminoAux);
                matElems[actual[0]][actual[1]].setOeste(originalOeste);
                matElems[siguiente[0]][siguiente[1]].setEste(originalEste);
            }
        }
        caminoAux.removeLast();
    }*/

    /*
    Ejercicio 3.
        Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las
        combinaciones de esos números tal que la suma sea igual a M.
    */

    public static List<List<Integer>> sumaConjuntos(List<Integer> conjunto, Integer m){
        List<List<Integer>> listaCombinaciones = new LinkedList<>();

        sumaConjuntos(conjunto, m, listaCombinaciones, new HashSet<>(), 0);

        return listaCombinaciones;
    }
    private static void sumaConjuntos(List<Integer> conjunto, int m, List<List<Integer>> listaCombinaciones, Set<Integer> combinacion, int index){
        int suma = combinacion.stream().mapToInt(Integer::intValue).sum();
        if (suma > m || conjunto.isEmpty()) return;
        if (suma == m) {
            listaCombinaciones.add(new LinkedList<>(combinacion));
            return;
        }
        for(int i = index; i < conjunto.size(); i++){
            int num = conjunto.get(i);
            combinacion.add(num);
            sumaConjuntos(conjunto, m, listaCombinaciones, combinacion, i+1);
            combinacion.remove(num);
        }
    }
    
    public static void main(String[] args) {
        List<Integer> hs = new LinkedList<>();
        hs.add(1);
        hs.add(2);
        hs.add(3);
        System.out.println(sumaConjuntos(hs, 6));
    }
    /*public static void main(String[] args) {
        Set<Integer> hs = new HashSet<>();
        hs.add(1);
        hs.add(2);
        hs.add(3);
        System.out.println(sumaConjuntos(hs, 6));*/
        
        
        
        
        
        
        /*Ejercicio 5
        List<Tarea> lista = new LinkedList<>();
        Tarea t1 = new Tarea("t1", 3);
        Tarea t2 = new Tarea("t2", 7);
        Tarea t3 = new Tarea("t3", 6);
        Tarea t4 = new Tarea("t4", 9);
        Tarea t5 = new Tarea("t5", 30);
        Tarea t6 = new Tarea("t6", 35);
        lista.add(t6);
        lista.add(t5);
        lista.add(t4);
        lista.add(t3);
        lista.add(t2);
        lista.add(t1);
        List<Tarea>[] aLists = asignarTareas(4, lista);
        for (int i = 0; i < aLists.length; i++) {
            System.out.println("Procesador " + i + ": " + aLists[i]);
        }*/

    //}
    /*
    Ejercicio 4.
        Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición
        en dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma.
    */
        /*
    Ejercicio 5.
        Asignación de tareas a procesadores. Se tienen m procesadores idénticos y n tareas con un
        tiempo de ejecución dado. Se requiere encontrar una asignación de tareas a procesadores de
        manera de minimizar el tiempo de ejecución del total de tareas.
    */
    
    public static List<Tarea>[] asignarTareas(Integer mProcesadores, List<Tarea> listTareas){
        List<Tarea>[] procesadores = (LinkedList<Tarea>[]) new LinkedList[mProcesadores];
        List<Tarea>[] posibleSolucion = (LinkedList<Tarea>[]) new LinkedList[mProcesadores];
        for(int i = 0; i < mProcesadores; i++){
            procesadores[i] = new LinkedList<>();
        }
        for(int i = 0; i < mProcesadores; i++){
            posibleSolucion[i] = new LinkedList<>();
        }
        asignarTareas(mProcesadores, listTareas, procesadores, posibleSolucion);

        return procesadores;
    }
    //Se repiten tareas
    /*private void asignarTareas(Integer mProcesadores, List<Tarea> listTareas, List<Tarea>[] procesadores){
        if (listTareas.isEmpty()) {
            
        }else{
            for(int i = 0; i < mProcesadores; i++){
                Tarea tarea = listTareas.removeFirst();
                procesadores[i].add(tarea);
                asignarTareas(mProcesadores, listTareas, procesadores);
                procesadores[i].removeLast();
                listTareas.addFirst(tarea);
            }
        }
    }*/
    private static int getMax(List<Tarea>[] arr){
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            int max = arr[i].stream().mapToInt(k -> k.getTiempo()).sum();
            if(result < max){
                result = max;
            }
        }
        return result;
        
    }
    private static boolean arrEstaVacio(List<Tarea>[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(!arr[i].isEmpty()){
                return false;
            }
        }
        return true;
    }
    private static boolean arrCompleto(List<Tarea>[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].isEmpty()){
                return false;
            }
        }
        return true;
    }
    private static void asignarTareas(Integer mProcesadores, List<Tarea> listTareas, List<Tarea>[] procesadores, List<Tarea>[] posibleSolucion){
        if (listTareas.isEmpty()) {
            int maxPosible = getMax(posibleSolucion);
            int maxResult = 0;
            if (arrEstaVacio(procesadores)) {
                maxResult = 1000000;
            }else{
                maxResult = getMax(procesadores);
            }
            if (arrCompleto(posibleSolucion) && maxResult > maxPosible) {
                System.out.println(maxResult + " " + maxPosible);
                for (int i = 0; i < posibleSolucion.length; i++) {
                    procesadores[i] = new LinkedList<>(posibleSolucion[i]);
                }
            }
        }else{
            Tarea tarea = listTareas.removeFirst();
            for(int i = 0; i < mProcesadores; i++){
                posibleSolucion[i].add(tarea);
                asignarTareas(mProcesadores, listTareas, procesadores, posibleSolucion);
                posibleSolucion[i].removeLast();
            }
            listTareas.addFirst(tarea);
        }
    }
    /*
    Ejercicio 6.
        Caballo de Atila. Por donde pisa el caballo de Atila jamás vuelve a crecer el pasto. El caballo fue
        directamente hacia el jardín de n x n casillas. Empezó su paseo por una casilla cualquiera y volvió
        a ella, es decir hizo un recorrido cerrado. No visitó dos veces una misma casilla, se movió de una
        casilla a otra vecina en forma horizontal o vertical, pero nunca en diagonal. Por donde pisó el
        caballo, el pasto jamás volvió a crecer. Luego de terminado el recorrido en algunas casillas
        todavía había pasto (señal de que en ellas no había estado el caballo). Escriba un algoritmo que
        deduzca el recorrido completo que hizo el caballo.
    */
    
    /*
    Ejercicio 7.
        Tablero mágico. Dado un tablero de tamaño n x n, construir un algoritmo que ubique (si es posible)
        n*n números naturales diferentes, entre 1 y un cierto k (con k>n*n), de manera tal que la suma de
        las columnas y de las filas sea igual a S.
    */
    
    
    /*
    Ejercicio 8.
        Colocar un entero positivo (menor que un cierto valor entero k dado) en cada casilla de una
        pirámide de base B (valor entero dado) de modo que cada número sea igual a la suma de las
        casillas sobre las que está apoyado. Los números de todas las casillas deben ser diferentes.
        Ver dibujo en tp
    */



    /*
    Ejercicio 9.
        Dado un tablero de 4 x 4, en cuyas casillas se encuentran desordenados los números enteros del
        1 al 15 y una casilla desocupada en una posición inicial dada, determinar una secuencia de pasos
        tal intercambiando números contiguos (en horizontal y en vertical) con la casilla desocupada, los
        números en el tablero queden ordenados (como muestra la figura) y la casilla desocupada quede
        en la posición 4,4.
        Ver dibujo en tp
    */
    
    
    /*
    Ejercicio 10
        Utilizando la técnica Backtraking, escriba un algoritmo que dado un conjunto de números
        enteros, devuelva (si existen) todos los subconjuntos de tamaño N (dado como parámetro),
        cuyas sumas sean exactamente cero.
        Por ejemplo dado el conjunto {-7, -3, -2, -1, 5, 8 } y N = 3, los subconjuntos que suman cero son:
        {-7, -1, 8} y {-3, -2, 5}.
    */




    /*
    Ejercicio 11
        El robot de limpieza necesita volver desde su posición actual hasta su base de carga. Dado que al
        robot le queda poca batería, desea encontrar el camino más corto. El robot dispone de un mapa
        de la casa representado como una matriz, donde cada celda es una posición de la casa. La matriz
        posee un 0 si la celda está vacía, o un 1 si la celda presenta algún obstáculo (por ejemplo, un
        mueble). Se desea encontrar entonces el camino más corto considerando que:
        - Desde una celda solo te puedes mover a las celdas contiguas (izquierda, derecha, arriba y
        abajo)
        - El robot sólo puede caminar por celdas libres (no por celdas con obstáculos)
        ¿Hay alguna poda que se pueda aplicar al algoritmo?
    */

/*
 * Ejercicio de final

Dado un grafo rotulado no dirigido aciclico, y los vértices v, w, z dados como parámetro, y los
siguientes problemas:

a) Obtener el camino más largo entre el vértice v y el vértice w que no pase por el vértice z.
b) Obtener el camino más corto entre el vértice v y el vértice w que no pase por el vértice z.

Diga para cada uno de los problemas:
Con qué técnica algoritmica lo resolvería para obtener de manera eficiente la solución
óptima. Y describa las características principales de dicha/s técnica/s.
Escriba en pseudo-código el algoritmo para cada problema.

Backtracking porque tengo que recorrer todos los caminos. Es el ejercicio 1 de este archivo con un par de modificaciones
*/

/*
 * Ejercicio 2 Problema de las Permutaciones.

Se tienen N elementos distintos (por ejemplo, una lista con los números 1, 2, y 3) y se

quiere obtener todas las formas distintas de colocar esos elementos, es decir, hay

que conseguir todas las permutaciones de los N elementos.

a) Muestre gráficamente cómo se construye el árbol de búsqueda.

b) Escriba en JAVA un algoritmo que use Backtracking para resolver el problema.

Por ejemplo, para los números {1, 2, 3} se deben obtener las siguientes permutaciones 
{1, 2, 3}, {1, 3, 2), (2, 1, 3}, {2, 3, 1), (3, 1, 2), (3, 2, 1}.

//Similar al ejercicio 3
public static List<List<Integer>> sumaConjuntos(List<Integer> conjunto, Integer m){
        List<List<Integer>> listaCombinaciones = new LinkedList<>();
        List<Integer> combinacion = new ArrayList<>();

        sumaConjuntos(conjunto, m, listaCombinaciones, combinacion);

        return listaCombinaciones;
    }
    private static int getSumaSet(List<Integer> s){
        int result = 0;
        Iterator<Integer> it = s.iterator();
        while (it.hasNext()) {
            int n = it.next();
            result+=n;
        }
        return result;
    }
    private static void sumaConjuntos(List<Integer> conjunto, int m, List<List<Integer>> listaCombinaciones, List<Integer> combinacion){
        int sum = getSumaSet(combinacion);
        if (m == sum) {
            listaCombinaciones.add(new LinkedList<>(combinacion));
        }else{
            if (m < sum) return;
            int i = 0;
            while(i<conjunto.size()){
                combinacion.add(conjunto.remove(i));
                sumaConjuntos(conjunto, m, listaCombinaciones, combinacion);
                conjunto.add(i, combinacion.removeLast()); //SI, ACTUALMENTE EXISTE REMOVELAST EN LIST
                i++;
            }
        }
    }
    public static void main(String[] args) {
        List<Integer> hs = new LinkedList<>();
        hs.add(1);
        hs.add(2);
        hs.add(3);
        System.out.println(sumaConjuntos(hs, 6));
    }
 */



}
