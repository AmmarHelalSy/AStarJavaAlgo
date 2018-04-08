
package structures;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	public BoardCell[][] cells;
	public int dim;
	public int colorNumber;
	public Color[] boardColors;
	public BoardCell sourceCell;
	public int sourceConnectedCellsCount = 0;

	public Board(int dim, int colorNumber) {
		this.colorNumber = colorNumber;
		this.dim = dim;
		this.boardColors = new Color[colorNumber];
		this.cells = new BoardCell[dim][dim];
		this.mtdInitBoardColor();
		this.mtdInitBoardCells();
		this.sourceCell = this.cells[0][0];
	}

	public Board(Board fromBoard) {
		this.dim = fromBoard.dim;
		this.colorNumber = fromBoard.colorNumber;
		this.boardColors = new Color[colorNumber];
		for (int i = 0; i < fromBoard.colorNumber; i++) {
			this.boardColors[i] = fromBoard.boardColors[i];
		}
		this.cells = new BoardCell[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				this.cells[i][j] = new BoardCell(fromBoard.cells[i][j]);
			}
		}
		this.sourceCell = cells[0][0];
	}

	public boolean isFullyColored() {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (cells[i][j].getColor() != sourceCell.getColor()) {
					return false;
				}
			}
		}
		return true;
	}

	public Board applyColor(Color p_color) {
		mtdResetBoard();
		setConnectedCellsColor(1, 0, p_color);
		setConnectedCellsColor(0, 1, p_color);

		sourceCell.color = p_color;
		return this;
	}

	public void setConnectedCellsColor(int x, int y, Color p_color) {
		if (!cells[x][y].isColored()) {

			cells[x][y].setIsVisited(true);
			if (cells[x][y].getColor() != sourceCell.color) {
			} else {
				if (x == 0 && y == 0) {
				} else if (x > 0 && x < dim - 1 && y > 0 && y < dim - 1) {
					setConnectedCellsColor(x - 1, y, p_color);
					setConnectedCellsColor(x, y - 1, p_color);
					setConnectedCellsColor(x + 1, y, p_color);
					setConnectedCellsColor(x, y + 1, p_color);
					cells[x][y].setColor(p_color);

				} else if (x > 0 && x < dim - 1 && y == 0) {
					setConnectedCellsColor(x - 1, y, p_color);
					setConnectedCellsColor(x, y + 1, p_color);
					setConnectedCellsColor(x + 1, y, p_color);
					cells[x][y].setColor(p_color);

				} else if (x == 0 && y > 0 && y < dim - 1) {
					setConnectedCellsColor(x, y - 1, p_color);
					setConnectedCellsColor(x, y + 1, p_color);
					setConnectedCellsColor(x + 1, y, p_color);
					cells[x][y].setColor(p_color);

				} else if (x > 0 && x < dim - 1 && y == dim - 1) {
					setConnectedCellsColor(x - 1, y, p_color);
					setConnectedCellsColor(x, y - 1, p_color);
					setConnectedCellsColor(x + 1, y, p_color);
					cells[x][y].setColor(p_color);

				} else if (y > 0 && y < dim - 1 && x == dim - 1) {
					setConnectedCellsColor(x - 1, y, p_color);
					setConnectedCellsColor(x, y - 1, p_color);
					setConnectedCellsColor(x, y + 1, p_color);
					cells[x][y].setColor(p_color);

				} else if (x == dim - 1 && y == dim - 1) {
					setConnectedCellsColor(x, y - 1, p_color);
					setConnectedCellsColor(x - 1, y, p_color);
					cells[x][y].setColor(p_color);

				} else if (x == 0 && y == dim - 1) {
					setConnectedCellsColor(x, y - 1, p_color);
					setConnectedCellsColor(x + 1, y, p_color);
					cells[x][y].setColor(p_color);

				} else if (x == dim - 1 && y == 0) {
					setConnectedCellsColor(x, y + 1, p_color);
					setConnectedCellsColor(x - 1, y, p_color);
					cells[x][y].setColor(p_color);

				}
			}

		}
	}

	public void mtdResetBoard() {
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				cells[i][j].setIsVisited(false);
			}
		}
	}

	private void mtdInitBoardColor() {
		Color[] colors = { Color.red, Color.yellow, Color.blue, Color.green, Color.ORANGE, Color.CYAN };
		for (int i = 0; i < colorNumber; i++) {
			boardColors[i] = colors[i];
		}

	}

	private void mtdInitBoardCells() {
		Color[] colors = { Color.red, Color.yellow, Color.blue, Color.green, Color.ORANGE, Color.CYAN };
		Random r = new Random();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				cells[i][j] = new BoardCell(colors[r.nextInt(colorNumber)], i, j);
			}

		}
	}

	public int getColorsCount() {
		List<Color> lstColors = new ArrayList<>();
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (!lstColors.contains(cells[i][j].getColor())) {
					lstColors.add(cells[i][j].getColor());
				}
			}
		}
		return lstColors.size();
	}

	public int getConnectedCellsWithSourceNumber() {
		sourceConnectedCellsCount = 0;
		mtdResetBoard();
		getSourceConnectedCount(0, 0);
		return sourceConnectedCellsCount;
	}

	public int getBlockNumber() {
		mtdResetBoard();
		int count = 0;
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (!cells[i][j].isColored()) {
					getSourceConnectedCount(i, j);
					count++;
				}
			}
		}
		return count;
	}

	private void getSourceConnectedCount(int x, int y) {
		try {
			if (!cells[x][y].isVisited && cells[x][y].getColor() == sourceCell.getColor()) {
				cells[x][y].setIsVisited(true);
				sourceConnectedCellsCount++;
				if (x > 0 && x < dim - 1 && y < dim - 1 && y > 0) {
					getSourceConnectedCount(x - 1, y);
					getSourceConnectedCount(x, y - 1);
					getSourceConnectedCount(x + 1, y);
					getSourceConnectedCount(x, y + 1);
				} else if (x == 0 && x < dim - 1 && y < dim - 1 && y > 0) {
					getSourceConnectedCount(0, y - 1);
					getSourceConnectedCount(0, y + 1);
					getSourceConnectedCount(1, y);
				} else if (x > 0 && x == dim - 1 && y < dim - 1 && y > 0) {
					getSourceConnectedCount(dim - 1, y - 1);
					getSourceConnectedCount(dim - 1, y + 1);
					getSourceConnectedCount(dim - 2, y);
				} else if (x > 0 && x < dim - 1 && y == dim - 1 && y > 0) {
					getSourceConnectedCount(x - 1, dim - 1);
					getSourceConnectedCount(x + 1, dim - 1);
					getSourceConnectedCount(x, dim - 2);
				} else if (x > 0 && x < dim - 1 && y < dim - 1 && y == 0) {
					getSourceConnectedCount(x - 1, 0);
					getSourceConnectedCount(x + 1, 0);
					getSourceConnectedCount(x, 1);
				} else if (x == 0 && x < dim - 1 && y < dim - 1 && y == 0) {
					getSourceConnectedCount(0, 1);
					getSourceConnectedCount(1, 0);
				} else if (x == dim - 1 && x > 0 && y == dim - 1 && y > 0) {
					getSourceConnectedCount(0, 1);
					getSourceConnectedCount(1, 0);
				} else if (x < dim - 1 && x == 0 && y == dim - 1 && y > 0) {
					getSourceConnectedCount(1, dim - 1);
					getSourceConnectedCount(0, dim - 2);
				} else if (x == dim - 1 && x > 0 && y < dim - 1 && y == 0) {
					getSourceConnectedCount(dim - 1, 1);
					getSourceConnectedCount(dim - 2, 0);
				}
			}

		} catch (Exception ex) {
			String ss = ex.toString();
		}

	}
}
