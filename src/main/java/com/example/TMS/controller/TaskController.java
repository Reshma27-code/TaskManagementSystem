package com.example.TMS.controller;

import com.example.TMS.JPARepositories.TaskRepository;
import com.example.TMS.JPARepositories.UserRepository;
import com.example.TMS.entities.Task;
import com.example.TMS.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Task> getTasks(Principal principal) {
        System.err.println(principal);
        User user = userRepository.findByUsername(principal.getName()).get();
        return taskRepository.findByUserId(user.getId());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task, Principal principal) {
        System.err.println(principal.getName());
        User user = userRepository.findByUsername(principal.getName()).get();
        System.err.println(user);
        task.setUser(user);
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask, Principal principal) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return ResponseEntity.ok("Task deleted");
    }
}
