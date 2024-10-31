package dianafriptuleac.u5w2d4viaggi.controllers;

import dianafriptuleac.u5w2d4viaggi.entities.Dipendente;
import dianafriptuleac.u5w2d4viaggi.exceptions.BadRequestException;
import dianafriptuleac.u5w2d4viaggi.payloads.DipendenteDTO;
import dianafriptuleac.u5w2d4viaggi.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public Page<Dipendente> findAllDipendenti(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8") int size,
                                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.dipendenteService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.dipendenteService.saveDipendente(body);

    }
}
