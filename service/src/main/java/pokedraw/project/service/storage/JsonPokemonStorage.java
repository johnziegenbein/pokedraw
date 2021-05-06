package pokedraw.project.service.storage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pokedraw.project.service.ApplicationConfiguration;
import pokedraw.project.service.data.Pokemon;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class JsonPokemonStorage implements PokemonStorage {

    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public JsonPokemonStorage(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    public List<Pokemon> get() {
        try {
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
        } catch (IOException e) {
            throw new IllegalStateException("Could not get from storage", e);
        }
    }

    @Override
    public Pokemon replacePokemon(Pokemon pokemon) {
        try {
            JSONObject pokemonListJsonObject = getPokemonJsonObject();
            JSONArray pokemonArray = pokemonListJsonObject.getJSONArray("pokemons");
            int index = resolveDBIndex(pokemon);

            pokemonArray.getJSONObject(index)
                    .put("name", pokemon.getName())
                    .put("id", pokemon.getId())
                    .put("drawn", pokemon.isDrawn());

            try (FileWriter file = new FileWriter(applicationConfiguration.getStorage())) {
                file.write(pokemonListJsonObject.toString(4));
                file.flush();
            }

            return pokemon;
        }
        catch (IOException e) {
            throw new IllegalStateException("Could not replace pokemon", e);
        }
    }

    @Override
    public List<Pokemon> findByFilter(String filter) {
        try {
            JSONArray jsonArray = getPokemonJsonObject().getJSONArray("pokemons");
            if (isNumeric(filter)) {
                return filterById(filter, jsonArray);
            }
            return filterByName(filter, jsonArray);
        } catch (IOException e) {
            throw new IllegalStateException("Could not findByFilter", e);
        }
    }

    private List<Pokemon> filterByName(String filter, JSONArray jsonArray) {
        ArrayList<Pokemon> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject pokemonJSONObject = jsonArray.getJSONObject(i);
            if (containsName(filter, pokemonJSONObject)) {
                list.add(new Pokemon(
                        pokemonJSONObject.getString("id"),
                        pokemonJSONObject.getString("name"),
                        pokemonJSONObject.getBoolean("drawn")
                ));
            }
        }
        return list;
    }

    private boolean containsName(String filter, JSONObject jsonObject) {
        return jsonObject.getString("name").toLowerCase(Locale.ROOT)
                .contains(filter.toLowerCase(Locale.ROOT));
    }

    private List<Pokemon> filterById(String filter, JSONArray jsonArray) {
        ArrayList<Pokemon> list = new ArrayList<>();

        return list;
    }

    /**
     * Resolves DB index from pokemon by parsing id.
     * We need to negate with one since pokemon index starts with 1 and java index starts with 0.
     */
    private int resolveDBIndex(Pokemon pokemon) {
        return Integer.parseInt(pokemon.getId()) - 1;
    }

    private JSONObject getPokemonJsonObject() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(applicationConfiguration.getStorage())));
        return new JSONObject(content);
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
