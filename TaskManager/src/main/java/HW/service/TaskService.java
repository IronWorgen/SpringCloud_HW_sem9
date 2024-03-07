package HW.service;

import HW.aspect.TrackUserAction;
import HW.data.task.Task;
import HW.data.task.TaskStatus;
import HW.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    // @PostMapping("/task/add")
    @TrackUserAction
    public   void save(Task task){
        taskRepository.save(task);
    }
    // @GetMapping("/task/")
    @TrackUserAction
    public List<Task> findAll(){
        List<Task> result = taskRepository.findAll();
        return result;
    }

    public List<Task> findAllByStatus(TaskStatus taskStatus){
        return taskRepository.findAllZadachiByStatus(taskStatus);
    }

    public Task findTaskById(Long id){
        return  taskRepository.findTaskById(id);
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }
}
