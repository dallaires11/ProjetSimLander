/**
 * Created by Chroon on 2016-11-08.
 */
package View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Group{
    private static Scene menu;
    private Group root;
    private Button commencer,option,quitter;
    private ImageView fond,titre;
    private VBox org;

    public Menu(Stage stage,Choix choix,Option classOption){
        fond=new ImageView("Image/cielEtoile.gif");
        titre=new ImageView("Image/Logo.png");
        commencer=new Button("Commencer");
        option=new Button("Option");
        quitter=new Button("Quitter");
        org=new VBox();
        org.getChildren().addAll(titre,commencer,option,quitter);

        setText();
        setPosition(stage);
        setAction(stage,choix,classOption);

        root=new Group();
        root.getChildren().addAll(fond,org);
        menu=new Scene(root,1400,700);

        classOption.setSceneMenu(menu);
    }

    private void setText(){

    }

    private void setPosition(Stage stage/*Option,Jeu*/){
        org.setAlignment(Pos.CENTER);
        org.setTranslateX(335);
        org.setSpacing(20);
        org.setPadding(new Insets(50,0,0,60));
        fond.fitHeightProperty().bind(stage.heightProperty());
        fond.fitWidthProperty().bind(stage.widthProperty());
    }

    private void setAction(Stage stage,Choix choix,Option classOption){
        quitter.setOnAction(event -> {
            stage.close();
        });
        option.setOnAction(event -> {
            stage.setScene(classOption.getSceneOption());
        });

        commencer.setOnAction(event -> {
            stage.setScene(choix.getSceneChoix());
        });

    }

    public static Scene getSceneMenu(){
        return  menu;
    }
    /*
    Scene
    demande un nom p etre
    3 choix de depart: commencer qui memne a choix de diff, choisir plante(gravite)
    option: sons option , mode berserk/chaos
    Video??? si temps
    credit??? si temps
    revenir menu quand mort

    */
}