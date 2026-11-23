# 👾 Hopfield Arcade 10x10

Red neuronal de Hopfield programada en Java que aprende 5 figuras del arcade clásico y las reconoce aunque la imagen de entrada venga con píxeles dañados.

> Sin numpy, TensorFlow ni ninguna librería: solo arrays, `for`, `while` e `if/else`.

## Figuras que memoriza

| # | Figura | Archivo |
|---|--------|---------|
| 1 | Pac-Man | `datasets/pacman.txt` |
| 2 | Fantasma | `datasets/fantasma.txt` |
| 3 | Cereza | `datasets/cereza.txt` |
| 4 | Corazón | `datasets/corazon.txt` |
| 5 | Llave | `datasets/llave.txt` |

Cada figura es una cuadrícula de 10x10 escrita con `1` (píxel pintado) y `0` (píxel vacío).

## Archivos de prueba

- `datasets/entrada.txt` → figura con ruido que la red tiene que adivinar.
- `datasets/resultado.txt` → figura ya corregida que la red guarda al terminar.

## Ejecutar

```bash
javac MImage.java
java MImage
```

El programa muestra las 5 figuras, la entrada con ruido, cada iteración de la red y al final la figura reconocida con su porcentaje de coincidencia.

Para probar otra figura solo edita `entrada.txt`.
