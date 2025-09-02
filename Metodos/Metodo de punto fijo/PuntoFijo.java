public class PuntoFijo {
    
    public static double g(double x) {
        return (x * x + 3) / 4.0;
    }

    public static void calcular() {
        double x0 = 1.5;
        double tolerancia = 0.001;
        int maxIter = 100;

        double xAnterior = x0;
        double xNuevo = 0;
        int iter = 0;
        boolean convergio = false;

        System.out.println("Método del Punto Fijo:");
        System.out.println("-----------------------");

        while (iter < maxIter) {
            xNuevo = g(xAnterior);

            double error = Math.abs(xNuevo - xAnterior);

            System.out.printf("Iteración %d: x = %.6f, Error = %.6f\n", iter + 1, xNuevo, error);

            if (error < tolerancia) {
                convergio = true;
                break;
            }

            xAnterior = xNuevo;
            iter++;
        }

        if (convergio) {
            System.out.printf("\nLa raíz aproximada es: %.6f\n", xNuevo);
        } else {
            System.out.println("\nNo se alcanzó la convergencia después de " + maxIter + " iteraciones.");
        }
    }
}
