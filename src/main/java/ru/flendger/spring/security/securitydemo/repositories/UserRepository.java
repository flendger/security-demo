package ru.flendger.spring.security.securitydemo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.flendger.spring.security.securitydemo.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
}
