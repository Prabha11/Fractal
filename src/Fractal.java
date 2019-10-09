/**
 * Created by PRABHA on 7/22/2017.
 * 076 84 17 354
 */
import java.awt.*; /* java abstract window toolkit */
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.util.InputMismatchException;

public class Fractal extends JPanel implements ActionListener,MouseListener {

    private int width,height;               //for dimensions for the JPanel
    private static int maxItarations=1000;  //the maximum number of iterations in set calculating loop
    private static double scalarx = 200;    //ratio of number of pixel to 1
    private static double scalary = 200;
    private static double juliaConstantReal=-0.4,juliaConstantIm=0.6;//constant values for implement julia set
    private static double xmin = -1, xmax = 1, ymax = -1, ymin = 1;
    static int [][] pixelArray;         //This array contains the number of iterations to converge for each point on the Jpanel
    private static int fractalCode=0;   // 0 = madelbroat fractal , 1 = julia fractal


    static {
        pixelArray = new int[800][800];
    }

    //this method set the values of array by calling makeMandelbroat or makeJulia method
    static void makeArray(){

        if(fractalCode == 0){
            makeMandelbroat();
        }
        else if (fractalCode == 1){
            makeJulia();
        }
    }

    //these two methods set the values of array by running threads
    static void makeJulia(){
        Thread thread1 = new juliaThread(0,xmin,xmax,ymax,ymin,maxItarations,juliaConstantReal,juliaConstantIm);
        Thread thread2 = new juliaThread(200,xmin,xmax,ymax,ymin,maxItarations,juliaConstantReal,juliaConstantIm);
        Thread thread3 = new juliaThread(400,xmin,xmax,ymax,ymin,maxItarations,juliaConstantReal,juliaConstantIm);
        Thread thread4 = new juliaThread(600,xmin,xmax,ymax,ymin,maxItarations,juliaConstantReal,juliaConstantIm);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void makeMandelbroat(){
        Thread thread1 = new Thread(new mandelbroatThread(0,xmin,xmax,ymax,ymin,maxItarations));
        Thread thread2 = new Thread(new mandelbroatThread(200,xmin,xmax,ymax,ymin,maxItarations));
        Thread thread3 = new Thread(new mandelbroatThread(400,xmin,xmax,ymax,ymin,maxItarations));
        Thread thread4 = new Thread(new mandelbroatThread(600,xmin,xmax,ymax,ymin,maxItarations));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //this method construct the frame
    static void frame(){

        int width = 800;
        int height = 800;

        scalarx = height/(xmin-xmax);
        scalary = width/(ymax-ymin);

        makeArray();

        JFrame frame = new JFrame("Fractal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fractal facpanel = new Fractal(width,height);

        frame.setContentPane(facpanel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        //!!!---Zooming interface:Not developed yet----!!!

        /* BEGINNING OF UNWANTED LINES

        JFrame subFrame = new JFrame("sub");
        subFrame.setLocation(950,100);
        subFrame.setSize(300,300);
        subFrame.setVisible(true);

        JButton btnZoom = new JButton("zoom");
        subFrame.add(btnZoom);
        btnZoom.setSize(250,100);
        btnZoom.setLocation(25,25);

        JButton btnShift = new JButton("Shift");
        subFrame.add(btnShift);
        btnShift.setSize(250,100);
        btnZoom.setLocation(25,125);

        btnZoom.addActionListener(facpanel);
        END OF UNWANTED LINES
        */

        facpanel.addMouseListener(facpanel);
    }

    //Zoom when clicked on fractal (half done)
    public void actionPerformed(ActionEvent e){

        //int x = getX(e);
        xmin = xmin + 0.1;
        ymax = ymax + 0.1;
        xmax = xmax - 0.1;
        ymin = ymin - 0.1;
        this.setSize();
        this.repaint();
    }

    //constructor
    public Fractal(int width, int height){
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width,height));
    }

    //this method can print a point on Jpanel
    private static void printPoint(Graphics2D frame, Color c, Point p) {
        frame.setColor(c);
        frame.draw(new Line2D.Double(p.getX(), p.getY(), p.getX(), p.getY()));
    }

    //print points on Jpanel
    private void printArray(Graphics g){
        Point currentPoint = new Point(0,0);

        for (int y = 0; y < height; y++)
            for (int x=0;x<width;x++) {

                int passer = pixelArray[x][y];

                currentPoint.setPoint(x, y);

                if (passer < maxItarations) {
                    printPoint((Graphics2D) g, Colors.getColorByItr2(passer), currentPoint);
                } else {
                    printPoint((Graphics2D) g, Color.getHSBColor(1, 0, 0), currentPoint);
                }
            }
    }

    //this method is called When Program has been executed
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        printArray(g);//Images are drawn According to the array values
    }

    //this method set ratios of scalar x and y
    public void setSize(){

        scalarx = height/ (xmin-xmax);
        scalary = width/ (ymax-ymin);
    }

    public static void main(String[] args){

        try{
            if(args[0].equals( "Mandelbroat" )){
                fractalCode = 0;

                try{
                    xmin = Double.parseDouble( args[1] );
                    xmax = Double.parseDouble( args[2] );
                    ymin = Double.parseDouble( args[3] );
                    ymax = Double.parseDouble( args[4] );

                }catch (ArrayIndexOutOfBoundsException|InputMismatchException|NumberFormatException e1){
                    xmin = -0.5;
                    xmax = 0.5;
                    ymin = -0.1;
                    ymax = 1;
                }

                try{
                    maxItarations = Integer.parseInt( args[5] );
                }catch (ArrayIndexOutOfBoundsException|InputMismatchException|NumberFormatException e1){
                    maxItarations = 1000;
                }
            }
            else if(args[0].equals( "Julia" )){
                fractalCode = 1;

                xmin = -1;
                xmax = 1;
                ymin = -1;
                ymax = 1;

                try{
                    juliaConstantReal = Double.parseDouble( args[1] );
                    juliaConstantIm = Double.parseDouble( args[2] );

                }catch (ArrayIndexOutOfBoundsException|InputMismatchException|NumberFormatException e1){

                    juliaConstantReal = -0.4;
                    juliaConstantIm = 0.6;

                }

                try{
                    maxItarations = Integer.parseInt( args[3] );
                }catch (ArrayIndexOutOfBoundsException|InputMismatchException|NumberFormatException e1){
                    maxItarations = 1000;
                }
            }
        }catch (ArrayIndexOutOfBoundsException|NumberFormatException|InputMismatchException e){
            fractalCode = 0;
            xmin = -0.5;
            xmax = 0.5;
            ymin = -0.1;
            ymax = 1;
            maxItarations = 1000;
        }

        frame();


    }

    //Mouse listener interfaced functions(for zooming)
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        double prescalarx = scalarx, prescalary = scalary;

        xmin += (400 - x) / scalarx;
        ymax += (400 - y) / scalary;
        xmax += (400 - x) / scalarx;
        ymin += (400 - y) / scalary;


        xmin += (xmax - xmin) * 0.1;
        ymax += (ymin - ymax) * 0.1;
        xmax -= (xmax - xmin) * 0.1;
        ymin -= (ymin - ymax) * 0.1;


        this.setSize();
        makeArray();
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

