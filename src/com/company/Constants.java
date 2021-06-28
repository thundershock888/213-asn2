package com.company;

public class Constants {
    public static final int width = 21;
    public static final int height = 17;
    public static final String help = "The player enters a move using one of the W (up), A (left), S (down), D (right) keys (both upper case and lower cases are acceptable).\n" +
            "▪ ‘?’ to reveal help that is shown at the start of the game.\n" +
            "▪ ‘m’ to display the entire map. This will be used during marking to evaluate the\n" +
            "maze generation algorithm (we have a program to check if your maze meets the\n" +
            "above constraints). You may either have this reveal the entire maze for the rest\n" +
            "of the game, or just display the entire maze once.\n" +
            "▪ ‘c’ to set the number of relics to be collected to 1. This will be used during\n" +
            "marking to evaluate winning the game. You may assume that this cheat code\n" +
            "will only be used before the first relic is collected.\n" +
            "o The player wins when they have collected all 3 relics (unless the cheat code is used).\n" +
            "o The player loses when the treasure hunter got killed by a guardian (or multiple):";
}
