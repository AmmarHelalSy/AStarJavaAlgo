package javadynui;

import Algorithms.Algorithm;
import Algorithms.FirstWidthSearchAlgo;
import Algorithms.RandomAlg;
import structure.Board;
import structure.Node;
import structure.Tree;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaDynUI extends JFrame {

    static JavaDynUI myFrame;
    static int countMe = 0;
    JPanel RandomAlgoPannel;
    JPanel userPannel;
    Board board;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {

        myFrame = new JavaDynUI();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.prepareUI();
        myFrame.setSize(1000, 500);
        myFrame.pack();
        myFrame.setVisible(true);
    }

    private void prepareUI() {

        RandomAlgoPannel = new JPanel();
        RandomAlgoPannel.setPreferredSize(new Dimension(500, 500));
        userPannel = new JPanel();
        userPannel.setPreferredSize(new Dimension(500, 500));
        // RandomAlgoPannel.setLayout(new GridLayout(3,3));

        JPanel subP1 = new JPanel();
        subP1.setPreferredSize(new Dimension(500, 100));
        subP1.setLayout(new BoxLayout(subP1, BoxLayout.X_AXIS));

        int dim = 4;//Integer.parseInt(txtDim.getText());
        board = new Board(dim, dim);
        Node root = new Node(board);
        Tree tree = new Tree(root);
        tree.GenerateTree();

        //PrintTree(tree.root);
        Algorithms.Algorithm alg = new RandomAlg(tree);

        alg.Solve();
        PrintListOfNodes(alg.Solution);

        for (int i = 0; i < board.colorNumber; i++) {

            JButton btnRed = new JButton(" ");
            btnRed.setBackground(board.BoardColors[i]);
            final Color col = board.BoardColors[i];
            btnRed.setPreferredSize(new Dimension(100, 100));
            btnRed.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    if (!board.IsFullyColored()) {
                        board = board.ApplyColor(col);
                        subPanel subP = new subPanel(board);
                        subP.setPreferredSize(new Dimension(100, 100));
                        subP.setLayout(new GridLayout(dim, dim));
                        userPannel.add(subP, BorderLayout.SOUTH);
                        myFrame.pack();

                    }
                }
            });

            subP1.add(btnRed);
        }

        getContentPane().add(subP1, BorderLayout.PAGE_START);

        getContentPane().add(RandomAlgoPannel, BorderLayout.CENTER);
        getContentPane().add(userPannel, BorderLayout.LINE_END);

        getContentPane().setPreferredSize(new Dimension(1500, 700));
    }

    void PrintListOfNodes(List<Node> lst) {
        for (int i = 0; i < lst.size(); i++) {
            printNode(lst.get(i));
        }
    }

    private void PrintTree(Node root) {

        printNode(root);
        for (Node n : root.Children) {
            PrintTree(n);
        }
    }

    void printNode(Node node) {
        if (node != null) {
            subPanel subP = new subPanel(node.board);
            subP.setPreferredSize(new Dimension(100, 100));
            subP.setLayout(new GridLayout(node.board.dim, node.board.dim));
            RandomAlgoPannel.add(subP, BorderLayout.SOUTH);
            myFrame.pack();
        }
    }

    private class subPanel extends JPanel {

        Board board;
        subPanel me;

        public subPanel(Board b) {
            super();
            board = b;
            int dim = board.dim;
            me = this;
            countMe++;
            for (int i = 0; i < dim * dim; i++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(5, 5));
                cell.setBackground(board.cells[i % dim][i / dim].color);
                cell.setBorder(new EtchedBorder(1));
                add(cell);
            }
        }
    }
}
