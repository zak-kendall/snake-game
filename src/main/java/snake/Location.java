package snake;

import java.util.Objects;

public class Location {
	private int column;
	private int row;

	public Location(int column, int row) {
		this.column = column;
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public Location getNeighbor(Direction direction) {
		switch (direction) {
		case EAST:
			return new Location(column + 1, row);
		case NORTH:
			return new Location(column, row + 1);
		case SOUTH:
			return new Location(column, row - 1);
		case WEST:
			return new Location(column - 1, row);
		}
		throw new RuntimeException("Direction unknown.");
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return column == other.column && row == other.row;
	}

}
