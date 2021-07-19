package com.company;


public class TextBox extends Rectangle {
    String message;

    TextBox(int x1, int y1, int x2, int y2, String msg) {
        super(x1, y1, x2, y2);
        this.message = msg;
    }
    public void setMessage(String msg){
        message = msg;
    }
    public String getMessage(){
        return message;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int maxChars = (x2-x1-1);
        if(maxChars < 0){
            maxChars = 0;
        }
        int messageIndex = 0;
        int messageLen = message.length();





        for (int j = y1+1; j < y2-1 ; j++) {
            for (int i = x1+1; i < x2-1 ; i++) {
                if(messageIndex< message.length()) {
                    canvas.setCellText(i, j, message.charAt(messageIndex));
                    messageIndex++;
                }
            }
        }


    }

    @Override
    public boolean isBorder(int x, int y) {
        if(x == x1|| y == y1 || x == x2-1|| y== y2-1){
            return true;
        }
        return false;
    }

}
