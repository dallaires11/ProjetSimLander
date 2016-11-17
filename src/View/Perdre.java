/**
 * Created by Chroon on 2016-11-08.
 */
package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class Perdre {
    static Scene perdre;
    Group groupe;




    public Perdre(){
        Rectangle background = new Rectangle(1401,701, Color.BLACK);

        Text texte = new Text(null);
        Font amatic = Font.loadFont("AmaticSc-Regular.ttf", 60);

        texte.setFont(amatic);
        //texte.setScaleX(); texte.setScaleY();
        texte.setFill(Color.CRIMSON);
        texte.setText("VOUS AVEZ PERDU");

        groupe = new Group(background, texte);
        perdre = new Scene(groupe, 1400, 700);
    }

    public static void setScene(Stage stage){
        stage.setScene(perdre);

    }

    public Group getGroup(){
        return groupe;
    }
}