package ru.pflb.scores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pflb.scores.dto.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserId(int userId);
    List<User> findByLevel(int level);
    User findByUserIdAndLevel(int userId, int level);
}
