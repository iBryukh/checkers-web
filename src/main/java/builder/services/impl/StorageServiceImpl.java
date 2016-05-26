package builder.services.impl;

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
    public boolean uploadFile(MultipartFile file, String project, String fileName, String version) throws IOException, DbxException {
        return uploadFile(file, project + '/' + version, fileName);
    }

    @Override
    public void downloadFile(OutputStream outputStream, String project, String fileName, String version) throws IOException, DbxException {
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
    private boolean uploadFile(MultipartFile file, String prefix, String id) throws IOException, DbxException {
        InputStream inputStream = null;
        try {
            try {
                dbxClient.delete('/' + prefix + '/' + id);
            } catch (Exception e){
                //file not found. so do nothing
            }
            inputStream = new ByteArrayInputStream(file.getBytes());
            dbxClient.uploadFile('/' + prefix + '/' + id, DbxWriteMode.add(), file.getBytes().length, inputStream);
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
    private void downloadFile(OutputStream outputStream, String prefix, String id) throws IOException, DbxException {
        dbxClient.getFile('/'+ prefix + '/' + id, null, outputStream);
    }
}
