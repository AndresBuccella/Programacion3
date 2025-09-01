package tp4;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
    
    private HashMap<Integer, HashSet<Arco<T>>> vertices;
    private int cantArcos;

    public GrafoDirigido(){
        this.vertices = new HashMap<>();
        this.cantArcos = 0;
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (vertices.containsKey(verticeId)) return;
        this.vertices.put(verticeId, new HashSet<>());
        this.cantArcos++;
    }

    @Override
    public void borrarVertice(int verticeId) {

		if(this.vertices.containsKey(verticeId)) {
			this.cantArcos = this.cantArcos - this.vertices.get(verticeId).size();
			this.vertices.remove(verticeId);
			for(HashSet<Arco<T>> setDeArcos : this.vertices.values()) {
				if (!setDeArcos.isEmpty()) {
                    Iterator<Arco<T>> it = setDeArcos.iterator();
                    while(it.hasNext()) {
                        Arco<T> arco = it.next();
                        if(arco.getDestino().equals(verticeId)) {
                            it.remove();
                            this.cantArcos--;                    
                        }
					}					
				}
			}
		}
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (!this.contieneVertice(verticeId1) || !this.contieneVertice(verticeId2)) return;

        Arco<T> nuevoArco = new Arco<T>(verticeId1, verticeId2, etiqueta);
        HashSet<Arco<T>> arcos = this.vertices.get(verticeId1);

        if (arcos.remove(nuevoArco)) this.cantArcos--; 

        arcos.add(nuevoArco);
        this.cantArcos++;
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if (!this.contieneVertice(verticeId1) || !this.contieneVertice(verticeId2)) return;

        Arco<T> arcoABorrar = new Arco<T>(verticeId1, verticeId2, null);
        this.vertices.get(verticeId1).remove(arcoABorrar);
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.vertices.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if (!this.contieneVertice(verticeId1) || !this.contieneVertice(verticeId2)) return false;

        Arco<T> arcoABuscar = new Arco<T>(verticeId1, verticeId2, null);
        return this.vertices.get(verticeId1).contains(arcoABuscar);
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if (!this.contieneVertice(verticeId1) || !this.contieneVertice(verticeId2)) return null;
        HashSet<Arco<T>> arcos = this.vertices.get(verticeId1);
        if(arcos == null || arcos.isEmpty()) return null;

        Iterator<Arco<T>> it = arcos.iterator();
        while (it.hasNext()) {
            Arco<T> arco = it.next();
            if (arco.getDestino().equals(verticeId2)) {
                return arco;
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return this.vertices.size();
    }

    @Override
    public int cantidadArcos() {
        return this.cantArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return this.vertices.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        return this.vertices.getOrDefault(verticeId, new HashSet<>())
                .stream()
                .map(Arco<T>::getDestino)
                .distinct()
                .iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        return this.vertices.values()
                .stream()
                .flatMap(HashSet::stream)
                .iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        return this.vertices.getOrDefault(verticeId, new HashSet<>()).iterator();
    }

    
}
