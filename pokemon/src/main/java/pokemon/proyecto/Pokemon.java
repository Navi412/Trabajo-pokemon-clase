package pokemon.proyecto;

import java.util.ArrayList;

public class Pokemon {
    private String nombre;
    private String tipo;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int experiencia;
    private int experienciaParaSubir;
    public ArrayList<Ataque> ataques;

    public Pokemon(String nombre, int nivel, int vida, int vidaMaxima, int experiencia, String tipo, ArrayList<Ataque> ataques) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
        this.experiencia = experiencia;
        this.tipo = tipo;
        this.ataques = ataques;
        this.experienciaParaSubir = 100 + (nivel - 1) * 25;
    }

    public String getNombre() { return nombre; }
    public int getNivel() { return nivel; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getExperiencia() { return experiencia; }
    public String getTipo() { return tipo; }
    public int getExperienciaParaSubir() { return experienciaParaSubir; }

    public void setVida(int vida) {
        this.vida = Math.max(0, Math.min(vida, vidaMaxima));
    }

    public boolean estaDebilitado() {
        return vida <= 0;
    }

    public int subirExperiencia(int exp) {
        int nivelesSubidos = 0;
        this.experiencia += exp;
        while (this.experiencia >= experienciaParaSubir) {
            this.experiencia -= experienciaParaSubir;
            subirNivelSinMensaje();
            nivelesSubidos++;
        }
        return nivelesSubidos;
    }

    private void subirNivelSinMensaje() {
        this.nivel++;
        this.vidaMaxima = (int)(this.vidaMaxima * 1.2);
        // NO restaurar vida al máximo al subir de nivel
        this.experienciaParaSubir += 25;
        
        // NUEVO: Aumentar el daño de los ataques al subir de nivel (5% por nivel)
        for (Ataque ataque : ataques) {
            int nuevoValor = (int)(ataque.getDaño() * 1.05);
            ataque.setDaño(nuevoValor);
        }
    }

    public void mostrarStats() {
        System.out.println(
            "Nombre: " + nombre +
            " | Nivel: " + nivel +
            " | Vida: " + vida + "/" + vidaMaxima +
            " | Exp: " + experiencia + "/" + experienciaParaSubir +
            " | Tipo: " + tipo +
            (estaDebilitado() ? " (Debilitado)" : "")
        );
    }

    public void curar(int cantidad) {
        if (!estaDebilitado()) {
            this.vida = Math.min(this.vida + cantidad, this.vidaMaxima);
        }
    }

    public void revivir() {
        this.vida = this.vidaMaxima;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void atacar(Pokemon objetivo, Ataque ataque) {
        int daño = ataque.getDaño();
        objetivo.setVida(objetivo.getVida() - daño);
        System.out.println(nombre + " usa " + ataque.getNombre() + " y hace " + daño + " de daño a " + objetivo.getNombre() + ".");
    }
}
