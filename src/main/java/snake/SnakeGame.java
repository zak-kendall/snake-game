package snake;

public class SnakeGame {

	public static void main(String[] args) {
		System.out.println("Welcome to Snake game!");
	}

	private Snake snake;
	private Direction direction;
	private int width;
	private int height;
	
	public SnakeGame() {
		this.direction = Direction.EAST;
		this.width = 30;
		this.height = 30;
		this.snake = new Snake(3, width / 2, height / 2, direction);
	}
	
	public void play() {
		while (true) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				return;
			}
			Location nextLocation = getNextLocation();
			LocationType locationType = getLocationType(nextLocation);
			switch (locationType) {
			case APPLE: 
				snake.move(direction, true);
				break;
			case OPEN:
				snake.move(direction, false);
				break;
			case SNAKE:
			case WALL: 
				return;
			}
		}
	}

	private LocationType getLocationType(Location nextLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	private Location getNextLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}
