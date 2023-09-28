public class InterpolationMethod {
    public static double[] GetLineFunc(double x1, double y1, double x2, double y2) {
        double m = (y2 - y1) / (x2 - x1);
        double c = y1 - m * x1;

//        System.out.println("Line equation: y = " + m + "x " + c);
        return new double[]{m, c};
    }
    public static double Calculate(double x1, double x2, double e) {
        if (Main.NonLinearFunction(x1)*Main.NonLinearFunction(x2) > 0) {
//            System.out.println("Invalid [x1; x2], no roots at this interval (or parabolic roots)");
        }
        double[] lineCoef = GetLineFunc(x1, Main.NonLinearFunction(x1), x2, Main.NonLinearFunction(x2));
        double c = EquationSolver.Calculate(lineCoef[0], lineCoef[1]);

        while ( Math.abs(x2-x1)/2 > e) {
            if (Main.NonLinearFunction(x1)*Main.NonLinearFunction(c) < 0) {
//                System.out.println("New values: x1 = " + x1 + "x2 = " + c);
                x2 = c;
            }
            else if (Main.NonLinearFunction(c)*Main.NonLinearFunction(x2) < 0) {
//                System.out.println("New values: x1 = " + c + "x2 = " + x2);
                x1 = c;
            }
            else  {
                System.out.println("Unexpected error during calculations");
                return 0;
                //throw new ArithmeticException();
            }
            lineCoef = GetLineFunc(x1, Main.NonLinearFunction(x1), x2, Main.NonLinearFunction(x2));
            c = EquationSolver.Calculate(lineCoef[0], lineCoef[1]); // NARROWING EVERYTHING
        }
        return c;
    }
}
