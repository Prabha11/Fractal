import java.awt.*;

/**
 * Created by PRABHA on 7/26/2017.
 */
public class Colors {

    //colour function 1 - return a color dependent on given number of iterations
    public static float getHue(int n) {

        if (n < 1000) {
            return (float) (0.6 + (n / 1000));
        } else {
            return (float) (0.16 + (n / 1));
        }
    }

    //colour function 2 - return a color dependent on given number of iterations
    public static Color getColorByItr(int itr) {

        float hue, saturation, brightness;

        if (itr <= 250) {
            hue = (float) (47.06 / 360);
            saturation = 1;
            brightness = ((float)itr) / 250;

            return Color.getHSBColor(hue, saturation, brightness);
        }

        else if (itr <= 500) {
            hue = (float) (47.06 / 360);
            saturation = 1 - (float)(itr - 250) / 250;
            brightness = 1;

            return Color.getHSBColor(hue, saturation, brightness);
        }

        else if (itr<=750){
            hue = (float)0.666666666;
            saturation =((float)itr - 500) / 250;
            brightness = 1;

            return Color.getHSBColor(hue, saturation, brightness);
        }

        else if (itr<=1000){
            hue = (float)0.666666666;
            saturation = 1;
            brightness = 1-((float)itr-750)/500;

            return Color.getHSBColor(hue, saturation, brightness);
        }

        return Color.red;
    }

    //colour function 3 - return a color dependent on given number of iterations
    public static Color getColorByItr2(int itr) {

        float hue, saturation, brightness;

        if (itr <= 50) {
            hue = (float)0.666666666;
            saturation = 1;
            brightness = (float) (0.15+((float)itr)/100);

            return Color.getHSBColor(hue, saturation, brightness);
        }

        else if (itr <= 100) {
            hue = (float)0.666666666;
            saturation =1-((float)itr - 50) / 50;
            brightness = 1;

            return Color.getHSBColor(hue, saturation, brightness);
        }

        else if (itr<=150){
            hue = (float) (47.06 / 360);
            saturation =(float)(itr - 100) / 50;
            brightness = 1;

            return Color.getHSBColor(hue, saturation, brightness);
        }

        else if (itr<=1000){
            hue = (float) (47.06 / 360);
            saturation = 1;
            brightness = (float) (1-((float)itr-150) / 400);

            return Color.getHSBColor(hue, saturation, brightness);
        }

        return Color.white;
    }
}
