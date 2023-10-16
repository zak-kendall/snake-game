package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeGame implements KeyListener {

	public static void main(String[] args) {
		System.out.println("Welcome to Snake game!");
		SnakeGame game = new SnakeGame(50, 50, SnakeSpeed.LUDICROUS);
		game.play();
	}

	private Random random;
	private Direction direction;
	private int rows;
	private int columns;
	private Snake snake;
	private SnakeDisplay display;
	private Location appleLocation;
	private SnakeSpeed snakeSpeed;
	private boolean started;

	public SnakeGame(int rows, int columns, SnakeSpeed snakeSpeed) {
		this.random = new Random();
		this.direction = Direction.SOUTH;
		this.rows = rows;
		this.columns = columns;
		this.snake = new Snake(this.direction,
				new Location(columns / 2, rows / 2),
				new Location(columns / 2 - 1, rows / 2),
				new Location(columns / 2 - 2, rows / 2)
				);
		this.display = new SnakeDisplay(rows, columns, this);
		this.appleLocation = getNextAppleLocation();
		this.snakeSpeed = snakeSpeed;
		this.started = false;

		display.display(snake, appleLocation);
	}

	private void play() {
		while(!started) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}

		boolean playing = true;
		while(playing) {
			try {
				Thread.sleep(this.snakeSpeed.getDelayMillis());
			} catch (InterruptedException e) {
				return;
			}

			// Move the snake
			boolean grow = false;
			switch(getMove()) {
			case APPLE:
				grow = true;
				appleLocation = getNextAppleLocation();
				System.out.println("Moving " + this.direction + " and growing.");
				snake.move(this.direction, grow);
				break;
			case OPEN:
				System.out.println("Moving " + this.direction + ".");
				snake.move(this.direction, grow);
				break;
			case SNAKE:
			case WALL:
				playing = false;
				break;
			default:
				throw new RuntimeException("Unhandled case.");
			}

			// Display
			display.display(snake, appleLocation);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			this.direction = Direction.NORTH;
			if(!started) {
				started = true;
			}
			break;
		case KeyEvent.VK_DOWN:
			this.direction = Direction.SOUTH;
			if(!started) {
				started = true;
			}
			break;
		case KeyEvent.VK_LEFT:
			this.direction = Direction.WEST;
			break;
		case KeyEvent.VK_RIGHT:
			this.direction = Direction.EAST;
			if(!started) {
				started = true;
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	private Location getNextAppleLocation() {
		Location appleLocation = new Location(random.nextInt(columns), random.nextInt(rows));
		while(snake.occupiesLocation(appleLocation)) {
			appleLocation = new Location(random.nextInt(columns), random.nextInt(rows));
		}
		return appleLocation;
	}

	private MoveResult getMove() {
		Location targetLocation = snake.getHeadLocation().getNeighbor(direction);
		if(targetLocation.getColumn() < 0 || targetLocation.getColumn() >= columns
				|| targetLocation.getRow() < 0 || targetLocation.getRow() >= rows
				) {
			return MoveResult.WALL;
		}
		if(targetLocation.equals(appleLocation)) {
			return MoveResult.APPLE;
		}
		if(snake.occupiesLocation(targetLocation)) {
			return MoveResult.SNAKE;
		}
		return MoveResult.OPEN;
	}

}
