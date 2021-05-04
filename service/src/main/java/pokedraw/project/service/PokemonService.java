package pokedraw.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pokedraw.project.service.data.Pokemon;
import pokedraw.project.service.storage.PokemonStorage;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonStorage pokemonStorage;

    @Autowired
    public PokemonService(PokemonStorage pokemonStorage) {
        this.pokemonStorage = pokemonStorage;
    }

    public List<Pokemon> getAll() {
        return pokemonStorage.get();
    }

    public Pokemon replacePokemon(Pokemon pokemon) {
        return pokemonStorage.replacePokemon(pokemon);
    }
}
