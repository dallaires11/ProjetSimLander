/**
 * Created by Chroon on 2016-11-08.
 */
package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gagner {
    private Scene gagner;
    private Text texte,sousTexte/*points*/;
    private Button toMenu/*quittez,reyassayer*/;
    private HBox orgButton;
    private VBox org;

    public Gagner(Stage stage){
        texte=new Text("YOU LANDED");
        sousTexte=new Text("Good job commander");

        toMenu=new Button("Another one\n-DJ Khaled 2015");
        toMenu.setOnAction(event -> stage.setScene(Menu.getSceneMenu()));

        orgButton=new HBox();
        orgButton.getChildren().add(toMenu);

        org=new VBox();
        org.getChildren().addAll(texte,sousTexte,orgButton);

        setText();
        setPosition();

        Group root=new Group();
        root.getChildren().add(org);

        gagner=new Scene(root,1400,700,Color.BLACK);
    }

    private void setText(){
        texte.setScaleX(10); texte.setScaleY(10);
        texte.setFill(Color.YELLOW);

        sousTexte.setFill(Color.WHITE);
        sousTexte.setScaleX(1.2);
        sousTexte.setScaleY(1.2);
    }

    private void setPosition(){
        org.setAlignment(Pos.CENTER);
        org.setSpacing(150);
        org.setPadding(new Insets(200,0,0,605));
    }

    public Scene getScene() {
        return gagner;
    }
}