package doutor.carangoapp.controller;

import android.app.Activity;
import android.content.ContentValues;

import java.io.IOException;
import java.util.Calendar;

import doutor.carangoapp.R;
import doutor.carangoapp.controller.okHttpController.OkHttpController;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by wilqu on 25/05/2018.
 */

public class WebServiceController {


    public static String recuperarInformacoesOficina(int id) throws IOException{

        String url="http://doutorcarango.kinghost.net:21015/estabelecimentos/procurar/id="+id+"&nome=*&email=*";

        return OkHttpController.getHttp(url, null);
    }

    public static String recuperarListaOficinasCategoria(String categoria, String ranking) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/estabelecimentos/filter/categoria=" + categoria + "&ranking=" + ranking;

        return OkHttpController.getHttp(url, null);
    }

    public static String cadastrar(String nome, String email, String senha, String telefone) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/usuarios/cadastrar";

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("email", email);
        contentValues.put("senha", senha);
        contentValues.put("telefone1", telefone);

        return OkHttpController.postHttp(url, contentValues);
    }

    public static String login(String email, String senha) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/login";

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("senha", senha);

        return OkHttpController.postHttp(url, contentValues);
    }

    public static String enviarAvaliacaoCusto(int IdOficina,int avaliacaoCusto,int idUsuario)throws IOException{
        String url="http://doutorcarango.kinghost.net:21015/avaliacoes/custo_beneficio/cadastrar";
        ContentValues contentValues=new ContentValues();
        contentValues.put("id_estabelecimentos",IdOficina);
        contentValues.put("id_usuarios",idUsuario);
        contentValues.put("nota",avaliacaoCusto);

        return (String) OkHttpController.postHttp(url,contentValues);
    }
    public static String enviarAvaliacaoAgilidade(int IdOficina,int avaliacaoAgilidade,int idUsuario)throws IOException{
        String url="http://doutorcarango.kinghost.net:21015/avaliacoes/agilidade/cadastrar";
        ContentValues contentValues=new ContentValues();
        contentValues.put("id_estabelecimentos",IdOficina);
        contentValues.put("id_usuarios",idUsuario);
        contentValues.put("nota",avaliacaoAgilidade);

        return (String) OkHttpController.postHttp(url,contentValues);
    }

    public static String enviarAvaliacaoServico(int IdOficina,int avaliacaoServico,int idUsuario)throws IOException{
        String url="http://doutorcarango.kinghost.net:21015/avaliacoes/servico/cadastrar";
        ContentValues contentValues=new ContentValues();
        contentValues.put("id_estabelecimentos",IdOficina);
        contentValues.put("id_usuarios",idUsuario);
        contentValues.put("nota",avaliacaoServico);

        return (String) OkHttpController.postHttp(url,contentValues);
    }
    public static String enviarComentario(int IdOficina,String comentario,int idUsuario)throws IOException{
        String url="http://doutorcarango.kinghost.net:21015/funcoes/comentarios/cadastrar";
        ContentValues contentValues=new ContentValues();
        contentValues.put("id_estabelecimentos",IdOficina);
        contentValues.put("id_usuarios",idUsuario);
        contentValues.put("comentario",comentario);
        contentValues.put("data_hora", Calendar.getInstance().getTime().toString());

        return (String) OkHttpController.postHttp(url,contentValues);
    }

    public static String recuperarComentariosParaOficina(int id)throws IOException {
        String url="http://doutorcarango.kinghost.net:21015/estabelecimentos/comentarios/id_estabelecimentos="+id;
        return OkHttpController.getHttp(url,null);
    }

    public static String recuperarNomedeUsuario(int idUsuario)throws IOException  {
        String url="http://doutorcarango.kinghost.net:21015/estabelecimentos/comentarios/id_estabelecimentos=";
        return OkHttpController.getHttp(url,null);
    }
}
