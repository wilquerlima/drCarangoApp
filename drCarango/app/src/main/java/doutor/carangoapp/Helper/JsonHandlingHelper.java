package doutor.carangoapp.Helper;

import org.json.JSONObject;

import doutor.carangoapp.base.BaseEstabelecimento;

public class JsonHandlingHelper {

    public static BaseEstabelecimento getOficinaFromJson(String json){

        try{

            JSONObject oficina=new JSONObject(json);
            BaseEstabelecimento estabelecimento=new BaseEstabelecimento();
            estabelecimento.setId(oficina.getInt("id"));
            estabelecimento.setNome(oficina.getString("nome"));
            estabelecimento.setEmail(oficina.getString("email"));
            estabelecimento.setRua(oficina.getString("rua"));
            estabelecimento.setNumero(oficina.getString("numero"));
            estabelecimento.setBairro(oficina.getString("bairro"));
            estabelecimento.setCidade(oficina.getString("cidade"));
            estabelecimento.setCep(oficina.getString("cep"));
            estabelecimento.setEstado(oficina.getString("estado"));
            estabelecimento.setPais(oficina.getString("pais"));
            estabelecimento.setComplemento(oficina.getString("complemento"));
            estabelecimento.setTelefone(oficina.getString("telefone1"));
            estabelecimento.setRankingAgilidade(Double.parseDouble(oficina.getString("rankingAgilidade")));
            estabelecimento.setRankingCustoBeneficio(Double.parseDouble(oficina.getString("rankingCustoBeneficio")));
            estabelecimento.setRankingServico(Double.parseDouble(oficina.getString("rankingServico")));
            estabelecimento.setCredenciada(Integer.parseInt(oficina.getString("credencia")));

            return estabelecimento;



        }catch (Exception e ){
            return null;
        }
    }
}

