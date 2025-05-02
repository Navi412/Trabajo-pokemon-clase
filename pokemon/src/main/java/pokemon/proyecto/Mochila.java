package pokemon.proyecto;

import java.util.HashMap;
import pokemon.*;

public class Mochila {
    private HashMap<String, Integer> items;

    public Mochila() {
        items = new HashMap<>();
        items.put("Poción", 0);
        items.put("Superpoción", 0);
        items.put("Revivir", 0);
    }

    public void agregarItem(String item, int cantidad) {
        items.put(item, items.get(item) + cantidad);
    }

    public boolean usarItem(String item) {
        if (items.get(item) > 0) {
            items.put(item, items.get(item) - 1);
            return true;
        }
        return false;
    }

    public void mostrarContenido() {
        System.out.println("\n--- Contenido de la mochila ---");
        for (String k : items.keySet()) {
            Integer v = items.get(k);
            System.out.println(k + ": " + v);
        }
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }
}
