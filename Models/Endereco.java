public class Endereco {
    private int numero;
    private String rua;
    private String cep;

    public Endereco(int numero, String rua, String cep) {
        this.numero = numero;
        this.rua = rua;
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int novoNumero) {
        this.numero = novoNumero;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String novaRua) {
        this.rua = novaRua;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String novoCep) {
        this.cep = novoCep;
    }

}