public class PairGenerator implements Iterator<Pair> {
    private Iterator<Integer> numbers;
    private boolean nextAvailable;
    private int first;
    private int second;
    
    public PairGenerator() {
        numbers = new NaturalNumbers(0);
        loadNext();
    }

    private void loadNext() {
        if (numbers.hasNext()) {
            first = numbers.next().intValue();
            if (numbers.hasNext()) {
                second = numbers.next().intValue();
                nextAvailable = true;
            }
        }
        nextAvailable = false;
    }
        
    public boolean hasNext() {
        if (!nextAvailable) {
            loadNext();
        }
        return nextAvailable;
    }

    public Pair next() {
        if (hasNext()) {
            final Pair retval = new Pair(first, second);
            loadNext();
            return retval;
        } else {
            throw new NoSuchElementException();
        }
    }
}

