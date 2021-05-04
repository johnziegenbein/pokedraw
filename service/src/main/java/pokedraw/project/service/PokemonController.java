package pokedraw.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pokedraw.project.service.data.Pokemon;

import java.util.List;

@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemon")
    public List<Pokemon> getPokemon() {
        return pokemonService.getAll();
    }

    @PutMapping("/pokemon")
    public Pokemon replacePokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.replacePokemon(pokemon);
    }
}
