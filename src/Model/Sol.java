package Model;

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

    Path solPath;
    Vector<Integer> solValeurs;


    public Sol(int diff){
        solValeurs = new Vector<>();
        IntStream.iterate(0, i -> i = (int) (Math.random() * 250))
                .limit(140)
                .forEach(i->solValeurs.add(i));

        solValeurs.stream().mapToInt(i -> (i))
                .map((i,j) -> {
                    if (Math.random() * 2 > 1)
                        i+j;
                })

        Path ground = new Path();
        for(int x=0;x<140;x++){
            if (x==0)
                ground.getElements().add(new MoveTo(0,700-sol.get(x)));
            else if(x==debPlat1||x==debPlat2){
                int h= 700- sol.get(x);
                ground.getElements().addAll(new LineTo(x*precision,h));
                x+=diff;
                ground.getElements().addAll(new LineTo(x*precision,h));
                for(int z=0;z<diff;z++){
                    solH.add(h);
                }
            }
            else {
                ground.getElements().add(new LineTo((x*precision),700-(sol.get(x))));
                solH.add(700-(sol.get(x)));
            }
        }

        return ground;
    }

}
