/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.awt.Color;

/**
 *
 * @author Ammar
 */
public class BoardCell {
    
    public Color color;
    public int xCor;
    public int yCor;
     boolean isVisited;
    
    public boolean IsColored()
    {
        return isVisited;
    }
    
    public Color GetColor()
    {
        return color;
    }
    
    public void SetColor(Color p_color)
    {
        color = p_color;
    }
    public void SetIsVisited(boolean value)
    {
        isVisited = value;
    }
    public BoardCell(BoardCell p_cell)
    {
        this.SetColor(p_cell.GetColor());
        this.isVisited = p_cell.IsColored();
        this.xCor = p_cell.xCor;
        this.yCor = p_cell.yCor;
        
    }
    public BoardCell(Color p_Color,int p_xCor,int p_yCor)
    {
        color = p_Color;
        xCor = p_xCor;
        yCor  = p_yCor;
        isVisited = false;
    }
            
    
    
}
