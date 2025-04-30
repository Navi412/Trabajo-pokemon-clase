package pokemon.proyecto;

public class Ataque {
    private String nombre;
    private int daño;

    public Ataque(String nombre, int daño) {
        this.nombre = nombre;
        this.daño = daño;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDaño() {
        return daño;
    }
    
    // Añadido para poder aumentar el daño al subir de nivel
    public void setDaño(int daño) {
        this.daño = daño;
    }
}
