# Red Neuronal de Hopfield en Java

Implementación desde cero de una **Red Neuronal de Hopfield** en Java para el reconocimiento de figuras representadas en una matriz de $M \times N$ ($8 \times 5 = 40$ neuronas), utilizando únicamente arreglos (`arrays`), ciclos `for`, `while` y sentencias condicionales `if / else`, sin librerías externas.

## Descripción

De acuerdo con el reto de la Sesión 2 (*Intro Deep Learning*):
- Cada figura se representa en una cuadrícula de $M \times N = 8 \times 5$ donde:
  - `+1` indica un cuadro negro (`██`).
  - `-1` indica un cuadro blanco (`··`).
- Se entrenan los tres patrones de ejemplo (**Número 1**, **Número 2** y **Número 4**) mediante la regla de aprendizaje de Hebb ($W = \sum_p x^{(p)} (x^{(p)})^T$ con diagonal principal $w_{ii} = 0$).
- La recuperación se realiza de forma síncrona mediante un ciclo `while` hasta que el estado de la red converge a un atractor estable.

## Compilación y Ejecución

```bash
javac Hopfield.java
java Hopfield
```
