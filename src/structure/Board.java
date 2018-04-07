/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ammar
 */
public class Board {
    public BoardCell [][] cells;
    public int dim;
    public int colorNumber;
    public Color [] BoardColors;
   public BoardCell sourceCell;
   public int SourceConnectedCellsCount = 0;
   
   public Board(int p_dim,int p_colorNumber)
   {
       colorNumber = p_colorNumber;
       dim  = p_dim;
       BoardColors = new Color[p_colorNumber];
       cells = new BoardCell[p_dim][p_dim];
       mtdInitBoardColor();
       mtdInitBoardCells();
       sourceCell = cells[0][0];
   }
   
   public Board(Board b)
   {
       this.dim = b.dim;
       this.colorNumber = b.colorNumber;
       this.BoardColors = new Color[colorNumber];
       for (int i = 0; i < b.colorNumber; i++) {
           this.BoardColors[i] = b.BoardColors[i];
       }
       this.cells = new BoardCell[dim][dim];
       for (int i = 0; i < dim; i++) {
           for (int j = 0; j < dim; j++) {
               this.cells[i][j] = new BoardCell(b.cells[i][j]);
           }
       }
       this.sourceCell = cells[0][0];
   }
   public boolean IsFullyColored()
   {
       for (int i = 0; i < dim; i++) {
           for (int j = 0; j < dim; j++) {
               if(cells[i][j].GetColor() != sourceCell.GetColor())
               {
                   return false;
               }
           }
       }
       return true;
   }
   public Board ApplyColor(Color p_color)
   {
       mtdResetBoard();
       ColorConnectedCells(1,0,p_color);
       ColorConnectedCells(0,1,p_color);
          
       sourceCell.color = p_color;     
       return this;
   }
   public void ColorConnectedCells(int x,int y,Color p_color)
   {
       if (!cells[x][y].IsColored())
       {
             
               cells[x][y].SetIsVisited(true); 
           if (cells[x][y].GetColor() != sourceCell.color)
           {
              // cells[x][y].SetColor( p_color);
           }
           else
           {
               if(x==0 && y ==0)
               {}else if(x>0 && x < dim-1 && y >0 && y<dim-1)
               {
                   ColorConnectedCells(x-1,y,p_color);
                   ColorConnectedCells(x,y-1,p_color);
                   ColorConnectedCells(x+1,y,p_color);
                   ColorConnectedCells(x,y+1,p_color);
               cells[x][y].SetColor( p_color);
              
                   
               } else if(x>0 && x < dim-1 && y ==0)
               {
                   ColorConnectedCells(x-1,y,p_color);
                   ColorConnectedCells(x,y+1,p_color);
                   ColorConnectedCells(x+1,y,p_color);
               cells[x][y].SetColor( p_color);
              
               } else if (x==0 && y>0 && y<dim-1)
               {
                   ColorConnectedCells(x,y-1,p_color);
                   ColorConnectedCells(x,y+1,p_color);
                   ColorConnectedCells(x+1,y,p_color);
               cells[x][y].SetColor( p_color);
             
               }else if(x>0 && x < dim-1 && y ==dim-1)
               {
                   ColorConnectedCells(x-1,y,p_color);
                   ColorConnectedCells(x,y-1,p_color);
                   ColorConnectedCells(x+1,y,p_color);
               cells[x][y].SetColor( p_color);
             
               } else if(y>0 && y < dim-1 && x ==dim-1)
               {
                   ColorConnectedCells(x-1,y,p_color);
                   ColorConnectedCells(x,y-1,p_color);
                   ColorConnectedCells(x,y+1,p_color);
               cells[x][y].SetColor( p_color);
            
               }else if (x==dim-1 && y == dim -1)
               {
                   ColorConnectedCells(x,y-1,p_color);
                   ColorConnectedCells(x-1,y,p_color);
               cells[x][y].SetColor( p_color);
            
               } else if (x==0 && y == dim -1)
               {
                   ColorConnectedCells(x,y-1,p_color);
                   ColorConnectedCells(x+1,y,p_color);
               cells[x][y].SetColor( p_color);
            
               }  else if (x==dim -1 && y == 0)
               {
                   ColorConnectedCells(x,y+1,p_color);
                   ColorConnectedCells(x-1,y,p_color);
               cells[x][y].SetColor( p_color);
            
               } 
           }
           
       }
   }
   public void mtdResetBoard()
   {
       for (int i = 0; i < dim; i++) {
           for (int j = 0; j < dim; j++) {
               cells[i][j].SetIsVisited( false);
           }
       }
   }
    private void mtdInitBoardColor() {
         Color [] colors = {Color.red,Color.yellow,Color.blue,Color.ORANGE,Color.green,Color.CYAN};
        for (int i = 0; i < colorNumber; i++) {
            BoardColors[i]=colors[i];
        }
        
    }

    private void mtdInitBoardCells() {
         Color [] colors = {Color.red,Color.yellow,Color.blue,Color.ORANGE,Color.green,Color.CYAN};
        Random r = new Random();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                cells[i][j]=new BoardCell(colors[r.nextInt(colorNumber)], i, j);
            }
            
        }
    }
    public int GetColorsCount()
    {
        List<Color> lstColors = new ArrayList<>();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (!lstColors.contains(cells[i][j].GetColor()))
                {
                    lstColors.add(cells[i][j].GetColor());
                }
            }
        }
        return lstColors.size();
    }
    public int GetTheNumberOfConnectedCellsWithSource()
    {
        SourceConnectedCellsCount = 0;
        mtdResetBoard();
        GetSourceConnectedCount(0,0);
        return SourceConnectedCellsCount;
    }
    public int GetCountOfBlocks()
    {
        mtdResetBoard();
        int count = 0;
        for (int i = 0; i < dim ; i++) {
            for (int j = 0; j < dim; j++) {
                if(!cells[i][j].IsColored())
                {
                    GetSourceConnectedCount(i,j);
                    count++;
                }
            }
        }
        return count;
    }
    private void GetSourceConnectedCount(int x,int y)
    {
        try
        {
        if (!cells[x][y].isVisited && cells[x][y].GetColor() == sourceCell.GetColor())
        {
            cells[x][y].SetIsVisited(true);
            SourceConnectedCellsCount ++;
            if(x > 0 && x<dim-1 && y<dim-1 && y>0)
            {
                GetSourceConnectedCount(x-1,y);
                GetSourceConnectedCount(x,y-1);
                GetSourceConnectedCount(x+1,y);
                GetSourceConnectedCount(x,y+1);
            }
            else if(x ==0 && x<dim-1 && y<dim-1 && y>0)
            {
                GetSourceConnectedCount(0,y-1);
                GetSourceConnectedCount(0,y+1);
                GetSourceConnectedCount(1,y);
            }
            else if(x>0 && x ==dim-1 && y<dim-1 && y>0)
            {
                GetSourceConnectedCount(dim-1,y-1);
                GetSourceConnectedCount(dim-1,y+1);
                GetSourceConnectedCount(dim-2,y);
            }
            else if(x > 0 && x<dim-1 && y==dim-1 && y>0)
            {
                GetSourceConnectedCount(x-1,dim-1);
                GetSourceConnectedCount(x+1,dim-1);
                GetSourceConnectedCount(x,dim-2);
            }
            else if(x > 0 && x<dim-1 && y<dim-1 && y==0)
            {
                GetSourceConnectedCount(x-1,0);
                GetSourceConnectedCount(x+1,0);
                GetSourceConnectedCount(x,1);
            }
            else if(x == 0 && x<dim-1 && y<dim-1 && y==0)
            {
                GetSourceConnectedCount(0,1);
                GetSourceConnectedCount(1,0);
            }
            else if(x == dim -1 && x>0 && y==dim-1 && y>0)
            {
                GetSourceConnectedCount(0,1);
                GetSourceConnectedCount(1,0);
            }
            else if(x < dim -1 && x==0 && y==dim-1 && y>0)
            {
                GetSourceConnectedCount(1,dim-1);
                GetSourceConnectedCount(0,dim-2);
            }
            else if(x == dim -1 && x>0 && y<dim-1 && y==0)
            {
                GetSourceConnectedCount(dim-1,1);
                GetSourceConnectedCount(dim-2,0);
            }
        }
        
        }
        catch(Exception ex)
        {
            String ss= ex.toString();
        }
        
    }
}
