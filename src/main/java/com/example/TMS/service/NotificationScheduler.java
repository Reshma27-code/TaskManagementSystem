package com.example.TMS.service;

import com.example.TMS.JPARepositories.TaskRepository;
import com.example.TMS.JPARepositories.UserRepository;
import com.example.TMS.entities.Task;
import com.example.TMS.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationScheduler {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    // Scheduler method that runs every hour
    @Scheduled(cron = "0 0 * * * ?") // Runs every hour
    public void checkTaskDeadlines() {
        List<Task> tasks = taskRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Task task : tasks) {
            if (task.getDeadline() != null && task.getDeadline().minusDays(1).isEqual(today)) {
                User user = task.getUser();
                String email = user.getEmail(); // Assuming email is username here
                System.err.println(email);
                String subject = "Reminder: Task Deadline Approaching";
                String body = "Dear " + user.getUsername() + ",\n\n"
                        + "Your task \"" + task.getTitle() + "\" is due tomorrow. Please complete it before the deadline.\n\n"
                        + "Best Regards,\nTask Management System";

                // Send notification
                notificationService.sendEmail(email, subject, body);
            }
        }
    }
}

