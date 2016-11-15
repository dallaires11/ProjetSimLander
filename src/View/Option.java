/**
 * Created by Chroon on 2016-11-12.
 */
package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Option {
    private Scene option,menu;
    private ImageView fond;
    private CheckBox chaos,god;
    private Button retour;
    Text infoChaos,infoGod;
    HBox opt1,opt2;
    VBox org;

    //Mode que quand tu lache le gaz le vaisseau deveinnt invisible

    public Option(Stage leStage){
        fond=new ImageView("Image/cielEtoile.gif");

        retour=new Button("Retour");

        chaos=new CheckBox();
        infoChaos=new Text("Mode Chaos: ");
        god=new CheckBox();
        infoGod=new Text("Infini Essence: ");

        opt1=new HBox();
        opt1.getChildren().addAll(infoChaos,chaos);
        opt2=new HBox();
        opt2.getChildren().addAll(infoGod,god);
        org=new VBox();
        org.getChildren().addAll(opt1,opt2,retour);

        setPosition(leStage);
        setAction(leStage);
        setText();

        Group root = new Group();
        root.getChildren().addAll(fond,org);

        option=new Scene(root,1400,700);
    }

    private void setPosition(Stage stage){
        org.setAlignment(Pos.CENTER);
        org.setSpacing(30);
        org.setPadding(new Insets(250,640,100,640));
        fond.fitHeightProperty().bind(stage.heightProperty());
        fond.fitWidthProperty().bind(stage.widthProperty());
    }

    private void setAction(Stage stage){
        retour.setOnAction(event -> {
            stage.setScene(menu);
        });

    }

    private void setText(){
        infoGod.setStroke(Color.WHITE);
        infoChaos.setStroke(Color.WHITE);

    }

    public Scene getSceneOption(){
        return  option;
    }

    public void setSceneMenu(Scene scene){
        menu=scene;
    }
}
