package pokedraw.project.service.storage;

import pokedraw.project.service.data.Pokemon;

import java.util.List;

public interface PokemonStorage {
    public List<Pokemon> get();

    public Pokemon replacePokemon(Pokemon pokemon);

    List<Pokemon> findByFilter(String filter);
}
