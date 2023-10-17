package snake;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {

	private JFrame frame;
	private JLabel[][] squares;

	public Display(int width, int height, KeyListener keyListener) {
		this.frame = new JFrame();
		this.frame.setLayout(new GridLayout(height, width));
		this.squares = new JLabel[width][height];
		for (int row = squares.length - 1; row >= 0; row--) {
			for (int column = 0; column < squares[row].length; column++) {
				JLabel square = new JLabel();
				//square.setText("" + row + "-" + column);
				square.setOpaque(true);
				squares[row][column] = square;
				frame.add(square);
			}
		}
		this.frame.setSize(1200, 800);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.frame.addKeyListener(keyListener);
		this.frame.setVisible(true);
	}

	public void draw(Snake snake, Location appleLocation) {
		Color appleColor = Color.RED;
		Color snakeColor = Color.PINK;
		Color mapColor = Color.GREEN;
		Color snakeHeadColor = Color.MAGENTA;
		for (int row = 0; row < squares.length; row++) {
			for (int column = 0; column < squares[row].length; column++) {
				JLabel square = squares[row][column];
				Location location = new Location(column, row);
				if (location.equals(appleLocation)) {
					square.setBackground(appleColor);
				} else if (snake.getHead().getLocation().equals(location)) {
					square.setBackground(snakeHeadColor);
				} else if (snake.occupiesLocation(location)) {
					square.setBackground(snakeColor);
				} else {
					square.setBackground(mapColor);
				}
			}
		}
	}
}
