package Terep;

import Jatekosok.Jatekos;

public class Mezo {
    boolean lehetPapir;
    int papirID;
    boolean vanPapir;
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
        this.vanPapir=false;
        this.papirID=-1;
    }

    public int getPapirID() {
        return papirID;
    }

    public void setPapirID(int papirID) {
        this.papirID = papirID;
    }

    public boolean isVanPapir() {
        return vanPapir;
    }

    public void setVanPapir(boolean vanPapir) {
        this.vanPapir = vanPapir;
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

    public Jatekos getKiAllRajta() {
        return kiAllRajta;
    }

    public void setKiAllRajta(Jatekos kiAllRajta) {
        this.kiAllRajta = kiAllRajta;
    }

    public boolean isLehetPapir() {
        return lehetPapir;
    }

    public boolean isJarhato() {
        return jarhato;
    }

}
