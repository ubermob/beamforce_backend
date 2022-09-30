package ru.beamforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beamforce.entity.FeedbackEntity;

/**
 * @author Andrey Korneychuk on 27-Apr-22
 * @version 1.0
 */
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
}