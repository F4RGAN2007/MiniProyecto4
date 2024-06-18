import java.util.Scanner;
import modelo.RegistroEstudiante;
import java.util.List;

public class App {
    public static void main(String[] args) {
        RegistroEstudiante registro = new RegistroEstudiante();
        Scanner scanner = new Scanner(System.in);
        String nombre;
        double calificacion;

        System.out.println("Introduce el nombre del estudiante y su calificación (o 'listo' para terminar):");

        while (true) {
            System.out.print("Nombre del estudiante: ");
            nombre = scanner.nextLine();
            if (nombre.equalsIgnoreCase("listo")) {
                break;
            }

            try{
            System.out.print("Calificación: ");
            calificacion = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer del scanner

            registro.agregarEstudiante(nombre, calificacion);
        } catch (Exception CommaException){
            System.out.println("Error: Utiliza una ',' en el decimal, ej: 4,8");
            scanner.nextLine(); // Limpiar el buffer del scanner después del error
        }

    }

        double promedio = registro.calcularPromedio();
        System.out.printf("Promedio de calificaciones: %.2f%n", promedio);

        List<String> superiores = registro.EstudiantesSuperiores(promedio);
        System.out.println("Estudiantes con calificación superior al promedio:");
        for (String estudiante : superiores) {
            System.out.println(estudiante);
        }
    }
}


