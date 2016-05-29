package builder.rest.logic_layers.services;

import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.ServiceException;
import builder.rest.exceptions.bad_request.BadFileExtension;
import builder.rest.logic_layers.services.storage.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public interface IStorageService {

    boolean uploadFile(MultipartFile file, String project, String fileName, String version) throws ServiceException, BadFileExtension;

    void downloadFile(OutputStream outputStream, String project, String fileName, String version) throws ServiceException;

    Project listFiles(String projectName) throws ServiceException, BadRequestException;
}
