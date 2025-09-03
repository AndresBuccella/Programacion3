package elResto;

import java.security.KeyStore.Entry;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Greedy {
    
    private List<Integer> conjunto;

    public Greedy(){
        this.conjunto = new LinkedList<>();
    }

    /*
     * Ejercicio 1
        Cambio de monedas. Dado un conjunto C de N tipos de monedas con un número ilimitado de
        ejemplares de cada tipo, se requiere formar, si se puede, una cantidad M empleando el mínimo
        número de ellas. Por ejemplo, un cajero automático dispone de billetes de distintos valores: 100$,
        25$, 10$, 5$ y 1$, si se tiene que pagar 289$, la mejor solución consiste en dar 10 billetes: 2 de
        100$, 3 de 25$, 1 de 10$ y 4 de 1$.
     */
    public static String obtenerCambioMonedas(List<Integer> conjunto, Integer cantidadM){
        conjunto.sort(Comparator.reverseOrder());
        Map<Integer, Integer> mapResult = new HashMap<>();
        int index = 0;
        while (cantidadM>0 && index < conjunto.size()) {
            int numAux = conjunto.get(index);
            int cant = 0;
            while (numAux<=cantidadM) {
                cant++;
                cantidadM-=numAux;
                mapResult.put(numAux, cant);
            }
            index++;
        }
        String result = "";
        for (Map.Entry<Integer, Integer> entrada : mapResult.entrySet()) {
            result+= "Se necesitan " + entrada.getValue() + " monedas de " + entrada.getKey() + " pesos" + "\n";
        }
        return result; //esta mal devolver un mapa. Ver despues que hacer
    }

    /*public static void main(String[] args) {
        List<Integer> monedas = Arrays.asList(100, 25, 10, 5, 1);
        System.out.println(obtenerCambioMonedas(monedas, 289));
    }*/


}
