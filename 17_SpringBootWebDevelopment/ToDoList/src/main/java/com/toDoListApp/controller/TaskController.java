package com.toDoListApp.controller;

import com.toDoListApp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.toDoListApp.model.Task;


@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String showAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksList());
        return "tasks/index";
    }

    @GetMapping("/{id}")
    public String showTaskById(@PathVariable("id") int taskID, Model model) {
        model.addAttribute("task", taskService.getTask(taskID));
        return "tasks/show";
    }

    @GetMapping("/new")
    public String addNewTask(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/new";
    }

    @PostMapping("/new")
    public String addNewTaskSubmit(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable("id") int taskID, Model model) {
        Task editableTask = taskService.getTask(taskID);
        model.addAttribute("task", editableTask);
        return "/tasks/edit-form";
    }

    @PostMapping("/edit")
    public String editTask(@ModelAttribute Task editableTask) {
        taskService.saveTask(editableTask);
        return "redirect:/" + editableTask.getId();
    }

    @GetMapping("/del/all")
    public String deleteAll() {
        taskService.deleteAllTasks();
        return "redirect:/";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") int taskID) {
        taskService.deleteTask(taskID);
        return "redirect:/";
    }

    @GetMapping("/all/set-done")
    public String setDoneStatusToAllTasks() {
        taskService.setDoneStatusToAllTasks();
        return "redirect:/";
    }

    @GetMapping("/all/set-not-done")
    public String setNotDoneStatusToAllTasks() {
        taskService.setNotDoneStatusToAllTasks();
        return "redirect:/";
    }

    @GetMapping("/{id}/set-done")
    public String setDoneToTaskById(@PathVariable("id") int taskID) {
        taskService.setDoneStatusToTaskById(taskID);
        return "redirect:/" + taskID;
    }

    @GetMapping("/{id}/set-not-done")
    public String setNotDoneToTaskById(@PathVariable("id") int taskID) {
        taskService.setNotDoneStatusToTaskById(taskID);
        return "redirect:/" + taskID;
    }

    @GetMapping("/done")
    public String getAllDoneTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksByDoneStatus(true));
        return "tasks/index";
    }

    @GetMapping("/not-done")
    public String getAllNotDoneTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasksByDoneStatus(false));
        return "tasks/index";
    }
}
