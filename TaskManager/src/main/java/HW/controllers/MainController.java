package HW.controllers;

import HW.data.task.Task;
import HW.data.task.TaskStatus;
import HW.repository.TaskRepository;
import HW.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("task")
public class MainController {
    private final TaskService taskService;


    @PostMapping("/add")
    public String addTask(@RequestBody Task task) {
        task.setTimeOfCreation(LocalDateTime.now());
        taskService.save(task);
        return "добавлена задача:\t" + task;
    }


    @GetMapping("/")
    public List<Task> findAllTasks() {
        List<Task> result = taskService.findAll();
        return result;
    }


    @GetMapping("/{taskStatus}")
    public List<Task> findByStatus(@PathVariable TaskStatus taskStatus) {
        return taskService.findAllByStatus(taskStatus);
    }


    @PutMapping("/update/{id}")
    public Task updateTaskStatus(@PathVariable long id, @RequestBody Task task) {
        Task taskFromDB = taskService.findTaskById(id);
        taskFromDB.setStatus(task.getStatus());
        taskService.save(taskFromDB);
        return taskFromDB;
    }


    @DeleteMapping("/delete/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

}
