package ru.teamlabs.fileservant.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.teamlabs.fileservant.FileException;
import ru.teamlabs.fileservant.WrongParamsException;
import ru.teamlabs.fileservant.file.dto.FileRequestDto;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Anton Rudenko.
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(method = RequestMethod.GET)
    public void getFile(@RequestParam String name, @RequestParam String file, HttpServletResponse response) {
        if (!StringUtils.hasText(name)) throw new WrongParamsException("Repository name is mandatory param");
        if (!StringUtils.hasText(file)) throw new WrongParamsException("File name is mandatory param");

        Path path = fileService.findFile(name, file);

        try (ServletOutputStream os = response.getOutputStream()) {
            response.setContentType("application/x-file-download");
            Files.copy(path, os);
            response.flushBuffer();
        } catch (Exception exception) {
            throw new FileException("Can't write file to output stream");
        }

    }



}
