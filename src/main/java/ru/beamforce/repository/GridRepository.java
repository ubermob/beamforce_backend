package ru.beamforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beamforce.entity.GridEntity;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
public interface GridRepository extends JpaRepository<GridEntity, Long> {
}