package doutor.carangoapp.base;

public class BaseComentario {

    private int mIdUsuario;
    private int mIdOficina;
    private String mNomeUsuario;
    private String mConteudo;

    public BaseComentario(int idUsuario,int idOficina,String nomeUsuario,String conteudo){
        this.mIdOficina=idOficina;
        this.mIdUsuario=idUsuario;
        this.mNomeUsuario=nomeUsuario;
        this.mConteudo=conteudo;
    }

    public int getmIdUsuario() {
        return mIdUsuario;
    }

    public void setmIdUsuario(int mIdUsuario) {
        this.mIdUsuario = mIdUsuario;
    }

    public int getmIdOficina() {
        return mIdOficina;
    }

    public void setmIdOficina(int mIdOficina) {
        this.mIdOficina = mIdOficina;
    }

    public String getmNomeUsuario() {
        return mNomeUsuario;
    }

    public void setmNomeUsuario(String mNomeUsuario) {
        this.mNomeUsuario = mNomeUsuario;
    }

    public String getmConteudo() {
        return mConteudo;
    }

    public void setmConteudo(String mConteudo) {
        this.mConteudo = mConteudo;
    }
}
