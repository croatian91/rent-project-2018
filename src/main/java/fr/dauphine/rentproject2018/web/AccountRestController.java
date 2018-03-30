package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.User;
import fr.dauphine.rentproject2018.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private final UserService userService;

    @Autowired
    public AccountRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/account/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable List() {
        return userService.findAll();
    }

    @RequestMapping("/account/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @RequestMapping("/account/update")
    @ResponseStatus(HttpStatus.OK)
    public User update(@ModelAttribute User user) {
        return userService.update(user);
    }

    @RequestMapping("/account/findOne")
    @ResponseStatus(HttpStatus.OK)
    public User findOne(@RequestBody User user) {
        User u = userService.findOne(user.getId());

        return u == null ? new User() : user;
    }

    @RequestMapping("/account/{id}/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        userService.delete(userService.findOne(id));
    }

    @RequestMapping("/account/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(Principal principal) {
        userService.delete(userService.findByUsername(principal.getName()));
    }
}
