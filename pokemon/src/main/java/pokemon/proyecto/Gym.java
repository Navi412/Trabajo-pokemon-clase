package pokemon.proyecto;

import java.util.ArrayList;
import pokemon.*;

public class Gym {
    private ArrayList<EntrenadorGym> entrenadores;
    private int numerinEntrenador = 0;

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
    
        ArrayList<Ataque> ataquesBlastoise = new ArrayList<>();
        ataquesBlastoise.add(new Ataque("Hidrobomba", 20));
        Pokemon blastoise = new Pokemon("Blastoise", 15, 85, 85, 0, "Agua", ataquesBlastoise);
        equipo.add(blastoise);
    
        ArrayList<Ataque> ataquesCharizard = new ArrayList<>();
        ataquesCharizard.add(new Ataque("Lanzallamas", 20));
        Pokemon charizard = new Pokemon("Charizard", 15, 90, 90, 0, "Fuego", ataquesCharizard);
        equipo.add(charizard);
    
        ArrayList<Ataque> ataquesVenusaur = new ArrayList<>();
        ataquesVenusaur.add(new Ataque("Hoja Afilada", 20));
        Pokemon venusaur = new Pokemon("Venusaur", 15, 80, 80, 0, "Planta", ataquesVenusaur);
        equipo.add(venusaur);
    
        ArrayList<Ataque> ataquesRaichu = new ArrayList<>();
        ataquesRaichu.add(new Ataque("Rayo", 20));
        Pokemon raichu = new Pokemon("Raichu", 15, 70, 70, 0, "Eléctrico", ataquesRaichu);
        equipo.add(raichu);
    
        return equipo;
    }
    

    public void resetarGimnasio() {
        entrenadores.clear();
        numerinEntrenador = 0;
        inicializarGimnasio();
    }

    public EntrenadorGym getEntrenadorActual() {
        if (numerinEntrenador < entrenadores.size()) {
            return entrenadores.get(numerinEntrenador);
        }
        return null;
    }

    public void siguienteEntrenador() {
        numerinEntrenador++;
    }

    public boolean comprobarEntrenadores() {
        return numerinEntrenador < entrenadores.size();
    }

    public ArrayList<EntrenadorGym> getEntrenadores() {
        return entrenadores;
    }
}
