package com.company;

public class MazeShape extends ShapeImp{
    Maze maze;
    MazeShape(int x1, int y1, int x2, int y2, Maze maze){
        super(x1, y1, x2, y2);
        this.maze = maze;
    }
    @Override
    public void draw(Canvas canvas) {
        maze.drawMazeOnCanvas(canvas);
    }

    @Override
    public boolean isBorder(int x, int y) {
        return false;
    }

    @Override
    public boolean isInside(int x, int y) {
        return false;
    }
}
