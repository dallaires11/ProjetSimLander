import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Prototype extends Application {

    Vaisseu vaisseu = new Vaisseu();
    Rectangle vesseau = new Rectangle(650,100,20,20);
    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Label vitesse = new Label("vitesse :" + vaisseu.getVitesse());
        Label position = new Label("Position :" + vaisseu.getVitesse());


        root.getChildren().addAll(vesseau, vitesse);

        Timeline gravite=new Timeline(new KeyFrame(
                Duration.millis(15), t->{
            vaisseu.accelerer();
            vesseau.setTranslateY(vesseau.getTranslateY()+(vaisseu.getVitesse()));
            vitesse.setText("vitesse :" + vaisseu.getVitesse());
            position.setText("Position :" + vesseau.getTranslateY());
            //System.out.println((int)(vaisseu.getVitesse()));
        }
        ));
        gravite.setCycleCount(Animation.INDEFINITE);
        gravite.play();

        primaryStage.setTitle("SimLander - Prototype");
        primaryStage.setScene(new Scene(root, 1400, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}