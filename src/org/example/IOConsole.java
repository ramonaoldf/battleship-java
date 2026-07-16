package org.example;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static org.example.Config.BoardType;

class IOConsole implements IO {

    private final Scanner scanner = new Scanner(in);

    @Override
    public void outputWelcome() {
        clear();
        out.println("Welcome to Battleship!\n");
        out.println(
        """
                      |    |    |                
                     )_)  )_)  )_)                
                    )___))___))___)\\                
                   )____)____)_____)\\\\                
                 _____|____|____|____\\\\\\__                
        ---------\\                   /---------                
          ^^^^^ ^^^^^^^^^^^^^^^^^^^^^                
            ^^^^      ^^^^     ^^^    ^^
        """);
    }

    @Override
    public void outputGameStart() {
        out.println("Let's play!");
    }

    @Override
    public void outputGameOver(int winner) {
        out.println("Game over! Winner is player " + (winner + 1) + "!");
    }

    @Override
    public void outputHelp() {
        out.println("Help yourself!");
    }

    @Override
    public void outputSetup(int player, boolean setupComplete) {
        if (setupComplete) {
            sleep();
        }
        out.println("Player " + (player + 1) + ", setup your ships!");
    }

    @Override
    public BoardType inputBoardType() {
        out.print("Choose board type [S/M/L]: ");
        return BoardType.valueOf(scanner.nextLine().toUpperCase());
    }

    @Override
    public Point inputHit(int player) {
        out.print("Player " + (player + 1) + ", enter x and y: ");
        return new Point(scanner.nextInt(), scanner.nextInt());
    }

    @Override
    public void outputBoards(Board[] boards, int showForPlayer) {
        clear();
        // TODO would it be better to display only the opponent's board?
        for (var y = boards[0].getFields().length - 1; y >= 0; y--) {
            for (var b : new int[]{ 0, 1 }) {
                for (var x = 0; x < boards[b].getFields().length; x++) {
                    var field = boards[b].getFields()[x][y];

                    if (field.hasShip() && (field.isHit() || b == showForPlayer)) {
                        out.print(" " + field.getShip() + " ");
                    } else {
                        out.print(field.isHit() ? " X " : " ~ ");
                    }
                }
                out.print("          ");
            }
            out.println();
        }
        out.println();
    }

    @Override
    public Location inputShipLocation(int shipSize) {
        out.print("Enter ship[" + shipSize + "] location (x y horizontal[true/false]): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        boolean horizontal = scanner.nextBoolean();
        scanner.nextLine();
        return new Location(new Point(x, y), horizontal);
    }

    @Override
    public void outputError(String error) {
        out.println("Error: " + error);
    }

    private void clear() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
