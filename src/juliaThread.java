/**
 * Created by PRABHA on 7/28/2017.
 */
import java.awt.*;
import java.lang.Thread;

public class juliaThread extends Thread {
    private int starterPoint;//Starting vertical level element of this thread
    private int width=800,height=800;//width and height of the Jpanel
    private int maxItarations = 1000;//maximum number of iterations
    private double scalarx = 200;//number of pixels for a distance of 1 of y axis
    private double scalary = 200;//number of pixels for a distance of 1 of x axis
    private double shiftDistancex = 400;//number of pixels to 'O' from left end
    private double shiftDistancey = 400;//number of pixels to 'O' from bottom end
    private double Rec= -0.4, Imc=0.6;//constant of julia set
    private double xmin = -1, xmax = 1, ymin = -1, ymax = 1;//maximum and minimum values for x and y

    //constructer if the constant is not set
    public juliaThread(int starterPoint,double xmin,double xmax,double ymin,double ymax,int maxItarations){
        this.starterPoint = starterPoint;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.maxItarations = maxItarations;
        scalarx = height/(xmin-xmax);
        scalary = width/(ymin-ymax);

        shiftDistancex = (width / 2) + scalarx * (xmax + xmin) / 2;
        shiftDistancey = (height / 2) + scalary * (ymax + ymin) / 2;
    }

    //constructer if the constant is set
    public juliaThread(int starterPoint,double xmin,double xmax,double ymin,double ymax,int maxItarations,double Rec,double Imc){
        this.starterPoint = starterPoint;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.Rec = Rec;
        this.Imc = Imc;
        this.maxItarations = maxItarations;
        scalarx = height/(xmin-xmax);
        scalary = width/(ymin-ymax);

        shiftDistancex = (width / 2) + scalarx * (xmax + xmin) / 2;
        shiftDistancey = (height / 2) + scalary * (ymax + ymin) / 2;
    }

    public void run(){
        Complex c = new Complex(Rec,Imc);
        Complex z = new Complex(0,0);

        int passer=0;//this variable pass the number of iterations to converge, in to array

        for (int y= starterPoint; y < starterPoint+200; y++) {//this loop travers through horizontal direction
            for (int x = 0 ;x<width;x++) {//this loop travers through vertical direction

                z.setByValues((shiftDistancex-x) /scalarx, (y-shiftDistancey) /scalary);

                for (passer = 0; passer <= maxItarations; passer++) {
                    z.setValue(Complex.add(z.getSquare(), c));

                    if(z.absolute()>4){
                        break;
                    }

                }

                Fractal.pixelArray[x][y]=passer;

            }
        }
    }
}
