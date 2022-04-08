package ru.beamforce.tmp;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrey Korneychuk on 08-Apr-22
 * @version 1.0
 */
public interface GridRepository extends JpaRepository<GridEntity, Long> {
}