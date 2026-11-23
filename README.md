# Red de Hopfield - Arcade Clásico / Pac-Man (10x10)

Proyecto en Java que usa una **Red Neuronal de Hopfield** para memorizar y reconocer figuras del arcade clásico (Pac-Man) en cuadrículas de **10x10 píxeles** (100 neuronas).

Hecho sin librerías externas, usando solo arreglos básicos, ciclos `for`/`while` y sentencias `if`/`else`.

## ¿Qué hay en este proyecto?

- `MImage.java`: El código principal que calcula la red neuronal.
- `datasets/`: Carpeta con los dibujos hechos con ceros y unos:
  - `pacman.txt`, `fantasma.txt`, `cereza.txt`, `corazon.txt`, `llave.txt`: Las 5 figuras que la red aprende de memoria.
  - `entrada.txt`: La figura de prueba (con ruido) que le damos a la red para ver si la adivina.
  - `resultado.txt`: Donde la red guarda la figura limpia y restaurada cuando termina.

## ¿Cómo funciona?

1. Lee las 5 figuras (`1` = píxel negro, `0` = píxel blanco) y las convierte a bipolar (`0` → `-1`).
2. Calcula la matriz de memoria de 100x100 sumando el producto `X * Xᵀ` de cada figura, con la diagonal en `0`.
3. Lee `entrada.txt` y la multiplica por la matriz de memoria, aplicando la función de activación bipolar (`> 0` → `1`, si no `-1`).
4. Repite hasta que la figura ya no cambia (la red se estabiliza).
5. Compara el resultado con las 5 figuras originales, muestra cuál reconoció y lo guarda en `resultado.txt`.

## Cómo correr el proyecto

En la terminal escribe:

```bash
javac MImage.java
java MImage
```

Para probar con otra figura, edita `datasets/entrada.txt` (dibuja con `0` y `1`, 10 filas x 10 columnas).
