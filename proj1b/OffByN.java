public class OffByN implements CharacterComparator{
    private int N;

    public OffByN(int n) {
        N = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = (int) (x - y);
        if (diff == N | diff == -N) {
            return true;
        }
        else {
            return false;
        }
    }
}
