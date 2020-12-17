package sample;
//taken from https://stackoverflow.com/questions/36748358/saving-color-as-state-in-a-javafx-application
import javafx.scene.paint.Color;

import java.io.Serializable;

public class SerializableColor implements Serializable {
    private double red;
    private double green;
    private double blue;
    private double alpha;
    private static SerializableColor[] zestyColors={new SerializableColor(new Color(245,121,58,1)),new SerializableColor(new Color(245,121,58,1)),new SerializableColor(new Color(169,90,161,1)),new SerializableColor(new Color(133,192,249,1))};
    private static SerializableColor[] elegantColors={new SerializableColor(new Color(171,195,201,1)),new SerializableColor(new Color(224,220,211,1)),new SerializableColor(new Color(204,190,159,1)),new SerializableColor(new Color(56,33,25,1))};
    private static SerializableColor[] retroColors={new SerializableColor(new Color(96,26,74,1)),new SerializableColor(new Color(238,68,47,1)),new SerializableColor(new Color(99,172,190,1)),new SerializableColor(new Color(249,244,236,1))};
    private static SerializableColor[] defaultColors={new SerializableColor(Color.BLUE),new SerializableColor(Color.RED),new SerializableColor(Color.GREEN),new SerializableColor(Color.YELLOW)};
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
