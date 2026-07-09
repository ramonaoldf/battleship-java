package org.example;

public final class Application {

    private final IO io = new IOConsole();

    private void run() {
        io.outputWelcome();
        io.outputHelp();

        var boardType = io.inputBoardType();
        // TODO setup players

        var game = new Game(boardType, io);

        game.play();
    }

    static void main(String[] args) {
        new Application().run();
    }
}
