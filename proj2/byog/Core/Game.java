package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.io.File;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        InputSource inputSource = new StringInputSource(input);
        StringBuilder s = new StringBuilder();
        TETile[][] finalWorldFrame = null;
        boolean setSeed = false;
        while (inputSource.possibleNextInput()) {
            char c = inputSource.getNextKey();
            if (c == 'N') {
                System.out.println("N (New Game)");
            } else if (c == 'S') {
                if (setSeed) {
                    System.out.println("S (Set Seed)");
                } else {
                    setSeed = true;
                    long seed = seedFromString(s);
                    finalWorldFrame = getRandomFrame(seed);
                }
            } else if (c == ':') {
                if (inputSource.possibleNextInput()) {
                    c = inputSource.getNextKey();
                    if (c == 'q') {
                        Utils.writeObject(Utils.join(CWD, "saved game"), finalWorldFrame);
                        break;
                    }
                }
            } else if ('0' <= c  && c <= '9'){
                s.append(c);
            } else if (c == 'l') {
                finalWorldFrame = Utils.readObject(Utils.join(CWD, "saved game"), TETile[][].class);
            } else {
                System.out.println(c);
            }
        }
        return finalWorldFrame;
    }

    public static TETile[][] getRandomFrame(long s) {
        RandomWorld.setSeed(s);

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        RandomWorld.fillWithRandomTiles(finalWorldFrame);
        return finalWorldFrame;
    }

    public static long seedFromString(StringBuilder s) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("Seed should be positive number!");
        }
        long seed = Long.parseLong(s.toString());
        return seed;
    }

    public static void main(String[] args) {
        TETile[][] world = playWithInputString("l");
        int width = world.length;
        int height = world[0].length;

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        ter.renderFrame(world);
    }
}
