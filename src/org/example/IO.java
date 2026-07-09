package org.example;

import static org.example.Config.BoardType;

interface IO {

    void outputWelcome();

    void outputGameStart();

    void outputGameOver(int winner);

    void outputHelp();

    void outputSetup(int player);

    Point inputHit(int player);

    BoardType inputBoardType();

    void outputBoards(Board[] boards, int showForPlayer);

    Location inputShipLocation(int shipSize);

    void outputError(String error);

    record Point(int x, int y) { }

    record Location(Point point, boolean horizontal) { }
}
