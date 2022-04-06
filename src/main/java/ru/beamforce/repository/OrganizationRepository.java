package ru.beamforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beamforce.entity.Organization;

/**
 * @author Andrey Korneychuk on 07-Apr-22
 * @version 1.0
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}