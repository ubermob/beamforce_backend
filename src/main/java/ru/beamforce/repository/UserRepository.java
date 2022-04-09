package ru.beamforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beamforce.entity.UserEntity;

/**
 * @author Andrey Korneychuk on 04-Feb-22
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByName(String name);
}