package bibfrog.controller;

import bibfrog.domain.Book;
import bibfrog.repositories.BooksRepo;
import bibfrog.service.ExportService;
import bibfrog.service.FileService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BooksController {

    @Autowired
    private BooksRepo booksRepo;

    @Autowired
    private ExportService exportService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/book/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @RequestMapping(value = "/book/{id}/edit", method = RequestMethod.GET)
    public String editBook(Model model, @PathVariable long id) {
        model.addAttribute("book", booksRepo.findOne(id));
        return "book_edit";
    }

    @RequestMapping(value = "/book/{id}/edit", method = RequestMethod.POST)
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult bindingResult) {
        booksRepo.delete(id);

        if (bindingResult.hasErrors()) {
            return "book_edit";
        }
        book = booksRepo.save(book);
        book.setAuthors();
        if (book.getReferenceKey() == null || book.getReferenceKey().isEmpty()) {
            book.generateReferenceKey();
        }

        booksRepo.save(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public String postBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book";
        }
        book = booksRepo.save(book);
        book.setAuthors();
        if (book.getReferenceKey() == null || book.getReferenceKey().isEmpty()) {
            book.generateReferenceKey();
        }
        booksRepo.save(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("bookList", booksRepo.findAll());
        return "books";
    }

    @RequestMapping(value = "/book/{id}/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadBook(@PathVariable Long id, @RequestParam String fileName) throws IOException {
        createFileForDownloading(id);
        File bookFile = fileService.getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(fileService.createPath(bookFile));
        return new HttpEntity<>(bytes, fileService.createHeaders(bookFile, fileName));
    }

    @RequestMapping(value = "/books/all/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadAllBooks(@RequestParam String fileName) throws IOException {
        String bibtex = exportService.createBibtexFromAllBooks(booksRepo.findAll());
        exportService.createFile(bibtex);
        File bookFile = fileService.getFilePathForBytes("src/bibtex.bib");
        byte[] bytes = Files.readAllBytes(fileService.createPath(bookFile));
        return new HttpEntity<>(bytes, fileService.createHeaders(bookFile, fileName));
    }

    private void createFileForDownloading(Long id) throws IOException {
        Book book = booksRepo.findOne(id);
        String bibtex = exportService.createBibtexFromBook(book);
        exportService.createFile(bibtex);
    }

}
