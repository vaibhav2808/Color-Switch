package sample;

import javafx.scene.paint.Paint;


public interface ColorChanging {
    public Paint getColor();
    public void changeColor(SerializableColor color);
}
