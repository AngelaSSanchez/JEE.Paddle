package web;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import business.wrapper.UserWrapper;
import data.daos.UserDao;
import data.entities.User;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("name")
public class Presenter {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private UserDao userDao;

    private String theme = "thymeleaf";

    public Presenter() {
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return theme + "/home";
    }
    
/*
    @RequestMapping("/create-theme")
    public ModelAndView theme(@RequestParam String theme) {
        this.theme = theme;
        return new ModelAndView(theme + "/home", "themes", THEMES);
    }
*/
    @RequestMapping(value = "/greeting")
    public String greeting(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, Model model) {
        model.addAttribute("stringList", Arrays.asList("uno", "dos", "tres"));
        model.addAttribute("cookie", cookie.getName());
        model.addAttribute("ip", request.getRemoteAddr());
        return theme + "/greeting";
    }
    
    @RequestMapping("/user-list")
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView(theme + "/userList");
        modelAndView.addObject("userList", userDao.findAll());
        return modelAndView;
    }

    @RequestMapping(value = {"/delete-user/{username}"})
    public String deleteUser(@PathVariable String username, Model model) {
    	User u = userDao.findByUsernameOrEmail(username);
        userDao.delete(u.getId());
        model.addAttribute("userList", userDao.findAll());
        return theme + "/userList";
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("user", new UserWrapper());
        return theme + "/createUser";
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public String createUserSubmit(@Valid User user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (userDao.save(user) != null) {
                model.addAttribute("username", user.getUsername());
                model.addAttribute("password", user.getPassword());
                model.addAttribute("email", user.getEmail());
                //Date time = new DateTime();
                model.addAttribute("birthDate", user.getBirthDate());
                return theme + "/registrationSuccess";
            } else {
                bindingResult.rejectValue("id", "error.user", "Usuario ya existente");
            }
        }
        return theme + "/createUser";
    }

}
