package bibfrog.controller;

import bibfrog.domain.Book;
import bibfrog.repositories.BooksRepo;
import bibfrog.service.*;
import java.io.IOException;
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
        return setBookAttributes(book);
    }

    private String setBookAttributes(Book book) {
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
        return setBookAttributes(book);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("bookList", booksRepo.findAll());
        return "books";
    }

    @RequestMapping(value = "/book/{id}/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadBook(@PathVariable Long id, @RequestParam String fileName) throws IOException {
        createFileForDownloading(id);
        return fileService.createBibFile(fileName);
    }

    @RequestMapping(value = "/books/all/download", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadAllBooks(@RequestParam String fileName) throws IOException {
        String bibtex = exportService.createBibtexFromAllBooks(booksRepo.findAll());
        exportService.createFile(bibtex);
        return fileService.createBibFile(fileName);
    }

    private void createFileForDownloading(Long id) throws IOException {
        Book book = booksRepo.findOne(id);
        String bibtex = exportService.createBibtexFromBook(book);
        exportService.createFile(bibtex);
    }

}
