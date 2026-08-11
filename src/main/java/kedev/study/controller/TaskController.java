package kedev.study.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kedev.study.dto.CreateTaskDto;
import kedev.study.model.Task;
import kedev.study.model.User;
import kedev.study.service.TaskService;
import kedev.study.service.UserService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public Page<Task> fetchAllTasks(Pageable pageable) {
        return taskService.fetchTasks(pageable);
    }

    @GetMapping("/search")
    public List<Task> fetchTasksBySearch(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String keyword
    ) {
        if (completed != null && keyword != null) {
            return taskService.fetchTasksByStatusAndKeyword(completed, keyword);
        }
        if (completed != null) {
            return taskService.fetchTasksByStatus(completed);
        }
        return List.of();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> fetchTaskById(@PathVariable Long id) {
        return taskService.fetchTaskById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task saveTask(@Valid @RequestBody CreateTaskDto createTaskDto) {
        Task task = new Task();
        task.setTitle(createTaskDto.getTitle());
        task.setCompleted(createTaskDto.isCompleted());
        
        if (createTaskDto.getUserId() != null) {
            User user = userService.fetchUserById(createTaskDto.getUserId()).orElse(null);
            task.setUser(user);
        }
        
        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody CreateTaskDto createTaskDto) {
        Task task = new Task();
        task.setTitle(createTaskDto.getTitle());
        task.setCompleted(createTaskDto.isCompleted());
        
        if (createTaskDto.getUserId() != null) {
            User user = userService.fetchUserById(createTaskDto.getUserId()).orElse(null);
            task.setUser(user);
        }
        
        return taskService.updateTask(id, task)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void executeTaskDeletion(@PathVariable Long id) {
        taskService.executeTaskDeletion(id);
    }
}
