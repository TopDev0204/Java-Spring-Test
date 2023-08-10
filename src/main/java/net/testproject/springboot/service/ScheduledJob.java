package net.testproject.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import net.testproject.springboot.model.Task;
import net.testproject.springboot.repository.TaskRepository;

@Component
public class ScheduledJob {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledJob.class);

	@Autowired
	private TaskRepository taskRepository;

    private LocalDateTime currentDateTime;
    private List<Task> tasks;

    @Scheduled(fixedDelay=30000) // every 30 seconds
    public void runJob() {
        logger.info("Scheduled job is running!");

        currentDateTime = LocalDateTime.now();
        tasks = taskRepository.findAll();
        
        for (Task task : tasks) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(task.getDateTime(), formatter);

            int isPassed = dateTime.compareTo(currentDateTime);

            if(task.getStatus() == false && isPassed > 0){
                task.setStatus(true);
                taskRepository.save(task);
                System.out.println(task.getName() + " " + task.getDescription() + " "  + task.getDateTime() + " "  + task.getStatus());
            }
        }
    }
}
