package src;
import java.util.*;

public class Cellule{
    enum voisin {
        LEFT , RIGHT,  UP,  DOWN; // neighbours
    }
    enum Couleur {
        BLUE, RED, Blanc; // colors
    }

    private EnumMap<voisin, Cellule> cell;
    private Couleur C;

    public Cellule()
    {
        this.cell = new EnumMap<>(voisin.class);
        this.C = Couleur.Blanc;
    }
    // assing neighbours 
    public void PutCellulevoisin(Cellule LeftIndice,Cellule RightIndi,Cellule UpIndi,Cellule DownIndi)
    {
        this.cell.put(voisin.LEFT, LeftIndice); // reference of Left cell
        this.cell.put(voisin.RIGHT, RightIndi); // reference of right cell
        this.cell.put(voisin.UP, UpIndi); // reference of upward cell
        this.cell.put(voisin.DOWN, DownIndi); // reference of downward cell
    }

    // get the cell
    public EnumMap<voisin, Cellule> getCellule() {
        return this.cell;
    }

    // get the color of the cell
    public Cellule.Couleur getColor() {
        return this.C;
    }

    // sets the cell a color
    public void setColor (Cellule.Couleur c)
    {
        this.C = c;
    }


    // in the following functions there are part of the famely to check if the game has been won

    public int CheckVoisinLeftint()
    { 
        Cellule v2 = this.cell.get(voisin.LEFT);
        if(v2 == null) {
            return 0;
        }
        if(this.C == v2.C) {
            return 1 + v2.CheckVoisinLeftint();
        }
        return 0;
    }
    public int CheckVoisinRightint()
    {
        Cellule v2 = this.cell.get(voisin.RIGHT);
        if(v2 == null) {
            return 0;
        }
        if(this.C == v2.C) {
            return 1 + v2.CheckVoisinRightint();
        }
        return 0;
    }
    public int CheckVoisinDownint()
    {
        Cellule v2 = this.cell.get(voisin.DOWN);
        if(v2 == null) {
            return 0;
        }
        if(this.C == v2.C) {
            return 1 + v2.CheckVoisinDownint();
        }
        return 0;
    }
    public int CheckVoisinDiagRightDownint()
    {
        Cellule v1 = this.cell.get(voisin.DOWN); 
        if (v1 == null) {
            return 0;
        }
        Cellule v2 = v1.cell.get(voisin.RIGHT);
        if(v2 == null) {
            return 0;
        }
        if(this.C == v2.C) {
            return 1 + v2.CheckVoisinDiagRightDownint();
        }
        return 0;
    }
    public int CheckVoisinDiagRightUPint()
    {
        Cellule v1 = this.cell.get(voisin.UP); 
        if (v1 == null) {
            return 0;
        }
        Cellule v2 = v1.cell.get(voisin.RIGHT);
        if(v2 == null) {
            return 0;
        }
        if(this.C == v2.C) {
            return  1 + v2.CheckVoisinDiagRightUPint();
        }
        return 0;
    }
    public int CheckVoisinDiagLeftUPint()
    {
        Cellule v1 = this.cell.get(voisin.UP);
        if (v1 == null)
        {
            return 0;
        }
        Cellule v2 = v1.cell.get(voisin.LEFT);
        if(v2 == null) {
            return 0;
        }
        if(this.C == v2.C) {
            return 1 + v2.CheckVoisinDiagLeftUPint();
        }
        return 0;
    }
    public int CheckVoisinDiagLeftDownint()
    {
        Cellule v1 = this.cell.get(voisin.DOWN); 
        if (v1 == null)
        {
            return 0;
        }
        Cellule v2 = v1.cell.get(voisin.LEFT);
        if(v2 == null) {
            return 0;
        }
        if(this.C == v2.C) {
            return 1 +  v2.CheckVoisinDiagLeftDownint();
        }
        return 0;
    }
    public boolean CheckAll(int count) 
    {
        if(this.CheckVoisinLeftint() >= count)
        {
            return true;
        }
        else if(this.CheckVoisinRightint() >= count) {
            return true;
        }
        else if(this.CheckVoisinDownint() >= count) {
            return true;
        }
        else if (this.CheckVoisinDiagRightUPint() + this.CheckVoisinDiagLeftDownint()   >= count) {
            return true;
        }
        else if (this.CheckVoisinDiagLeftUPint() + this.CheckVoisinDiagRightDownint()  >= count) {
            return true;
        }
        else {
            return false;
        }
    }
}
