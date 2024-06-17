package modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class RegistroEstudiante {
    private HashMap<String, Double> registros;

    public RegistroEstudiante() {
        this.registros = new HashMap<>();
    }

    public void agregarEstudiante(String nombre, double calificacion) {
        registros.put(nombre, calificacion);
    }

    public double calcularPromedio() {
        if (registros.isEmpty()) {
            return 0;
        }
        double sumaCalificaciones = 0;
        for (double calificacion : registros.values()) {
            sumaCalificaciones += calificacion;
        }
        return sumaCalificaciones / registros.size();
    }

    public List<String> EstudiantesSuperiores(double promedio) {
        List<String> superiores = new ArrayList<>();
        for (Map.Entry<String, Double> estudiante : registros.entrySet()) {
            if (estudiante.getValue() > promedio) {
                superiores.add(estudiante.getKey());
            }
        }
        return superiores;
    }

    public HashMap<String, Double> obtenerRegistros() {
        return this.registros;
    }
}

