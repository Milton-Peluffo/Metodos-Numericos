//Metodo Falsa Posicion
import java.util.Scanner;
import java.util.function.Function;

public class MetodoDeFalsaPosicion {

    public static void main(String[] args) {
        System.out.println("=== MÉTODO DE FALSA POSICIÓN (Regula Falsi) ===");

        // Definir la función f(x)
        Function<Double, Double> f = (x) -> Math.pow(x, 3) + x - 1; 
        

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingresa el extremo inferior (a): ");
            double a = sc.nextDouble();

            System.out.print("Ingresa el extremo superior (b): ");
            double b = sc.nextDouble();

            System.out.print("Ingresa la tolerancia (Ej: 1e-6): ");
            double tol = sc.nextDouble();

            System.out.print("Ingresa el número máximo de iteraciones: ");
            int maxIter = sc.nextInt();

            falsaPosicion(f, a, b, tol, maxIter);
        }
    }

    public static void falsaPosicion(Function<Double, Double> f, double a, double b, double tol, int maxIter) {
        double fa = f.apply(a);
        double fb = f.apply(b);

        if (fa * fb > 0) {
            System.out.println("Error: f(a) y f(b) deben tener signos opuestos.");
            return;
        }

        System.out.printf("\nIteración |     a      |     b      |     xr     |   f(xr)   |   Error\n");
        System.out.println("--------------------------------------------------------------------------");

        double xr = a;
        double prevXr = xr;
        double error = Double.MAX_VALUE;

        for (int i = 1; i <= maxIter; i++) {
            xr = (a * fb - b * fa) / (fb - fa);
            double fxr = f.apply(xr);

            if (i > 1) {
                error = Math.abs(xr - prevXr);
            }

            System.out.printf("%9d | %9.6f | %9.6f | %9.6f | %9.6f | %9.6e\n",
                    i, a, b, xr, fxr, error);

            if (Math.abs(fxr) < tol || error < tol) {
                System.out.printf("\nConvergió en iteración %d: raíz ≈ %.10f (error ≈ %.2e)\n", i, xr, error);
                return;
            }

            if (fa * fxr < 0) {
                b = xr;
                fb = fxr;
            } else {
                a = xr;
                fa = fxr;
            }

            prevXr = xr;
        }

        System.out.printf("\nNo convergió después de %d iteraciones. Última aproximación: raíz ≈ %.10f (error ≈ %.2e)\n",
                maxIter, xr, error);
    }
}
