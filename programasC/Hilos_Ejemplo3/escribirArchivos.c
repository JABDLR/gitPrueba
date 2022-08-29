#include <stdlib.h>
#include <stdio.h>
//Libreria para hilos
#include <pthread.h> 
//Librerias para manejar archivos
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>

void *escribirLinea(void *argumento){
    char mensaje[] = "Nueva linea\n";
    int numeroLineas = *((int*) argumento); //El argumento lo casteo a un entero y psoteriormente tomo su valor
    int fd;

    printf("El hilo empezo a ejecutarse...");
    for (int i = 0; i < numeroLineas; i++)
    {
        fd = open("C:\\Users\\Jorge A\\Desktop\\workspace\\programasC\\Hilos_Ejemplo3\\archivo.txt", O_WRONLY|O_APPEND);
        write(fd, mensaje, sizeof(mensaje)-1);
    }
    

}

int main(int argc, char const *argv[])
{
    pthread_t hilo;
    int lineas;

    if (1 < argc)
    {
        lineas = atoi(argv[1]);
    }else
    {
        printf("No ingresaste el número de linas en consola, argumentos");
        return -1;
    }
    
    

    /* pthread_create(Puntero al identificador del hilo, Atributos del hilo, Rutina a ejecutarse, Argumentos de la rutina); */
    if (pthread_create(&hilo, NULL, escribirLinea, &lineas) != 0)
    {
        return -1;
    }
    
    //pthread_join(Identificador del hilo, Valor del retorno);
    pthread_join(hilo, NULL);
    return 0;
}
