package de.hsos.swa.mocktail.suchen.entity;

import java.util.ArrayList;

import javax.enterprise.context.Dependent;

// Dependent - always creates different instance of object 
// during the injection
@Dependent
public class Mocktail {
    private String mocktailNummer;
    private String name;
    private ArrayList<Zutat> produktinformation;

    public Mocktail() {}
    
    public Mocktail(String mocktailNummer, String name) {
        this.mocktailNummer = mocktailNummer;
        this.name = name;
        this.produktinformation = new ArrayList<>();
    }

    public Mocktail(String mocktailNummer, String name, ArrayList<Zutat> produktinformation) {
        this.mocktailNummer = mocktailNummer;
        this.name = name;
        this.produktinformation.addAll(produktinformation);
    }
    
    @Override
    public String toString() {
        return "\nWare [name=" + name + ", produktinformation=" + produktinformation + ", mocktailNummer=" + mocktailNummer + "]\n";
    }

    public String getMocktailNummer() {
        return mocktailNummer;
    }

    public void setMocktailNummer(String mocktailNummer) {
        this.mocktailNummer = mocktailNummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Zutat> getProduktinformation() {
        return produktinformation;
    }

    public void setProduktinformation(Zutat newProductInfo) {
        produktinformation.add(newProductInfo);
    }


}
