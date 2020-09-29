# Fork/Join Framework en Java
En este repositorio está almacenada la práctica realizada en el artículo del blog en Medium.

## Estructuración del proyecto
* helper
    * _DispositivoHelper.java_ Contiene métodos para generar data aleatoria y llenar la lista con la cantidad de dispositivos deseados
* model
    * _Dispositivo.java_ Pojo o modelo que se usará para la práctica
* _SumatoriaBateriaForkJoin.java_ es la clase que extiende la clase para dividir las tareas y ejecutarlas paralelamente

## Explicación breve del framework Fork/Join
El framework Fork/Join implementa la interface ExecutorService, cuya función de esta anterior es permitirnos construir una tarea para que pueda ser procesada asíncronamente por un hilo. El framework Fork/Join posee un pool de hilos (threads), que es como un conjunto de procesadores disponibles en la Java Virtual Machine. Cada uno de estos hilos actuará como una Double-Ended Queue para almacenar tareas y llevarlas a su ejecución.

![Explicación de cómo funciona el ForkJoin](https://cdn-images-1.medium.com/max/800/1*fnjklt1nrOk8adWhWR5MEA.png)
