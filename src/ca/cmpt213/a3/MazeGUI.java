package ca.cmpt213.a3;

import ca.cmpt213.a3.UI.PicturePanel;
import ca.cmpt213.a3.shapes.MazeShape;
import ca.cmpt213.a3.Maze.Maze;
import ca.cmpt213.a3.shapes.TextBox;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MazeGUI {
    // Default size of the panels
    private static final int SIZE_X = 80;
    private static final int SIZE_Y = 24;


    /**
     * Application to display the test "pictures" to the UI.
     */
    public static void main(String[] args) {

        Maze maze = new Maze();
        maze.printMaze();
        MazeShape mazepattern = new MazeShape(0,0,0,0,maze);
        PicturePanel pict = makeMazePicture();
        pict.addFront(mazepattern);


        List<PicturePanel> pictures = new ArrayList<>();



        pictures.add(pict);

        JFrame frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        for (PicturePanel p : pictures) {
            // Add picture to UI
            frame.add(p);

            // Write picture to file (to support marking)
            p.writePictureToFile("Picture -- "+p.getTitle()+".txt");
        }
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private static PicturePanel makeMazePicture(){
        PicturePanel picture = new PicturePanel("Maze Picture", 17, 21);
        return picture;
    }
    }