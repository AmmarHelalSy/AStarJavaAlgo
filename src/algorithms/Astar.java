package algorithms;

import structures.Tree;

public class Astar extends Algorithm {

	public Astar(Tree tree) {
		super(tree);
	}

	public void solve() {
		this.solution.add(tree.root);

	}
}
