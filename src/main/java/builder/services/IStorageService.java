package builder.services;

import com.dropbox.core.DbxException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public interface IStorageService {

    boolean uploadFile(MultipartFile file, String project, String fileName, String version) throws IOException, DbxException;

    void downloadFile(OutputStream outputStream, String project, String fileName, String version) throws IOException, DbxException;
}
