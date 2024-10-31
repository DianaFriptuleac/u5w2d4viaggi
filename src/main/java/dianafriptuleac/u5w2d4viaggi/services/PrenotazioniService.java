package dianafriptuleac.u5w2d4viaggi.services;

import dianafriptuleac.u5w2d4viaggi.entities.Dipendente;
import dianafriptuleac.u5w2d4viaggi.entities.Prenotazione;
import dianafriptuleac.u5w2d4viaggi.entities.Viaggio;
import dianafriptuleac.u5w2d4viaggi.exceptions.BadRequestException;
import dianafriptuleac.u5w2d4viaggi.exceptions.NotFoundException;
import dianafriptuleac.u5w2d4viaggi.payloads.PrenotazioneDTO;
import dianafriptuleac.u5w2d4viaggi.repositories.DipendenteRepository;
import dianafriptuleac.u5w2d4viaggi.repositories.PrenotazioneRepository;
import dianafriptuleac.u5w2d4viaggi.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Prenotazione savePrenotazione(PrenotazioneDTO body) {
        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new NotFoundException(body.dipendenteId()));

        Viaggio viaggio = viaggioRepository.findById(body.viaggioId())
                .orElseThrow(() -> new NotFoundException(body.viaggioId()));
        prenotazioneRepository.findByDipendenteAndDataRichiesta(dipendente, viaggio.getDataViaggio())
                .ifPresent(prenotazione -> {
                    throw new BadRequestException("Il dipendente " + dipendente.getNome() + " " + dipendente.getCognome() +
                            " ha già una prenotazione per la data " + viaggio.getDataViaggio());
                });

        Prenotazione prenotazione = new Prenotazione(body.note(), dipendente, viaggio);
        return prenotazioneRepository.save(prenotazione);
    }


    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);
    }


    public Prenotazione findById(long prenotazioneId) {
        return this.prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public Prenotazione updatePrenotazione(long prenotazioneId, PrenotazioneDTO body) {
        Prenotazione prenotazione = findById(prenotazioneId);

        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new NotFoundException(body.dipendenteId()));

        Viaggio viaggio = viaggioRepository.findById(body.viaggioId())
                .orElseThrow(() -> new NotFoundException(body.viaggioId()));

        prenotazione.setNote(body.note());
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);

        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazioni(long prenotazioneId) {
        Prenotazione prenotazione = findById(prenotazioneId);
        prenotazioneRepository.delete(prenotazione);
    }


}
