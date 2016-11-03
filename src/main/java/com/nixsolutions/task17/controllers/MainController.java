package com.nixsolutions.task17.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.task17.captcha.Captcha;
import com.nixsolutions.task17.model.Role;
import com.nixsolutions.task17.model.User;
import com.nixsolutions.task17.service.RoleService;
import com.nixsolutions.task17.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory
            .getLogger(MainController.class);

    // data for password checking
    public static final int WEAK = 1;
    public static final int MIDDLE = 5;
    public static final int STRONG = 7;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {
        if (request.isUserInRole("admin")) {
            logger.info("redirecting to admin page");
            return "redirect:/admin";
        }
        if (request.isUserInRole("user")) {
            logger.info("redirecting to user page");
            return "redirect:/user";
        }
        return "login";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
    public String forUser(ModelMap model) {
        User user = userService.findByLogin(getPrincipal());
        model.addAttribute("user", user);
        logger.info("User " + user + "logged in");
        return "user/user";
    }

    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        User user = userService.findByLogin(getPrincipal());
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        logger.info("Admin " + user + "logged in");
        return "admin/admin";

    }

    @RequestMapping(value = { "/register" }, method = { RequestMethod.GET })
    public ModelAndView register() {
        logger.info("redirecting to registration page");
        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping(value = { "/registered" }, method = { RequestMethod.POST })
    public String addUser(@Valid User user, BindingResult result,
            ModelMap model,
            @RequestParam("g-recaptcha-response") String recaptcha,
            Locale locale) throws IOException {
        user.setRole(roleService.findByName("user"));
        // verifying Captchacaptcha.error
        boolean verify = new Captcha().verify(recaptcha);
        if (!verify) {
            FieldError recapthaError = new FieldError("recaptcha", "recaptcha",
                    messageSource.getMessage("captcha.error", null, locale));
            result.addError(recapthaError);
        }
        // checking whether User exists
        if (!userService.isUser(user.getLogin())) {
            FieldError loginError = new FieldError("user", "login",
                    messageSource.getMessage("login.exists", null, locale));
            result.addError(loginError);
        }

        if (result.hasErrors()) {
            return "register";
        }
        try {
            userService.create(user);
            model.addAttribute("message",
                    messageSource.getMessage("register.success", null, locale));
        } catch (Exception e) {
            logger.error("Error during creating user: " + user);
            throw new RuntimeException(e);
        }
        return "login";
    }

    @RequestMapping(value = "/register/checkPassword", method = RequestMethod.GET, produces = {
            "text/html; charset=UTF-8" })
    public @ResponseBody String checkPassword(@RequestParam String password,
            ModelMap model, Locale locale) {
        try {
            if (password.length() >= WEAK & password.length() < MIDDLE) {
                return messageSource.getMessage("password.weak", null, locale);

            } else if (password.length() >= MIDDLE
                    & password.length() < STRONG) {
                return messageSource.getMessage("password.middle", null,
                        locale);
            } else if (password.length() >= STRONG) {
                return messageSource.getMessage("password.strong", null,
                        locale);
            }
        } catch (NoSuchMessageException e) {
            logger.error("No message found");
            throw new RuntimeException(e);
        }
        return messageSource.getMessage("password.weak", null, locale);
    }

    @RequestMapping(value = { "/addUser/add" }, method = RequestMethod.GET)
    public ModelAndView userAddEdit(ModelMap model) {
        model.addAttribute("action", "add");
        // getting Role list
        List<Role> roles = roleService.findAll();
        if (roles.size() != 0) {
            model.addAttribute("roles", roles);
        }
        return new ModelAndView("admin/add_edit_user", "user", new User());
    }

    @RequestMapping(value = { "/addEditUserDo/add" }, method = {
            RequestMethod.POST })
    public String addUserByAdmin(@Valid User user, BindingResult result,
            ModelMap model, Locale locale) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("action", "add");
            List<Role> roles = roleService.findAll();
            if (roles.size() != 0) {
                model.addAttribute("roles", roles);
            }
            return "admin/add_edit_user";
        }
        // checking whether User exists
        if (!userService.isUser(user.getLogin())) {
            logger.info("This login " + user.getLogin() + " already exists!");
            FieldError loginError = new FieldError("user", "login",
                    messageSource.getMessage("login.exists", null, locale));
            result.addError(loginError);
            model.addAttribute("action", "add");
            List<Role> roles = roleService.findAll();
            if (roles.size() != 0) {
                model.addAttribute("roles", roles);
            }
            return "admin/add_edit_user";
        }
        try {
            user.setRole(roleService.findByName("user"));
            userService.create(user);
            logger.info("User " + user + "was successfully created");
            User admin = userService.findByLogin(getPrincipal());
            model.addAttribute("user", admin);
            return "redirect:/admin";
        } catch (Exception e) {
            logger.error("Error during creating user: " + user);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = { "/addEditUserDo/edit" }, method = {
            RequestMethod.POST })
    public String editUserByAdmin(@Valid User user, BindingResult bindingResult,
            ModelMap model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", "edit");
            List<Role> roles = roleService.findAll();
            if (roles.size() != 0) {
                model.addAttribute("roles", roles);
            }
            return "admin/add_edit_user";
        }
        // setting User fields with new data
        User userReturned = null;
        Role userRole = null;
        try {
            userReturned = userService.findByLogin(user.getLogin());
            userReturned.setLogin(user.getLogin());
            userReturned.setLastName(user.getLastName());
            userReturned.setFirstName(user.getFirstName());
            userReturned.setBirthday(user.getBirthday());
            userReturned.setEmail(user.getEmail());
            userReturned.setPassword(user.getPassword());
            userReturned.setPasswordagain(user.getPasswordagain());
            userRole = roleService.findByName(user.getRole().getName());
            userReturned.setRole(userRole);
            userService.update(userReturned);
            logger.info("User " + userReturned + "was successfully created");
            User admin = userService.findByLogin(getPrincipal());
            model.addAttribute("user", admin);
            return "redirect:/admin";
        } catch (Exception e) {
            logger.error("Error during user saving in DB! " + e);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
    public ModelAndView userAddEdit(ModelMap model,
            @PathVariable("id") long id) {
        model.addAttribute("action", "edit");
        User userById = null;
        if (id >= 0) {
            userById = userService.findById(id);
            Role roleById = roleService.findById(userById.getRole().getId());
            model.addAttribute("userById", userById);
            model.addAttribute("roleById", roleById);
        }
        // getting Role list
        List<Role> roles = roleService.findAll();
        if (roles.size() != 0) {
            model.addAttribute("roles", roles);
        }
        return new ModelAndView("admin/add_edit_user", "user", userById);
    }

    @RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
    public String userDelete(ModelMap model, @PathVariable long id) {
        userService.remove(userService.findById(id));
        return "redirect:/admin";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("login", getPrincipal());
        return "403";
    }

    private String getPrincipal() {

        String userName = null;
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            AuthenticationTrustResolver authenticationTrustResolver = new AuthenticationTrustResolverImpl();
            if (authenticationTrustResolver.isAnonymous(
                    SecurityContextHolder.getContext().getAuthentication())) {
                return "ANONYMOUS";
            }
        }
        return userName;
    }
}
