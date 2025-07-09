package outsera.prova.exceptions;

public class ResourceWithProblem extends RuntimeException {
    public ResourceWithProblem(String mensagem) {
        super(mensagem);
    }
}