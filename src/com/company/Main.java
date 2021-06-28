package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println(Constants.help);
        Maze maze = new Maze();
	    maze.printMaze();
	     while(maze.mazeState == 0){
            maze.movePlayer(myObj);
            if(maze.mazeState ==0) {
                maze.moveGuardians();
                maze.checkWinConditions();
            }
            maze.printMaze();
	    }

    }
}
