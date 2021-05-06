package pokedraw.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pokedraw.project.service.data.Pokemon;

import java.util.ArrayList;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/pokemon/findByFilter")
    public List<Pokemon> findByFilter(@RequestParam String filter) {
        return pokemonService.findByFilter(filter);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/pokemon")
    public Pokemon replacePokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.replacePokemon(pokemon);
    }
}
