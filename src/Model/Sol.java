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
        solValeurs = new Vector<>();
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

        IntStream.iterate((int) (Math.random() * variation), i -> i = (int) (Math.random() * variation) + 80)
                .limit(141)
                .forEach(i -> temp.add(i));

            plat1 = 70 - (int)(Math.random() * 40);
            plat2 = (int)(Math.random() * 35) + 100;

            Path ground = new Path();
            for (int x = 0; x <= 140; x++) {
                if (x == 0) {
                    ground.getElements().add(new MoveTo(0, 700 - temp.get(x)));
                    solValeurs.add(700 - temp.get(x));
                }

                else if (x == plat1 || x == plat2) {
                    int h = 700 - temp.get(x);
                    ground.getElements().addAll(new LineTo(x * precision, h));
                    x += (6/diff);
                    ground.getElements().addAll(new LineTo(x * precision, h));
                    for (int z = 0; z <= (6/diff); z++) {
                        solValeurs.add(h);
                    }

                } else {
                    ground.getElements().add(new LineTo((x * precision), 700 - (temp.get(x))));
                    solValeurs.add(700 - (temp.get(x)));
                }
            }

        ground.getElements().addAll(new LineTo(1400,700), new LineTo(0,700));
        path = ground;
        return path;
    }

    private void setPathColor(int planete){
        LinearGradient terre = new LinearGradient(0,0, 0,1, true, CycleMethod.NO_CYCLE,
                new Stop(0.1, Color.IVORY), new Stop(0.2, Color.DIMGRAY), new Stop(0.5, Color.FORESTGREEN), new Stop(0.9, Color.SIENNA), new Stop(1, Color.BLACK));

        LinearGradient mars = new LinearGradient(0,0, 0,1, true, CycleMethod.NO_CYCLE,
                new Stop(0,Color.BURLYWOOD), new Stop(0.1, Color.DARKGOLDENROD), new Stop(0.6, Color.CHOCOLATE), new Stop(1, Color.MAROON));
        switch (planete){
            case 1:
                solPath.setStroke(terre);
                solPath.setFill(terre);
                break;
            case 2:
                solPath.setStroke(mars);
                solPath.setFill(mars);
                break;
            case 3:
                LinearGradient lnGr1=new LinearGradient(0,0,0,1,true, CycleMethod.NO_CYCLE,
                        new Stop(0,Color.WHITE), new Stop(0.3, Color.GRAY), new Stop(1,Color.DIMGRAY));
                solPath.setStroke(lnGr1);
                solPath.setFill(lnGr1);
                break;
            case 4:
                LinearGradient lnGr2=new LinearGradient(0,0,0.1,1,true, CycleMethod.REFLECT,
                        new Stop(0,Color.FIREBRICK),new Stop(1,Color.AQUAMARINE));
                solPath.setStroke(lnGr2);
                solPath.setFill(lnGr2);
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

    public void cleanup(){
        solValeurs.clear();
    }
}
