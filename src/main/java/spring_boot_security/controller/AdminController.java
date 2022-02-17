package spring_boot_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spring_boot_security.model.Role;
import spring_boot_security.model.User;
import spring_boot_security.service.RoleService;
import spring_boot_security.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @RequestMapping(value = "/admin/saveUser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(required = false) String roleAdmin) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByName("USER"));
        if (roleAdmin != null) {
            roles.add(roleService.getRoleByName("ADMIN"));}
        userService.saveUser(user, roles);
        return "redirect:/admin/";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public String updateUser(@RequestParam("userId") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "new";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
