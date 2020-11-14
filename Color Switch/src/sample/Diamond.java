package sample;

public class Diamond extends Obstacle{
    private double angle;
    private double side;

    public Diamond(){

    }

    public void setAngle(double a){
        this.angle = a;
    }
    public void setSide(double s){
        this.side = s;
    }
    public double getAngle(){
        return angle;
    }
    public double getSide(){
        return side;
    }
}
