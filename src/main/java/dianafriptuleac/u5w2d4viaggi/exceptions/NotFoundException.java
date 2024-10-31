package dianafriptuleac.u5w2d4viaggi.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {

        super("IIl record con l'id: " + id + " non è stato trovato!");
    }
}
