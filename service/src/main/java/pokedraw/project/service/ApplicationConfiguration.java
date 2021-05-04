package pokedraw.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    private final String storage;

    @Autowired
    public ApplicationConfiguration(@Value("${json.storage.path}") String storage) {
        this.storage = storage;
        System.out.println("================== " + storage + "================== ");
    }

    @Bean
    public String getStorage() {
        return storage;
    }
}
