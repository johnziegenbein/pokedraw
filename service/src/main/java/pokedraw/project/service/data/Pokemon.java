package pokedraw.project.service.data;

import java.util.Objects;

public final class Pokemon {
    private final String id;
    private final String name;
    private final boolean drawn;

    public Pokemon(String id, String name, boolean drawn) {
        this.id = id;
        this.name = name;
        this.drawn = drawn;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDrawn() {
        return drawn;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Pokemon) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.drawn, that.drawn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, drawn);
    }

    @Override
    public String toString() {
        return "Pokemon[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "drawn=" + drawn + ']';
    }
}
