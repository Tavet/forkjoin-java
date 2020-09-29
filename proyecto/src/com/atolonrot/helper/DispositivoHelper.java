package com.atolonrot.helper;

import com.atolonrot.model.Dispositivo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Breyner
 * Clase auxiliar para obtener datos aleatorios y llevarlos a una lista de Dispositivos
 */

public class DispositivoHelper {
    private DispositivoHelper() {
    }

    private static String getRandomBrand() {
        final String[] brands = {"Asus", "Samsung", "Huwaei", "Apple", "Xiaomi", "Motorola"};
        return brands[new Random().nextInt(brands.length)];
    }

    private static String getRandomModel() {
        final String[] models = {"A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "CAZ2", "XYZ1", "89ASD"};
        return models[new Random().nextInt(models.length)];
    }

    private static Integer getRandomBattery() {
        return new Random().nextInt(100);
    }

    public static List<Dispositivo> llenarConDatosAleatorios(int cantidad) {
        List<Dispositivo> list = new LinkedList<>();

        for (int i = 0; i < cantidad; i++) {
            list.add(new Dispositivo(getRandomBattery(), getRandomModel(), getRandomBrand()));
        }

        return list;
    }
}
