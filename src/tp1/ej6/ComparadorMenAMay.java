package tp1.ej6;

import java.util.Comparator;

public class ComparadorMenAMay implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
