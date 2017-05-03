package bibfrog.service;

import java.io.File;
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
    
    public HttpEntity<byte[]> createBibFile(String fileName) throws IOException {
        File referenceFile = getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(createPath(referenceFile));
        return new HttpEntity<>(bytes, createHeaders(referenceFile, fileName));
    }

    /**
     * Return file with specified filepath.
     *
     * @param filePath
     * @return specified file
     */
    private File getFilePathForBytes(String filePath) {
        return new File(filePath);
    }

    /**
     * Creates path for given file.
     *
     * @param file
     * @return filepath
     */
    private Path createPath(File file) {
        return Paths.get(file.getPath());
    }

    /**
     * Creates HttpHeaders for given file.
     *
     * @param file
     * @param fileName
     * @return HttpHeaders
     */
    private HttpHeaders createHeaders(File file, String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName + ".bib".replace(".txt", ""));
        headers.setContentLength(file.length());
        return headers;
    }

}
