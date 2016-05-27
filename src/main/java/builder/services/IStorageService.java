package builder.services;

import builder.exceptions.ServiceException;
import builder.exceptions.bad_request.BadFileExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public interface IStorageService {

    boolean uploadFile(MultipartFile file, String project, String fileName, String version) throws ServiceException, BadFileExtension;

    void downloadFile(OutputStream outputStream, String project, String fileName, String version) throws ServiceException;
}