package pokedraw.project.service.storage;

import pokedraw.project.service.data.Pokemon;

import java.util.List;

public interface PokemonStorage {
    public List<Pokemon> get();
}
