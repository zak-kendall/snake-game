package snake;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SnakeDisplay {

	private JLabel[][] labels;

	public SnakeDisplay(int rows, int columns, KeyListener keyListener) {
		JFrame frame = new JFrame("Snake");
		frame.setSize(800, 600);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.addKeyListener(keyListener);

		this.labels = new JLabel[rows][columns];
		frame.setLayout(new GridLayout(rows, columns));
		for (int row = rows - 1; row > -1; row--) {
			for (int col = 0; col < columns; col++) {
				// String text = "" + row + "-" + col;
				String text = "";
				JLabel label = new JLabel(text);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setOpaque(true);
				this.labels[row][col] = label;
				frame.add(label);
			}
		}

		frame.setVisible(true);
	}

	public void display(Snake snake, Location appleLocation) {
		Color backgroundColor = Color.LIGHT_GRAY;
		Color snakeHeadColor = Color.BLACK;
		Color snakeColor = Color.GREEN;
		Color appleColor = Color.RED;

		for (int row = 0; row < labels.length; row++) {
			for (int col = 0; col < labels[row].length; col++) {
				if (snake.headOccupiesLocation(new Location(col, row))) {
					labels[row][col].setBackground(snakeHeadColor);
				} else if (snake.occupiesLocation(new Location(col, row))) {
					labels[row][col].setBackground(snakeColor);
				} else if (appleLocation.equals(new Location(col, row))) {
					labels[row][col].setBackground(appleColor);
				} else {
					labels[row][col].setBackground(backgroundColor);
				}
			}
		}
	}
}
