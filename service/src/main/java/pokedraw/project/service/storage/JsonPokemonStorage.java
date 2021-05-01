package pokedraw.project.service.storage;

import org.json.JSONArray;
import org.json.JSONObject;
import pokedraw.project.service.data.Pokemon;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPokemonStorage implements PokemonStorage {

    private static final String POKEMON_FILE = "pokemons.json";

    @Override
    public List<Pokemon> get() {
        JSONArray pokemonArray = getPokemonJsonArray();
        List<Pokemon> pokemonList = new ArrayList<>();
        for (int i = 0; i < pokemonArray.length(); i++) {
            Pokemon pokemon = new Pokemon(
                    pokemonArray.getJSONObject(i).get("id").toString(),
                    pokemonArray.getJSONObject(i).get("name").toString(),
                    (boolean) pokemonArray.getJSONObject(i).get("drawn")
            );
            pokemonList.add(pokemon);
        }
        return pokemonList;
    }

    private JSONArray getPokemonJsonArray() {
        InputStream pokemonInputStream = getClass().getClassLoader().getResourceAsStream(POKEMON_FILE);
        String pokemonString =
                new BufferedReader(new InputStreamReader(pokemonInputStream))
                        .lines()
                        .collect(Collectors.joining("\n"));
        System.out.println(pokemonString);
        JSONObject obj = new JSONObject(pokemonString);
        return obj.getJSONArray("pokemons");
    }
}
