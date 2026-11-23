public class MImage {
    public static void main(String args[]) throws Exception {

        System.out.println("          RED DE HOPFIELD - ARCADE CLASICO / PAC-MAN (10x10)         \n");

        // Ruta de la carpeta donde se guardan los archivos .txt de las figuras
        String ruta = "datasets/";

        // 1. LEER LOS 5 PATRONES (figuras de 10x10 = 100 pixeles)
        // 1. Pac-Man
        int[] pacman = new int[100];
        java.util.Scanner sc1 = new java.util.Scanner(new java.io.File(ruta + "pacman.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros del txt uno por uno
            pacman[i] = sc1.nextInt();
        }
        sc1.close();

        // 2. Fantasma
        int[] fantasma = new int[100];
        java.util.Scanner sc2 = new java.util.Scanner(new java.io.File(ruta + "fantasma.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros del fantasma
            fantasma[i] = sc2.nextInt();
        }
        sc2.close();

        // 3. Cereza
        int[] cereza = new int[100];
        java.util.Scanner sc3 = new java.util.Scanner(new java.io.File(ruta + "cereza.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros de la cereza
            cereza[i] = sc3.nextInt();
        }
        sc3.close();

        // 4. Corazon (vida extra)
        int[] corazon = new int[100];
        java.util.Scanner sc4 = new java.util.Scanner(new java.io.File(ruta + "corazon.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros del corazon
            corazon[i] = sc4.nextInt();
        }
        sc4.close();

        // 5. Llave
        int[] llave = new int[100];
        java.util.Scanner sc5 = new java.util.Scanner(new java.io.File(ruta + "llave.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros de la llave
            llave[i] = sc5.nextInt();
        }
        sc5.close();

        // 2. IMPRIMIR LOS PATRONES EN PANTALLA (CON CUADROS)
        // f = fila (0 a 9), c = columna (0 a 9), f*10+c accede al arreglo plano de 100
        System.out.println("Patrón 1: Pac-Man");
        for (int f = 0; f < 10; f++) { // f = recorre las 10 filas
            for (int c = 0; c < 10; c++) { // c = recorre las 10 columnas
                System.out.print(pacman[f * 10 + c] == 1 ? "██" : "  "); // pinta cuadro si es 1 o espacio si es 0
            }
            System.out.println(); // salto de linea al terminar la fila
        }
        System.out.println();

        System.out.println("Patrón 2: Fantasma");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(fantasma[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 3: Cereza");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(cereza[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 4: Corazón");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(corazon[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("Patrón 5: Llave");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(llave[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        // 3. REEMPLAZAR CEROS POR -1 (REPRESENTACIÓN BIPOLAR)
        for (int k = 0; k < 100; k++) { // k = posicion del pixel (0 a 99)
            if (pacman[k] == 0)   pacman[k] = -1;
            if (fantasma[k] == 0) fantasma[k] = -1;
            if (cereza[k] == 0)   cereza[k] = -1;
            if (corazon[k] == 0)  corazon[k] = -1;
            if (llave[k] == 0)    llave[k] = -1;
        }

        // 4. Matrices de memoria N*N (figura 10x10 = 100 pixeles, matriz de 100x100)
        int N = 100;
        int[][] m1 = new int[N][N];
        int[][] m2 = new int[N][N];
        int[][] m3 = new int[N][N];
        int[][] m4 = new int[N][N];
        int[][] m5 = new int[N][N];

        // 5. Producto cruzado pixel por pixel (X * X transpuesta)
        for (int a = 0; a < N; a++) { // a = pixel en la fila
            for (int t = 0; t < N; t++) { // t = pixel en la columna
                m1[a][t] = pacman[a] * pacman[t];
                m2[a][t] = fantasma[a] * fantasma[t];
                m3[a][t] = cereza[a] * cereza[t];
                m4[a][t] = corazon[a] * corazon[t];
                m5[a][t] = llave[a] * llave[t];
            }
        }

        // 6. Suma de memorias de las 5 figuras
        int[][] mTotal = new int[N][N];
        for (int i = 0; i < N; i++) { // i = fila de la matriz
            for (int a = 0; a < N; a++) { // a = columna de la matriz
                mTotal[i][a] = m1[i][a] + m2[i][a] + m3[i][a] + m4[i][a] + m5[i][a];
            }
        }

        // Diagonal en ceros (i = misma fila y columna [i][i], no autoconexion)
        for (int i = 0; i < N; i++) { // i = recorre la diagonal de 0 a 99
            mTotal[i][i] = 0;
        }

        // 7. LEER EL PATRÓN DE ENTRADA A RECONOCER
        int[] entrada = new int[100];
        java.util.Scanner sc6 = new java.util.Scanner(new java.io.File(ruta + "entrada.txt"));
        for (int i = 0; i < 100; i++) { // i = lee los 100 numeros de la entrada
            entrada[i] = sc6.nextInt();
        }
        sc6.close();

        // Convertir ceros a -1 en la entrada
        for (int k = 0; k < 100; k++) { // k = pixel de la entrada
            if (entrada[k] == 0) {
                entrada[k] = -1;
            }
        }

        System.out.println("--- PATRÓN DE ENTRADA (CON RUIDO A BUSCAR) ---");
        for (int f = 0; f < 10; f++) { // f = fila
            for (int c = 0; c < 10; c++) { // c = columna
                System.out.print(entrada[f * 10 + c] == 1 ? "██" : "  ");
            }
            System.out.println();
        }
        System.out.println();

        // 8. BÚSQUEDA Y RECUPERACIÓN (PROCESO ITERATIVO)
        // La red busca corrigiendo pixeles dañados hasta estabilizarse
        int encontrado = 0;
        int iteracion = 1;
        int maxIteraciones = 100; // limite de seguridad por si la red oscila entre dos estados

        while (encontrado == 0 && iteracion <= maxIteraciones) {
            System.out.println("Iteración " + iteracion + ":");

            // PASO 1 (BUSCAR): Multiplica el patron por la matriz de memoria (suma de pesos)
            int[] producto = new int[N];
            for (int f = 0; f < N; f++) { // f = neurona de salida (fila)
                for (int c = 0; c < N; c++) { // c = neurona de entrada (columna)
                    producto[f] = producto[f] + (entrada[c] * mTotal[f][c]);
                }
            }

            // PASO 2 (CORREGIR PIXELES): Funcion de activacion bipolar (si > 0 es 1, si no es -1)
            for (int i = 0; i < N; i++) { // i = revisa el signo de cada pixel
                if (producto[i] > 0) {
                    producto[i] = 1;
                } else {
                    producto[i] = -1;
                }
            }

            // PASO 3: Imprimir como va quedando la figura en esta iteracion
            for (int f = 0; f < 10; f++) { // f = fila (0 a 9)
                for (int c = 0; c < 10; c++) { // c = columna (0 a 9)
                    System.out.print(producto[f * 10 + c] == 1 ? "██" : "  ");
                }
                System.out.println();
            }
            System.out.println();

            // PASO 4 (COMO SABE SI LO ENCONTRO): Compara entrada con producto.
            // Si son exactamente iguales en todos los pixeles, la red ya es estable (convergio)
            encontrado = 1;
            for (int i = 0; i < N; i++) { // i = compara pixel actual con el anterior
                if (entrada[i] != producto[i]) {
                    encontrado = 0; // si un solo pixel es diferente, todavia no termina
                    break;
                }
            }

            if (encontrado == 1) {
                // PASO 5 (IDENTIFICAR CUAL FIGURA ES):
                // Cuenta coincidencias de pixeles del resultado final con cada una de las 5 figuras originales
                int cPacman = 0;
                int cFantasma = 0;
                int cCereza = 0;
                int cCorazon = 0;
                int cLlave = 0;

                for (int i = 0; i < N; i++) { // i = compara pixel por pixel con cada figura original
                    if (producto[i] == pacman[i]) cPacman++;     // si coincide con el pixel i de pacman, suma 1
                    if (producto[i] == fantasma[i]) cFantasma++; // si coincide con el pixel i del fantasma, suma 1
                    if (producto[i] == cereza[i]) cCereza++;     // si coincide con el pixel i de la cereza, suma 1
                    if (producto[i] == corazon[i]) cCorazon++;   // si coincide con el pixel i del corazon, suma 1
                    if (producto[i] == llave[i]) cLlave++;       // si coincide con el pixel i de la llave, suma 1
                }

                // La que tenga mayor coincidencia es la figura que la red reconocio
                int mayor = cPacman;
                String figura = "Pac-Man (Patrón 1)";
                if (cFantasma > mayor) {
                    mayor = cFantasma;
                    figura = "Fantasma (Patrón 2)";
                }
                if (cCereza > mayor) {
                    mayor = cCereza;
                    figura = "Cereza (Patrón 3)";
                }
                if (cCorazon > mayor) {
                    mayor = cCorazon;
                    figura = "Corazón (Patrón 4)";
                }
                if (cLlave > mayor) {
                    mayor = cLlave;
                    figura = "Llave (Patrón 5)";
                }

                System.out.println("¡ENCONTRADO!");
                System.out.println("Figura reconocida: " + figura);
                System.out.println("Coincidencia: " + mayor + "%\n"); // 100 pixeles, cada coincidencia = 1%

                // PASO 6 (GUARDAR): Guarda la figura recuperada limpia en resultado.txt
                java.io.PrintWriter pw = new java.io.PrintWriter(ruta + "resultado.txt");
                for (int f = 0; f < 10; f++) { // f = fila del resultado
                    for (int c = 0; c < 10; c++) { // c = columna del resultado
                        pw.print((producto[f * 10 + c] > 0 ? 1 : 0) + " ");
                    }
                    pw.println();
                }
                pw.close();
                System.out.println("Guardado con éxito en: " + ruta + "resultado.txt\n");

            } else {
                // SI AUN NO LO ENCUENTRA: El resultado se vuelve la nueva entrada y repite el ciclo
                for (int i = 0; i < N; i++) { // i = copia el producto para la siguiente iteracion
                    entrada[i] = producto[i];
                }
                iteracion++;
            }
        }

        if (encontrado == 0) {
            System.out.println("La red no logró estabilizarse en " + maxIteraciones + " iteraciones.");
        }
    }
}
