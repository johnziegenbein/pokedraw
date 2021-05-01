package pokedraw.project.service.storage;

import org.junit.jupiter.api.Test;
import pokedraw.project.service.data.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;

class JsonPokemonStorageTest {

    private JsonPokemonStorage jsonPokemonStorage = new JsonPokemonStorage();

    @Test
    void get_contains3Pokemon() {
        assertThat(jsonPokemonStorage.get()).hasSize(4);
    }

    @Test
    void get_pokemonContainsAllData() {
        Pokemon bulbasaur = jsonPokemonStorage.get().get(0);
        assertThat(bulbasaur.getId()).isEqualTo("001");
        assertThat(bulbasaur.getName()).isEqualTo("Bulbasaur");
        assertThat(bulbasaur.isDrawn()).isEqualTo(false);
    }
}