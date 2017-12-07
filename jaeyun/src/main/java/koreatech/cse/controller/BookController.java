package koreatech.cse.controller;

import koreatech.cse.domain.Book;
import koreatech.cse.repository.BookMapper;
import koreatech.cse.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;

@Controller
@RequestMapping("/book")
public class BookController {

    @Inject
    private BookMapper bookMapper;
    @Inject
    private BookService bookService;

    @RequestMapping(value = "/bestseller", method = RequestMethod.GET)
    public String register(Model model, @RequestParam("categoryName") String categoryName) {
        ArrayList<Book> books = new ArrayList<Book>();
        Book book = new Book();
        String categoryId = bookMapper.findCategoryId(categoryName);
        book.setCategoryId(categoryId);
        book.setCategoryName(categoryName);

        //books = bookService.loadBestSellerByCategory(book);
        return "bestseller";
    }


}