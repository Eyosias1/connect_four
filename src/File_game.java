package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class File_game
{
    private  Prog_princ gameProg;
    private  Cellule.Couleur Bleu; // blue color
    private  Cellule.Couleur Red; // red color
    private  Cellule.Couleur Blanc;// white color
    private  File file;
    private  FileWriter fw;
    private  FileReader fr;
    private  BufferedWriter out;
    private  BufferedReader outred;
    private String unicodeMessage = "\uD83D\uDD35"; // blue circle unicode 
    private String unicodeMessage2 = "\uD83D\uDD34"; // red circle unicode 

    public File_game() throws IOException
    {
        this.file = new File("Game_saved.txt"); // create the file
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // assign values colors
        this.Blanc = Cellule.Couleur.Blanc;
        this.Bleu = Cellule.Couleur.BLUE;
        this.Red = Cellule.Couleur.RED;
    }
    
    public void Save_game (Prog_princ g)
    {
        try {
            this.gameProg = g; 
            this.fw = new FileWriter(this.file); 
            this.out = new BufferedWriter(this.fw);
            out.append(this.gameProg.getPlayer1name() + "\n"); // append the p1 name int the file
            out.append(this.gameProg.getPlayer2name() + "\n"); // append the p2 name int the file
            out.append(this.gameProg.getColType() + "\n"); // append the the color type of the player turn
            out.append(this.gameProg.getplayerturn() + "\n"); // append the player turn in the file
            for(int i = 0; i < this.gameProg.getMatrice().getRow(); i++)
            {
                for(int j = 0; j < this.gameProg.getMatrice().getCol(); j++)
                {
                    if (this.gameProg.getMatrice().getCellFromArr(i, j).getColor() == this.Blanc) // if the cell was empty append 0
                    {
                        out.append("0"); 
                    }
                    else if (this.gameProg.getMatrice().getCellFromArr(i, j).getColor() == this.Bleu) // if the cell was a blue circle append 1
                    {
                        out.append("1"); 
                    }
                    else // if the cell was a red circle append 2 in the file
                    {
                        out.append("2"); 
                    }
                }
                out.append("\n"); 
            }
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    

    public void Load_game (Prog_princ g) throws IOException
    {
        String p1;
        String p2;

        this.fr = new FileReader(this.file);
        this.outred = new BufferedReader(this.fr);
        if (this.file.length() == 0) { // start a new game in the file there is no data
            Scanner sc = new Scanner(System.in);
            Scanner inpu = new Scanner(System.in);
            System.out.print("\n\n\n\n\t\t\t\t\tThere is no game saved Restart !!!\n\n\n\n");
            System.out.print("\t\t\t\t\t\tState your name P1 : ");
            p1 = sc.nextLine();
            System.out.print("\t\t\t\t\t\tState your name P2 : ");
            p2 = sc.nextLine();
            System.out.print("\n\t\t\t\t\t\tWhat Token color do you pick " + "'"+ p1 + "'" + this.unicodeMessage+ "  " + this.unicodeMessage2+"\n\t\t\t\t\t\ttype 1 for BLUE or 2 For RED : ");
            g.setColType(inpu.nextInt());
            g.setPlayersname(p1, p2);
            g.setPlayersColType();
        }
        else 
        {
            p1 = outred.readLine(); // assign the name of the p1 from the file
            p2 = outred.readLine();// assign the name of the p2 from the file
            g.setColType(Integer.parseInt(outred.readLine())); // assign the color of the players turn
            g.setPlayerturn(Integer.parseInt(outred.readLine())); // assign the play player turn 
            g.setPlayersname(p1, p2); // set the name
            g.setPlayersColType(); // set the color type
            int i;
            int j = -1; // col
            int k = 0; // row 
            while( (i = outred.read()) != -1)
            {
                j++;
                if ((char)i == '0') { // if 0 we assign cell color white
                    g.getMatrice().getCellFromArr(k, j).setColor(this.Blanc);
                }
                else if ((char)i == '1') {// if 2 we assign cell color blue 
                    g.getMatrice().getCellFromArr(k, j).setColor(this.Bleu);
                    g.getMatrice().DecrementCelAivBy1(j);
                }
                else if ((char)i == '2')// if 3 we assign cell color red
                {
                    g.getMatrice().getCellFromArr(k, j).setColor(this.Red);
                    g.getMatrice().DecrementCelAivBy1(j);
                }
                else if ((char)i == '\n')
                {
                    k++; // we increment the row
                    j = -1; // we restart the col
                }
            }
        }
    }

}