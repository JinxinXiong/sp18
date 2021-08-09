public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();

        for(int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            d.addLast(c);
        }
        return d;
    }

    public boolean isPalindrome_helper(Deque<Character> d) {
        if (d.isEmpty() | d.size() == 1) {
            return true;
        }

        char first = d.removeFirst();
        char last = d.removeLast();

        if (first != last) {
            return false;
        }
        else {
            return isPalindrome_helper(d);
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindrome_helper(d);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        char first;
        char last;

        while (!d.isEmpty() && !(d.size()==1)) {
            first = d.removeFirst();
            last = d.removeLast();
            if (!cc.equalChars(first, last)){
                return false;
            }
        }
        return true;
    }

}
