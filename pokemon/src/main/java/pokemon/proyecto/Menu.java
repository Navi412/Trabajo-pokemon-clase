package pokemon.proyecto;

import pokemon.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static Gym gym;
    private static boolean enGimnasio = false;
    public static Mochila mochila = new Mochila();

    public static void mostrarMenu(ArrayList<Pokemon> equipo) {
        Scanner sc = new Scanner(System.in);
        String respuesta;
        do {
            System.out.println("\n------ Menú Principal --------");
            System.out.println("1. Continuar (siguiente entrenador)");
            System.out.println("2. Iniciar desafío de gimnasio");
            System.out.println("3. Mochila");
            System.out.println("4. Ver equipo");
            System.out.println("5. Tienda");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    if (enGimnasio) {
                        EntrenadorGym entrenador = gym.getEntrenadorActual();
                        if (entrenador != null) {
                            System.out.println("\n--- Combate contra " + entrenador.getNombre() + " ---");
                            int expGanada = 0;
                            boolean victoria = true;
                            for (Pokemon enemigo : entrenador.getPokemons()) {
                                boolean resultado = Combate.iniciarCombate(equipo, enemigo);
                                if (!resultado) {
                                    victoria = false;
                                    break;
                                }
                                expGanada += (enemigo.getNivel() * 50);
                            }

                            if (victoria) {
                                int recompensa = 500 * (gym.getEntrenadores().indexOf(entrenador) + 1);
                                Main.cliricomblins += recompensa;
                                System.out.println("¡Has ganado " + recompensa + " Cliricomblins!");

                                System.out.println("\n¡Has derrotado a " + entrenador.getNombre() + "!");
                                System.out.println("Experiencia total ganada: " + expGanada);
                                for (Pokemon p : equipo) {
                                    int niveles = p.subirExperiencia(expGanada);
                                    if (niveles > 0) {
                                        System.out.println(p.getNombre() + " ha subido " + niveles + " nivel" + (niveles > 1 ? "es" : "") +
                                            " y ahora es nivel " + p.getNivel() + ".");
                                    }
                                }
                                gym.siguienteEntrenador();

                                if (!gym.comprobarEntrenadores()) {
                                    System.out.println("\n----------- ¡FELICIDADES! ----------- ");
                                    System.out.println("\n--- Has ganado el Gimnasio y obtenido la MEDALLA IFP ENHORABUENAAA  ´´ GOD CABRON `` --- ");                            
                                    enGimnasio = false;
                                    LecturaEscrituraFichero.guardar(Main.cliricomblins, equipo);
                                }
                            } else {
                                System.out.println("¡Has perdido el desafío!");
                                enGimnasio = false;
                            }
                        }
                    } else {
                        System.out.println("No estás en un gimnasio. Usa la opción 2 para iniciar.");
                    }
                    break;

                case "2":
                    gym = new Gym();
                    enGimnasio = true;
                    System.out.println("\n¡Bienvenido al Gimnasio Pokemon!");
                    System.out.println("Usa la opción 1 para enfrentarte al primer entrenador.");
                    break;

                case "3":
                    System.out.println("\n--- Mochila ---");
                    mochila.mostrarContenido();
                    System.out.println("\n1. Usar objeto\n2. Volver");
                    String opcionMochila = sc.nextLine();
                    if (opcionMochila.equals("1")) {
                        usarItemFueraDeCombate(equipo, sc);
                    }
                    break;

                case "4":
                    System.out.println("\n--- Tu equipo ---");
                    for (Pokemon x : equipo) {
                        x.mostrarStats();
                    }
                    break;

                case "5":
                    tienda(sc);
                    break;

                case "6":
                    System.out.println("¡Gracias por jugar!");
                    LecturaEscrituraFichero.guardar(Main.cliricomblins, equipo);
                    return;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        } while (!respuesta.equals("6"));
    }

    private static void tienda(Scanner sc) {
        System.out.println("\n--- Tienda ---");
        System.out.println("Cliricomblins: " + Main.cliricomblins);
        System.out.println("1. Poción (200 Cliricomblins) - Restaura 50 PS");
        System.out.println("2. Superpoción (500 Cliricomblins) - Restaura 150 PS");
        System.out.println("3. Revivir (800 Cliricomblins) - Revive a un Pokemon debilitado y restaura toda su vida");
        System.out.println("4. Salir");
        System.out.print("Elige: ");
        String opcion = sc.nextLine();

        switch(opcion) {
            case "1":
                if (Main.cliricomblins >= 200) {
                    mochila.agregarItem("Poción", 1);
                    Main.cliricomblins -= 200;
                    System.out.println("¡Has comprado una Poción!");
                } else {
                    System.out.println("¡No tienes suficientes Cliricomblins!");
                }
                break;
            case "2":
                if (Main.cliricomblins >= 500) {
                    mochila.agregarItem("Superpoción", 1);
                    Main.cliricomblins -= 500;
                    System.out.println("¡Has comprado una Superpoción!");
                } else {
                    System.out.println("¡No tienes suficientes Cliricomblins!");
                }
                break;
            case "3":
                if (Main.cliricomblins >= 800) {
                    mochila.agregarItem("Revivir", 1);
                    Main.cliricomblins -= 800;
                    System.out.println("¡Has comprado un Revivir!");
                } else {
                    System.out.println("¡No tienes suficientes Cliricomblins!");
                }
                break;
        }
    }

    private static void usarItemFueraDeCombate(ArrayList<Pokemon> equipo, Scanner sc) {
        System.out.println("\n¿Qué objeto quieres usar?");
        System.out.println("1. Poción\n2. Superpoción\n3. Revivir");
        String opcionItem = sc.nextLine();

        System.out.println("¿En qué Pokemon?");
        for(int i = 0; i < equipo.size(); i++) {
            Pokemon p = equipo.get(i);
            System.out.println((i+1) + ". " + p.getNombre() + (p.estaDebilitado() ? " (Debilitado)" : " - PS: " + p.getVida() + "/" + p.getVidaMaxima()));
        }
        int pokemonElegido = Integer.parseInt(sc.nextLine()) - 1;

        if(pokemonElegido >= 0 && pokemonElegido < equipo.size()) {
            Pokemon p = equipo.get(pokemonElegido);
            switch(opcionItem) {
                case "1":
                    if(p.estaDebilitado()) {
                        System.out.println("¡No puedes usar una Poción en un Pokemon debilitado!");
                    } else if(mochila.usarItem("Poción")) {
                        p.curar(50);
                        System.out.println(p.getNombre() + " recuperó 50 PS!");
                    } else {
                        System.out.println("¡No tienes Pociones!");
                    }
                    break;
                case "2":
                    if(p.estaDebilitado()) {
                        System.out.println("¡No puedes usar una Superpoción en un Pokemon debilitado!");
                    } else if(mochila.usarItem("Superpoción")) {
                        p.curar(150);
                        System.out.println(p.getNombre() + " recuperó 150 PS!");
                    } else {
                        System.out.println("¡No tienes Superpociones!");
                    }
                    break;
                case "3":
                    if(p.estaDebilitado()) {
                        if(mochila.usarItem("Revivir")) {
                            p.revivir();
                            System.out.println(p.getNombre() + " ha sido revivido y está a máxima salud!");
                        } else {
                            System.out.println("¡No tienes Revivir!");
                        }
                    } else {
                        System.out.println("¡Este Pokemon no está debilitado!");
                    }
                    break;
            }
        }
    }
}
