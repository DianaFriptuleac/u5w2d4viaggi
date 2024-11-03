package dianafriptuleac.u5w2d4viaggi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("II record con l'id: " + id + " non è stato trovato!");
    }
}
