package ge.find.findjob.repo;

import ge.find.findjob.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllByActiveIsFalseAndCreatedDateBefore(Instant dateTime);

}
