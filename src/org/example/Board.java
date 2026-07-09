package org.example;

class Board {

    private final Field[][] fields;
    
    public Board(int size) {
        fields = new Field[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = new Field(i, j);
            }
        }       
    }

    /**
     * Returns the 2D array of fields representing the current state of the board.
     *
     * @return a 2D array of {@code Field} objects, where each {@code Field} represents
     *         a specific position on the board.
     */
    public Field[][] getFields() {
        return fields;
    }

    /**
     * Checks if all ships on the board have been hit.
     *
     * @return true if all ships present on the board have been hit, false otherwise.
     */
    public boolean allShipsHit() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j].hasShip() && !fields[i][j].isHit()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Attempts to hit a field at the specified coordinates on the board.
     *
     * @param x The row index of the field to hit.
     * @param y The column index of the field to hit.
     * @return -1 if the coordinates are out of bounds,
     *         0 if no new hit was made (the field was already hit or no ship was present),
     *         1 if a ship is present at the field and was successfully hit.
     */
    public int hit(int x, int y) {
        // Check bounds
        if (x < 0 || x >= fields.length || y < 0 || y >= fields.length) return -1;

        if (fields[x][y].isHit()) return 0; // already hit

        fields[x][y].hit();

        return fields[x][y].hasShip() ? 1 : 0;
    }

    /**
     * Places a ship on the board at the specified location and orientation.
     *
     * @param ship        The identifier of the ship to be placed.
     * @param x           The starting row index for the ship.
     * @param y           The starting column index for the ship.
     * @param size        The size of the ship.
     * @param horizontal  If true, the ship is placed horizontally; if false, vertically.
     * @return            0 if the ship is successfully placed, 
     *                    -1 if the ship placement is out of bounds, 
     *                    or -2 if the target fields are already occupied.
     */
    public int placeShip(int ship, int x, int y, int size, boolean horizontal) {
        // Check bounds
        if (horizontal) {
            if (x < 0 || x + size > fields.length || y < 0 || y >= fields.length) {
                return -1;
            }
        } else {
            if (x < 0 || x >= fields.length || y < 0 || y + size > fields.length) {
                return -1;
            }
        }

        // Check if fields are already occupied
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                if (fields[x + i][y].hasShip()) {
                    return -2;
                }
            } else {
                if (fields[x][y + i].hasShip()) {
                    return -2;
                }
            }
        }

        // Place the ship
        for (int i = 0; i < size; i++) {
            if (horizontal) {
                fields[x + i][y].setShip(ship);
            } else {
                fields[x][y + i].setShip(ship);
            }
        }

        return 0;
    }
}
