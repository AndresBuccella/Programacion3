package tp1.ej12;

public class Main {

    public static String decToBin(int numDec){
        int resto = numDec % 2;
        numDec = numDec / 2;
        if(numDec == 1){
            return (resto!=0)? resto + " " + numDec : " " + numDec;

        }
        return decToBin(numDec) + " " + resto;
    }


    public static void main(String[] args) {
        System.out.println(decToBin(15));
    }
}
