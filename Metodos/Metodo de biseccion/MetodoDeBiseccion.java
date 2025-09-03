import java.util.Scanner;
import java.util.function.Function;

public class MetodoDeBiseccion {

    public static void main(String[] args) {
        System.out.println("=== MÉTODO DE BISECCIÓN ===");

        Function<Double, Double> funcion = (x) -> Math.pow(x, 3) + x - 1;

        try (Scanner scanner = new Scanner(System.in)) {
            int opcion;
            do {
                // =================== ENTRADA DE DATOS ===================
                System.out.print("Ingresa el extremo inferior (a): ");
                double extremoInferior = scanner.nextDouble();

                System.out.print("Ingresa el extremo superior (b): ");
                double extremoSuperior = scanner.nextDouble();

                System.out.print("Ingresa la tolerancia (Ej: 1e-6): ");
                double tolerancia = scanner.nextDouble();

                System.out.print("Ingresa el número máximo de iteraciones: ");
                int maximoIteraciones = scanner.nextInt();

                // =================== EJECUCIÓN DEL MÉTODO ===================
                metodoBiseccion(funcion, extremoInferior, extremoSuperior, tolerancia, maximoIteraciones);

                // =================== MENÚ DE REPETICIÓN ===================
                System.out.println("\n¿DESEA HACER OTRO CÁLCULO?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

            } while (opcion == 1);

            System.out.println("\nPrograma finalizado. 👋");
        }
    }

    // ============ MÉTODO PARA CALCULAR RAÍZ CON BISECCIÓN ===============
    public static void metodoBiseccion(Function<Double, Double> funcion,
                                       double extremoInferior,
                                       double extremoSuperior,
                                       double tolerancia,
                                       int maximoIteraciones) {

        double fExtremoInferior = funcion.apply(extremoInferior);
        double fExtremoSuperior = funcion.apply(extremoSuperior);

        if (fExtremoInferior * fExtremoSuperior > 0) {
            System.out.println("Error: f(a) y f(b) deben tener signos opuestos.");
            return;
        }

        System.out.printf("\nIteración |     a      |     b      |     xr     |   f(xr)   |   Error\n");
        System.out.println("--------------------------------------------------------------------------");

        double puntoMedio = (extremoInferior + extremoSuperior) / 2.0;
        double puntoMedioAnterior = puntoMedio;
        double errorAprox = Double.MAX_VALUE;

        // =================== CICLO DE ITERACIONES ===================
        for (int iteracion = 1; iteracion <= maximoIteraciones; iteracion++) {
            puntoMedio = (extremoInferior + extremoSuperior) / 2.0;
            double fPuntoMedio = funcion.apply(puntoMedio);

            if (iteracion > 1) {
                errorAprox = Math.abs(puntoMedio - puntoMedioAnterior);
            }

            System.out.printf("%9d | %9.6f | %9.6f | %9.6f | %9.6f | %9.6e\n",
                    iteracion, extremoInferior, extremoSuperior, puntoMedio, fPuntoMedio, errorAprox);

            if (Math.abs(fPuntoMedio) < tolerancia || errorAprox < tolerancia) {
                System.out.printf("\n✅ Convergió en iteración %d: raíz ≈ %.10f (error ≈ %.2e)\n",
                        iteracion, puntoMedio, errorAprox);
                return;
            }

            if (fExtremoInferior * fPuntoMedio < 0) {
                extremoSuperior = puntoMedio;
                fExtremoSuperior = fPuntoMedio;
            } else {
                extremoInferior = puntoMedio;
                fExtremoInferior = fPuntoMedio;
            }

            puntoMedioAnterior = puntoMedio;
        }

        System.out.printf("\n⚠ No convergió después de %d iteraciones. Última aproximación: raíz ≈ %.10f (error ≈ %.2e)\n",
                maximoIteraciones, puntoMedio, errorAprox);
    }
}
