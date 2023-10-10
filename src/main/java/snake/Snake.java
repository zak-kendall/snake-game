package snake;

public class Snake {

	private SnakePart head;
	
	public Snake(int length, int x, int y, Direction direction) {
		this.head = new SnakePart(x, y, direction, null);
		for (int i = 1; i < length; i++) {
			grow();
		}
	}
	
	
	
	private void grow() {
		// Get the tail
		SnakePart tail = head.getTail();
		// Set the new x,y based on the x,y,direction of the tail
		int x;
		int y;
		
		if (tail.getDirection() == Direction.NORTH) {
			x = tail.getX();
			y = tail.getY() - 1;
		} else if (tail.getDirection() == Direction.EAST) {
			x = tail.getX() - 1;
			y = tail.getY();
		} else if (tail.getDirection() == Direction.SOUTH) {
			x = tail.getX();
			y = tail.getY() + 1;
		} else {
			x = tail.getX() + 1;
			y = tail.getY();
		}
		// Create the new part
		SnakePart newPart = new SnakePart(x, y, tail.getDirection(), tail);
	}
	
	
}
