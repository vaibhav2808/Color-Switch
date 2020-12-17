package sample;
//taken from https://stackoverflow.com/questions/36748358/saving-color-as-state-in-a-javafx-application
import javafx.scene.paint.Color;

import java.io.Serializable;

public class SerializableColor implements Serializable {
    private double red;
    private double green;
    private double blue;
    private double alpha;
    public static SerializableColor[] zestyColors={new SerializableColor(new Color((double)15/255,(double)32/255,(double)128/255,1)),new SerializableColor(new Color((double)245/255,(double)121/255,(double)58/255,1)),new SerializableColor(new Color((double)169/255,(double)90/255,(double)161/255,1)),new SerializableColor(new Color((double)133/255,(double)192/255,(double)249/255,1))};
    public static SerializableColor[] elegantColors={new SerializableColor(new Color((double)171/255,(double)195/255,(double)201/255,1)),new SerializableColor(new Color((double)224/255,(double)220/255,(double)211/255,1)),new SerializableColor(new Color((double)204/255,(double)190/255,(double)159/255,1)),new SerializableColor(new Color((double)56/255,(double)33/255,(double)25/255,1))};
    public static SerializableColor[] retroColors={new SerializableColor(new Color((double)96/255,(double)26/255,(double)74/255,1)),new SerializableColor(new Color((double)238/255,(double)68/255,(double)47/255,1)),new SerializableColor(new Color((double)99/255,(double)172/255,(double)190/255,1)),new SerializableColor(new Color((double)249/255,(double)244/255,(double)236/255,1))};
    public static SerializableColor[] defaultColors={new SerializableColor(Color.BLUE),new SerializableColor(Color.RED),new SerializableColor(Color.GREEN),new SerializableColor(Color.YELLOW)};
    public SerializableColor(Color color)
    {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getOpacity();
    }
    public SerializableColor(double red, double green, double blue, double alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
    public Color getFXColor()
    {
        return new Color(red, green, blue, alpha);
    }
}
