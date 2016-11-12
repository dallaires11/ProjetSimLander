/**
 * Created by Chroon on 2016-11-12.
 */
package View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;

public class Option {
    private Scene option;
    private ImageView fond;
    private CheckBox chaos;
    private CheckBox god;
    private Button retour;

    public Option(){
        fond=new ImageView("Image/cielEtoile.gif");
    }

    public Scene getSceneOption(){
        return  option;
    }
}
