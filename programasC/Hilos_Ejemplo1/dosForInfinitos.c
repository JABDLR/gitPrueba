#include <stdio.h>
#include <stdlib.h>
#include <pthread.h> //Libreria para manejar hilos

void *hilo(void *texto);

int main(int argc, char const *argv[]) {
    /*pthread_t -> Tipo de dato que se utiliza para identificar
    de forma unica un subproceso (hilo)*/
    pthread_t primerCiclo;
    pthread_t segunndoCiclo;

    /*pthread_create -> Crea un nuevo subproceso dentro de un proceso. 
    Si se tiene exito devuelbe un 0, de lo contrario -1. El hilo contendra
    el ID del ilo creado*/
    /* pthread_create(Direccion de memoria del proceso, NULL, Direccion de memoria del la funcion a ejecutar, parametro); */
    pthread_create(&primerCiclo , NULL, &hilo , "Hola");
    pthread_create(&segunndoCiclo, NULL, &hilo, "Adios");

    /*pthread_join -> suspenderá la ejecución del subproceso de llamada 
    hasta que finalice el subproceso de destino. Si se tiene exito devuelbe 
    un 0, de lo contrario -1.*/
    /*Unimos el hilo de ejecución (hilo) al hilo principal (main)*/
    pthread_join(primerCiclo, NULL);
    pthread_join(segunndoCiclo, NULL);
    return 0;
}

void *hilo(void *texto) {
    int i = 0;
    char *mensaje = (char *) texto;

    /* struct timespec tiempoEspera = {Segundos, nanosegundos};*/
    struct timespec tiempoEspera = {2, 0};

    while (i < 5) {
        i++;

        printf("%s\n", mensaje);
        pthread_delay_np(&tiempoEspera);
    }    
}
