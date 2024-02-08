package src;
import java.io.IOException;
import java.util.Scanner;






public class Prog_princ {
    private String p1;// name of the player 1
    private String p2; // name of the player 2
    private int PlayernextPlay; // the turn of a player in the game
    private Cellule.Couleur P1C; // Player one color
    private Cellule.Couleur P2C;// Player two color
    private String unicodeMessage = "\uD83D\uDD35"; // blue circle unicode
    private String unicodeMessage2 = "\uD83D\uDD34"; // red circle unicode
    private Matrice Gamegrid; // Game grid Matrice
    private boolean GameState;// the state of the game if true continue if not stop
    private int gamestate; // checks if the user entered a value to save the game
    private int Gcol; // the column to place the token
    private int Grow; // the row to place the token
    private int ColType; // int of the color chosen at the begining

    public Prog_princ() // constructeur
    {
        this.Gamegrid = new Matrice(6, 7);
        this.GameState = true;
        this.Gcol = 0;
        this.Grow = 0;
        this.PlayernextPlay = 0;

    }

    public int getColType()
    {
        return this.ColType;
    }

    public void setColType(int val)
    {
        this.ColType =  val;
    }

    public Matrice getMatrice()
    {
        return this.Gamegrid;
    }

    public void setPlayersColType() // sets the players color based on the imput of the player 1
    {
        if (this.ColType == 1) {
            this.P1C = Cellule.Couleur.BLUE;
            this.P2C = Cellule.Couleur.RED;
        }
        else {
            this.P1C = Cellule.Couleur.RED;
            this.P2C = Cellule.Couleur.BLUE;
        }
    }

    public void setPlayersname(String p1, String p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getPlayer1name()
    {
        return this.p1;
    }

    public String getPlayer2name(){
        return this.p2;
    }

    public Cellule.Couleur getP1Color()
    {
        return this.P1C;
    }

    public Cellule.Couleur getP2Color()
    {
        return this.P2C;
    }

    public int getplayerturn()
    {
        if (this.PlayernextPlay == 0) { // then p1 turn in the game
            return 0;
        }
        else { // then p2 turn in the game
            return 1;
        } 
    }

    public void setPlayerturn(int val) {
        this.PlayernextPlay = val;
    }

    // after putting a token on the grid we change the players turn 
    public void setPlayertokenOngrid() {

            if (this.getplayerturn() == 0) {
                this.Gamegrid.getCellFromArr(this.Grow, this.Gcol).setColor(this.getP1Color());
                this.setPlayerturn(1);
            }
            else {
                this.Gamegrid.getCellFromArr(this.Grow, this.Gcol).setColor(this.getP2Color());
                this.setPlayerturn(0);
            }
    }
    
    public String getUnicodeofPlayer(Cellule.Couleur C)
    {
        if (C == Cellule.Couleur.BLUE) {
            return this.Gamegrid.getUnicodeBlue();
        }
        else 
        {
            return this.Gamegrid.getUnicodeRed();
        }
    }

    public void printWinnerOrGameOver()
    {
        if (this.Gamegrid.getCellFromArr(this.Grow, this.Gcol).CheckAll(3) == true)
        {
            // from the token place we checked if the game is over or not if the game is over we compare teh color to the players color
            if (this.Gamegrid.getCellFromArr(this.Grow, this.Gcol).getColor() == this.getP1Color())
            {
                System.out.println("\n\t\t\t\t\t\t    " + this.p1 + " " + this.getUnicodeofPlayer(this.getP1Color())  + " is the winner !!");
            }
            else
            {
                System.out.println("\n\t\t\t\t\t\t    " + this.p2 + " " + this.getUnicodeofPlayer(this.getP2Color()) + " is the winner !!");
            }
            this.GameState = false; // we stop the game
        } 
        else if (this.Gamegrid.checkIfALlColRempli() == true)
        {
            // we check also if all the column is empty then the game is a tie
            System.out.println("\n\t\t\t\t\t\tThe Game is Over no player one");
            this.GameState = false;
        } 
    }

    public void printWelcompage() {
        System.out.println("\t\t\t\t\t\t***********************");
        System.out.println("\t\t\t\t\t\t******           ******");
        System.out.println("\t\t\t\t\t\t****** Connect 4 ******");
        System.out.println("\t\t\t\t\t\t******           ******");
        System.out.println("\t\t\t\t\t\t***********************");
        System.out.print("\n\n\n");
        System.out.print("\t\t\t\t\t\tStart New Game: type 1\n\t\t\t\t\t\tContinue Game: type 2\n");
        System.out.print("\t\t\t\t\t\tChoose : ");
    }

    public void printFirstPage() throws IOException {
        String p1;
        String p2;
        int choice;
        Scanner inpu = new Scanner(System.in);
        this.printWelcompage();
        choice = inpu.nextInt();
        System.out.print("\n\n\n");                 // we initialize all the variables
        if (choice == 1 ) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\t\t\t\t\t\tState your name P1 : ");
            p1 = sc.nextLine();
            System.out.print("\t\t\t\t\t\tState your name P2 : ");
            p2 = sc.nextLine();
            System.out.print("\n\t\t\t\t\t\tWhat Token color do you pick " + "'"+ p1 + "'" + this.unicodeMessage+ "  " + this.unicodeMessage2+"\n\t\t\t\t\t\ttype 1 for BLUE or 2 For RED : ");
            this.ColType = inpu.nextInt();
            this.setPlayersname(p1, p2);
            this.setPlayersColType();
        }
        else {
            File_game F = new File_game();
            F.Load_game(this); // we load the game if there is a file saved if not we start a new game
        }
    }

    public static void main(String[] args) throws IOException {
        Prog_princ Game = new Prog_princ();
        Game.printFirstPage(); // we check if the user wants to start a new game or load one
        Game.Gamegrid.PrintMat(); // print the grid
        Scanner inpu = new Scanner(System.in);
        while(Game.GameState == true) { // each time we ask the current player to put a token on the column desired
            System.out.println(Game.getPlayer1name() + " Color is : " + Game.getP1Color() + " "+ Game.getUnicodeofPlayer(Game.getP1Color()) +" \n" + Game.getPlayer2name() + " Color is : " + Game.getP2Color()+ " "+ Game.getUnicodeofPlayer(Game.getP2Color()));
            if (Game.getplayerturn() == 0) { // means if player 1 turn 
                System.out.print("\n\t\t\t\t\tTo save hit 999\n\t\t\t\t\tChose a column to put your token " + "'"+ Game.getPlayer1name() + "'"  + "\n\t\t\t\t\tBetween 0 and 6 : ");
            }
            else { //means if player 2 turn
                System.out.print("\n\t\t\t\t\tTo save hit 999\n\t\t\t\t\tChose a column to put your token " + "'"+ Game.getPlayer2name() + "'"  + "\n\t\t\t\t\tBetween 0 and 6 : ");
            }
            Game.gamestate = inpu.nextInt(); // get the number from the imput stream
            if (Game.gamestate == 999) { // means save the game
                Game.GameState = false;
                File_game F = new File_game();
                F.Save_game(Game);
                break; // check out the game
            }
            else {
                Game.Gcol = Game.gamestate; // colone
                Game.Gcol = Game.Gamegrid.Myexception(Game.Gcol); // after the column has been checked
                Game.Gamegrid.DecrementCelAivBy1(Game.Gcol);
                Game.Grow = Game.Gamegrid.getRowAv(Game.Gcol);
                Game.setPlayertokenOngrid(); // we set the player token on the grid and change the turn
                Game.Gamegrid.PrintMat();    // print the grid
                Game.printWinnerOrGameOver(); // check if the game is a tie, won or continues
            }
        }
    }
}
