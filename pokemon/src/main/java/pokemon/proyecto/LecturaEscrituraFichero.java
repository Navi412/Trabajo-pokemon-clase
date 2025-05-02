package pokemon.proyecto;

import pokemon.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaEscrituraFichero {

        // Archivo donde se guardan los datos del juego
    private static String nombre_archivo = "datos_juego.txt";

    public static void guardar(int dinero, ArrayList<Pokemon> equipo) {
        File archivo = new File(nombre_archivo);

        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write("Dinero actual (Cliricomblins): " + dinero + "\n\n");
            fw.write("Pokemons y sus Stats:\n");

                    // Escribe la información de cada Pokemon del equipo
            for (Pokemon p : equipo) {
                fw.write("Nombre: " + p.getNombre() +
                         " | Nivel: " + p.getNivel() +
                         " | Vida: " + p.getVida() + "/" + p.getVidaMaxima() +
                         " | Tipo: " + p.getTipo() +
                         (p.estaDebilitado() ? " (Debilitado)" : "") +
                         "\n");
            }

            fw.flush();
            System.out.println("Archivo de datos del juego creado: " + nombre_archivo);
        } catch (IOException e) {
            System.out.println("Error guardando datos: " + e.getMessage());
        }
    }

    public static void mostrarContenidoFichero() {
        File archivo = new File("datos_juego.txt");

                // Verifica si el archivo existe
        if (!archivo.exists()) {
            System.out.println("El archivo no existe. No se pueden mostrar los datos guardados.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("datos_juego.txt"));
            String linea;

            // Lee y muestra cada línea del archivo
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); 
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
