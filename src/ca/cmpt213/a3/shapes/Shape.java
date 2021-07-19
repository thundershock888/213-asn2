package ca.cmpt213.a3.shapes;

import ca.cmpt213.a3.UI.Canvas;

import java.awt.*;

public interface Shape {
    public void draw(Canvas canvas);

    public Color getColor();

    public void setColor(Color c);

    public char getBorderChar();

    public void setBorderChar(char c);

    public int getLocationX();

    public int getLocationY();



}
