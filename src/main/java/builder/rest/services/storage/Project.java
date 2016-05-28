package builder.rest.services.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh_kurpiak on 27.05.16.
 */
public class Project {

    private List<VersionFolder> versions;

    public Project(){
        this(new ArrayList<VersionFolder>());
    }

    public Project(List<VersionFolder> versions){
        this.versions = versions;
    }

    public List<VersionFolder> getVersions() {
        return versions;
    }

    public void setVersions(List<VersionFolder> versions) {
        this.versions = versions;
    }

    public void addVersion(VersionFolder folder){
        if(versions == null)
            versions = new ArrayList<>();
        versions.add(folder);
    }
}
