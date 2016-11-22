/**
 * Created by Chroon on 2016-11-08.
 */
package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.File;

public class Perdre {
    static Scene perdre;
    Group groupe;
    Stage ceStage;

    public Perdre(){
        Rectangle background = new Rectangle(1401,701, Color.BLACK);

        Text texte = new Text(null);
        Text lol = new Text("Get good.");


        texte.setScaleX(10); texte.setScaleY(10);
        texte.setFill(Color.RED);
        texte.setText("YOU HAVE CRASHED");
        texte.setLayoutY(300);
        texte.setLayoutX(650);

        lol.setLayoutY(400);
        lol.setLayoutX(650);
        lol.setFill(Color.WHITE);
        lol.setScaleX(1.2); lol.setScaleY(1.2);

        Button toMenu = new Button("I suck, take me back home");
        toMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ceStage.setScene(Menu.getSceneMenu());
            }
        });


        groupe = new Group(background, texte, lol, toMenu);
        perdre = new Scene(groupe, 1400, 700);
    }

    public void setScene(Stage stage){
        stage.setScene(perdre);
        ceStage = stage;
    }

    public static Scene getScene(){
        return perdre;
    }

    public Group getGroup(){
        return groupe;
    }
}