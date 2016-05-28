package builder.rest.services.storage;

import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.ServiceException;
import builder.rest.exceptions.bad_request.BadFileExtension;
import builder.rest.exceptions.bad_request.BadProjectName;
import builder.rest.services.IStorageService;
import com.dropbox.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@Service
public class StorageServiceImpl implements IStorageService {

    @Autowired
    private DbxClient dbxClient;

    @Override
    public boolean uploadFile(MultipartFile file, String project, String fileName, String version) throws ServiceException, BadFileExtension {
        if(!fileExtension(fileName))
            throw new BadFileExtension(AVAILABLE_EXTENSIONS);
        return uploadFile(file, project + '/' + version, fileName);
    }

    @Override
    public void downloadFile(OutputStream outputStream, String project, String fileName, String version) throws ServiceException {
        downloadFile(outputStream, project + '/' + version, fileName);
    }

    /**
     *
     * @param projectName - the name of project
     * @return list of project version and it's files
     * @throws ServiceException - if some exception where throwed from storage in storage
     * @throws BadRequestException - if project with passed name does not exist
     */
    @Override
    public Project listFiles(String projectName) throws ServiceException, BadRequestException {
        Project project = new Project();
        try {
            DbxEntry.WithChildren root = dbxClient.getMetadataWithChildren('/'+ projectName);
            if(root == null)
                throw new BadProjectName(projectName);

            List<DbxEntry> versions = root.children;

            if(versions != null){
                for(DbxEntry version : versions){
                    if(version.isFolder()) {
                        VersionFolder folder = new VersionFolder(version.name);

                        // get children files for current version folder
                        DbxEntry.WithChildren versionFolder = dbxClient.getMetadataWithChildren(version.path);
                        List<DbxEntry> files = versionFolder.children;

                        if(files != null)
                            for(DbxEntry file : files)
                                folder.addFile(new StorageFile(file.name));

                        project.addVersion(folder);
                    }
                }
            }
        } catch (DbxException e) {
            throw new ServiceException(e.getMessage());
        }
        return project;
    }

    /**
     *
     * @param file - file to be saved
     * @param id - identifier of file
     * @param prefix - folder
     * @return success
     * @throws ServiceException - method throws it if something happens with storage
     */
    private boolean uploadFile(MultipartFile file, String prefix, String id) throws ServiceException {
        InputStream inputStream = null;
        byte[] bytes;
        try {
            try {
                dbxClient.delete('/' + prefix + '/' + id);
            } catch (Exception e){
                //file not found. so do nothing
            }
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                throw new ServiceException(e.getMessage());
            }

            inputStream = new ByteArrayInputStream(bytes);

            try {
                dbxClient.uploadFile('/' + prefix + '/' + id, DbxWriteMode.add(), bytes.length, inputStream);
            } catch (DbxException | IOException e) {
                throw new ServiceException(e.getMessage());
            }
            return true;
        } finally {
            try {
                if(inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param outputStream - stream for file
     * @param id - identifier of file
     * @param prefix - folder
     * @throws ServiceException method throw it if something happens with storage
     */
    private void downloadFile(OutputStream outputStream, String prefix, String id) throws ServiceException {
        try {
            dbxClient.getFile('/'+ prefix + '/' + id, null, outputStream);
        } catch (DbxException | IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     *
     * @param name - name of tested file
     * @return - true if correct extension, false otherwise
     */
    private boolean fileExtension(String name){
        int dot = name.lastIndexOf('.');
        if(dot > -1){
            String extension = name.substring(dot+1);
            return extension.matches(AVAILABLE_EXTENSIONS);
        }
        return false;
    }


    private static final String AVAILABLE_EXTENSIONS = "h|lib";
}
