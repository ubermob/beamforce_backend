package ru.beamforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beamforce.entity.User;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}