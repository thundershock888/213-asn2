package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Maze {
    public int mazeState = 0;
    private int relicCount = 0;
    private int numRelicsNeeded = 3;
    private Tile player = new Tile(1, 1, "player");
    private Tile[][] tileArr = new Tile[Constants.height][Constants.width];
    private ArrayList<Tile> listOfSquares = new ArrayList<>();
    private ArrayList<Tile> listOfEdges = new ArrayList<>();
    private ArrayList<Tile> guardians = new ArrayList<>();
    private ArrayList<Tile> relics = new ArrayList<>();
    boolean revealToggle = false;

    public Maze() {
        generateMaze();
    }
    public boolean checkValidInput(String nextMove){
        nextMove.toLowerCase();
        if(!(nextMove.equals("w")||nextMove.equals("a")||nextMove.equals("s")||nextMove.equals("d")||nextMove.equals("c")||nextMove.equals("m")||nextMove.equals("?"))){
            System.out.println(nextMove + " is not a valid input, please try again");

            return false;
        }
        return true;
    }

    public void movePlayer(Scanner s) {
        if(mazeState == 0) {
            String nextMove = s.nextLine();
            //System.out.println(nextMove);
            while(!checkValidInput(nextMove)){
                nextMove = s.nextLine();
            }

            if (nextMove != "w" || nextMove != "a" || nextMove != "s" || nextMove != "d") {
                updateLocation(nextMove);
            }
            if (nextMove.equals("m")) {
                System.out.println("Toggled Fog of War");
                revealToggle = true;
            }
            if (nextMove.equals("c")) {
                System.out.println("Relics Needed set to 1");
                numRelicsNeeded = 1;
            }
            if (nextMove.equals("?")) {
                System.out.println("Showing Help: ");
                System.out.println(Constants.help);
                numRelicsNeeded = 1;
            }
        }
    }

    public void updateLocation(String nextMove) {
        int x = player.x;
        int y = player.y;
        if (nextMove.equals("d") && y + 1 < Constants.width - 2 && isEmpty(x, y + 1)) {
            player.y++;

        }
        if (nextMove.equals("s") && x + 1 < Constants.height - 2 && isEmpty(x + 1, y)) {
            player.x++;

        }
        if (nextMove.equals("w") && x - 1 > 0 && isEmpty(x - 1, y)) {
            player.x--;
        }
        if (nextMove.equals("a") && y - 1 > 0 && isEmpty(x, y - 1)) {
            player.y--;

        }
        for (Tile g:guardians
        ) {
            if(wouldKillPlayer(g.x, g.y)){
                System.out.println("You Touched a Guardian");
            }

        }
        for (int i = 0; i< relics.size();i++) {
            Tile r = relics.get(i);
            if(wouldTouchRelic(r.x, r.y)){
                relics.remove(i);
                if(relicCount == numRelicsNeeded){
                    System.out.println("You won with "+ numRelicsNeeded+ " Relics! ");
                    mazeState = 2;
                }
                placeObject(0,"relic");
                System.out.println("You Got a Relic, total relics = " + relicCount);
            }

        }

    }

    private boolean isEmpty(int x, int y) {

        if (tileArr[x][y].type.equals("empty")) {
            return true;
        }
        return false;
    }
    private boolean wouldTouchRelic(int x, int y){
        if(player.x == x&& player.y == y){
            relicCount++;
            return true;
        }
        return false;
    }





    public void printMaze() {
        boolean printedGameObj = false;
        int x = player.x;
        int y = player.y;
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                tileArr[i][j].viewed = true;
            }

        }

            for (int i = 0; i < Constants.height; i++) {
                for (int j = 0; j < Constants.width; j++) {
                    Tile tile = tileArr[i][j];
                    if (tile.x == player.x && tile.y == player.y) {
                        if(mazeState == 0){
                        System.out.print("@");
                        printedGameObj = true;
                        }else if (mazeState == 1){
                            System.out.print("X");
                            printedGameObj = true;
                        }else if(mazeState == 2){
                            System.out.print("W");
                            printedGameObj = true;
                        }
                    }
                    for (Tile g : guardians
                    ) {
                        if (g.x == tile.x && g.y == tile.y && !printedGameObj) {
                            System.out.print("!");
                            printedGameObj = true;
                        }

                    }
                    for (Tile r : relics
                    ) {
                        if (r.x == tile.x && r.y == tile.y && printedGameObj != true) {
                            System.out.print("^");
                            printedGameObj = true;
                        }

                    }
                    if (!printedGameObj) {
                        if (tile.viewed||revealToggle) {
                            if (tile.getType().equals("wall")) {
                                System.out.print("#");
                            }
                            if (tile.getType().equals("empty")) {
                                System.out.print(".");
                            }
                        }else if (!printedGameObj){
                            System.out.print(" ");
                    }
                    }
                    printedGameObj = false;
                }

                System.out.println();
            }
        System.out.println("Relic In Possesion: " + relicCount);
        System.out.println("Relics Needed: " + numRelicsNeeded);
        }



    private void generateMaze() {
        clearListOfSquares();
        initArr();
        fillEdges();
        makeTendrils();
        makeSomeCycles(10);
        addGameObjects();


    }


    public void moveGuardians() {
        for (Tile g : guardians
        ) {
            ArrayList<Tile> avail;
            avail = findAvailableTiles(g.x, g.y);
            int size = avail.size();
            if (size == 1) {
                g.lastXPos = g.x;
                g.lastYPos = g.y;
                g.x = avail.get(0).x;
                g.y = avail.get(0).y;

            } else {
                int random = betterRandom(0, size);
                if (avail.get(random).x == g.lastXPos && avail.get(random).y == g.lastYPos) {
                    random--;
                    if (random < 0) {
                        random += size;
                    }

                }
                g.lastXPos = g.x;
                g.lastYPos = g.y;
                g.x = avail.get(random).x;
                g.y = avail.get(random).y;

            }

        }
        for (Tile g:guardians
             ) {
            if(wouldKillPlayer(g.x, g.y)){
                System.out.println("Guardian Killed You");
            }

        }


    }
    private boolean wouldKillPlayer(int x, int y){
        if(x==player.x&&y==player.y){
            mazeState = 1;
            return true;
        }
        return false;
    }

    private ArrayList<Tile> findAvailableTiles(int x, int y) {
        ArrayList<Tile> temp = new ArrayList<>();
        if (x > 0) {
            Tile t = tileArr[x - 1][y];
            if (!t.type.equals("wall")) {
                temp.add(t);
            }
        }
        if (x < Constants.height - 1) {
            Tile t = tileArr[x + 1][y];
            if (!t.type.equals("wall")) {
                temp.add(t);
            }
        }
        if (y < Constants.width - 1) {
            Tile t = tileArr[x][y + 1];
            if (!t.type.equals("wall")) {
                temp.add(t);
            }
        }
        if (y > 0) {
            Tile t = tileArr[x][y - 1];
            if (!t.type.equals("wall")) {
                temp.add(t);
            }
        }
        return temp;
    }

    private void addGameObjects() {

        //Tile finish = new Tile(Constants.width-1, Constants.height-1, "finish");

        //tileArr[Constants.height-2][Constants.width-2] = finish;
        placeObject(0, "relic");
        placeObject(2, "guardian");


    }

    private void placeObject(int num, String type) {
        while (num >= 0) {
            int x = betterRandom(0, Constants.height);
            int y = betterRandom(0, Constants.width);
            while (!(isEmpty(x, y))||(player.x == x&&player.y == y)){
                x = betterRandom(0, Constants.height);
                y = betterRandom(0, Constants.width);
            }

            Tile obj = new Tile(x, y, type);

            if (type.equals("guardian")) {
                guardians.add(obj);
            }
            if (type.equals("relic")) {
                relics.add(obj);
            }
            num--;
        }
    }

    private int betterRandom(int low, int up) {
        return (int) (((Math.random() * up) + low));
    }

    private void makeSomeCycles(int numCycles) {
        while (numCycles > 0) {
            int x = 1 + (int) (Math.random() * (Constants.height - 3));
            int y = 1 + (int) (Math.random() * (Constants.width - 3));
            if (getWallAdjacentCount(x, y) == 2) {
                if ((tileArr[x + 1][y].type.equals("wall") && tileArr[x - 1][y].type.equals("wall")) || (tileArr[x][y - 1].type.equals("wall") && tileArr[x][y + 1].type.equals("wall"))) {
                    tileArr[x][y].type = "empty";
                    numCycles--;
                }
            }

        }


    }

    private int getWallAdjacentCount(int x, int y) {
        int wallAdjacentCount = 0;
        if (tileArr[x - 1][y].type.equals("wall")) {
            wallAdjacentCount++;
        }
        if (tileArr[x + 1][y].type.equals("wall")) {
            wallAdjacentCount++;
        }
        if (tileArr[x][y - 1].type.equals("wall")) {
            wallAdjacentCount++;
        }
        if (tileArr[x][y + 1].type.equals("wall")) {
            wallAdjacentCount++;
        }
        return wallAdjacentCount;
    }

    private void makeTendrils() {
        int failed = 0;
        int wallstart = 20;
        while (failed < 20000) {

            createTentril(wallstart);
            //printMaze();
            wallstart--;
            failed++;
        }
    }

    private void clearListOfSquares() {
        listOfSquares.clear();
    }

    private void createTentril(int wallStart) {
        int x = 0;
        int y = 0;
        if (wallStart > 0) {
            int randomStartIndex = (int) (Math.random() * listOfEdges.size());
            Tile startTile = listOfEdges.get(randomStartIndex);
            x = startTile.getX();
            y = startTile.getY();

        } else {
            int randomStartIndex = (int) (Math.random() * listOfSquares.size());
            Tile startTile = listOfSquares.get(randomStartIndex);
            x = startTile.getX();
            y = startTile.getY();
        }
        if (y % 2 == 0 && x % 2 == 0) {
            int countLen = 0;
            int spawnAttempts = 0;
            int failed = 0;
            while (failed < 10) {
                int direction = (int) (Math.random() * 4);
                while (direction == 0 && checkValidSquare(x + 1, y, direction)) {
                    failed = 0;
                    x++;
                    countLen++;
                    tileArr[x][y].type = "wall";
                    listOfSquares.add(tileArr[x][y]);
                    if (countLen > 1 && x % 2 == 0 && (int) (Math.random() * 4) > 0) {
                        direction = (int) (Math.random() * 4);
                        countLen = 0;
                    }

                }
                while (direction == 1 && checkValidSquare(x - 1, y, direction)) {
                    failed = 0;
                    x--;
                    countLen++;
                    tileArr[x][y].type = "wall";
                    listOfSquares.add(tileArr[x][y]);
                    if (countLen > 1 && x % 2 == 0 && (int) (Math.random() * 4) > 0) {
                        direction = (int) (Math.random() * 4);
                        countLen = 0;
                    }

                }
                while (direction == 2 && checkValidSquare(x, y + 1, direction)) {
                    failed = 0;
                    y++;
                    countLen++;
                    tileArr[x][y].type = "wall";
                    listOfSquares.add(tileArr[x][y]);
                    if (countLen > 1 && x % 2 == 0 && (int) (Math.random() * 4) > 0) {
                        direction = (int) (Math.random() * 4);
                        countLen = 0;
                    }
                }
                while (direction == 3 && checkValidSquare(x, y - 1, direction)) {
                    failed = 0;
                    y--;
                    countLen++;
                    tileArr[x][y].type = "wall";
                    listOfSquares.add(tileArr[x][y]);
                    if (countLen > 1 && x % 2 == 0 && (int) (Math.random() * 4) > 0) {
                        direction = (int) (Math.random() * 4);
                        countLen = 0;
                    }
                }
                failed++;
            }
        }


    }

    private boolean checkValidSquare(int x, int y, int direction) {
        if (x < 1 || x > Constants.height - 2) {
            return false;
        }
        if (y < 1 || y > Constants.width - 2) {
            return false;
        }
        int wallAdjacentCount = getWallAdjacentCount(x, y);
        int wallCornerCount = 0;

        if (direction == 0) {
            if (tileArr[x + 1][y - 1].type.equals("wall")) {
                wallCornerCount++;
            }
            if (tileArr[x + 1][y + 1].type.equals("wall")) {
                wallCornerCount++;
            }
        }
        if (direction == 1) {
            if (tileArr[x - 1][y - 1].type.equals("wall")) {
                wallCornerCount++;
            }
            if (tileArr[x - 1][y + 1].type.equals("wall")) {
                wallCornerCount++;
            }
        }
        if (direction == 2) {
            if (tileArr[x + 1][y + 1].type.equals("wall")) {
                wallCornerCount++;
            }
            if (tileArr[x - 1][y + 1].type.equals("wall")) {
                wallCornerCount++;
            }
        }
        if (direction == 3) {
            if (tileArr[x + 1][y - 1].type.equals("wall")) {
                wallCornerCount++;
            }
            if (tileArr[x - 1][y - 1].type.equals("wall")) {
                wallCornerCount++;
            }
        }
        if (wallAdjacentCount == 1 && wallCornerCount == 0) {
            return true;
        }
        return false;

    }

    private void initArr() {
        for (int i = 0; i < Constants.height; i++) {
            for (int j = 0; j < Constants.width; j++) {
                Tile tile = new Tile(i, j, "empty");
                tileArr[i][j] = tile;
            }
        }
    }

    private void fillEdges() {
        for (int i = 0; i < Constants.height; i++) {
            tileArr[i][0].type = "wall";
            tileArr[i][0].viewed = true;
            tileArr[i][Constants.width - 1].type = "wall";
            tileArr[i][Constants.width-1].viewed = true;
            listOfSquares.add(tileArr[i][0]);
            listOfSquares.add(tileArr[i][Constants.width - 1]);
            listOfEdges.add(tileArr[i][0]);
            listOfEdges.add(tileArr[i][Constants.width - 1]);
        }
        for (int i = 0; i < Constants.width; i++) {
            tileArr[0][i].type = "wall";
            tileArr[0][i].viewed = true;
            tileArr[Constants.height - 1][i].type = "wall";
            tileArr[Constants.height - 1][i].viewed = true;

            listOfSquares.add(tileArr[0][i]);
            listOfSquares.add(tileArr[Constants.height - 1][i]);
            listOfEdges.add(tileArr[0][i]);
            listOfEdges.add(tileArr[Constants.height - 1][i]);
        }

    }
    public boolean checkWinConditions(){
        if(mazeState == 2){
            System.out.println("you win");
            return true;
        }else if(mazeState == 1){
            System.out.println("you lost");
            return true;
        }
        return false;
}}

