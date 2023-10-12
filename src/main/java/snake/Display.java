package snake;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {

	private JFrame frame;
	private JLabel[][] squares;
	
	public Display() {
		
	}
	
	public void draw(Snake snake, Location appleLocation) {
		for (int row = 0; row < squares.length; row++) {
			for (int colum = 0; colum < squares[row].length; colum++) {
				JLabel square = squares[row][colum];
				Location location = new Location(colum, row);
				if (location.equals(appleLocation)) {
					// color apple
				} else if (snake.occupiesLocation(location)) {
					// color snake
				} else {
					// color map
				}
			}
		}
	}
}
