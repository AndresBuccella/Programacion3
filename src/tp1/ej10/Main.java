package tp1.ej10;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static boolean estaOrdenado(ArrayList<Integer> arr){

        if (arr.size() <= 1)
            return true;

        return estaOrdenadoPriv(arr, 0, true, true);
    }
    private static boolean estaOrdenadoPriv(ArrayList<Integer> arr, int pos, boolean ascendente, boolean descendente){
        if(pos == arr.size()-1) {
            return ascendente || descendente;
        }
        if(arr.get(pos).compareTo(arr.get(pos+1)) < 1)
            ascendente = false;
        else
            descendente = false;

        if(!ascendente && !descendente)
            return false;

        return estaOrdenadoPriv(arr, ++pos, ascendente, descendente);
    }
    private static boolean estaOrdenadoPrivList(LinkedList<Integer> list, int pos, boolean ascendente, boolean descendente){
        if(pos == list.size()-1) {
            return ascendente || descendente;
        }
        if(list.get(pos).compareTo(list.get(pos+1)) < 1)
            ascendente = false;
        else
            descendente = false;

        if(!ascendente && !descendente)
            return false;

        return estaOrdenadoPrivList(list, ++pos, ascendente, descendente);
    }

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        System.out.println(arr + "\n");
        System.out.println("\n");
    }
    //1. ¿Qué complejidad O tiene? (La complejidad en el peor caso)
    //Tiene complejidad O(n) donde n es la cantidad de elementos en el arreglo

    //2. ¿Trae algún problema hacerlo recursivo? ¿Cuál?
    //No encontré ningún problema para hacerlo recursivo a menos que sea por crear una fun priv

    //3. ¿Qué cambiaría si la estructura fuera una lista en lugar de un arreglo?
    //Cambiaria la forma de recorrer y cambiaria la complejidad a O(n^2) ya que el get recorre todos los elementos
    //hasta la posicion por cada elemento
}
