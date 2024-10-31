package dianafriptuleac.u5w2d4viaggi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private LocalDate dataRichiesta;
    private String note;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    public Prenotazione(LocalDate dataRichiesta, String note, Dipendente dipendente, Viaggio viaggio) {
        this.dataRichiesta = LocalDate.now();
        this.note = note;
        this.dipendente = dipendente;
        this.viaggio = viaggio;
    }
}
