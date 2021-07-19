package com.company;

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
