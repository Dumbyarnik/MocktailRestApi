package de.hsos.swa.mocktail.suchen.entity;

public class Zutat {
    private final int id;
    private String zutat;


    public Zutat(int id, String zutat) {
        this.id = id;
        this.zutat = zutat;

    }
    
    @Override
    public String toString() {
        return "Zutat [zutat=" + zutat + ", id=" + id + "]";
    }

    public int getId() {
        return id;
    }

    public String getzutat() {
        return zutat;
    }

    public void setzutat(String zutat) {
        this.zutat = zutat;
    }

}
