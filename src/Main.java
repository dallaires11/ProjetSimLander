import Model.Vaisseau;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Timeline timeline;
    Vaisseau vaisseau;
    double point;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        //Temps 2 keyframe / timeline
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}