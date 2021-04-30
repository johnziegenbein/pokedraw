package generic.project.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {
    @GetMapping("/generic")
    public GenericData generic() {
        return new GenericData(1, "Generic Project");
    }
}
