package structures;

import java.awt.Color;

public class BoardCell {

	public Color color;
	public int xCor;
	public int yCor;
	public boolean isVisited;

	public boolean isColored() {
		return this.isVisited;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color newColor) {
		this.color = newColor;
	}

	public void setIsVisited(boolean value) {
		this.isVisited = value;
	}

	public BoardCell(BoardCell fromCell) {
		this.setColor(fromCell.getColor());
		this.isVisited = fromCell.isColored();
		this.xCor = fromCell.xCor;
		this.yCor = fromCell.yCor;

	}

	public BoardCell(Color newColor, int newxCor, int newyCor) {
		this.color = newColor;
		this.xCor = newxCor;
		this.yCor = newyCor;
		this.isVisited = false;
	}

}
