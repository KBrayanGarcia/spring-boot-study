package kedev.study.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import kedev.study.model.Task;
import kedev.study.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> fetchTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Optional<Task> fetchTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setCompleted(updatedTask.isCompleted());
                    return taskRepository.save(existingTask);
                });
    }

    public void executeTaskDeletion(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> fetchTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    public List<Task> fetchTasksByStatusAndKeyword(boolean completed, String keyword) {
        return taskRepository.fetchTasksByStatusAndKeyword(completed, keyword);
    }
}
