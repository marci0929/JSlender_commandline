package Terep;

import Jatekosok.Jatekos;

public class Mezo {
    boolean lehetPapir;
    boolean jarhato;
    Jatekos kiAllRajta;
    String neve;
    String jele;

    public Mezo(boolean lehetPapir, boolean jarhato, String neve, String jele) {
        this.lehetPapir = lehetPapir;
        this.jarhato = jarhato;
        this.kiAllRajta = null;
        this.neve = neve;
        this.jele = jele;
    }

    public String getJele() {
        return jele;
    }

    public void setJele(String jele) {
        this.jele = jele;
    }

    public String getNeve() {
        return neve;
    }

    public void setNeve(String neve) {
        this.neve = neve;
    }

    public Jatekos getKiAllRajta() {
        return kiAllRajta;
    }

    public void setKiAllRajta(Jatekos kiAllRajta) {
        this.kiAllRajta = kiAllRajta;
    }

    public boolean isLehetPapir() {
        return lehetPapir;
    }

    public void setLehetPapir(boolean lehetPapir) {
        this.lehetPapir = lehetPapir;
    }

    public boolean isJarhato() {
        return jarhato;
    }

    public void setJarhato(boolean jarhato) {
        this.jarhato = jarhato;
    }
}