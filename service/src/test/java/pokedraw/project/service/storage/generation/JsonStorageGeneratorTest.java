package pokedraw.project.service.storage.generation;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class JsonStorageGeneratorTest {

    @Test
    void generateStorageFromExternalSystemFile() throws IOException {
        JsonStorageGenerator jsonStorageGenerator = new JsonStorageGenerator();
        jsonStorageGenerator.generateStorageFromExternalSystemFile();
    }

}