package dianafriptuleac.u5w2d4viaggi.repositories;

import dianafriptuleac.u5w2d4viaggi.entities.Dipendente;
import dianafriptuleac.u5w2d4viaggi.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Optional<Prenotazione> findByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);
}
