/**
 * Created by Chroon on 2016-11-08.
 */
package Model;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.Vector;
import java.util.stream.IntStream;

public class Sol {

    private int precision;
    private Path solPath;
    private Vector<Integer> solValeurs;
    private int plat1;
    private int plat2;
    private int diff;


    public Sol(){
        solValeurs = new Vector<Integer>();
        precision = 10;
    }

    public void genererPath(int diff,int planete){
        this.diff = diff;
        if (planete == 1) {
            solPath = generateur(diff, 75);
        }
        if (planete == 2) {
            solPath = generateur(diff, 150);
        }
        if(planete == 3) {
            solPath = generateur(diff, 250);
        }
        if(planete == 4) {
            solPath = generateur(diff, 400);
        }
        setPathColor(planete);
    }

    public Path getPath(){
        return solPath;
    }

    public Vector<Integer> getSolValeurs(){
        return solValeurs;
    }

    private Path generateur(int diff, int variation){
        Path path;
        Vector<Integer> temp = new Vector<>();
        IntStream.iterate(0, i -> i = (int) (Math.random() * variation) + 80)
                    .limit(140)
                    .forEach(i -> temp.add(i));

            plat1 = 70 - (int)(Math.random() * 65);
            plat2 = (int)(Math.random() * 65) + 70;

            Path ground = new Path();
            for (int x = 0; x < 140; x++) {
                if (x == 0)
                    ground.getElements().add(new MoveTo(0, 700 - temp.get(x)));

                else if (x == plat1 || x == plat2) {
                    int h = 700 - temp.get(x);
                    ground.getElements().addAll(new LineTo(x * precision, h));
                    x += 6/diff;
                    ground.getElements().addAll(new LineTo(x * precision, h));
                    for (int z = 0; z < diff; z++) {
                        solValeurs.add(h);
                    }

                } else {
                    ground.getElements().add(new LineTo((x * precision), 700 - (temp.get(x))));
                    solValeurs.add(700 - (temp.get(x)));
                }
            }

        path = ground;
        return path;
    }

    private void setPathColor(int planete){
        switch (planete){
            case 1:
                solPath.setStroke(Color.GREEN);
                break;
            case 2:
                solPath.setStroke(Color.DARKORANGE);
                break;
            case 3:
                LinearGradient lnGr1=new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                        new Stop(0,Color.WHITE),new Stop(1,Color.DARKGRAY));
                solPath.setStroke(lnGr1);
                break;
            case 4:
                LinearGradient lnGr2=new LinearGradient(0,0,.5,1,true, CycleMethod.REFLECT,
                        new Stop(0,Color.FIREBRICK),new Stop(1,Color.AQUAMARINE));
                solPath.setStroke(lnGr2);
                break;
            default:
                solPath.setStroke(Color.GREEN);
                break;
        }
    }

    public int getPlat1(){
        return plat1;
    }

    public int getPlat2(){
        return plat2;
    }

    public int getDiff(){
        return diff;
    }
}
