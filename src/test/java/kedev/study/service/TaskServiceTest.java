package kedev.study.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import kedev.study.model.Task;
import kedev.study.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task sampleTask;

    @BeforeEach
    void setUp() {
        sampleTask = new Task(1L, "Test Task", false);
    }

    @Test
    void shouldReturnTaskWhenFoundById() {
        // Arrange
        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask));

        // Act
        Optional<Task> result = taskService.fetchTaskById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getTitle());
    }
}
