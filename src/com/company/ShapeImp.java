package com.company;

import java.awt.*;

public abstract class ShapeImp implements Shape{
    int x1;
    int x2;
    int y1;
    int y2;
    char borderChar;
    Color color;

    ShapeImp(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x1+x2;
        this.y1 = y1;
        this.y2 = y1+y2;
        //borderChar = ' ';


    }
    public abstract void draw(Canvas canvas);

    public abstract boolean isBorder(int x, int y);

    public abstract boolean isInside(int x, int y);

    public int getLocationX(){
        return x1;
    }
    public int getLocationY(){
        return y1;
    }
    public char getBorderChar(){
        return borderChar;
    }
    public void setBorderChar(char c){
        borderChar = c;
    }
    public void setColor(Color c){
        color = c;
    }
    public Color getColor(){
        return color;
    }
    public int getHeight(){
        return y2;
    }
    public int getWidth(){
        return x2;
    }

}
