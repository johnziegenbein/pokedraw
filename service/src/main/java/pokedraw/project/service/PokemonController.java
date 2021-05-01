package pokedraw.project.service;

import org.springframework.web.bind.annotation.*;
import pokedraw.project.service.data.Pokemon;

import java.util.List;

@RestController
public class PokemonController {
    private PokemonService pokemonService = new PokemonService();

    @GetMapping("/pokemon")
    public List<Pokemon> getPokemon() {
        return pokemonService.getAll();
    }

    @PutMapping("/pokemon")
    public Pokemon replacePokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.replacePokemon(pokemon);
    }
}
