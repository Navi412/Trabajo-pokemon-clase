package pokemon.proyecto;

import pokemon.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Combate {
    private static Scanner sc = new Scanner(System.in);

    public static boolean iniciarCombate(ArrayList<Pokemon> equipo, Pokemon enemigo) {
        boolean combateTerminado = false;
        boolean victoria = false;

        // Elegir Pokémon inicial que no esté debilitado
        while (equipo.size() > 1 && equipo.get(0).estaDebilitado()) {
            System.out.println("\n--- Elige el Pokemon para comenzar el combate ---");
            printNombresEquipo(equipo);
            int opcionInicial = 0;
            while (opcionInicial < 1 || opcionInicial > equipo.size() || equipo.get(opcionInicial - 1).estaDebilitado()) {
                System.out.print("Elige un Pokemon (1-" + equipo.size() + ", no debilitado): ");
                opcionInicial = sc.nextInt();
            }
            if (opcionInicial != 1) {
                Pokemon pokemonElegido = equipo.get(opcionInicial - 1);
                equipo.remove(opcionInicial - 1);
                equipo.add(0, pokemonElegido);
            }
        }

        while (!combateTerminado && comprobarPokemonVivo(equipo) && enemigo.estaVivo()) {
            System.out.println("\n--- Estado del combate ---");
            System.out.println("Tu Pokemon activo:");
            equipo.get(0).mostrarStats();
            System.out.println("Enemigo:");
            enemigo.mostrarStats();

            System.out.println("\n¿Qué quieres hacer?");
            System.out.println("1. Atacar");
            System.out.println("2. Cambiar Pokemon");
            System.out.println("3. Usar objeto");
            System.out.println("4. Huir");
            int opcionTurno = 0;
            while (opcionTurno < 1 || opcionTurno > 4) {
                System.out.print("Opción: ");
                opcionTurno = sc.nextInt();
            }

            switch (opcionTurno) {
                case 1:
                    atacar(equipo, enemigo);
                    break;
                case 2:
                    cambiarPokemon(equipo);
                    break;
                case 3:
                    usarItem(equipo);
                    break;
                case 4:
                    System.out.println("Has huido del combate.");
                    return false;
            }

            if (!enemigo.estaVivo()) {
                System.out.println("¡Has derrotado a " + enemigo.getNombre() + "!");
                victoria = true;
                combateTerminado = true;
            } else if (enemigo.estaVivo()) {
                Ataque ataqueEnemigo = enemigo.ataques.get((int)(Math.random() * enemigo.ataques.size()));
                enemigo.atacar(equipo.get(0), ataqueEnemigo);
            }

            if (equipo.get(0).estaDebilitado()) {
                System.out.println("¡" + equipo.get(0).getNombre() + " ha sido derrotado!");
                // No eliminar del equipo, solo cambiar al siguiente vivo
                if (!comprobarPokemonVivo(equipo)) {
                    System.out.println("¡Te has quedado sin Pokemon!");
                    combateTerminado = true;
                } else {
                    System.out.println("Elige un nuevo Pokemon:");
                    cambiarPokemon(equipo);
                }
            }
        }
        return victoria && comprobarPokemonVivo(equipo);
    }

    private static boolean comprobarPokemonVivo(ArrayList<Pokemon> equipo) {
       
        for (Pokemon p : equipo) {
            if (!p.estaDebilitado()) 
                return true;
        }

        return false;
    }

    private static void atacar(ArrayList<Pokemon> equipo, Pokemon enemigo) {
        if (equipo.get(0).estaDebilitado()) {
            System.out.println("¡No puedes atacar con un Pokemon debilitado!");
            return;
        }


        System.out.println("Elige un ataque:");
        ArrayList<Ataque> ataques = equipo.get(0).ataques;
        for (int i = 0; i < ataques.size(); i++) {
            System.out.println((i+1) + ". " + ataques.get(i).getNombre() + " (Daño: " + ataques.get(i).getDaño() + ")");
        }

        int opcionAtaque = 0;
        while (opcionAtaque < 1 || opcionAtaque > ataques.size()) {
            System.out.print("Opción: ");
            opcionAtaque = sc.nextInt();
        }

        Ataque ataqueJugador = ataques.get(opcionAtaque-1);
        equipo.get(0).atacar(enemigo, ataqueJugador);


    }

    private static void cambiarPokemon(ArrayList<Pokemon> equipo) {
        System.out.println("\n--- Cambiar Pokemon ---");
        printNombresEquipo(equipo);
        int opcion = 0;
        while (opcion < 1 || opcion > equipo.size() || equipo.get(opcion - 1).estaDebilitado()) {
            System.out.print("Elige un Pokemon (1-" + equipo.size() + ", no debilitado): ");
            opcion = sc.nextInt();
        }
        if (opcion != 1) {
            Pokemon pokemonElegido = equipo.get(opcion - 1);
            equipo.remove(opcion - 1);
            equipo.add(0, pokemonElegido);
            System.out.println(equipo.get(0).getNombre() + " entra al combate!");
        }      

    }


    private static void usarItem(ArrayList<Pokemon> equipo) {
        System.out.println("\n¿Qué objeto quieres usar?");
        System.out.println("1. Poción (+50 PS)\n2. Superpoción (+150 PS)\n3. Revivir");
        int opcionItem = sc.nextInt();

        switch(opcionItem) {
            case 1:

                if(Menu.mochila.usarItem("Poción")) {
                    equipo.get(0).curar(50);
                    System.out.println(equipo.get(0).getNombre() + " ha regenerado 50 PS!");
                } else {
                    System.out.println("¡No tienes Pociones!");
                }
                break;
            case 2:

                if(Menu.mochila.usarItem("Superpocion")) {
                    equipo.get(0).curar(150);
                    System.out.println(equipo.get(0).getNombre() + " ha regenerado 150 PS!");
                } else {
                    System.out.println("¡No tienes Superpciones!"); /* Cambio en el Superpociones */
                }
                break;
            case 3:

                if(equipo.get(0).estaDebilitado()) {
                    if(Menu.mochila.usarItem("Revivir")) {
                        equipo.get(0).revivir();
                        System.out.println(equipo.get(0).getNombre() + " ha sido revivido y tiene la saluda al máximo!");
                    } else {
                        System.out.println("¡No tienes Revivir!");
                    }
                } else {
                    System.out.println("¡Este Pokemon no está debilitado!");
                }

                break;
        }
    }

    public static void printNombresEquipo(ArrayList<Pokemon> equipo) {
        int cont = 1;
        for (Pokemon p : equipo) {
            System.out.print(cont++ + ". ");
            p.mostrarStats();
        }
    }

    public class GuardarEstado {

        public static void guardarEstadoPokemons(List<Pokemon> pokemons, String nombreArchivo) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
                for (Pokemon p : pokemons) {
                    writer.write("Nombre: " + p.getNombre());
                    writer.newLine();
                    writer.write("Nivel: " + p.getNivel());
                    writer.newLine();
                    writer.write("Vida: " + p.getVida() + "/" + p.getVidaMaxima());
                    writer.newLine();
                    writer.write("Experiencia: " + p.getExperiencia() + "/" + p.getExperienciaParaSubir());
                    writer.newLine();
                    writer.write("Tipo: " + p.getTipo());
                    writer.newLine();
                    writer.write("Ataques:");
                    writer.newLine();
                    for (Ataque a : p.ataques) {
                        writer.write("  - " + a.getNombre() + " (Daño: " + a.getDaño() + ")");
                        writer.newLine();
                    }
                    writer.write("-----------------------------");
                    writer.newLine();
                }
                System.out.println("Estado de los Pokémon guardado en " + nombreArchivo);
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        }
}

}
