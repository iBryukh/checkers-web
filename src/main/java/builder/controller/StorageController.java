package builder.controller;

import builder.services.IStorageService;
import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@Controller
public class StorageController {

    @Autowired
    private IStorageService storageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file")MultipartFile file) throws IOException, DbxException {
        storageService.uploadFile(file, "test", "lib.h", "1.1");
        return "redirect:/";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws IOException, DbxException {
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=lib.h");
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
        storageService.downloadFile(response.getOutputStream(), "test", "lib.h", "1.1");
    }

}
