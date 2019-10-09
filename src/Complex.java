import javax.swing.*;

/**
 * Created by PRABHA on 7/22/2017.
 */
public class Complex{
    private double real;//real part
    private double imaginary;//imaginary part

    //constructor
    public Complex(double real,double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    //getters
    public double getReal(){
        return  this.real;
    }

    public double getImaginary(){
        return this.imaginary;
    }

    //assign real and imaginary from another complex
    public void setValue(Complex z){
        this.real = z.getReal();
        this.imaginary = z.getImaginary();
    }

    //change complex
    public void setByValues(double real,double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    //return square of absolute value of this complex
    public double absolute(){
        return ((this.real*this.real)+(this.imaginary*this.imaginary));
    }

    //add two complex numbers
    public static Complex add(Complex z1, Complex z2){
        return new Complex((z1.getReal()+z2.getReal()),(z1.getImaginary()+z2.getImaginary()));
    }

    //return square of this complex number
    public Complex getSquare(){
        return new Complex((this.real*this.real-this.imaginary*this.imaginary),(2*this.real*this.imaginary));
    }

}