package tp3.ej1.ej2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.SequencedSet;
import java.util.Set;
import java.util.Stack;

import tp3.ej1.Arco;
import tp3.ej1.Grafo;
import tp3.ej1.GrafoDirigido;

public class Service<T> {
    

	private CSVReader dataset;
    private Grafo<Integer> grafo;
	private Estado estado;
    private int tiempo;

    private final int BLANCO = 0;
    private final int AMARILLO = 1;
    private final int NEGRO = 2;

    public Service(String path){
        this.grafo = new GrafoDirigido<>();
        this.tiempo = 0;
        this.dataset = new CSVReader(path);
		this.setGrafo(this.dataset);
    }

    private void setGrafo(CSVReader dataset){
        Iterator<Arco<Integer>> it =  this.dataset.getTuneles();

        while (it.hasNext()) {
            Arco<Integer> arco = it.next();

            this.grafo.agregarVertice(arco.getOrigen());
            this.grafo.agregarVertice(arco.getDestino());
            
            this.grafo.agregarArco(arco.getOrigen(), arco.getDestino(), arco.getEtiqueta());
        }
    }
    
    private HashMap<Integer, int[]> getAgenda(){
        HashMap<Integer, int[]> agendaVisitas = new HashMap<>();
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while(it.hasNext()) {
            Integer vertice = it.next();
            int[] nodoEstado = {
                0, // tiempo inicial
                0, // tiempo final
                this.BLANCO, //0=blanco, 1=amarillo, 2=negro
                0
            }; 
            agendaVisitas.put(vertice, nodoEstado);
        }
        return agendaVisitas;
    }

    /* Ej2
     * Implemente los recorridos Depth-First-Search y Breadth-First-Search
     */
    public List<Integer> dfs(){
        HashMap<Integer, int[]> agendaVisitas = this.getAgenda();
        List<Integer> verticesOrdenadosPorVisita = new Stack<>();
        for (Integer vertice : agendaVisitas.keySet()) {
            if (agendaVisitas.get(vertice)[2] == this.BLANCO){
                verticesOrdenadosPorVisita.addAll(DFS_Visit(vertice, agendaVisitas));
            }
        }
        this.tiempo = 0;
        return new ArrayList<>(verticesOrdenadosPorVisita);
    }
    private List<Integer> DFS_Visit(Integer vertice, HashMap<Integer, int[]> agendaVisitas){
        
        List<Integer> verticesOrdenadosPorVisita = new Stack<>();
        verticesOrdenadosPorVisita.add(vertice);

        tiempo++;
        agendaVisitas.get(vertice)[0] = tiempo;//tiempo inicial

        agendaVisitas.get(vertice)[2] = this.AMARILLO;

        Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
        while (it.hasNext()) {
            Integer destino = it.next();
            if (agendaVisitas.get(destino)[2] == this.BLANCO) 
                verticesOrdenadosPorVisita.addAll(DFS_Visit(destino, agendaVisitas));
        }

        agendaVisitas.get(vertice)[2] = this.NEGRO;

        tiempo++;
        agendaVisitas.get(vertice)[1] = tiempo; //tiempo final

        return verticesOrdenadosPorVisita;
    }
    
    public Iterator<Integer> bfs(){
        List<Integer> fila = new LinkedList<>();
        HashMap<Integer, int[]> agendaVisitas = this.getAgenda();
        for(Integer vertice : agendaVisitas.keySet()){
            if (agendaVisitas.get(vertice)[2] == BLANCO) {
                fila.addAll(bfs_Visit(vertice, agendaVisitas));
            }
        }
        this.tiempo = 0;
        return fila.iterator();
    }
    private List<Integer> bfs_Visit(Integer vertice, HashMap<Integer, int[]> agendaVisitas){
        agendaVisitas.get(vertice)[2] = AMARILLO;
        this.tiempo++;
        agendaVisitas.get(vertice)[0] = tiempo;

        List<Integer> listaHijos = new LinkedList<>();
        List<Integer> listaResultante = new LinkedList<>();
        listaHijos.add(vertice);
        while (!listaHijos.isEmpty()) {

            Integer actual = listaHijos.remove(0);
            listaResultante.add(actual);

            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(actual);
            while(adyacentes.hasNext()){
                Integer adyacente = adyacentes.next();
                if(agendaVisitas.get(adyacente)[2] == BLANCO){
                    agendaVisitas.get(adyacente)[2] = AMARILLO;
                    this.tiempo++;
                    agendaVisitas.get(adyacente)[0] = tiempo;
                    listaHijos.add(adyacente);
                }
            }
            agendaVisitas.get(actual)[2] = NEGRO;
            this.tiempo++;
            agendaVisitas.get(actual)[1] = tiempo;
        }
        
        return listaResultante;
    }

    /* Ej3
     * Implemente un algoritmo que determine si un grafo dirigido tiene algún ciclo.
     */

    public boolean tieneCiclo(){
        HashMap<Integer, int[]> agendaVisitados = this.getAgenda();
        for(Integer vertice : agendaVisitados.keySet()){
            if (agendaVisitados.get(vertice)[2] == BLANCO){
                if (tieneCicloPriv(vertice, agendaVisitados)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean tieneCicloPriv(Integer vertice, HashMap<Integer, int[]> agendaVisitados){
        agendaVisitados.get(vertice)[2] = AMARILLO;
        Iterator<Integer> it = this.grafo.obtenerAdyacentes(vertice);
        while (it.hasNext()) {
            Integer adyacente = it.next();
            if (agendaVisitados.get(adyacente)[2] == AMARILLO) 
                //if(agendaVisitados.get(vertice)[3] != adyacente.intValue())
                    return true;
                
            if (agendaVisitados.get(adyacente)[2] == BLANCO){
                //agendaVisitados.get(adyacente)[3] = vertice; //se guarda el padre para GND
                if (tieneCicloPriv(adyacente, agendaVisitados)){
                    return true;
                }
            }
        }
        agendaVisitados.get(vertice)[2] = NEGRO;
        return false;
    }

    /*Ej4
        Escribir un algoritmo que, dado un grafo dirigido y dos vértices i, j de este grafo, devuelva el
        camino simple (sin ciclos) de mayor longitud del vértice i al vértice j. Puede suponerse que
        el grafo de entrada es acíclico.
    */
    public List<Integer> caminoMasLargo(Integer i, Integer j){
        if (!this.grafo.contieneVertice(i) || !this.grafo.contieneVertice(j)) return new LinkedList<>();

        List<Integer> listResult = new LinkedList<>();
        List<Integer> listAux = new LinkedList<>();

        HashMap<Integer, int[]> agendaVisitados = this.getAgenda();
        caminoMasLargoPriv(i, j, agendaVisitados, listResult, listAux);
        return listResult;
    }
    private void caminoMasLargoPriv(Integer origen, Integer destino, HashMap<Integer, int[]> agendaVisitados, List<Integer> listResult, List<Integer> listAux){

        listAux.add(origen);
        agendaVisitados.get(origen)[2] = AMARILLO;

        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(origen);
        
        while(adyacentes.hasNext()){
            Integer adyacente = adyacentes.next();
            if (adyacente.equals(destino)) {
                listAux.add(adyacente);
                if (listResult.size() < listAux.size()) {
                    listResult.clear();
                    listResult.addAll(listAux);
                }
                listAux.removeLast();
            }
            if (agendaVisitados.get(adyacente)[2] == AMARILLO) continue;
            listAux.add(adyacente);
            caminoMasLargoPriv(adyacente, destino, agendaVisitados, listResult, listAux);
            listAux.removeLast();
        }
    }

    /*Ej5
        Escriba un algoritmo que dado un grafo G y un vértice v de dicho grafo, devuelva una lista
        con todos los vértices a partir de los cuales exista un camino en G que termine en v.
    */
    /*
     * Mi implementación no está mal pero no tiene en cuenta caminos por los que yo se haya pasado por
     * un vertice en el mismo camino. Ej: 1->2->3->4->2->5 siendo 1 el origen y 5 el destino.
     * Lo termina haciendo igual pero si tuviese en cuenta eso, tal vez sería más eficiente. 
     */

    public Iterator<Integer> tieneCaminoA(/*GRAFO que está en la clase*/ Integer v){

        HashSet<Integer> setResult = new HashSet<Integer>();
        HashMap<Integer, int[]> agendaVisitas = this.getAgenda();
        HashSet<Integer> camino = new HashSet<>();
        Iterator<Integer> vertices = this.grafo.obtenerVertices();
        while (vertices.hasNext()) {
            Integer vertice = vertices.next();
            if (!setResult.contains(vertice)) {
                tieneCaminoAPriv(v, vertice, setResult, agendaVisitas, camino);
            }
        }
        return setResult.iterator();
    }
    private void tieneCaminoAPriv(Integer destino, Integer vertice, HashSet<Integer> setResult, HashMap<Integer, int[]> agendaVisitas, HashSet<Integer> camino){
        camino.add(vertice);
        agendaVisitas.get(vertice)[2] = AMARILLO;
        
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
        while (adyacentes.hasNext()) {
            Integer adyacente = adyacentes.next();

            if (//agendaVisitas.get(vertice)[2] == AMARILLO && No funciona si contamos con que pueda pasar
                    //por un nodo ya visitado
                agendaVisitas.get(adyacente)[2] == AMARILLO) {
                continue;
            }
            if(agendaVisitas.get(adyacente)[2] == NEGRO){
                    continue;
            }
            
            if (adyacente.equals(destino)) {
                setResult.addAll(camino);
            }else{
                camino.add(adyacente);
                tieneCaminoAPriv(destino, adyacente, setResult, agendaVisitas, camino);
                camino.remove(adyacente);
                agendaVisitas.get(vertice)[2] = BLANCO;
            }
            
        }
        if (!setResult.contains(vertice)) {
            agendaVisitas.get(vertice)[2] = NEGRO;
        }
        camino.remove(vertice);
    }




    /* Ej 6
     * Supongamos una conexión entre computadoras (1, ... ,n) que se encuentra modelada
        mediante un grafo. Se requiere, si existe, dar una conexión entre dos computadoras a y b
        existentes sabiendo que la computadora i está fuera de servicio.
     */
//En DFS
    public Iterator<Integer> conexionSinPasarPor(Integer a, Integer b, Integer i){
        List<Integer> caminoResultado = new LinkedList<>();
        List<Integer> camino = new LinkedList<>();
        HashMap<Integer, int[]> agendaVisitas = this.getAgenda();
        agendaVisitas.get(i)[2] = AMARILLO; //No disponible
        conexionSinPasarPorPriv(a, b, i, agendaVisitas, camino, caminoResultado);
        
        return caminoResultado.iterator();
    }
    public boolean conexionSinPasarPorPriv(Integer actual, Integer destino, Integer fueraDeServ, 
                                        HashMap<Integer, int[]> agendaVisitas, List<Integer> camino, List<Integer> caminoResultado){
        agendaVisitas.get(actual)[2] = AMARILLO;
        camino.add(actual);
        if (actual.equals(destino)) {
            caminoResultado.addAll(camino);
            return true;
        }

        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(actual);
        while (adyacentes.hasNext()) {
            Integer adyacente = adyacentes.next();
            if (adyacente.equals(fueraDeServ)) continue;
            if (agendaVisitas.get(adyacente)[2] == BLANCO) {
                if (conexionSinPasarPorPriv(adyacente, destino, fueraDeServ, agendaVisitas, camino, caminoResultado)){
                    return true;
                }
            }
        }
        camino.removeLast();
        return false;
    }
//EN BFS
    public List<Integer> conexionSinPasarPor(Integer a, Integer b, Integer i) {
        Queue<Integer> cola = new LinkedList<>();
        Map<Integer, Integer> predecesor = new HashMap<>();
        Set<Integer> visitados = new HashSet<>();

        visitados.add(i); // fuera de servicio
        visitados.add(a);
        cola.add(a);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (actual == b) break;

            Iterator<Integer> it = this.grafo.obtenerAdyacentes(actual);
            while (it.hasNext()) {
                int ady = it.next();
                if (!visitados.contains(ady)) {
                    visitados.add(ady);
                    predecesor.put(ady, actual);
                    cola.add(ady);
                }
            }
        }

        if (!predecesor.containsKey(b) && a != b) return List.of(); // no hay camino

        // reconstruir camino
        LinkedList<Integer> camino = new LinkedList<>();
        for (Integer x = b; x != null; x = predecesor.get(x)) {
            camino.addFirst(x);
            if (x.equals(a)) break;
        }
        return camino;
    }
    /* Ej 7
     * Supongamos que una ciudad se encuentra modelada mediante un grafo, donde cada nodo
        es una esquina, y las aristas representan las calles. Diseñe un algoritmo tal que dadas dos
        esquinas, devuelva el camino más corto entre ambas de manera de caminar la menor
        cantidad de cuadras posible
     */
    /* Ej 8
     * Dados un grafo G con sus vértices rotulados con colores y dos vértices v1 y v2, escriba un
        algoritmo que encuentre un camino desde el vértice v1 al vértice v2 tal que no pase por
        vértices rotulados con el color rojo.
     */
    /* Ej 9
     * Dado un grafo no orientado que modela las rutas de la provincia de Buenos Aires, devolver
        todos los caminos alternativos que se pueden tomar para ir desde la ciudad de Buenos
        Aires a la ciudad de Tandil, considerando que en el tramo Las Flores-Rauch está cortado al
        tránsito.
     */
    /* Ej 10
     * Se dispone de un conjunto de tareas, donde cada tarea tiene un nombre, una descripción y
        una duración (medida en horas). Se sabe también que hay una dependencia en el orden
        posible en el cual se pueden ejecutar estas tareas y un tiempo de espera entre dos tareas
        consecutivas (también medido en horas). Por ejemplo, si la tarea B depende de la tarea A y
        tiene un tiempo de espera de 5 horas; significa que:
        ● B no puede ejecutarse antes que A y,
        ● B debe ejecutarse 5 horas después de haber finalizado la ejecución de A.
        Objetivo
        Implementar un algoritmo que obtenga la secuencia de ejecución crítica de estas tareas, es
        decir, la secuencia de tareas que resulta en el máximo tiempo empleado para su ejecución.
        Por ejemplo: si partimos de la siguiente configuración podemos encontrar el camino crítico
        en la secuencia de tareas [0, 2, 5, 6, 10], ya que su tiempo de ejecución es la duración de
        cada tarea más el tiempo de espera entre cada par de tareas: 70 horas.
        Ver práctico con diagrama.
     */



}
