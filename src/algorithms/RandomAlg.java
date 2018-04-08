package algorithms;

import structures.Node;
import structures.Tree;

public class RandomAlg extends Algorithm {

	public RandomAlg(Tree tree) {
		super(tree);
	}

	public void solve() {
		solution.add(tree.root);
		chooseOneChild(tree.root);
	}

	private void chooseOneChild(Node root) {
		if (root != null) {
			if (!root.board.isFullyColored()) {
				Node n = root.getChildWithlMinColorsCount();
				solution.add(n);
				chooseOneChild(n);
			}
		}
	}
}
