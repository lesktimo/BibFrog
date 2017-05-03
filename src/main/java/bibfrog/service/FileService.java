package bibfrog.service;

import bibfrog.domain.Inproceeding;
import java.io.File;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * Service for returning specified file, creating headers and filepaths.
 */
@Service
public class FileService {

    /**
     * Return file with specified filepath.
     *
     * @param filePath
     * @return specified file
     */
    public File getFilePathForBytes(String filePath) {
        return new File(filePath);
    }

    /**
     * Creates path for given file.
     *
     * @param file
     * @return filepath
     */
    public Path createPath(File file) {
        return Paths.get(file.getPath());
    }

    /**
     * Creates HttpHeaders for given file.
     *
     * @param file
     * @param fileName
     * @return HttpHeaders
     */
    public HttpHeaders createHeaders(File file, String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName + ".bib".replace(".txt", ""));
        headers.setContentLength(file.length());
        return headers;
    }

    public Inproceeding createInproceedingFromFile(File inpro) throws FileNotFoundException {
        Scanner fileReader = new Scanner(inpro);
        Inproceeding newInpro = new Inproceeding();
        String inprotex = "";

        while (fileReader.hasNextLine()) {
            inprotex += fileReader.nextLine();
        }

        String[] attributes = inprotex.split(",");

        for (String attribute : attributes) {
            System.out.println(attribute);
        }

        return newInpro;

    }

    public HttpEntity<byte[]> createBibFile(String fileName) throws IOException {
        File referenceFile = getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(createPath(referenceFile));
        return new HttpEntity<>(bytes, createHeaders(referenceFile, fileName));

    }

}
