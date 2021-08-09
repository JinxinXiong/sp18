public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int FACTOR = 2;
    private int n = 0;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 8;
        nextFirst = 7;
        nextLast = 0;
    }

    public void resize(int nsize) {
        T[] a = (T[]) new Object[nsize];

        int i = (nextFirst + 1) % size;
        int j = 0;
        while (j < size) {
            a[j] = items[i];
            j += 1;
            i  = (i + 1) % size;
        }

//        System.out.println("current capacity" + size + " to " + nsize);
        size = nsize;
        items = a;
        nextFirst = nsize - 1;
        nextLast = j;
    }

    public void addFirst(T item) {
        if (items[nextFirst] != null) {
            resize(size * FACTOR);
        }
        items[nextFirst] = item;
        nextFirst  = (nextFirst - 1 + size) % size;
        n += 1;
    }

    public void addLast(T item) {
        if (items[nextLast] != null) {
            resize(size * FACTOR);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % size;
        n += 1;
    }

    public boolean isEmpty() {
        return (items[(nextFirst + 1 + size) % size] == null) && (items[(nextLast - 1 + size) % size] == null);
    }

    public int size() {
        return n;
    }

    public void printDeque() {
        int i = (nextFirst + 1) % size;
        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i  = (i + 1) % size;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size > 16 && n / size < 0.25) {
            resize(size / FACTOR);
        }
        nextFirst = (nextFirst + 1) % size;
        T x = items[nextFirst];
        items[nextFirst] = null;
        n -= 1;
        return x;
    }

    public T removeLast() {
        if (size > 16 && n / size < 0.25) {
            resize(size / FACTOR);
        }
        nextLast = (nextLast - 1 + size) % size;
        T x = items[nextLast];
        items[nextLast] = null;
        n -= 1;

        return x;
    }

    public T get(int index) {
        return items[(nextFirst + 1 + index) % size];
    }
    public static void main(String[] args) {
        ArrayDeque<String> AD = new ArrayDeque<>();
        System.out.println(AD.isEmpty());

        AD.addLast("a");
        AD.addLast("b");
        AD.addFirst("c");
        AD.addLast("d");
        AD.addLast("e");
        AD.addFirst("f");
        System.out.println(AD.isEmpty());
        System.out.println(AD.size());
        System.out.println(AD.get(1));
        AD.printDeque();
    }
}
