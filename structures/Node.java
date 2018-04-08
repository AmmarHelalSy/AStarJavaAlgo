package structures;

import java.util.ArrayList;

public class Node {

	public Node parent;
	public Board board;
	public java.util.List<Node> children;
	int Depth;
	public boolean isVisited;

	public Node(Board fromBoard) {
		board = fromBoard;
		children = new ArrayList<>();
		isVisited = false;
	}

	public void generateChildren() {
		if (board.isFullyColored()) {
			return;
		}
		for (int i = 0; i < board.colorNumber; i++) {
			if (!(board.boardColors[i] == board.sourceCell.getColor())) {
				Board board1 = new Board(board);
				board1 = board1.applyColor(board1.boardColors[i]);
				Node child = new Node(board1);
				child.parent = this;
				child.Depth = this.Depth + 1;
				child.parent = this;
				if (child.board.sourceConnectedCellsCount > this.board.sourceConnectedCellsCount
						|| child.board.getBlockNumber() < board.getBlockNumber()
						|| child.board.getColorsCount() < board.getColorsCount()) {
					children.add(child);
				}
			}

		}
	}

	public Node getChildWithlMinColorsCount() {
		Node res = null;
		int min = board.getColorsCount();
		int max = board.getConnectedCellsWithSourceNumber();
		int countOfBlocks = board.getBlockNumber();
		for (Node node : children) {
			if (node.board.getBlockNumber() < countOfBlocks) {
				countOfBlocks = node.board.getBlockNumber();
				res = node;

			}
		}
		if (countOfBlocks < board.getBlockNumber() && res.board.getConnectedCellsWithSourceNumber() != 0) {
			return res;
		}

		for (Node node : children) {
			if (node.board.getColorsCount() < min) {
				min = node.board.getColorsCount();
				res = node;
				if (res.board.getConnectedCellsWithSourceNumber() != 0) {
					return res;
				}
			}

		}

		for (Node node : children) {
			if (node.board.getConnectedCellsWithSourceNumber() > max) {
				max = node.board.getConnectedCellsWithSourceNumber();
				res = node;
			}
		}
		return res;
	}
}
