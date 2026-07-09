package org.example;

class Field {

    public final int x;
    public final int y;

    private int ship;

    private boolean hit;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setShip(int ship) {
        this.ship = ship;
    }

    public boolean hasShip() {
        return ship != 0;
    }

    public int getShip() {
        return ship;
    }

    public void hit() {
        this.hit = true;
    }

    public boolean isHit() {
        return hit;
    }
}
