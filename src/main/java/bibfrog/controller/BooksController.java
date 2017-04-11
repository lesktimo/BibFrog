package bibfrog.controller;

import bibfrog.domain.Book;
import bibfrog.repositories.BooksRepo;
import bibfrog.service.ExportService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BooksController {

    @Autowired
    private BooksRepo booksRepo;

    @Autowired
    private ExportService exportService;

    @RequestMapping(value = "/book/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "book";
    }

    @RequestMapping(value = "/book/add", method = RequestMethod.POST)
    public String postBook(@Valid @ModelAttribute Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book";
        }
        booksRepo.save(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("bookList", booksRepo.findAll());
        return "books";
    }

}
