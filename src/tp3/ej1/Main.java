package tp3.ej1;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    
    public static void main(String[] args) {
        GrafoDirigido<String> gd = new GrafoDirigido<>();
        gd.agregarArco(0, 1, "A");
        gd.agregarArco(1, 2, "A");
        gd.agregarArco(2, 3, "A");
        gd.agregarArco(3, 1, "A");
        gd.agregarArco(3, 0, "A");
        System.out.println(gd);
    }
}
