import java.util.Iterator;

// Example: Pair(2, 3)
//
// for {
//   n1 <- nat()
//   n2 <- nat()
// } yield (n1, n2)
public class NaturalNumbers implements Iterator<Integer> {
    private int currentNumber;

    public NaturalNumbers(final int startingNumber) {
        currentNumber = startingNumber;
    }

    public boolean hasNext() {
        return true;
    }

    public Integer next() {
        return Integer.valueOf(currentNumber++);
    }

    public static void main(String[] args) {
        Iterator<Integer> it = new NaturalNumbers(0);
        for (int num = 0; num < 100 && it.hasNext(); num++) {
            System.out.println(it.next().intValue());
        }
    }
}
