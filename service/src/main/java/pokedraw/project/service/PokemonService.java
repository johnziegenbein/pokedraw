package pokedraw.project.service;

import pokedraw.project.service.data.Pokemon;
import pokedraw.project.service.storage.JsonPokemonStorage;
import pokedraw.project.service.storage.PokemonStorage;

import java.util.List;

public class PokemonService {
    private PokemonStorage pokemonStorage = new JsonPokemonStorage();

    public List<Pokemon> getAll() {
        return pokemonStorage.get();
    }

    public Pokemon replacePokemon(Pokemon pokemon) {
        return pokemonStorage.replacePokemon(pokemon);
    }
}
