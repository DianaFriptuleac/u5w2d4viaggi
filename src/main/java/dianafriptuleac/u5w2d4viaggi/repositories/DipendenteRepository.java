package dianafriptuleac.u5w2d4viaggi.repositories;

import dianafriptuleac.u5w2d4viaggi.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);

    Optional<Dipendente> findByUsername(String username);

}
