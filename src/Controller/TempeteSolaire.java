/**
 * Created by Chroon on 2016-12-08.
 */
package Controller;

import View.SceneJeu;
import javafx.animation.*;
import javafx.util.Duration;

public class TempeteSolaire {
    private Timeline tempete;
    private SceneJeu sceneJeu;
    private int diff,chance;

    public TempeteSolaire(SceneJeu jeu){
        sceneJeu=jeu;
        diff=1;
        chance=0;
        initialiserTempete();
    }

    private void initialiserTempete(){

        FadeTransition lumiere=new FadeTransition(Duration.seconds(.9),sceneJeu.getRoot());
        lumiere.setFromValue(1);
        lumiere.setToValue(0);
        FadeTransition lumiereArret=new FadeTransition(Duration.seconds(8),sceneJeu.getRoot());
        lumiereArret.setFromValue(0);
        lumiereArret.setToValue(1);
        lumiere.setOnFinished(event->lumiereArret.playFromStart());

        FadeTransition alerteShow = new FadeTransition(Duration.seconds(2),sceneJeu.getAlerte());
        alerteShow.setFromValue(0);
        alerteShow.setToValue(1);
        TranslateTransition tt= new TranslateTransition(Duration.millis(1),sceneJeu.getAlerte());
        tt.setFromX(1500);
        tt.setToX(1300);
        FadeTransition alerteStop = new FadeTransition(Duration.seconds(2),sceneJeu.getAlerte());
        SequentialTransition st = new SequentialTransition(alerteShow,tt);
        alerteShow.setOnFinished(event->alerteStop.playFromStart());
        alerteStop.setOnFinished(event->{
            lumiere.playFromStart();
            sceneJeu.deplacerAlerte(1500);
        });



        tempete=new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            chance= (int) (Math.random()*2+1);
            if (chance==2){
                st.playFromStart();
            }

        }));
        tempete.setCycleCount(Animation.INDEFINITE);
    }

    public void startTempete(){
        tempete.playFromStart();
    }

    public void stopTempete(){
        sceneJeu.deplacerAlerte(1500);
        tempete.stop();
    }
}
