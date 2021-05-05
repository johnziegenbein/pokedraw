package pokedraw.project.service.storage.generation;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonStorageGenerator {

    void generateStorageFromExternalSystemFile() throws IOException {
        JSONObject jsonObject = getPokemonJsonObject();
        JSONArray pokemonArray = jsonObject.getJSONArray("results");
        System.out.println(pokemonArray);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < pokemonArray.length(); i++) {
            JSONObject pokemonJsonObject = new JSONObject();

            JSONObject oldPokemonObject = pokemonArray.getJSONObject(i);
            pokemonJsonObject.put("id", getIdFromOldJSONObject(oldPokemonObject));
            pokemonJsonObject.put("name", getNameFromOldJSONObject(oldPokemonObject));
            pokemonJsonObject.put("drawn", false);

            jsonArray.put(pokemonJsonObject);
        }
        JSONObject storageJsonObject = new JSONObject();
        storageJsonObject.put("pokemons", jsonArray);

        try (FileWriter file = new FileWriter("C:\\Git\\pokedraw\\service\\src\\main\\resources\\pokemons.json")) {
            file.write(storageJsonObject.toString(4));
            file.flush();
        }
    }

    private String getNameFromOldJSONObject(JSONObject oldPokemonObject) {
        String oldName = oldPokemonObject.getString("name");
        return oldName.substring(0, 1).toUpperCase() + oldName.substring(1);
    }

    private String getIdFromOldJSONObject(JSONObject jsonObject) {
        return String.format("%03d", Integer.parseInt(jsonObject.getString("url").split("/")[6]));
    }

    private JSONObject getPokemonJsonObject() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(
                "C:\\Git\\pokedraw\\service\\src\\main\\resources\\pokemonsFromExternalSystem.json"))
        );
        return new JSONObject(content);
    }
}
