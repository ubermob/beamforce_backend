package ru.beamforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beamforce.entity.ModelEntity;

/**
 * @author Andrey Korneychuk on 22-Apr-22
 * @version 1.0
 */
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
}