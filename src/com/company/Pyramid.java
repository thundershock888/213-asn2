package com.company;

public class Pyramid extends ShapeImp{

    Pyramid(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Canvas canvas) {
        /*float base = x2-x1;
        float height = y2-y1;

        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                int left =x1+ (int) Math.floor(((y2-j-1)*(base)/2/height));
                int right = x1+(int) Math.ceil((base-1)-(y2-j-1)*base/2/height);
                if(i >= left&& i <=right) {
                    canvas.setCellColor(i, j, color);
                }
                if(isBorder(i,j)){
                    canvas.setCellText(i,j,borderChar);
                }

            }
        }*/

        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                if(isInside(i,j)||isBorder(i,j)){
                    canvas.setCellColor(i, j, color);
                }
                if(isBorder(i,j)){
                    canvas.setCellText(i,j,borderChar);
                }
                }
            }


    }


    @Override
    public boolean isInside(int x, int y) {
        float base = x2 - x1;
        float height = y2 - y1;
        int left = x1 + (int) Math.floor(((y2 - y - 1) * (base) / 2 / height));
        int right = x1 + (int) Math.ceil((base - 1) - (y2 - y - 1) * base / 2 / height);
        if(x>left&&x<right){
            if(y>y1&&y<y2-1){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBorder(int x, int y) {
        float base = x2-x1;
        float height = y2-y1;
        int left =x1+ (int) Math.floor(((y2-y-1)*(base)/2/height));
        int right = x1+(int) Math.ceil((base-1)-(y2-y-1)*base/2/height);
        if(x == left || x == right&& y>=y1&&y<=y2-1||y == y2-1||(y ==y1&&x>=left&&x<=right)){
            return true;
        }
        return false;
    }
}
