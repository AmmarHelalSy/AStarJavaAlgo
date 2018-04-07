/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import structure.Tree;

/**
 *
 * @author Ammar
 */
public class Astar extends Algorithm{
    
    public Astar(Tree tree) {
        super(tree);
    }
     @Override
    public void Solve()
    {
        Solution.add(tree.root);
        
    }
}
