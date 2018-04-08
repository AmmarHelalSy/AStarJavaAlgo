package structures;

public class Tree {

	public Node root;
	public static int nodesNumber;

	public Tree(Node newRoot) {
		root = newRoot;
	}

	public void generateTree() {
		if (!root.board.isFullyColored() && !(root.Depth == (root.board.dim - 1) * (root.board.dim - 1))) {
			root.generateChildren();
			for (int i = 0; i < root.children.size(); i++) {
				Tree tree = new Tree(root.children.get(i));
				tree.generateTree();
			}
		}
	}

	public void generateSubTree(int depth) {
		root.generateChildren();
		if (root.Depth == depth) {
			return;
		}
		for (int i = 0; i < root.children.size(); i++) {
			Tree tree = new Tree(root.children.get(i));
			tree.generateSubTree(depth);
		}
	}
}
