package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public String printUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping()
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-create";
        }
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String editUserForm(@PathVariable("id") Long id,
                               Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }

    @PatchMapping("/user-update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-update";
        }
        userService.createUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
