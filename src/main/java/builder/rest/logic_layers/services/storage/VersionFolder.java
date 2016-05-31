package builder.rest.logic_layers.services.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh_kurpiak on 27.05.16.
 */
public class VersionFolder {

    private final String version;

    private List<StorageFile> files;

    public VersionFolder(String version){
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public List<StorageFile> getFiles() {
        return files;
    }

    public void setFiles(List<StorageFile> files) {
        this.files = files;
    }

    public void addFile(StorageFile file){
        if(files == null)
            files = new ArrayList<>();
        files.add(file);
    }
}
