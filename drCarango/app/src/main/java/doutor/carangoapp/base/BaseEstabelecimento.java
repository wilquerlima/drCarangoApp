package doutor.carangoapp.base;


/**
 * Created by wilqu on 25/05/2018.
 */

public class BaseEstabelecimento {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String login;
    private String senha;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
    private String complemento;
    private String telefone;
    private double rankingAgilidade;
    private double rankingCustoBeneficio;
    private double rankingServico;
    private boolean isParceira;

    public boolean isParceira() {
        return isParceira;
    }

    public void setParceira(boolean parceira) {
        isParceira = parceira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public double getRankingAgilidade() {
        return rankingAgilidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setRankingAgilidade(double rankingAgilidade) {
        this.rankingAgilidade = rankingAgilidade;
    }

    public double getRankingCustoBeneficio() {
        return rankingCustoBeneficio;
    }

    public void setRankingCustoBeneficio(double rankingCustoBeneficio) {
        this.rankingCustoBeneficio = rankingCustoBeneficio;
    }

    public double getRankingServico() {
        return rankingServico;
    }

    public void setRankingServico(double rankingServico) {
        this.rankingServico = rankingServico;
    }
}
