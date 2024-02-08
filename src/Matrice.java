package src;
import java.util.Scanner;

public class Matrice  {
    private int m_Col; // rows  of the matrice
    private int m_Row; // columns of the matrice
    private Cellule [][]arr;
    private int []celAvailb; 
    private String unicodeMessage = "\uD83D\uDD35"; // blue circle unicode 
    private String unicodeMessage2 = "\uD83D\uDD34"; // red circle unicode 
    private String unicodeMessage3 = "\u25EF"; // empty bloc 

    public Matrice(int row, int col) { // constructeur
        this.m_Row = row;
        this.m_Col = col;
        this.arr = new Cellule[row][col];
        this.celAvailb = new int[col];
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                this.arr[i][j] = new Cellule(); // we allocate the cell a type object Cellule
                if (i == row - 1) {
                    this.celAvailb[j] = i + 1; // we initialize with the number of rows availaible
                }
            }
        }
        // assign each column there neigbours
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                if (i == 0  || i == row - 1 || j == 0 || j == col-1)
                {
                    if (i == 0 && (j == 0 || j == col-1))
                    {
                        if (j == 0) {
                            this.arr[i][j].PutCellulevoisin(null, this.arr[i][j+1], null, this.arr[i + 1][j]);
                        }
                        else {
                            this.arr[i][j].PutCellulevoisin(this.arr[i][j - 1], null, null, this.arr[i + 1][j]);
                        }
                    }
                    else if (i == row -1 &&(j == 0 || j == col - 1))
                    {
                        if (j == 0) {
                            this.arr[i][j].PutCellulevoisin(null, this.arr[i][j + 1], this.arr[i - 1][j], null);
                        }
                        else {
                            this.arr[i][j].PutCellulevoisin(this.arr[i][j - 1], null, this.arr[i - 1][j], null);
                        }
                    }
                    else if ((j > 0 && j < col - 1) && (i == 0 || i == row - 1))
                    {
                        if (i == 0) {
                            this.arr[i][j].PutCellulevoisin(this.arr[i][j-1], this.arr[i][j+1], null, this.arr[i + 1][j]);
                        }
                        else {
                            this.arr[i][j].PutCellulevoisin(this.arr[i][j-1], this.arr[i][j+1], this.arr[i - 1][j],null);
                        }
                    } 
                    else if ((i > 0 && i < row - 1) && (j == 0 || j == col - 1))
                    {
                        if (j == 0) {
                            this.arr[i][j].PutCellulevoisin(null, this.arr[i][j+1], this.arr[i - 1][j],this.arr[i + 1][j]);
                        }
                        else {
                            this.arr[i][j].PutCellulevoisin(this.arr[i][j - 1], null, this.arr[i - 1][j],this.arr[i + 1][j]);
                        }
                    }
                }
                else
                {
                    this.arr[i][j].PutCellulevoisin(this.arr[i][j - 1], this.arr[i][j + 1], this.arr[i - 1][j],this.arr[i + 1][j]);
                }
            }
        }
    }

    public String getUnicodeBlue()
    {
        return this.unicodeMessage;
    }

    public String getUnicodeRed()
    {
        return this.unicodeMessage2;
    }

    public Cellule getCellFromArr(int i, int j)
    {
        return  this.arr[i][j];
    }

    public int getRow() {
        return this.m_Row;
    }

    public int getCol() {
        return this.m_Col;
    }

    public int getRowAv(int col){ // get the number of rows available in a column
        return this.celAvailb[col];
    }

    public void setRowAvDecrem(int minus, int col) // decrement the number of rows availaible in a column
    {
        this.celAvailb[col] -= minus;
    }

    public boolean CheckIfRempli(int col) // checks if the specific column is empty
    {
        if (this.getRowAv(col) <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean checkIfALlColRempli()  // checks if all the columns is full
    {
        int compteur = 0;
        for(int i = 0; i < this.m_Col; i++)
        {
            if (this.getRowAv(i) == 0)
            {
                compteur++;
            }
        }
        if (compteur == this.m_Col)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int Myexception(int col) // we check in this function if 
    {                               // the columns entered exists or if it is full
        Boolean ME = false;         // if is is one of the above we tell the user to enter the correct value
        Scanner inpu = new Scanner(System.in);
        if (col > 6 || col < 0) {
            while (ME == false) {
                System.out.print("There is no column " + col + " restart : ");
                col = inpu.nextInt(); // colone
                if (col <= 6 && col >= 0) {
                    ME = true;
                }
            }
        }
        else if (this.CheckIfRempli(col) == false)
        {
            ME = false;
            while(ME == false)
            {
                System.out.print("Cette colonne est rempli " + col + " Ressayer : ");
                col = inpu.nextInt(); // colone
                if (col <= 6 && col >= 0 && this.CheckIfRempli(col) == true)
                {
                    ME = true;
                }
            }
        }
        return col;
    }

    // we decrement the number of space available in the column
    public void DecrementCelAivBy1(int col)
    {
        this.setRowAvDecrem(1, col);
    }

    public void PrintMat() // we print the grid
    {
        System.out.print("\n\n");
        System.out.println("\t\t\t\t\t    0      1      2      3      4      5      6\n");
        for(int i = 0; i < this.getRow(); i++)
        {
            System.out.print("\t\t\t\t\t");
            for(int j = 0 ; j < this.getCol(); j++)
            {
                if(this.arr[i][j].getColor() == Cellule.Couleur.BLUE)
                {
                    System.out.print("   " + this.unicodeMessage   + "  ");
                }
                else if (this.arr[i][j].getColor() == Cellule.Couleur.RED)
                {
                    System.out.print("   " + this.unicodeMessage2 + "  ");
                }
                else
                {
                    System.out.print("   " + this.unicodeMessage3 + "   ");
                }
            }
            System.out.printf("\n\n");        
        }
    }

}
