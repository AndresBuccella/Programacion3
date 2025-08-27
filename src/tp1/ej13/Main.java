package tp1.ej13;

public class Main {

    public static String fibo(int veces){
        return fiboRecursivo(0, 1, veces);
    }

    public static String fiboRecursivo(int primero, int segundo, int cant){
        if (cant == 0)
            return primero + ", " + segundo;
        return primero + ", " + segundo + ", " + fiboRecursivo(primero + segundo, primero + 2 * segundo , cant - 1);
    }

    public static void main(String[] args) {
        System.out.println(fibo(10));
    }
}
