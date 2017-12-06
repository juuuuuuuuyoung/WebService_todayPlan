package koreatech.cse.controller;

import koreatech.cse.domain.Authority;
import koreatech.cse.domain.Book;
import koreatech.cse.domain.Searchable;
import koreatech.cse.domain.User;
import koreatech.cse.repository.AuthorityMapper;
import koreatech.cse.repository.BookMapper;
import koreatech.cse.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("/book")
public class BookController {

    @Inject
    private BookMapper bookMapper;
    @Inject
    private AuthorityMapper authorityMapper;
    @Inject
    private BookService bookService;

    @RequestMapping(value = "/bestseller", method = RequestMethod.GET)
    public String register(Model model, @RequestParam("categoryName") String categoryName) {
        ArrayList<Book> books = new ArrayList<Book>();
        Book book = new Book();
        String categoryId = bookMapper.findCategoryId(categoryName);
        book.setCategoryId(categoryId);
        book.setCategoryName(categoryName);

        books = bookService.loadBestSellerByCategory(book);
        return "bestseller";
    }

//    @Transactional
//    @RequestMapping(value="/register", method= RequestMethod.POST)
//    public String register(@ModelAttribute Book book) {
//        //bookService.register(book);
//
//        /**
//         *  User loginUser = User.current();
//         *  if(loginUser != null) {
//         *      book.setUserId(loginUser.getId());
//         *  }
//         */
//
//        return "redirect:/book/list";
//    }
//
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(Model model) {
//        Searchable searchable = new Searchable();
//
//        model.addAttribute("books", bookMapper.findByScript(searchable));
//        return "list";
//    }
//
//    @RequestMapping(value="/delete", method= RequestMethod.GET) // 위험! 주소만 알면 다 할 수 있음
//    public String delete(@RequestParam int id) {
//
//        int bookUserId = bookMapper.findOne(id).getUserId();
//        int curUserId;
//        String curUserAuthority;
//
//        if ( User.current() != null ) {
//            curUserId = User.current().getId();
//            curUserAuthority = authorityMapper.findByUserId(curUserId).get(0).getAuthority();
//
//
//        } else {
//            curUserId = -99999;
//            curUserAuthority = "";
//        }
//
//        // User loginUser = User.current();
//        // if (loginUser.hasRole("ROLE_ADMIN") || book.getUserId()==loginUser.getId()) {
//        //      bookMapper.delete(id);
//        // }
//
//
//        if( curUserAuthority.equals("ROLE_ADMIN") || curUserId == bookUserId )
//            bookMapper.delete(id);
//
//        return "redirect:/book/list";
//
//    }

}