package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeGame implements KeyListener {

	public static void main(String[] args) {
		System.out.println("Welcome to Snake game!");
		SnakeGame snakeGame = new SnakeGame();
		snakeGame.play();
	}

	private Snake snake;
	private Direction direction;
	private int width;
	private int height;
	private Display display;
	private Location appleLocation;
	private Random random;

	public SnakeGame() {
		this.random = new Random();
		this.direction = Direction.EAST;
		this.width = 50;
		this.height = 50;
		this.snake = new Snake(3, width / 2, height / 2, direction);
		this.display = new Display(this.width, this.height, this);
		setNewAppleLocation();
		display.draw(snake, this.appleLocation);
	}

	public void play() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
			Location nextLocation = getNextLocation();
			LocationType locationType = getLocationType(nextLocation);
			switch (locationType) {
			case APPLE:
				snake.move(direction, true);
				setNewAppleLocation();
				break;
			case OPEN:
				snake.move(direction, false);
				break;
			case SNAKE:
			case WALL:
				return;
			}
			display.draw(snake, appleLocation);
		}
	}

	private LocationType getLocationType(Location location) {
		if (location.equals(appleLocation)) {
			return LocationType.APPLE;
		} else if (snake.occupiesLocation(location)) {
			return LocationType.SNAKE;
		} else if (location.getX() < 0 || location.getX() >= width || location.getY() < 0
				|| location.getY() >= height) {
			return LocationType.WALL;
		} else {
			return LocationType.OPEN;
		}

	}

	private Location getNextLocation() {
		return snake.getNextLocation(direction);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.direction = Direction.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			this.direction = Direction.SOUTH;
			break;
		case KeyEvent.VK_LEFT:
			this.direction = Direction.WEST;
			break;
		case KeyEvent.VK_RIGHT:
			this.direction = Direction.EAST;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void setNewAppleLocation() {
		while (true) {
			int randomColumn = random.nextInt(width);
			int randomRow = random.nextInt(height);
			Location location = new Location(randomColumn, randomRow);
			if (!snake.occupiesLocation(location)) {
				appleLocation = location;
				break;
			}
		}

	}
}
