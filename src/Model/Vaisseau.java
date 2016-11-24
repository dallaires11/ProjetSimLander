/**
 * Created by Chroon on 2016-11-08.
 */
package Model;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Vaisseau extends Group {

    private int essence;
    private double vitesseX, vitesseY;
    private int rotation;
    private double positionX, positionY;
    private Label nom;
    private VBox org;
    private ImageView structure;

    public Vaisseau() {
        essence = 1000;
        vitesseX = 0;
        vitesseY = 0;
        rotation = 90;
        positionX = 0;
        positionY = 0;
        nom = new Label("");

        structure= new ImageView("Image/Spaceship.png");
        structure.setFitHeight(20);
        structure.setFitWidth(20);

        org=new VBox();
        org.getChildren().addAll(nom,structure);

        setPositonForme();

        this.getChildren().add(org);
    }


    public void reduireEssence(int reduction) {
        essence -= reduction;
    }

    public void rotationGauche(int vitesseRotation) {
        rotation += vitesseRotation;
        if (rotation < 0)
            rotation = 360 + rotation;

        structure.setRotate(90-rotation);
    }

    public void rotationDroite(int vitesseRotation) {
        rotation -= vitesseRotation;
        if (rotation > 360)
            rotation -= 360;

        structure.setRotate(90-rotation);
    }

    private void bouger() {
        positionX += vitesseX;
        positionY -= vitesseY;

        if(positionX <= -20-getNomTailleMod()){
            positionX = 1400;
            this.setTranslateX(positionX);
        }
        if(positionX >= 1420+getNomTailleMod()){
            positionX = 0;
            this.setTranslateX(positionX);
        }

    }

    public void accelerer(boolean appuyer) {
        double accelerationX = 0;
        double accelerationY = 0;
        double radian=Math.toRadians(rotation);
        if (appuyer&&essence!=0) {
            accelerationX =(float) (0.035 * Math.cos(radian));
            accelerationY =(float) (0.035 * Math.sin(radian));
            reduireEssence(2);
        }
        vitesseX += accelerationX;
        vitesseY += accelerationY - 0.0098;

        bouger();
    }

    public int getEssence(){
        return essence;
    }

    public double getX(){
        return positionX;
    }

    public double getY(){
        return positionY;
    }

    public void setPosInitial(int x,int y){
        positionX=x;
        positionY=y;
    }

    public int getRotation(){
        return rotation;
    }

    public void setNom(String nouveauNom){
        if (nouveauNom.equalsIgnoreCase("vincent")){
            nom.setText("Dieu");
        } else
            nom.setText(nouveauNom);
        nom.setTextFill(Color.WHITE);
    }

    private void setPositonForme(){
        org.setAlignment(Pos.CENTER);

    }

    public double getVitesseX(){
        return vitesseX;

    }

    public double getVitesseY(){
        return vitesseY;
    }

    public double getNomTailleMod(){
        Text test =new Text (nom.getText());
        double test2 = test.getBoundsInLocal().getWidth()/2;
        return  test2;
    }


}