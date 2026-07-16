package org.example;

import static org.example.Config.*;

class Game {

    private final BoardType boardType;
    private final Board[] boards;

    private final IO io;

    public Game(BoardType boardType, IO io) {
        this.boardType = boardType;
        this.boards = new Board[] {
                new Board(BOARD_SIZES.get(boardType)),
                new Board(BOARD_SIZES.get(boardType)),
        };
        this.io = io;
    }

    /**
     * Initiates and manages the main game loop, alternating turns between two players.
     */
    public void play() {
        setup();

        io.outputGameStart();

        var player = 0;
        var opponent = 1;

        while (true) {
            io.outputBoards(boards, -1);

            if (nextTurn(player, boards[opponent])) { // hit a ship
                if (boards[opponent].allShipsHit()) { // game over
                    break;
                }
                continue;
            }
            // switch players
            player = opponent;
            opponent = player == 0 ? 1 : 0;
        }

        io.outputBoards(boards, -1);
        io.outputGameOver(player);
    }

    private boolean nextTurn(int player, Board opponentBoard) {
        while (true) {
            var point = io.inputHit(player);
            var result = opponentBoard.hit(point.x(), point.y());

            if (result < 0) {
                io.outputError((result == -1 ? "out of bounds" : "unknown"));
                continue;
            }
            return result == 1;
        }
    }

    private void setup() {
        for (var player : new int[]{ 0, 1 }) {
            io.outputBoards(boards, player);
            io.outputSetup(player, false);

            int shipId = 0;
            var shipsSetup = SHIP_SIZES.get(boardType);

            for (var shipSize : shipsSetup) {
                while (true) {
                    var location = io.inputShipLocation(shipSize);
                    var result = boards[player].placeShip(
                            shipId + 1, location.point().x(), location.point().y(), shipSize, location.horizontal());

                    if (result < 0) {
                        io.outputError((result == -1 ? "out of bounds" : (result == -2 ? "occupied" : "unknown")));
                        continue;
                    }

                    shipId++;

                    io.outputBoards(boards, player);
                    io.outputSetup(player, shipId == shipsSetup.size());
                    break;
                }
            }
        }
    }
}
