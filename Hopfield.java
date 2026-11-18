public class Hopfield {

    private int filas;       // M
    private int columnas;    // N
    private int numNeuronas; // K = M * N
    private int[][] pesos;   // Matriz de pesos W de tamaño K x K
    private int[][][] patronesEntrenados;
    private String[] nombresPatrones;

    public Hopfield(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.numNeuronas = filas * columnas;
        this.pesos = new int[numNeuronas][numNeuronas];
    }

    // Convierte una matriz M*N en un arreglo unidimensional de tamaño M*N
    public int[] aplanarMatriz(int[][] matriz) {
        int[] vector = new int[numNeuronas];
        int indice = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                vector[indice] = matriz[i][j];
                indice++;
            }
        }
        return vector;
    }

    // Convierte un arreglo unidimensional de tamaño M*N de vuelta a una matriz M*N
    public int[][] reconstruirMatriz(int[] vector) {
        int[][] matriz = new int[filas][columnas];
        int indice = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = vector[indice];
                indice++;
            }
        }
        return matriz;
    }

    // Entrenamiento mediante la regla de Hebb usando arrays, ciclos for y sentencias if/else
    public void entrenar(int[][][] patrones, String[] nombres) {
        this.patronesEntrenados = patrones;
        this.nombresPatrones = nombres;

        // Inicializar matriz de pesos en 0
        for (int i = 0; i < numNeuronas; i++) {
            for (int j = 0; j < numNeuronas; j++) {
                pesos[i][j] = 0;
            }
        }

        // Acumular productos externos de cada patrón
        for (int p = 0; p < patrones.length; p++) {
            int[] vectorPatron = aplanarMatriz(patrones[p]);

            for (int i = 0; i < numNeuronas; i++) {
                for (int j = 0; j < numNeuronas; j++) {
                    if (i == j) {
                        // Sin autoconexiones (diagonal principal en 0)
                        pesos[i][j] = 0;
                    } else {
                        pesos[i][j] = pesos[i][j] + (vectorPatron[i] * vectorPatron[j]);
                    }
                }
            }
        }
    }

    // Reconocimiento síncrono usando ciclo while, for y sentencias if/else
    public int[][] reconocer(int[][] figuraEntrada, int maxIteraciones) {
        int[] estadoActual = aplanarMatriz(figuraEntrada);
        int[] nuevoEstado = new int[numNeuronas];

        boolean estable = false;
        int iteracion = 0;

        while (!estable && iteracion < maxIteraciones) {
            iteracion++;
            estable = true;

            // Operación síncrona sobre todas las neuronas
            for (int i = 0; i < numNeuronas; i++) {
                int suma = 0;
                for (int j = 0; j < numNeuronas; j++) {
                    suma = suma + (pesos[i][j] * estadoActual[j]);
                }

                // Función de activación escalón bipolar con if / else
                if (suma > 0) {
                    nuevoEstado[i] = 1;
                } else if (suma < 0) {
                    nuevoEstado[i] = -1;
                } else {
                    nuevoEstado[i] = estadoActual[i];
                }
            }

            // Verificar si el estado cambió respecto a la iteración anterior
            for (int i = 0; i < numNeuronas; i++) {
                if (nuevoEstado[i] != estadoActual[i]) {
                    estable = false;
                }
                estadoActual[i] = nuevoEstado[i];
            }
        }

        System.out.println("Convergencia alcanzada en " + iteracion + " iteración(es).");
        return reconstruirMatriz(estadoActual);
    }

    // Identifica a cuál de las figuras entrenadas corresponde la matriz recuperada
    public String identificarFigura(int[][] figuraRecuperada) {
        for (int p = 0; p < patronesEntrenados.length; p++) {
            boolean coincide = true;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (figuraRecuperada[i][j] != patronesEntrenados[p][i][j]) {
                        coincide = false;
                    }
                }
            }
            if (coincide) {
                return nombresPatrones[p];
            }
        }
        return "Figura no reconocida (estado espurio)";
    }

    // Imprime la matriz M*N de forma visual (+1 como cuadro negro ██ y -1 como cuadro blanco ··)
    public static void imprimirFigura(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            // Representación visual
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    System.out.print("██");
                } else {
                    System.out.print("··");
                }
            }
            System.out.print("    [ ");
            // Representación numérica (+1 / -1)
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    System.out.print("+1 ");
                } else {
                    System.out.print("-1 ");
                }
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        // Dimensiones de la matriz según la diapositiva 2: M = 8 filas, N = 5 columnas (40 neuronas)
        int M = 8;
        int N = 5;

        // Patrón "1" (8x5) de la diapositiva 2 (+1 = negro, -1 = blanco)
        int[][] patron1 = {
            {-1, -1,  1, -1, -1},
            {-1,  1,  1, -1, -1},
            {-1, -1,  1, -1, -1},
            {-1, -1,  1, -1, -1},
            {-1, -1,  1, -1, -1},
            {-1, -1,  1, -1, -1},
            {-1, -1,  1, -1, -1},
            {-1, -1, -1, -1, -1}
        };

        // Patrón "2" (8x5) de la diapositiva 2
        int[][] patron2 = {
            { 1,  1,  1,  1, -1},
            {-1, -1, -1, -1,  1},
            {-1, -1, -1, -1,  1},
            {-1, -1,  1,  1, -1},
            {-1,  1, -1, -1, -1},
            { 1, -1, -1, -1, -1},
            { 1,  1,  1,  1,  1},
            {-1, -1, -1, -1, -1}
        };

        // Patrón "4" (8x5) de la diapositiva 2
        int[][] patron4 = {
            { 1, -1, -1,  1, -1},
            { 1, -1, -1,  1, -1},
            { 1, -1, -1,  1, -1},
            { 1,  1,  1,  1,  1},
            {-1, -1, -1,  1, -1},
            {-1, -1, -1,  1, -1},
            {-1, -1, -1,  1, -1},
            {-1, -1, -1, -1, -1}
        };

        int[][][] patrones = {patron1, patron2, patron4};
        String[] nombres = {"Número 1", "Número 2", "Número 4"};

        Hopfield red = new Hopfield(M, N);
        red.entrenar(patrones, nombres);

        System.out.println("=========================================================");
        System.out.println("   RED NEURONAL DE HOPFIELD - RECONOCIMIENTO DE FIGURAS  ");
        System.out.println("   Matriz M*N = " + M + "x" + N + " (" + (M * N) + " neuronas)");
        System.out.println("=========================================================\n");

        for (int p = 0; p < patrones.length; p++) {
            System.out.println("Patrón entrenado: " + nombres[p]);
            imprimirFigura(patrones[p]);
            System.out.println();
        }

        // Prueba 1: Figura "1" con ruido
        int[][] prueba1ConRuido = {
            {-1, -1,  1, -1,  1}, // ruido en (0,4)
            {-1,  1, -1, -1, -1}, // pixel faltante en (1,2)
            {-1, -1,  1, -1, -1},
            { 1, -1,  1, -1, -1}, // ruido en (3,0)
            {-1, -1,  1, -1, -1},
            {-1, -1, -1, -1, -1}, // pixel faltante en (5,2)
            {-1, -1,  1, -1, -1},
            {-1, -1, -1, -1, -1}
        };

        // Prueba 2: Figura "2" con ruido
        int[][] prueba2ConRuido = {
            { 1, -1,  1,  1, -1}, // pixel faltante en (0,1)
            {-1, -1, -1, -1,  1},
            {-1,  1, -1, -1,  1}, // ruido en (2,1)
            {-1, -1,  1,  1, -1},
            {-1,  1, -1, -1, -1},
            { 1, -1, -1,  1, -1}, // ruido en (5,3)
            { 1,  1,  1, -1,  1}, // pixel faltante en (6,3)
            {-1, -1, -1, -1, -1}
        };

        // Prueba 3: Figura "4" con ruido
        int[][] prueba4ConRuido = {
            { 1, -1, -1,  1, -1},
            {-1, -1, -1,  1, -1}, // pixel faltante en (1,0)
            { 1, -1,  1,  1, -1}, // ruido en (2,2)
            { 1,  1,  1, -1,  1}, // pixel faltante en (3,3)
            {-1, -1, -1,  1, -1},
            {-1,  1, -1,  1, -1}, // ruido en (5,1)
            {-1, -1, -1,  1, -1},
            {-1, -1, -1, -1, -1}
        };

        int[][][] pruebas = {prueba1ConRuido, prueba2ConRuido, prueba4ConRuido};

        for (int t = 0; t < pruebas.length; t++) {
            System.out.println("---------------------------------------------------------");
            System.out.println("PRUEBA #" + (t + 1) + " - Figura de entrada (con ruido):");
            imprimirFigura(pruebas[t]);
            System.out.println();

            int[][] figuraRecuperada = red.reconocer(pruebas[t], 100);
            String resultado = red.identificarFigura(figuraRecuperada);

            System.out.println("Figura recuperada por la Red de Hopfield:");
            imprimirFigura(figuraRecuperada);
            System.out.println(">> Resultado del reconocimiento: " + resultado);
            System.out.println();
        }
    }
}
