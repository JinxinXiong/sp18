//package deque;



public class LinkedListDeque<T> {
    public class ListNode<T>{
        public T item;
        public ListNode prev;
        public ListNode next;

        public ListNode (T i) {
            item = i;
            prev = null;
            next = null;
        }
    }

    private int size = 0;
    private ListNode<T> sentFront;
    private ListNode<T> sentBack;

    public LinkedListDeque() {
        sentFront = new ListNode<>(null);
        sentBack = new ListNode<>(null);
        sentFront.next = sentBack;
        sentBack.prev = sentFront;
    }

    /** Add a node with value x to the end of the list**/
    public void addFirst(T item) {
        ListNode<T> newNode = new ListNode<>(item);
        ListNode<T> first = sentFront.next;
        newNode.next = first;
        newNode.prev = sentFront;
        first.prev = newNode;
        sentFront.next = newNode;
        size += 1;

    }
    public void addLast(T item) {
        ListNode<T> newNode = new ListNode<>(item);
        ListNode<T> secondLast = sentBack.prev;
        newNode.prev = secondLast;
        newNode.next = sentBack;
        secondLast.next = newNode;
        sentBack.prev = newNode;
        size += 1;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        ListNode<T> p = sentFront.next;

        while (p != sentBack) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0){
            return null;
        }

        ListNode<T> first = sentFront.next;

        sentFront.next = first.next;
        first.next.prev = sentFront;
        size -= 1;

        return first.item;
    }

    public T removeLast() {
        if (size == 0){
            return null;
        }

        ListNode<T> last = sentBack.prev;

        sentBack.prev = last.prev;
        last.prev.next = sentBack;
        size -= 1;

        return last.item;
    }

    public T get(int index) {
        if (index + 1 > size) {
            System.out.println("index out of bound");
            return null;
        }
        ListNode<T> p = sentFront.next;
        int i = 0;
        while (p != sentBack && i < index) {
            p = p.next;
            i += 1;
        }
        return p.item;
    }





//    public static void main(String[] args) {
//        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
//        lld.addLast(10);
//        lld.addFirst(15);
//        lld.addLast(20);
//        lld.printDeque();
//
////        lld.removeFirst();
////        lld.printDeque();
////
////        lld.removeLast();
////        lld.printDeque();
//        System.out.println(lld.get(1));
//    }


}
