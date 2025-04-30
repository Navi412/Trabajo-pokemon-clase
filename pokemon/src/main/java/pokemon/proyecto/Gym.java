package pokemon.proyecto;

import java.util.ArrayList;

public class Gym {
    private ArrayList<EntrenadorGym> entrenadores;
    private int currentTrainerIndex = 0;

    public Gym() {
        this.entrenadores = new ArrayList<>();
        inicializarGimnasio();
    }

    private void inicializarGimnasio() {
        entrenadores.add(new EntrenadorGym("Sonia", "Agua", 2));
        entrenadores.add(new EntrenadorGym("Mariano", "Fuego", 2));
        entrenadores.add(new EntrenadorGym("Tomas", "Planta", 3));
        entrenadores.add(new EntrenadorGym("Edu", "Eléctrico", 3));

        EntrenadorGym hector = new EntrenadorGym("Hector", "Mixto", 4);
        hector.setPokemons(generarEquipoMixto());
        entrenadores.add(hector);
    }

    private ArrayList<Pokemon> generarEquipoMixto() {
        ArrayList<Pokemon> equipo = new ArrayList<>();
        equipo.add(new Pokemon("Blastoise", 15, 85, 85, 0, "Agua", new ArrayList<>() {{ add(new Ataque("Hidrobomba", 20)); }}));
        equipo.add(new Pokemon("Charizard", 15, 90, 90, 0, "Fuego", new ArrayList<>() {{ add(new Ataque("Lanzallamas", 20)); }}));
        equipo.add(new Pokemon("Venusaur", 15, 80, 80, 0, "Planta", new ArrayList<>() {{ add(new Ataque("Hoja Afilada", 20)); }}));
        equipo.add(new Pokemon("Raichu", 15, 70, 70, 0, "Eléctrico", new ArrayList<>() {{ add(new Ataque("Rayo", 20)); }}));
        return equipo;
    }

    public void reiniciarGimnasio() {
        entrenadores.clear();
        currentTrainerIndex = 0;
        inicializarGimnasio();
    }

    public EntrenadorGym getEntrenadorActual() {
        if (currentTrainerIndex < entrenadores.size()) {
            return entrenadores.get(currentTrainerIndex);
        }
        return null;
    }

    public void siguienteEntrenador() {
        currentTrainerIndex++;
    }

    public boolean quedanEntrenadores() {
        return currentTrainerIndex < entrenadores.size();
    }

    // CORRECCIÓN: Añadido el método que faltaba
    public ArrayList<EntrenadorGym> getEntrenadores() {
        return entrenadores;
    }
}
