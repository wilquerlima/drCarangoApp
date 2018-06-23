package doutor.carangoapp.base;

public class BaseServicosRealizados {

    private String nomeusuario;
    private String dataServico;
    private String nomeOficina;
    private String notaServico;
    private String categoriaServico;

    public String getCategoriaServico() {
        return categoriaServico;
    }

    public void setCategoriaServico(String categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    public String getNomeusuario() {
        return nomeusuario;
    }

    public void setNomeusuario(String nomeusuario) {
        this.nomeusuario = nomeusuario;
    }

    public String getDataServico() {
        return dataServico;
    }

    public void setDataServico(String dataServico) {
        this.dataServico = dataServico;
    }

    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }

    public String getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(String notaServico) {
        this.notaServico = notaServico;
    }
}
