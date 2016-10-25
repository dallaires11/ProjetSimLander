/**
 * Created by Chroon on 2016-10-25.
 */
public class Vaisseu {
    float vitessey;
    float vitessex;
    int rotation;
    float accelationx=0;
    float accelationy=0;
    public Vaisseu(){
        vitessey= 0.00f;
        vitessex= 0.00f;
        rotation=90;
        accelationx=0;
        accelationy=0;
    }



    public float getVitesseY(){
        return  vitessey;
    }

    public float getVitesseX() {
        return vitessex;
    }

    public float getAccelationy() {
        return accelationy;
    }

    public float getAccelationx() {
        return accelationx;
    }


    public  void accelerer(boolean accelerationJoueur){
        System.out.println(accelerationJoueur);
        if(accelerationJoueur) {
            System.out.println("test");
            //accelationx=(float)(0.02f*Math.cos(rotation));
            accelationy = (float) (0.05f * Math.sin(rotation));
        }
        else  if(!accelerationJoueur)
            accelationy=0;

        vitessey+=0.0098f-accelationy;
        //vitessex+=accelationx;
    }

    public int getRotation(){
        return  rotation;
    }

    public void rotationDroite(){
        if (rotation==0)
            rotation=359;
        else
            rotation--;
    }

    public void rotationGauche(){
        if(rotation==359)
            rotation=0;
        else
            rotation++;
    }
}
