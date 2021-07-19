package ca.cmpt213.a3.shapes;


import ca.cmpt213.a3.UI.Canvas;


public class TextBox extends Rectangle {
    String message;

    public TextBox(int x1, int y1, int x2, int y2, String msg) {
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
        int  xSpace = x2-x1-2;
        int ySpace = y2-y1-2;
        String[] words = message.split(" ");
        if(words.length>0){
            message = fillAppendAndPrepend(message,xSpace);
        }

        int messageIndex = 0;
        int messageLen = message.length();
        for (int j = y1 + 1; j < y2 - 1; j++) {
            for (int i = x1 + 1; i < x2 - 1; i++) {
                if (messageIndex < message.length()) {
                    canvas.setCellText(i, j, message.charAt(messageIndex));
                    messageIndex++;
                }
            }
        }
    }
    private void drawCeneteredCharacters(Canvas canvas){
        int  xSpace = x2-x1-2;
        int ySpace = y2-y1-2;

        int maxSize = xSpace*ySpace;
        //if(message.length()> maxSize){
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

        //}



        //System.out.println(fillAppendAndPrepend(words[0],xSpace));


    }
    public static String fillAppendAndPrepend(String s, int xSpace) {

        StringBuilder newStringBuilder = new StringBuilder();
        newStringBuilder.insert(0,s);
        int spaceTotal = xSpace-s.length();
        int spaceLeft;
        int spaceRight;
        if(spaceTotal%2==0){
            spaceLeft = spaceTotal/2;
            spaceRight = spaceLeft;
        }else{
            spaceLeft = (int)Math.ceil(spaceTotal/2)+1;
            spaceRight = (int)Math.floor(spaceTotal/2);
        }
        for (int i = 0; i < spaceLeft; i++) {
            newStringBuilder.insert(0," ");
        }
        for (int i = 0; i < spaceRight; i++) {
            newStringBuilder.append(" ");
        }
        return newStringBuilder.toString();
    }





}
