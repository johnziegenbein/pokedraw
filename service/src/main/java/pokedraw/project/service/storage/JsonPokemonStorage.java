package pokedraw.project.service.storage;

import org.json.JSONArray;
import org.json.JSONObject;
import pokedraw.project.service.data.Pokemon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPokemonStorage implements PokemonStorage {

    private static final String POKEMON_FILE = "pokemons.json";

    @Override
    public List<Pokemon> get() {
        JSONArray pokemonArray = getPokemonJsonObject().getJSONArray("pokemons");
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

    @Override
    public Pokemon replacePokemon(Pokemon pokemon) {
        JSONObject pokemonListJsonObject = getPokemonJsonObject();
        JSONArray pokemonArray = pokemonListJsonObject.getJSONArray("pokemons");
        int index = resolveDBIndex(pokemon);

        pokemonArray.getJSONObject(index)
                .put("name", pokemon.getName())
                .put("id", pokemon.getId())
                .put("drawn", pokemon.isDrawn());

        try (FileWriter file = new FileWriter("src/main/resources/pokemons.json")) {
            file.write(pokemonListJsonObject.toString(4));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemon;
    }

    /**
     * Resolves DB index from pokemon by parsing id.
     * We need to negate with one since pokemon index starts with 1 and java index starts with 0.
     */
    private int resolveDBIndex(Pokemon pokemon) {
        return Integer.parseInt(pokemon.getId()) - 1;
    }

    private JSONObject getPokemonJsonObject() {
        String pokemonString = getJsonString();
        System.out.println(pokemonString);
        return new JSONObject(pokemonString);
    }

    private String getJsonString() {
        InputStream pokemonInputStream = getClass().getClassLoader().getResourceAsStream(POKEMON_FILE);
        return new BufferedReader(new InputStreamReader(pokemonInputStream))
                .lines()
                .collect(Collectors.joining("\n"));
    }
}
