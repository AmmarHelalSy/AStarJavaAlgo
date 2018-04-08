package ui;

import structures.Board;
import structures.Node;
import structures.Tree;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import algorithms.Algorithm;
import algorithms.RandomAlg;

public class GameUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static GameUI gameUI;
	static int countMe = 0;
	JPanel randomAlgoSolutionPanel;
	JPanel userSolutionPanel;
	Board board;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			createAndShowGUI();
		});
	}

	private static void createAndShowGUI() {
		gameUI = new GameUI();
		gameUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameUI.prepareUI();
		gameUI.setSize(1000, 500);
		gameUI.pack();
		gameUI.setVisible(true);
	}

	private void prepareUI() {
		int dim = 4;
		board = new Board(dim, dim);
		Node root = new Node(board);
		Tree tree = new Tree(root);
		tree.generateTree();
		Algorithm alg = new RandomAlg(tree);
		alg.solve();
		
		this.setLayout(new BorderLayout());
		randomAlgoSolutionPanel = new JPanel();
		randomAlgoSolutionPanel.setLayout(new FlowLayout());
		userSolutionPanel = new JPanel();
		userSolutionPanel.setLayout(new FlowLayout());
		userSolutionPanel.setPreferredSize(new Dimension(100, 100));
		randomAlgoSolutionPanel.setPreferredSize(new Dimension(100, 100));
		// RandomAlgoPannel.setLayout(new GridLayout(3,3));

		JPanel colorsBtnPanel = new JPanel();
		colorsBtnPanel.setPreferredSize(new Dimension(500, 100));
		colorsBtnPanel.setLayout(new FlowLayout());

		// Integer.parseInt(txtDim.getText());


		for (int i = 0; i < board.colorNumber; i++) {
			JButton btnRed = new JButton(" ");
			btnRed.setBackground(board.boardColors[i]);
			final Color col = board.boardColors[i];
			btnRed.setPreferredSize(new Dimension(50, 50));
			btnRed.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					if (!board.isFullyColored()) {
						board = board.applyColor(col);
						BoardPanel boardPanel = new BoardPanel(board);
						boardPanel.setPreferredSize(new Dimension(100, 100));
						boardPanel.setLayout(new GridLayout(dim, dim));
						userSolutionPanel.add(boardPanel);
						gameUI.pack();

					}
				}
			});

			colorsBtnPanel.add(btnRed);
		}
		PrintListOfNodes(alg.solution);
		getContentPane().add(colorsBtnPanel, BorderLayout.NORTH);

		getContentPane().add(userSolutionPanel, BorderLayout.CENTER);
		getContentPane().add(randomAlgoSolutionPanel, BorderLayout.SOUTH);

		getContentPane().setPreferredSize(new Dimension(1500, 700));
	}

	void PrintListOfNodes(List<Node> lst) {
		for (int i = 0; i < lst.size(); i++) {
			printNode(lst.get(i));
		}
	}

	private void PrintTree(Node root) {

		printNode(root);
		for (Node n : root.children) {
			PrintTree(n);
		}
	}

	void printNode(Node node) {
		if (node != null) {
			BoardPanel board = new BoardPanel(node.board);
			board.setPreferredSize(new Dimension(100, 100));
			board.setLayout(new GridLayout(node.board.dim, node.board.dim));
			randomAlgoSolutionPanel.add(board);
			gameUI.pack();
		}
	}

	private class BoardPanel extends JPanel {

		Board board;
		BoardPanel me;

		public BoardPanel(Board b) {
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
