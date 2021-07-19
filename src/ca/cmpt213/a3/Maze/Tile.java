package ca.cmpt213.a3.Maze;

public class Tile {
    int x;
    int y;
    String type;
    int lastXPos;
    int lastYPos;
    boolean viewed;

    public Tile(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.lastXPos = x;
        this.lastYPos = y;
        this. viewed = false;
    }
    public Tile(){
        type = "empty";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }

}
