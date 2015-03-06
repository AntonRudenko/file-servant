package ru.teamlabs.fileservant.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.teamlabs.fileservant.FileException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author Anton Rudenko.
 */
@Service
public class FileService {

    @Autowired
    @Qualifier("repositories")
    private Properties repositoriesProperties;

    public Path findFile(String repostoryName, String file) {
        if (!repositoriesProperties.keySet().contains(repostoryName)) throw new FileException("Repository does not exist");

        Path path = Paths.get(repositoriesProperties.getProperty(repostoryName), file);
        if (!Files.exists(path)) throw new FileException("File does not exist");

        return path;
    }
}
