package kedev.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kedev.study.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
