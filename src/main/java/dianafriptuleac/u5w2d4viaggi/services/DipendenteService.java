package dianafriptuleac.u5w2d4viaggi.services;

import dianafriptuleac.u5w2d4viaggi.entities.Dipendente;
import dianafriptuleac.u5w2d4viaggi.exceptions.BadRequestException;
import dianafriptuleac.u5w2d4viaggi.payloads.DipendenteDTO;
import dianafriptuleac.u5w2d4viaggi.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente saveDipendente(DipendenteDTO body) {
        this.dipendenteRepository.findByEmail(body.email()).ifPresent(dipendente -> {
            throw new BadRequestException("Email " + body.email() + " già in uso!");
        });

        this.dipendenteRepository.findByUsername(body.username()).ifPresent(dipendente -> {
            throw new BadRequestException("Username " + body.username() + " già in uso!");
        });

        Dipendente newDipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email(),
                "https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        return this.dipendenteRepository.save(newDipendente);
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }
}
