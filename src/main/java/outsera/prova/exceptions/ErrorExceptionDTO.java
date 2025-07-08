package outsera.prova.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorExceptionDTO {
    private LocalDateTime timestamp;
    private int status;
    private String erro;
    private String mensagem;
    private String caminho;

    public ErrorExceptionDTO(HttpStatus status, String mensagem, String caminho) {
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.erro = status.getReasonPhrase();
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public ErrorExceptionDTO() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}