package faust.controller;

import faust.entity.User;
import faust.service.GroupService;
import faust.service.UserService;
import faust.validate.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final GroupService groupService;
    @Qualifier("passwordValidation")
    private final PasswordValidator passwordValidator;

    @Autowired
    public UserController(UserService userService, GroupService groupService, PasswordValidator passwordValidator) {
        this.userService = userService;
        this.groupService = groupService;
        this.passwordValidator = passwordValidator;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("users", users);

        return "user_list";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        User user = new User();
        user.setEditingPassword(true);
        model.addAttribute("user", user);

        return "edit_user";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("userId") int userId, Model model) {
        User user = userService.getUser(userId);
        user.setEditingPassword(false);
        model.addAttribute("user", user);

        return "edit_user";

    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam("userId") int userId, Model model) {
        User user = userService.getUser(userId);
        user.setEditingPassword(true);
        user.setCurrentPasswordHash(user.getPassword());
        model.addAttribute("user", user);

        return "user_change_password";
    }

    @PostMapping("/saveNewPassword")
    public String saveNewPassword(@ModelAttribute("user") User user, BindingResult bindingResult) {
        passwordValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user_change_password";
        }
        userService.saveNewPassword(user);
        return "redirect:/user/list";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }
        passwordValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);

        return "redirect:/user/list";
    }


    @ModelAttribute("existingGroups")
    public List<String> getExistingGroups() {
        return groupService.getGroupNames();
    }
}
