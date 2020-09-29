package com.atolonrot;

import com.atolonrot.model.Dispositivo;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static com.atolonrot.helper.DispositivoHelper.llenarConDatosAleatorios;

/**
 * @author Breyner
 * Clase principal de ejecución
 */
public class Main {

    public static void main(String... args) {
        // Rellenamos la lista con X datos aleatorios
        List<Dispositivo> dispositivos = llenarConDatosAleatorios(100_000);

        // Test secuencial
        sequentialTest(dispositivos);

        // Test paralelo con el framework ForkJoin
        forkJoinParallelTest(dispositivos.toArray(new Dispositivo[dispositivos.size()]));

        // Test paralelo con la API Stream
        streamParallelTest(dispositivos);
    }


    /**
     * Test secuencial. Suma la batería de todos los dispositivos en un foreach tradicional utilizando un único hilo
     *
     * @param dispositivos
     */
    private static void sequentialTest(List<Dispositivo> dispositivos) {
        long initTime = System.currentTimeMillis();

        double totalBateria = 0;
        for (Dispositivo dispositivo : dispositivos) {
            totalBateria += dispositivo.obtenerBateria();
        }

        double promedioBateria = totalBateria / dispositivos.size();

        System.out.println(String.format("Batería promedio: %f", promedioBateria));
        System.out.println(String.format("Se demora %d milisegundos en calcular secuencialmente", System.currentTimeMillis() - initTime));
    }

    /**
     * Test paralelo. Utilizamos el framework ForkJoin aquí
     *
     * @param dispositivos
     */
    private static void forkJoinParallelTest(Dispositivo[] dispositivos) {

        long initTime = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SumatoriaBateriaForkJoin testParallel = new SumatoriaBateriaForkJoin(0, dispositivos.length, dispositivos);
        pool.invoke(testParallel);
        double promedioBateria = testParallel.getValue() / dispositivos.length;

        System.out.println(String.format("Batería promedio: %f", promedioBateria));
        System.out.println(String.format("Se demora %d milisegundos en calcular paralelamente con el framework ForkJoin", System.currentTimeMillis() - initTime));
    }

    /**
     * Test con la API Stream. Esta API utiliza el framework ForkJoin configurándolo automáticamente sin mucho esfuerzo
     *
     * @param dispositivos
     */
    private static void streamParallelTest(List<Dispositivo> dispositivos) {
        long initTime = System.currentTimeMillis();

        // Stream API. Se puede hacer en un único bloque de código
        double promedioBateria = dispositivos
                .parallelStream()
                .mapToDouble(dispositivo -> dispositivo.obtenerBateria())
                .average()
                .orElse(0);

        System.out.println(String.format("Batería promedio: %f", promedioBateria));
        System.out.println(String.format("Se demora %d milisegundos en calcular paralelamente con la API Stream", System.currentTimeMillis() - initTime));
    }


}
