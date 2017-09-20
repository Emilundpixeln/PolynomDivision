import java.util.Scanner;

public class Main {
    private static void printPoly(float[] poly) {
        // prints a polynomial: 2x^3 +x^2 -x -4
        boolean nonZeroEncountered = false;
        String spacer;
        for (int i = 0; i < poly.length; i++) {
            if (i != 0 && poly[i] > 0 && nonZeroEncountered)
                spacer = " +";
            else
                spacer = " ";
            if (poly[i] != 0) {
                nonZeroEncountered = true;
                if (poly[i] == 1)
                    System.out.print(spacer + printPower(poly.length - 1 - i));
                else if (poly[i] == -1) {
                    if (poly.length - 1 - i == 0)
                        System.out.print(" -1");
                    else
                        System.out.print(" -" + printPower(poly.length - 1 - i));
                }

                else if (poly[i] % 1 == 0.0f)
                    System.out.print(spacer + Integer.toString((int)poly[i]) + printPower(poly.length - 1 - i));
                else
                    System.out.print(spacer + Float.toString(poly[i]) + printPower(poly.length - 1 - i));
            }

        }
    }

    private static String printPower(int power) {
        // prints x^power
        if(power == 0)
            return "";
        else if (power == 1)
            return "x";
        else
            return "x^" + Integer.toString(power);
    }
    public static void main(String[] args) {


        // setup scanner
        Scanner scanner = new Scanner(System.in);

        // ask for the largest power
        System.out.print("num Grad");


        // left hand side of the division
        int numMax = scanner.nextInt() + 1;
        float[] num = new float[numMax];

        // ask for the coefficients
        for (int i = 0; i < num.length; i++) {
            System.out.print("num ?x^" + Integer.toString(numMax - i - 1));
            num[i] = scanner.nextFloat();
        }

        // make a copy for printing later, because this gets modified
        float[] numCopy = num.clone();


        System.out.print("div Grad");

        // right hand side of the division
        int divMax = scanner.nextInt() + 1;
        float[] div = new float[divMax];

        for (int i = 0; i < div.length; i++) {
            System.out.print("div ?x^" + Integer.toString(divMax - i - 1));
            div[i] = scanner.nextFloat();
        }

        // array for the resulting polynomial
        float[] res = new float[numMax - divMax + 1];
        boolean rest = false;

        // go through all the parts of num
        for (int i = 0; i < num.length; i++) {

            // if 0 there's no point of continuing
            if (num[i] == 0.0f)
                continue;

            // stop if the largest power on the right is larger than the largest power on the left and just print the rest
            if (num.length - i - 1 < div.length - 1) {
                rest = true;
                break;
            }

            // do polynomial long division:
            res[i] = num[i] / div[0];
            for (int j = 0; j < div.length; j++) {
                num[i + j] -= div[j] * res[i];
            }
        }

        // print the output
        System.out.print("\n(");
        printPoly(numCopy);
        System.out.print(") : (");
        printPoly(div);
        System.out.print(") =3");
        printPoly(res);
        if (rest) {
            System.out.print("  Rest:");
            printPoly(num);
        }

    }
}
