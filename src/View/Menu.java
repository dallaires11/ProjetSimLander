/**
 * Created by Chroon on 2016-11-08.
 */
package View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Menu extends Group{
    private Scene menu;
    private Group root;
    private Button commencer,option,quitter;
    private ImageView fond,titre;

    public Menu(Stage stage){
        fond=new ImageView("Image/cielEtoile.gif");
        titre=new ImageView();
        commencer=new Button("Commencer");
        option=new Button("Option");
        quitter=new Button("Quitter");
        setText();
        setPosition(stage);
        setAction(stage);
        root=new Group();
        root.getChildren().addAll(fond,titre,commencer,option,quitter);
        menu=new Scene(root,1400,700);
    }

    private void setText(){

    }

    private void setPosition(Stage stage/*Option,Jeu*/){
        titre.setTranslateX(700);
        titre.setTranslateY(50);
        commencer.setTranslateX(700);
        commencer.setTranslateY(200);
        option.setTranslateX(700);
        option.setTranslateY(250);
        quitter.setTranslateX(700);
        quitter.setTranslateY(300);
        fond.fitHeightProperty().bind(stage.heightProperty());
        fond.fitWidthProperty().bind(stage.widthProperty());
    }

    private void setAction(Stage stage/*option, SceneJeu*/){
        quitter.setOnAction(event -> {
            stage.close();
        });
        /*option.setOnAction(event -> {
            stage.setScene(Option.getSceneOption());
        });
        commencer.setOnAction(event -> {
            stage.setScene(Option.getSceneChoix);


            stage.setScene(Jeu.getSceneJeu);
            Pause(2s)
            Jeu.StartTimeline()
            );
        });*/

    }

    public Scene getSceneMenu(){
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