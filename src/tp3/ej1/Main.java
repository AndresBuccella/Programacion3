package tp3.ej1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {
    
    public static void main(String[] args) {
        /*GrafoDirigido<String> gd = new GrafoDirigido<>();
        gd.agregarArco(0, 1, "A");
        gd.agregarArco(1, 2, "A");
        gd.agregarArco(2, 3, "A");
        gd.agregarArco(3, 1, "A");
        gd.agregarArco(3, 0, "A");
        System.out.println(gd);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            Integer i = it.next();
            System.out.println(i);
            if(i % 2 == 0){
                list.add(list.get(list.size()-1)+1);
            }
        }
        LinkedList<HashSet<Integer>> list = new LinkedList<>();
        HashSet<Integer> n1 = new HashSet<>();
        HashSet<Integer> n2 = new HashSet<>();
        HashSet<Integer> n3 = new HashSet<>();
        n1.add(1);
        n1.add(2);
        n1.add(3);
        n1.add(4);
        n2.add(2);
        n3.add(1);
        n3.add(2);
        n3.add(3);
        n3.add(4);
        list.add(n1);
        list.add(n3);
        System.out.println(list.contains(n2));*/
/*        int[] arr = new int[100000000];
        arr[38524855] = 38524855;
        System.out.println(arr[38524855]);*/
        LinkedList<Integer> conjunto = new LinkedList<>();
        conjunto.add(50);
        conjunto.add(15);
        conjunto.add(52);
        conjunto.add(31);
        conjunto.add(40);
        conjunto.add(70);
        conjunto.add(100);
        conjunto.sort(Comparator.naturalOrder());
        System.out.println(conjunto);
    }
}
