package snake;

public class SnakePart {
	private int x;
	private int y;
	private Direction direction;
	private SnakePart front;
	private SnakePart behind;
	
	public SnakePart(int x, int y, Direction direction, SnakePart front) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.front = front;
		if (this.front != null) {
			this.front.setBehind(this);
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public SnakePart getFront() {
		return front;
	}

	public void setFront(SnakePart front) {
		this.front = front;
	}

	public SnakePart getBehind() {
		return behind;
	}

	public void setBehind(SnakePart behind) {
		this.behind = behind;
	}
	
	public SnakePart getTail() {
		if (behind == null) {
			return this;
		}
		return behind.getTail();
	}

	public SnakePart getHead() {
		if (front == null) {
			return this;
		}
		return front.getHead();
	}
}
