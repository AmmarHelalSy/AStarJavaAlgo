/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import structure.Node;
import structure.Tree;

/**
 *
 * @author Ammar
 */
public class RandomAlg extends Algorithm{

    public RandomAlg(Tree tree) {
        super(tree);
    }
    @Override
    public void Solve()
    {
        Solution.add(tree.root);
        chooseOneChild(tree.root);
    }

    private void chooseOneChild(Node root) {
        if (root != null)
        {
            if (!root.board.IsFullyColored())
            {
                Node n = root.GetChildWithlMinColorsCount();
                Solution.add(n);
                chooseOneChild(n);
            }
        }
    }
}
