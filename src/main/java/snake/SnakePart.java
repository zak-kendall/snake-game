package snake;

public class SnakePart {
	private Location location;
	private Direction direction;
	private SnakePart front;
	private SnakePart behind;

	public SnakePart(Location location, Direction direction, SnakePart front, SnakePart behind) {
		this.location = location;
		this.direction = direction;
		this.front = front;
		this.behind = behind;
		if (this.front != null) {
			this.front.setBehind(this);
		}
		if(this.behind != null) {
			this.behind.setFront(this);
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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
