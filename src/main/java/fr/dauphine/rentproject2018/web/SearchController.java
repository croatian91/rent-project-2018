package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("/search")
    public @ResponseBody
    Collection<User> search(@RequestParam("q") String q) {
        return searchService.findUserByUsernameOrEmailOrFirstNameOrLastName(q, q, q, q);
    }
}
