package ca.cmpt213.a3;

import ca.cmpt213.a3.UI.PicturePanel;
import ca.cmpt213.a3.shapes.Pyramid;
import ca.cmpt213.a3.shapes.Rectangle;
import ca.cmpt213.a3.shapes.TextBox;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


/**
 * Test program showing a number of different shapes in a GUI
 */
public class MainGUI {
	// Default size of the panels
	private static final int SIZE_X = 80;
	private static final int SIZE_Y = 24;


	/**
	 * Application to display the test "pictures" to the UI.
	 */
	public static void main(String[] args) {

		// Select the pictures to test:
		// (Comment / Uncomment these calls as you like during testing)
		List<PicturePanel> pictures = new ArrayList<>();
		//pictures.add(makeMixedPicture());
	pictures.add(makePyramidsPicture());
	pictures.add(makeRectanglesPicture());
		//pictures.add(makeTextBoxesPicture());
	pictures.add(makeMixedPicture());
		pictures.add(makeFrontAndBackPicture());

		// Show picture on graphical UI
		// (You should understand all of this!)
		JFrame frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

		for (PicturePanel p : pictures) {
			// Add picture to UI
			frame.add(p);

			// Write picture to file (to support marking)
			p.writePictureToFile("Picture -- "+p.getTitle()+".txt");
		}

		// Display UI
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}


	// --------------------------------------------------------------------------
	// Pyramid Test
	// --------------------------------------------------------------------------
	private static PicturePanel makePyramidsPicture() {
		PicturePanel picture = new PicturePanel("Pyramid Picture Test", SIZE_X, 19);
		testPyramidRow1(picture);
		testPyramidRow2(picture);
		return picture;
	}

	private static void testPyramidRow1(PicturePanel picture) {
		Pyramid p1 = new Pyramid(0, 0, 1, 1);
		p1.setBorderChar('*');
		p1.setColor(Color.MAGENTA);
		picture.addFront(p1);

		Pyramid p2 = new Pyramid(5, 0, 2, 2);
		p2.setBorderChar('*');
		p2.setColor(Color.GREEN);
		picture.addFront(p2);

		Pyramid p3 = new Pyramid(10, 0, 7, 4);
		p3.setBorderChar('*');
		p3.setColor(Color.RED);
		picture.addFront(p3);

		Pyramid p4 = new Pyramid(20, 0, 6, 4);
		p4.setBorderChar('*');
		picture.addFront(p4);

		Pyramid p5 = new Pyramid(30, 0, 12, 6);
		p5.setBorderChar('*');
		p5.setColor(Color.BLUE);
		picture.addFront(p5);
	}

	private static void testPyramidRow2(PicturePanel picture) {
		final int ROW = 5;
		Pyramid p1 = new Pyramid(0, ROW, 5, 10);
		p1.setColor(Color.PINK);
		picture.addFront(p1);

		p1 = new Pyramid(7, ROW, 10, 9);
		p1.setColor(Color.LIGHT_GRAY);
		p1.setBorderChar('#');
		picture.addFront(p1);

		p1 = new Pyramid(18, ROW, 15, 8);
		p1.setColor(Color.RED);
		p1.setBorderChar('%');
		picture.addFront(p1);

		p1 = new Pyramid(32, ROW, 30, 16);
		p1.setColor(Color.GREEN);
		p1.setBorderChar('@');
		picture.addFront(p1);
	}


	// --------------------------------------------------------------------------
	// Rectangle Test
	// --------------------------------------------------------------------------
	private static PicturePanel makeRectanglesPicture() {
		PicturePanel picture = new PicturePanel("Rectangle Picture Test", SIZE_X, 19);
		testRectangleRow1(picture);
		testRectangleRow2(picture);
		return picture;
	}

	private static void testRectangleRow1(PicturePanel picture) {
		Rectangle r1 = new Rectangle(0, 0, 1, 1);
		r1.setColor(Color.MAGENTA);
		picture.addFront(r1);

		Rectangle r2 = new Rectangle(5, 0, 2, 2);
		r2.setBorderChar('*');
		r2.setColor(Color.GREEN);
		picture.addFront(r2);

		Rectangle r3 = new Rectangle(10, 0, 3, 3);
		r2.setBorderChar('*');
		r3.setColor(Color.RED);
		picture.addFront(r3);

		Rectangle r4 = new Rectangle(15, 0, 4, 4);
		r2.setBorderChar('*');
		picture.addFront(r4);

		Rectangle r5 = new Rectangle(27, 0, 35, 18);
		r2.setBorderChar('*');
		r5.setColor(Color.ORANGE);
		picture.addFront(r5);
	}

	private static void testRectangleRow2(PicturePanel picture) {
		final int ROW = 5;
		Rectangle r1 = new Rectangle(0, ROW, 5, 10);
		r1.setColor(Color.PINK);
		picture.addFront(r1);

		r1 = new Rectangle(10, ROW, 5, 10);
		r1.setColor(Color.LIGHT_GRAY);
		r1.setBorderChar('#');
		picture.addFront(r1);

		r1 = new Rectangle(20, ROW, 5, 10);
		r1.setColor(Color.RED);
		r1.setBorderChar('%');
		picture.addFront(r1);

		r1 = new Rectangle(30, ROW, 14, 49);
		r1.setColor(Color.GREEN);
		r1.setBorderChar('@');
		picture.addFront(r1);
	}


	// --------------------------------------------------------------------------
	// TextBox Test
	// --------------------------------------------------------------------------
	private static PicturePanel makeTextBoxesPicture() {
		PicturePanel picture = new PicturePanel("Text Box Test", SIZE_X, SIZE_Y);
		testTextBoxRow1(picture);
		testTextBoxRow2(picture);
		return picture;
	}

	private static void testTextBoxRow1(PicturePanel picture) {
		TextBox tb1 = new TextBox(0, 0, 14, 3, "Hello world!");
		tb1.setColor(Color.CYAN);
		picture.addFront(tb1);

		tb1 = new TextBox(0, 5, 16, 3, "Hello world!");
		tb1.setColor(Color.RED);
		picture.addFront(tb1);

		TextBox tb2 = new TextBox(20, 0, 9, 9,
				"And so it began, the great debugging!");
		tb2.setColor(Color.GREEN);
		picture.addFront(tb2);

		// Code Monkey Lyrics, by Jonathan Coulton
		// See: http://www.youtube.com/watch?v=qYodWEKCuGg
		TextBox tb3 = new TextBox(
				30,
				0,
				30,
				15,
				"Code Monkey get up get coffee. "
						+ "Code Monkey go to job. "
						+ "Code Monkey have boring meeting with boring manager Rob. "
						+ "Rob say Code Monkey very diligent, "
						+ "but his output stink. "
						+ "His code not functional or elegant. "
						+ "What do Code Monkey think? "
						+ "Code Monkey think maybe manager want to write goddamn login page himself. "
						+ "Code Monkey not say it out loud. "
						+ "Code Monkey not crazy just proud.");
		picture.addFront(tb3);

		TextBox tb4 = new TextBox(65, 5, 13, 10,
				"And there was much rejoicing!");
		tb4.setColor(Color.ORANGE);
		picture.addFront(tb4);
	}

	private static void testTextBoxRow2(PicturePanel picture) {
		// Test how well the text wraps
		final int ROW = 16;
		String alphabet = " a b c d e f g h i j k l m n o p q r s t u v w x y z"
				+ " A B C D E F G H I J K L M O P Q R S T U V W X Y Z ";
		TextBox tb1 = new TextBox(0, ROW, 8, 8, alphabet);
		tb1.setColor(Color.CYAN);
		picture.addFront(tb1);

		tb1 = new TextBox(9, ROW, 8, 8, alphabet);
		tb1.setColor(Color.GREEN);
		picture.addFront(tb1);

		tb1 = new TextBox(20, ROW, 7, 8, alphabet);
		tb1.setColor(Color.MAGENTA);
		picture.addFront(tb1);

		tb1 = new TextBox(30, ROW, 9, 8, alphabet);
		tb1.setColor(Color.LIGHT_GRAY);
		picture.addFront(tb1);

		tb1 = new TextBox(40, ROW, 9, 8, alphabet);
		tb1.setColor(Color.CYAN);
		tb1.setMessage("Hi yal!");
		tb1.setBorderChar('#');
		picture.addFront(tb1);

		tb1 = new TextBox(50, ROW, 9, 3, alphabet);
		tb1.setColor(Color.GREEN);
		tb1.setMessage("Hi yal!");
		tb1.setBorderChar('&');
		picture.addFront(tb1);

		tb1 = new TextBox(60, ROW, 20, 5, alphabet);
		tb1.setColor(Color.RED);
		tb1.setMessage("Hi yal!");
		tb1.setBorderChar('*');
		picture.addFront(tb1);
	}

	// --------------------------------------------------------------------------
	// Mixed Picture Test
	// --------------------------------------------------------------------------
	private static PicturePanel makeMixedPicture() {
		PicturePanel pic = new PicturePanel("Mixed Element Picture Test", SIZE_X, SIZE_Y);

		// Add border
		Rectangle rect = new Rectangle(0, 0, SIZE_X, SIZE_Y);
		rect.setColor(Color.ORANGE);
		rect.setBorderChar('#');
		pic.addFront(rect);

		// Text box with styling:
		rect = new TextBox(45, 10, 30, 4, "A box!");
		rect.setColor(Color.RED);
		rect.setBorderChar('%');
		pic.addFront(rect);

		// Bunch of other shapes:
		Pyramid shape2 = new Pyramid(15, 2, 10, 6);
		shape2.setColor(Color.CYAN);
		pic.addFront(shape2);

		Rectangle shape3 = new Rectangle(25, 8, 15, 8);
		shape3.setColor(Color.GREEN);
		pic.addFront(shape3);

		Pyramid shape4 = new Pyramid(54, 1, 12, 10);
		shape4.setColor(Color.DARK_GRAY);
		pic.addFront(shape4);

		TextBox shape5 = new TextBox(2, 2, 3, 20, "Thin text in a box!");
		shape5.setColor(Color.YELLOW);
		pic.addFront(shape5);

		Rectangle shape6 = new Rectangle(72, 18, 5, 4);
		shape6.setColor(Color.MAGENTA);
		pic.addFront(shape6);

		return pic;
	}

	// --------------------------------------------------------------------------
	// Test picture by adding shapes to the front and back.
	// --------------------------------------------------------------------------
	private static PicturePanel makeFrontAndBackPicture() {
		PicturePanel pic = new PicturePanel("Front And Back Picture Test", SIZE_X, SIZE_Y);

		// Box around text
		Rectangle rect = new Rectangle(30, 7, 20, 12);
		rect.setColor(Color.RED);
		rect.setBorderChar('B');
		pic.addBack(rect);

		// Pyramid in background-left
		Pyramid pyramidLeft = new Pyramid(25, 3, 18, 4);
		pyramidLeft.setColor(Color.CYAN);
		pyramidLeft.setBorderChar('L');
		pic.addBack(pyramidLeft);

		// Text on top:
		TextBox textTop = new TextBox(35, 9, 10, 8, "I'm on top! WOOO HOO!");
		textTop.setColor(Color.LIGHT_GRAY);
		textTop.setBorderChar('T');
		pic.addFront(textTop);

		// Pyramid in background-right
		Pyramid pyramidRight = new Pyramid(45, 3, 18, 6);
		pyramidRight.setColor(Color.BLUE);
		pyramidRight.setBorderChar('R');
		pic.addBack(pyramidRight);

		// Text in very back:
		TextBox textBack = new TextBox(10, 1, SIZE_X-20, SIZE_Y-2, "Aww drat, I'm at the back.");
		textBack.setColor(Color.YELLOW);
		textBack.setBorderChar('O');
		pic.addBack(textBack);

		return pic;
	}

}

