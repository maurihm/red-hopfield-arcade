# Red de Hopfield - Arcade Clásico / Pac-Man (10x10)

Proyecto en Java que usa una Red Neuronal de Hopfield para memorizar y reconocer figuras del arcade clásico (Pac-Man) en cuadrículas de 10x10 píxeles.

Hecho sin librerías externas, usando solo arreglos básicos y ciclos for/while.

## ¿Qué hay en este proyecto?

- **MImage.java**: El código principal que calcula la red neuronal.
- **datasets/**: Carpeta con los dibujos hechos con ceros y unos:
  - **pacman.txt, fantasma.txt, cereza.txt, corazon.txt, llave.txt**: Las 5 figuras que la red aprende de memoria.
  - **entrada.txt**: La figura de prueba que le damos a la red para ver si la adivina.
  - **resultado.txt**: Donde la red guarda la figura limpia y restaurada cuando termina.

## Cómo correr el proyecto

En la terminal escribe:

```
javac MImage.java
java MImage
```
