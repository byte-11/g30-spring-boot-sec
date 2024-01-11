package uz.pdp.g30spingbootsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g30spingbootsecurity.domain.Role;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}