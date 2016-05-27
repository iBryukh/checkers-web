package builder.services.impl;

import builder.exceptions.ServiceException;
import builder.exceptions.bad_request.BadFileExtension;
import builder.services.IStorageService;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWriteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
     * @param file - file to be saved
     * @param id - identifier of file
     * @param prefix - folder
     * @return success
     * @throws IOException
     * @throws DbxException
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
     * @throws IOException
     * @throws DbxException
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
