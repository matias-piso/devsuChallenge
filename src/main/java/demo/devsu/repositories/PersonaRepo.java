package demo.devsu.repositories;

import demo.devsu.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepo extends JpaRepository<Persona, Integer> {
}
