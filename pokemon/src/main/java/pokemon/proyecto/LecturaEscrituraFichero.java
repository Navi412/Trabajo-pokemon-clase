package pokemon.proyecto;

import pokemon.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaEscrituraFichero {

    private static final String nombre_archivo = "datos_juego.txt";

    public static void guardar(int dinero, ArrayList<Pokemon> equipo) {
        File archivo = new File(nombre_archivo);
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write("Dinero actual (Cliricomblins): " + dinero + "\n\n");
            fw.write("Pokemons y sus Stats:\n");
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
            System.out.println("Error guardando datos finales: " + e.getMessage());
        }
    }
}