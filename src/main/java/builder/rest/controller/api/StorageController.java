package builder.rest.controller.api;

import builder.rest.domain.response.Response;
import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.ServiceException;
import builder.rest.exceptions.bad_request.BadFileExtension;
import builder.rest.logic_layers.services.IStorageService;
import builder.rest.logic_layers.services.storage.Project;
import builder.rest.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@Controller
@RequestMapping("/api/storage")
public class StorageController {

    @Autowired
    private IStorageService storageService;

    @Autowired
    private ResponseBuilder builder;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody
    Response<Boolean> upload(
            @RequestParam("project") String project,
            @RequestParam("version") String version,
            @RequestParam("file_name") String fileName,
            @RequestParam("file")MultipartFile file
    ) throws ServiceException, BadFileExtension {
        boolean success = storageService.uploadFile(file, project, fileName, version);

        return builder.get(success);
    }

    @RequestMapping(value = "/{project}/{version}/{file}/", method = RequestMethod.GET)
    public void download(
            @PathVariable("project") String project,
            @PathVariable("version") String version,
            @PathVariable("file") String fileName,
            HttpServletResponse response
    ) throws ServiceException {
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");

        try {
            storageService.downloadFile(response.getOutputStream(), project, fileName, version);
        } catch (IOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @RequestMapping(value = "/{project}/versions", method = RequestMethod.GET)
    public @ResponseBody Response<Project> getVersions(
            @PathVariable("project") String project
    ) throws ServiceException, BadRequestException {
        return builder.get(storageService.listFiles(project));
    }

}
