package com.mindbowser.springsecurity.persistance.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindbowser.springsecurity.business.util.MyEnumerations.ERole;
import com.mindbowser.springsecurity.persistance.entity.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
	Optional<Role> findByName(ERole name);
}
