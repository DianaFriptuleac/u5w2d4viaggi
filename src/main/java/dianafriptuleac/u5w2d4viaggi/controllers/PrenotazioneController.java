package dianafriptuleac.u5w2d4viaggi.controllers;

import dianafriptuleac.u5w2d4viaggi.entities.Prenotazione;
import dianafriptuleac.u5w2d4viaggi.exceptions.BadRequestException;
import dianafriptuleac.u5w2d4viaggi.payloads.PrenotazioneDTO;
import dianafriptuleac.u5w2d4viaggi.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioniService prenotazioniService;


    @GetMapping
    public Page<Prenotazione> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        return this.prenotazioniService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.prenotazioniService.savePrenotazione(body);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione findById(@PathVariable Long prenotazioneId) {
        return this.prenotazioniService.findById(prenotazioneId);
    }


    @PutMapping("/{prenotazioneId}")
    public Prenotazione findByIdAndUpdate(@PathVariable Long prenotazioneId, @RequestBody @Validated PrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.prenotazioniService.updatePrenotazione(prenotazioneId, body);
    }


    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long prenotazioneId) {
        this.prenotazioniService.deletePrenotazioni(prenotazioneId);
    }
}

