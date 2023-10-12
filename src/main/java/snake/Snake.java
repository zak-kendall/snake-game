package snake;

public class Snake {
	private SnakePart head;

	public Snake(int length, int x, int y, Direction direction) {
		this.head = new SnakePart(new Location(x, y), direction, null, null);
		for (int i = 1; i < length; i++) {
			growTail();
		}
	}

	private void growTail() {
		// Get the tail
		SnakePart tail = head.getTail();

		// Set the new x,y based on the x,y,direction of the tail
		int x = tail.getLocation().getX();
		int y = tail.getLocation().getY();
		switch (tail.getDirection()) {
		case EAST:
			x--;
			break;
		case NORTH:
			y--;
			break;
		case SOUTH:
			y++;
			break;
		case WEST:
			x++;
			break;
		}

		// Create the new part
		SnakePart newPart = new SnakePart(new Location(x, y), tail.getDirection(), tail, null);
	}

	public void move(Direction direction, boolean grow) {
		int newHeadX = this.head.getLocation().getX();
		int newHeadY = this.head.getLocation().getY();
		switch (head.getDirection()) {
		case EAST:
			newHeadX++;
			break;
		case NORTH:
			newHeadY++;
			break;
		case SOUTH:
			newHeadY--;
			break;
		case WEST:
			newHeadX--;
			break;
		}

		this.head = new SnakePart(new Location(newHeadX, newHeadY), direction, null, head);

		if(!grow) {
			removeTail();
		}
	}

	private void removeTail() {
		SnakePart tail = this.head.getTail();
		tail.getFront().setBehind(null);
	}
	
	public boolean occupiesLocation(Location location) {
		SnakePart part = head;
		while (part != null) {
			if (part.getLocation().equals(location)) {
				return true;
			} 
			part = part.getBehind();
		}
		return false;
	}
}
