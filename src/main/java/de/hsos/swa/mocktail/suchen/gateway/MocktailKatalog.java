package de.hsos.swa.mocktail.suchen.gateway;

import java.util.Collection;
import java.util.Optional;

import de.hsos.swa.mocktail.suchen.entity.Mocktail;

public interface MocktailKatalog {

   Mocktail add(String name);
   Optional<Mocktail> addZutat(String wareId, String description);
   Optional<Mocktail> findById(final String wareId);
   Collection<Mocktail> getAll();
   boolean delete (final String wareId);
}
