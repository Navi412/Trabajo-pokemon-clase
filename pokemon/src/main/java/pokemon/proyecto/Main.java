package pokemon.proyecto;

import java.util.ArrayList;
import java.util.Scanner;
import pokemon.*;

public class Main {
    public static int cliricomblins = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Ataque> ataquesElectricos = new ArrayList<>();
        ataquesElectricos.add(new Ataque("Impactrueno", 15));
        ataquesElectricos.add(new Ataque("Placaje", 10));

        ArrayList<Ataque> ataquesFuego = new ArrayList<>();
        ataquesFuego.add(new Ataque("Ascuas", 15));
        ataquesFuego.add(new Ataque("Arañazo", 10));

        ArrayList<Ataque> ataquesAgua = new ArrayList<>();
        ataquesAgua.add(new Ataque("Pistola Agua", 15));
        ataquesAgua.add(new Ataque("Placaje", 10));

        ArrayList<Ataque> ataquesPlanta = new ArrayList<>();
        ataquesPlanta.add(new Ataque("Látigo Cepa", 15));
        ataquesPlanta.add(new Ataque("Placaje", 10));

        ArrayList<Pokemon> pokemonsDisponibles = new ArrayList<>();
        pokemonsDisponibles.add(new Pokemon("Pikachu", 5, 35, 35, 0, "Eléctrico", ataquesElectricos));
        pokemonsDisponibles.add(new Pokemon("Zapdos", 5, 60, 60, 0, "Eléctrico", ataquesElectricos));
        pokemonsDisponibles.add(new Pokemon("Jolteon", 5, 65, 65, 0, "Eléctrico", ataquesElectricos));
        pokemonsDisponibles.add(new Pokemon("Charmander", 5, 39, 39, 0, "Fuego", ataquesFuego));
        pokemonsDisponibles.add(new Pokemon("Tepig", 5, 38, 38, 0, "Fuego", ataquesFuego));
        pokemonsDisponibles.add(new Pokemon("Growlithe", 5, 48, 48, 0, "Fuego", ataquesFuego));
        pokemonsDisponibles.add(new Pokemon("Squirtle", 5, 44, 44, 0, "Agua", ataquesAgua));
        pokemonsDisponibles.add(new Pokemon("Psyduck", 5, 50, 50, 0, "Agua", ataquesAgua));
        pokemonsDisponibles.add(new Pokemon("Totodile", 5, 50, 50, 0, "Agua", ataquesAgua));
        pokemonsDisponibles.add(new Pokemon("Bulbasaur", 5, 45, 45, 0, "Planta", ataquesPlanta));
        pokemonsDisponibles.add(new Pokemon("Chikorita", 5, 40, 40, 0, "Planta", ataquesPlanta));
        pokemonsDisponibles.add(new Pokemon("Snivy", 5, 55, 55, 0, "Planta", ataquesPlanta));

        ArrayList<Pokemon> equipo = new ArrayList<>();
        System.out.println("Selecciona 6 pokemons para tu equipo:");
        while (equipo.size() < 6) {
            System.out.println("\nPokemons disponibles:");
            for (int i = 0; i < pokemonsDisponibles.size(); i++) {
                Pokemon p = pokemonsDisponibles.get(i);
                System.out.println((i+1) + ". " + p.getNombre() + " | Tipo: " + p.getTipo() + " | Vida: " + p.getVidaMaxima());
            }

            System.out.print("\nElige un Pokemon (1-" + pokemonsDisponibles.size() + "): ");
            int eleccion = -1;
            try {
                eleccion = Integer.parseInt(sc.nextLine()) - 1;
            } catch (Exception e) {
                eleccion = -1;
            }

            if (eleccion >= 0 && eleccion < pokemonsDisponibles.size()) {
                Pokemon seleccionado = pokemonsDisponibles.remove(eleccion);
                equipo.add(seleccionado);
                System.out.println(seleccionado.getNombre() + " ha sido añadido a tu equipo.");
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }

        Menu.mostrarMenu(equipo);
        sc.close();
    }
}
