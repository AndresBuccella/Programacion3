package tp3.ej1;

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
        System.out.println(gd);*/
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
        
    }
}
