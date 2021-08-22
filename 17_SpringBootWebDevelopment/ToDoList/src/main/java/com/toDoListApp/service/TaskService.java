package com.toDoListApp.service;

import com.toDoListApp.model.Task;
import com.toDoListApp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasksList() {
        List<Task> allTasksList = new ArrayList<>();
        Iterable<Task> tasksIterable = taskRepository.findAll();
        tasksIterable.forEach(allTasksList::add);
        return allTasksList;
    }

    public void saveTask(Task task) {
        if (task.getDeadlineDate() == null || task.getDeadlineDate().isBefore(task.getCreateDate())) {
            task.setDeadlineDate(task.getCreateDate());
        }
        taskRepository.save(task);
    }

    public Task getTask(int taskID) {
        return taskRepository.findById(taskID).orElse(null);
    }

    public void deleteTask(int taskID) {
        if (taskRepository.existsById(taskID)) {
            taskRepository.deleteById(taskID);
        }
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public void setDoneStatusToAllTasks() {
        Iterable<Task> tasksIterable = taskRepository.findAll();
        tasksIterable.forEach(task -> {
            task.setDoneStatus(true);
            taskRepository.save(task);
        });
    }

    public void setNotDoneStatusToAllTasks() {
        Iterable<Task> tasksIterable = taskRepository.findAll();
        tasksIterable.forEach(task -> {
            task.setDoneStatus(false);
            taskRepository.save(task);
        });
    }

    public void setDoneStatusToTaskById(int taskID) {
        if (taskRepository.existsById(taskID)) {
            Task settableTask = taskRepository.findById(taskID).get();
            settableTask.setDoneStatus(true);
            taskRepository.save(settableTask);
        }
    }

    public void setNotDoneStatusToTaskById(int taskID) {
        if (taskRepository.existsById(taskID)) {
            Task settableTask = taskRepository.findById(taskID).get();
            settableTask.setDoneStatus(false);
            taskRepository.save(settableTask);
        }
    }

    public List<Task> getAllTasksByDoneStatus(boolean doneStatus) {
        return
                taskRepository.findAllByDoneStatus(doneStatus);
    }
}
