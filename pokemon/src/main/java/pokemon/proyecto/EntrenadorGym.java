package pokemon.proyecto;

import java.util.ArrayList;

public class EntrenadorGym {
    private String nombre;
    private String tipoEspecialidad;
    private int cantidadPokemon;
    private ArrayList<Pokemon> pokemons;

    public EntrenadorGym(String nombre, String tipo, int cantidad) {
        this.nombre = nombre;
        this.tipoEspecialidad = tipo;
        this.cantidadPokemon = cantidad;
        this.pokemons = generarEquipo();
    }

    private ArrayList<Pokemon> generarEquipo() {
        ArrayList<Pokemon> equipo = new ArrayList<>();
        ArrayList<Ataque> ataques = new ArrayList<>();

        switch(tipoEspecialidad) {
            case "Agua": ataques.add(new Ataque("Hidrobomba", 20)); break;
            case "Fuego": ataques.add(new Ataque("Lanzallamas", 20)); break;
            case "Planta": ataques.add(new Ataque("Hoja Afilada", 20)); break;
            case "Eléctrico": ataques.add(new Ataque("Rayo", 20)); break;
        }

        for(int i = 0; i < cantidadPokemon; i++) {
            int nivel = 10 + (i * 2);
            int vidaBase = 40 + (i * 10);
            equipo.add(new Pokemon(
                obtenerNombrePokemon(tipoEspecialidad, i),
                nivel,
                vidaBase,
                vidaBase,
                0,
                tipoEspecialidad,
                new ArrayList<>(ataques)
            ));
        }
        return equipo;
    }

    private String obtenerNombrePokemon(String tipo, int index) {
        switch(tipo) {
            case "Agua": return new String[]{"Squirtle", "Wartortle", "Blastoise"}[index];
            case "Fuego": return new String[]{"Charmander", "Charmeleon", "Charizard"}[index];
            case "Planta": return new String[]{"Bulbasaur", "Ivysaur", "Venusaur"}[index];
            case "Eléctrico": return new String[]{"Pikachu", "Raichu", "Electabuzz"}[index];
            default: return "Pokémon";
        }
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public String getNombre() { return nombre; }
    public ArrayList<Pokemon> getPokemons() { return pokemons; }
}
