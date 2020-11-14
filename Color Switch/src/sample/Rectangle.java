package sample;

public class Rectangle extends Obstacle{
    private double length;
    private double breadth;

    public Rectangle(){

    }

    public void setLength(double l){
        this.length = l;
    }
    public void setBreadth(double b){
        this.breadth = b;
    }
    public double getLength(){
        return length;
    }
    public double getBreadth(){
        return breadth;
    }
}
