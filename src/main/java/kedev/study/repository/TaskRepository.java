package kedev.study.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import kedev.study.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // 1. Consulta derivada por nombre de método (Derived Query Method)
    List<Task> findByCompleted(boolean completed);

    // 2. Consulta personalizada usando JPQL (Java Persistence Query Language)
    @Query("SELECT t FROM Task t WHERE t.completed = :completed AND LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> fetchTasksByStatusAndKeyword(@Param("completed") boolean completed, @Param("keyword") String keyword);
}
