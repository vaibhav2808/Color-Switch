package sample;

public class Ball implements ColorChanging{
    private String color;
    private final int radius;

    public Ball(){

    }

    public void setColor(String s){
        this.color = s;
    }

    @Override
    public String getColor(){
        return "Red";
    }

    @Override 
    public void changeColor(){

    }
}
