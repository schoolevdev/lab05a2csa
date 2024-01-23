// Lab07a1.java
// This is a program that handles rational numbers via the Rational class
// and outputs the fractions, decimals, and reduced fractions
// Evin Lodder 1/23/24
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        while(true) {
            System.out.print("\nEnter the 1st numerator ----> ");
            int num1 = Keyboard.getInt();
            System.out.print("\nEnter the 1st denominator --> ");
            int den1 = Keyboard.getInt();
            if(den1 == 0) {
                System.out.println("\nDenominator can't be 0!");
                continue;
            }
            System.out.print("\nEnter the 2nd numerator ----> ");
            int num2 = Keyboard.getInt();
            System.out.print("\nEnter the 2nd denominator --> ");
            int den2 = Keyboard.getInt();
            if(den2 == 0) {
                System.out.println("\nDenominator can't be 0!");
                continue;
            }
            System.out.println();
            Rational r1 = new Rational(num1,den1);
            Rational r2 = new Rational(num2,den2);

            Rational r3 = Rational.multiply(r1, r2);
            System.out.println("the multiplied fraction is " + r3.getReduced());

            r3 = Rational.divide(r1, r2);
            System.out.println("the divided fraction is " + r3.getReduced());

            r3 = Rational.add(r1, r2);
            System.out.println("the added fraction is " + r3.getReduced());

            r3 = Rational.subtract(r1, r2);
            System.out.println("the subtracted fraction is " + r3.getReduced());

            String play_again = Keyboard.getString();
            if(!play_again.contains("y")) break;
        }
    }
}

class Rational
{
    private final int num;
    private final int den;
    public Rational() {
        this.num = 0;
        this.den = 1;
    }
    // Complete for 80-Points
    public Rational(int n, int d)
    {
        this.num = n;
        this.den = d;
    }
    // Complete for 80-Points
    public double getDecimal()
    {
        return ((double)num)/den;
    }
    // Complete for 80-Points
    public String getRational()
    {
        return String.format("%d/%d", num, den);
    }
    // Complete for 100-Points
    public String getReduced()
    {
        int gcf = getGCF(num, den);
        return String.format("%d/%d", num / gcf, den / gcf);
    }
    // Method for 80-Points; Change for 100-Points
    public void displayData()
    {
        System.out.println();
        // Make sure double is printed, round to 3 decimal places
        System.out.println(getRational() + " equals " + Math.round(getDecimal() * 1000)/(double)1000);
        System.out.println();
        System.out.println("and reduces to " + getReduced());
    }
    // Complete for 100-Points
    private static int getGCF(int n1,int n2)
    {
        // Euclid's algorithm
        while(true) {
            int div = n1 / n2;
            int remainder = n1 - (div * n2);
            if(remainder == 0) break;
            n1 = n2;
            n2 = remainder;
        }
        return Math.abs(n2);
    }
    // Required for 80-points
    public static Rational multiply(Rational r1, Rational r2)
    {
        return new Rational(r1.num * r2.num, r1.den * r2.den);
    }
    // Required for 80-points
    public static Rational divide(Rational r1, Rational r2)
    {
        return new Rational(r1.num * r2.den, r1.den * r2.num);
    }
    // Required for 100-points
    public static Rational add(Rational r1, Rational r2)
    {
        //get least common multiple- will be denominator of fraction
        int lcm = Math.abs(r1.den) * (Math.abs(r2.den) / getGCF(r1.den, r2.den));
        //numerator is addition of the products of the individual numerators and the lcm divided by the denominators (factor to multiply by)
        return new Rational((r1.num * (lcm / r1.den)) + (r2.num * (lcm / r2.den)), lcm);
    }
    // Required for 100-points
    public static Rational subtract(Rational r1, Rational r2)
    {
        //get least common multiple- will be denominator of fraction
        int lcm = Math.abs(r1.den) * (Math.abs(r2.den) / getGCF(r1.den, r2.den));
        //numerator is difference of the products of the individual numerators and the lcm divided by the denominators (factor to multiply by)
        return new Rational((r1.num * (lcm / r1.den)) - (r2.num * (lcm / r2.den)), lcm);
    }
}

// Library of keyboard input methods.
// Place library in same folder as the program that uses the methods.
class Keyboard
{
    public static int getInt()
    {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    public static double getDouble()
    {
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }
    public static String getString()
    {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
