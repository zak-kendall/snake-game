package snake;

public class Snake {
	private SnakePart head;
	private Direction direction;

	public Snake(Direction direction, Location... headToTailLocations) {
		this.head = new SnakePart(headToTailLocations[0], null, null);
		this.direction = direction;
		for (int i = 1; i < headToTailLocations.length; i++) {
			new SnakePart(headToTailLocations[i], head.getTail(), null);
		}
	}

	public void move(Direction direction, boolean grow) {
		this.direction = direction;
		int newHeadColumn = this.head.getLocation().getColumn();
		int newHeadRow = this.head.getLocation().getRow();
		switch (this.direction) {
		case EAST:
			newHeadColumn++;
			break;
		case NORTH:
			newHeadRow++;
			break;
		case SOUTH:
			newHeadRow--;
			break;
		case WEST:
			newHeadColumn--;
			break;
		}

		this.head = new SnakePart(new Location(newHeadColumn, newHeadRow), null, head);

		if(!grow) {
			removeTail();
		}
	}

	private void removeTail() {
		SnakePart tail = this.head.getTail();
		tail.getFront().setBehind(null);
	}

	public boolean occupiesLocation(Location location) {
		SnakePart part = this.head;
		while(part != null) {
			if(part.getLocation().equals(location)) {
				return true;
			}
			part = part.getBehind();
		}
		return false;
	}

	public boolean headOccupiesLocation(Location location) {
		return this.head.getLocation().equals(location);
	}

	public Location getHeadLocation() {
		return head.getLocation();
	}
}
