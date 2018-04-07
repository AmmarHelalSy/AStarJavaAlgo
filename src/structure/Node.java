/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Ammar
 */
public class Node {

    public Node parent;
    public Board board;
    Node Parent;
    public java.util.List<Node> Children;
    int Depth;
    public boolean isVisited;

    public Node(Board p_board) {
        board = p_board;
        Children = new ArrayList<>();
        isVisited = false;
    }

    public void GenerateAllChildren() {
        if (board.IsFullyColored()) {
            return;
        }
        for (int i = 0; i < board.colorNumber; i++) {
            if (!(board.BoardColors[i] == board.sourceCell.GetColor())) {
                Board board1 = new Board(board);
                board1 = board1.ApplyColor(board1.BoardColors[i]);
                Node child = new Node(board1);
                child.Parent = this;
                child.Depth = this.Depth + 1;
                child.parent = this;
                if (child.board.SourceConnectedCellsCount > this.board.SourceConnectedCellsCount
                        || child.board.GetCountOfBlocks() < board.GetCountOfBlocks()
                        || child.board.GetColorsCount() < board.GetColorsCount()) {
                    Children.add(child);
                }
            }

        }
    }

    public Node GetChildWithlMinColorsCount() {
        Node res = null;
        int min = board.GetColorsCount();
        int max = board.GetTheNumberOfConnectedCellsWithSource();
        int countOfBlocks = board.GetCountOfBlocks();
        for (Node node : Children) {
            if (node.board.GetCountOfBlocks() < countOfBlocks) {
                countOfBlocks = node.board.GetCountOfBlocks();
                res = node;

            }
        }
        if (countOfBlocks < board.GetCountOfBlocks() && res.board.GetTheNumberOfConnectedCellsWithSource() != 0) {
            return res;
        }

        for (Node node : Children) {
            if (node.board.GetColorsCount() < min) {
                min = node.board.GetColorsCount();
                res = node;
                if (res.board.GetTheNumberOfConnectedCellsWithSource() != 0) {
                    return res;
                }
            }

        }

        for (Node node : Children) {
            if (node.board.GetTheNumberOfConnectedCellsWithSource() > max) {
                max = node.board.GetTheNumberOfConnectedCellsWithSource();
                res = node;
            }
        }
        return res;
    }
}
