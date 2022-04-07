import java.io.File;
import java.util.Scanner;

public class Assignment6Driver {
    public static void main(String[] args) {

        testGame();
//        playGame("moves1.txt");
//        System.out.println();
//        playGame("moves2.txt");
    }

    private static void playGame(String filename) {
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            HexGame game = new HexGame(11);
            while (input.hasNextLine()) {
                int moveBlue = input.nextInt();
                int moveRed = input.nextInt();
                if (game.playBlue(moveBlue, false)) {
                    System.out.println("Blue wins with move at position " + moveBlue + "!");
                    printGrid(game);
                    return;
                }
                if (game.playRed(moveRed, false)) {
                    System.out.println("Red wins with move at position " + moveRed + "!");
                    printGrid(game);
                    return;
                }
            }
        } catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }

    private static void testGame() {
        HexGame game = new HexGame(11);

        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

        printGrid(game);
    }

    private static void printGrid(HexGame game) {
        for (int i=0; i < game.board.size(); i++) {
            String format = "%" + i + "s";
            System.out.println();
            if (i != 0) {
                System.out.printf(format, " ");
            }
            for (int j=0; j < game.board.get(i).size(); j++) {
                System.out.printf("%s ", game.board.get(i).get(j));
            }
        }
    }
}
