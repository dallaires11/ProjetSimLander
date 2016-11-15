package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.Map;
import java.util.Vector;
import java.util.stream.IntStream;

/**
 * Created by Chroon on 2016-11-08.
 */
public class Sol {
    /*
    Ininitialisateur de point(constructeur)
    Liste de point (souvenir)
    Path
    note: mettre path colueur selon planete
     */

    private int precision;
    private Path solPath;
    private Vector<Integer> solValeurs;


    public Sol(){
        solValeurs = new Vector<Integer>();
        precision = 10;
    }

    public void creerPath(int diff){
        Vector<Integer> temp = new Vector<>();
        IntStream.iterate(0, i -> i = (int) (Math.random() * 250))
                .limit(140)
                .forEach(i->temp.add(i));

        int plat1 = 700 - (int)(Math.random() * 680);
        int plat2 = (int) (Math.random() * 680) + 700;

        Path ground = new Path();
        for(int x = 0; x < 140; x++){
            if (x == 0)
                ground.getElements().add(new MoveTo(0, 700 - temp.get(x)));
            else if(x == plat1 || x == plat2){
                int h = 700 - temp.get(x);
                ground.getElements().addAll(new LineTo(x * precision,h));

                x += diff;
                ground.getElements().addAll(new LineTo(x * precision,h));

                for(int z=0; z<diff ;z++){
                    solValeurs.add(h);
                }
            }
            else {
                ground.getElements().add(new LineTo((x * precision), 700 - (temp.get(x))));
                solValeurs.add(700-(temp.get(x)));
            }
        }
        solPath = ground;
    }

    public Path getPath(){
        return solPath;
    }

    public Vector<Integer> getSolValeurs(){
        return solValeurs;
    }

    public void setPathColor(Color couleur){
        solPath.setFill(couleur);
    }
}
