#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

typedef struct triangulo
{
    int id;
    int ladoA;
    int ladoB;
    int ladoC;
} TRIANGLULO;

void* hiloPerimetro (void *data){
    TRIANGLULO *trianguloTemporal = (TRIANGLULO*) data;
    int perimetro = trianguloTemporal->ladoA + trianguloTemporal->ladoB + trianguloTemporal->ladoC;

    printf("El perimetro es: %d\n", perimetro);
}

int main(int argc, char const *argv[])
{
    TRIANGLULO misTriangulos[] = {
        {0, 10, 15, 15},
        {1, 5, 7, 9},
        {2, 4, 8, 10}
    };
    pthread_t misThreads[3];

    for (int i = 0; i < 3; i++)
    {
        pthread_create(&misThreads[i], NULL, hiloPerimetro, (void*) &misTriangulos[i]);
        pthread_join(misThreads[i], NULL);
    }
    return 0;
}

