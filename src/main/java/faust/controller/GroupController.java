package faust.controller;


import faust.entity.Group;
import faust.service.GroupService;
import faust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    private final UserService userService;
    private final GroupService groupService;

    @Autowired
    public GroupController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    @GetMapping("/list")
    public String listGroups(Model model) {

        List<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "group_list";
    }

    @GetMapping("/new")
    public String newGroupForm(Model model) {

        Group group = new Group();
        model.addAttribute("group", group);

        return "edit_group";
    }

    @GetMapping("/edit")
    public String editGroupForm(@RequestParam("groupId") int groupId, Model model) {
        Group group = groupService.getGroup(groupId);
        model.addAttribute("group", group);

        return "edit_group";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@Valid @ModelAttribute("group") Group group, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit_group";
        }
        groupService.saveGroup(group);
        return "redirect:/group/list";
    }

    @GetMapping("/delete")
    public String deleteGroup(@RequestParam("groupId") int groupId) {
        groupService.deleteGroup(groupId);

        return "redirect:/group/list";
    }


    @ModelAttribute("existingUsers")
    public List<String> getExistingUsers() {
        return userService.getUserNames();
    }


}
