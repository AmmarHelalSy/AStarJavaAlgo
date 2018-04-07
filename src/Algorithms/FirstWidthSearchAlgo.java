/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.io.Console;
import structure.Node;
import structure.Tree;

/**
 *
 * @author Ammar
 */
public class FirstWidthSearchAlgo extends Algorithm {

    public FirstWidthSearchAlgo(Tree tree) {
        super(tree);
    }

    @Override
    public void Solve() {
        Node res = BreadthFirstSearch(tree.root);
        if (res == null) {

        Solution.add(tree.root);
        } else {
            Add(res);
        }
    }

    private void Add(Node res) {
        if (res.parent != null) {
            Add(res.parent);
        }
        Solution.add(res);
    }

    private Node BreadthFirstSearch(Node root) {
        try {
            if (root.board.IsFullyColored()) {
                return root;
            } else {
                root.isVisited = true;
                
                if (root.parent != null) {
                    for (Node node : root.parent.Children) {
                        if (!node.isVisited) {
                            System.out.println("sibling");
                            return BreadthFirstSearch(node);
                        }
                    }
                }
                for (Node node : root.Children) {
                    if (!node.isVisited) {
                            System.out.println("child");
                        return BreadthFirstSearch(node);
                    }
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

}
