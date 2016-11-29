/**
 * Created by Chroon on 2016-11-29.
 */
package Controller;

import View.Menu;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class IntroJeu {
    private Scene sceneIntro;
    private ImageView vaisseau,fond,logo;
    private Rectangle sol;
    private Group root,ensemblesol;
    private MediaPlayer intro;

    public IntroJeu(){
        intro=new MediaPlayer(new Media(new File("src/Sound/Intro.mp3").toURI().toString()));
        vaisseau=new ImageView("Image/Spaceship.png");
        fond=new ImageView("Image/fondJeu.jpg");
        logo=new ImageView("Image/LogoComp.png");
        sol=new Rectangle(1400,400, Color.BISQUE);



        setPosition();

        ensemblesol=new Group();
        ensemblesol.getChildren().addAll(fond,sol,logo);

        root=new Group();
        root.getChildren().addAll(ensemblesol,vaisseau);

        sceneIntro=new Scene(root,1400,700,Color.BLACK);
    }

    private void setPosition(){
        vaisseau.setFitHeight(500);
        vaisseau.setFitWidth(500);
        vaisseau.setTranslateX(400);



        sol.setTranslateY(900);

        logo.setFitHeight(160);
        logo.setFitWidth(130);
        logo.setTranslateX(590);
        logo.setTranslateY(1000);
    }

    public Scene getSceneIntro(Stage stage) {
        setAnimationInro(stage);
        return sceneIntro;
    }

    private void setAnimationInro(Stage stage){
        FadeTransition ft = new FadeTransition(Duration.seconds(5),root);
        ft.setFromValue(0);
        ft.setToValue(1);

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(5),ensemblesol);
        tt1.setFromY(0);
        tt1.setToY(-400);

        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(2),ensemblesol);
        tt2.setFromY(-400);
        tt2.setToY(-490);

        TranslateTransition tt3 = new TranslateTransition(Duration.seconds(2),ensemblesol);
        tt3.setFromY(-490);
        tt3.setToY(-550);

        FadeTransition ftLogo =  new FadeTransition(Duration.seconds(4),logo);
        ftLogo.setFromValue(0);
        ftLogo.setToValue(1);

        ParallelTransition pt = new ParallelTransition(ft,tt1);

        SequentialTransition st = new SequentialTransition(pt,tt2,tt3,ftLogo);
        st.play();

        stage.setScene(Menu.getSceneMenu());
    }
}
