package demo.devsu.repositories;

import demo.devsu.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepo extends JpaRepository<Movimiento, Integer> {
}
