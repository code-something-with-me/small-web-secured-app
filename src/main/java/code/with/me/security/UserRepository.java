package code.with.me.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author andong@xiaomalixing.com
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
