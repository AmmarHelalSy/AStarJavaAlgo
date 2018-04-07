/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;

/**
 *
 * @author Ammar
 */
public class Tree {
    
    public Node root;
    public static int CountOfNodes;
    
    public Tree(Node r)
    {
        root = r;
    }
    
    public void GenerateTree()
    {
        BasisLibrary.consoleOutput(root.Depth + "");
        if (! root.board.IsFullyColored() && !( root.Depth == (root.board.dim-1) * (root.board.dim-1)))
        {
            root.GenerateAllChildren();
            for (int i = 0; i < root.Children.size(); i++) {
                Tree tree = new Tree(root.Children.get(i));
                tree.GenerateTree();
            }
        }
    }
    public void GenerateTree(int MaxDepth)
    {
        root.GenerateAllChildren();
        if (root.Depth == MaxDepth)
        {
            return;
        }
        for (int i = 0; i < root.Children.size(); i++) {
            Tree tree = new Tree(root.Children.get(i));
            tree.GenerateTree(MaxDepth);
        }
    }
}
