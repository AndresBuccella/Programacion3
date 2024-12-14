package tp1.ej9;

import java.util.ArrayList;

public class Main {

    public static boolean esPalindroma(String cadena){
        if(cadena.length() >= 2){
            if(cadena.charAt(0) == cadena.charAt(cadena.length()-1)){
                esPalindroma(cadena.substring(1, cadena.length()-1));
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        System.out.println(esPalindroma("neuqquen"));
    }
}
