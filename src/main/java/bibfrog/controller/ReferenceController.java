package bibfrog.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class ReferenceController {

    protected File getFilePathForBytes(String filePath) {
        return new File(filePath);

    }

    protected Path createPath(File file) {
        return Paths.get(file.getPath());
    }

    protected HttpHeaders createHeaders(File file, String fileName) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + fileName + ".bib".replace(".txt", ""));
        headers.setContentLength(file.length());
        return headers;
    }
}
