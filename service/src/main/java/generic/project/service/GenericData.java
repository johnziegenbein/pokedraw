package generic.project.service;

public class GenericData {
    private final long id;
    private final String content;

    public GenericData(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
