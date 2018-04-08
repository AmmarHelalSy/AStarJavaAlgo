package algorithms;

import structures.Node;
import structures.Tree;

public class BreadthFirstSearchAlgo extends Algorithm {

	public BreadthFirstSearchAlgo(Tree tree) {
		super(tree);
	}

	public void solve() {
		Node res = breadthFirstSearch(tree.root);
		if (res == null) {

			solution.add(tree.root);
		} else {
			addNode(res);
		}
	}

	private void addNode(Node res) {
		if (res.parent != null) {
			addNode(res.parent);
		}
		solution.add(res);
	}

	private Node breadthFirstSearch(Node root) {
		try {
			if (root.board.isFullyColored()) {
				return root;
			} else {
				root.isVisited = true;

				if (root.parent != null) {
					for (Node node : root.parent.children) {
						if (!node.isVisited) {
							System.out.println("sibling");
							return breadthFirstSearch(node);
						}
					}
				}
				for (Node node : root.children) {
					if (!node.isVisited) {
						System.out.println("child");
						return breadthFirstSearch(node);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

		return null;
	}

}
