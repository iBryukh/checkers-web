package builder.rest.services.storage;

/**
 * Created by oleh_kurpiak on 27.05.16.
 */
public class StorageFile {

    private final String name;

    public StorageFile(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
