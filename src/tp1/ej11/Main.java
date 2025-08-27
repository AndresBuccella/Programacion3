package tp1.ej11;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static boolean get(ArrayList<Integer> arr, int elem){
        if(arr.size() == 0)
            return false;

        return getRecursivo(arr, elem, 0);
    }
    private static boolean getRecursivo(ArrayList<Integer> arr, int elem, int pos){
        if(pos == arr.size())
            return false;
        if(arr.get(pos) > elem)
            return false;
        if(arr.get(pos).equals(elem))
            return true;

        pos += 1;
        return getRecursivo(arr, elem, pos);
    }


    public static void main(String[] args) {

    }
}
