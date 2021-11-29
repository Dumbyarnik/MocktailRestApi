package de.hsos.swa.mocktail.suchen.gateway.rdb;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;

import de.hsos.swa.mocktail.suchen.entity.Zutat;
import de.hsos.swa.mocktail.suchen.entity.Mocktail;
import de.hsos.swa.mocktail.suchen.gateway.MocktailKatalog;


// Singleton - for @Inject, so it will be the 
// same instance as long as this component is injected

// ApplicationScoped - there is only one instance of it in 
// the whole app
@ApplicationScoped
@Singleton
public class MocktailRepository implements Serializable, MocktailKatalog {
    private static final long serialVersionUID = 1L;
    private Map<String, Mocktail> mocktailRepo = new ConcurrentHashMap<>();
    private int repoNumber = 0;
    private static final int MAXSIZE = 16;

    @Override
    public Mocktail add(String name){
        if(mocktailRepo.size() < MAXSIZE){
            repoNumber++;
            Mocktail newMocktail = new Mocktail("" + repoNumber, name);
            this.mocktailRepo.put("" + repoNumber, newMocktail);  
            System.out.println("Added: " + name); 
            return newMocktail;
        } else{
            System.out.println("Repo ist voll");
        }
        return null;
    }

    @Override
    public Optional<Mocktail> addZutat(String mocktailId, String description){
        Optional<Mocktail> mocktail_tmp = this.findById(mocktailId);
        Mocktail mocktail;
        if (mocktail_tmp != null){
            mocktail = mocktail_tmp.get();
            mocktail.setProduktinformation(new Zutat(mocktail.getProduktinformation().size(), description));
        }
        return mocktail_tmp;     
    }

    @Override
    public Optional<Mocktail> findById(final String mocktailId){
        for(Map.Entry<String, Mocktail> entry : this.mocktailRepo.entrySet()){
            if(mocktailId.equals(entry.getValue().getMocktailNummer())){
                return Optional.of(entry.getValue());
            }
        }
        return null;
    }
 
    @Override
    public Collection<Mocktail> getAll(){
        Map<String, Mocktail> op = new ConcurrentHashMap<>();
        for(Map.Entry<String, Mocktail> entry : this.mocktailRepo.entrySet()){
            op.put(entry.getKey(), entry.getValue());
        }   
        return op.values();
    }

    @Override
    public boolean delete (final String mocktailId){
        Optional<Mocktail> mocktail_tmp = this.findById(mocktailId);

        if(mocktail_tmp != null){
            for(Map.Entry<String, Mocktail> entry : this.mocktailRepo.entrySet()){
                if(entry.getValue().equals(mocktail_tmp.get())){
                    mocktailRepo.remove(entry.getKey());
                    System.out.println("Deleted: " + mocktail_tmp.get().getName());                    
                    
                    return true; 
                }
            }       
        }
        return false;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Map<String, Mocktail> getmocktailRepo() {
        return mocktailRepo;
    }

    public void setmocktailRepo(Map<String, Mocktail> mocktailRepo) {
        this.mocktailRepo = mocktailRepo;
    }

    public int getCount() {
        return repoNumber;
    }

    public void setCount(int count) {
        this.repoNumber = count;
    }

    @Override
    public String toString() {
        return "mocktailnRepository [repoNumber=" + repoNumber + ", mocktailRepo=" + mocktailRepo + "]";
    }

}
