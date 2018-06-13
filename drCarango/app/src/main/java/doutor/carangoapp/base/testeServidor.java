package doutor.carangoapp.base;

import java.util.ArrayList;

public class testeServidor {

    private ArrayList<BaseEstabelecimento> estabelecimentos;

    public ArrayList<BaseEstabelecimento> getEstabelecimentos() {
        return estabelecimentos;
    }

    public void setEstabelecimentos(ArrayList<BaseEstabelecimento> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    public testeServidor(){

    }

    public void SetupEstabelecimentoTest() {

        ArrayList<BaseEstabelecimento> listaOficinas=new ArrayList<BaseEstabelecimento>();
        BaseEstabelecimento oficina1=new BaseEstabelecimento();
        oficina1.setNome("Oficina S. João");
        oficina1.setRankingServico(4.98);
        oficina1.setBairro("Dois Unidos");
        oficina1.setCep("52-000000");
        oficina1.setCidade("Recife");
        oficina1.setComplemento("bloco A");
        oficina1.setEstado("PE");
        oficina1.setNumero("255");
        oficina1.setRua("Avenida Beberibe");
        oficina1.setTelefone("81 3555 3555");


        BaseEstabelecimento oficina2=new BaseEstabelecimento();
        oficina2.setNome("Central Auto Center");
        oficina2.setRankingServico(4.84);
        oficina2.setBairro("Dois Unidos");
        oficina2.setCep("52-000000");
        oficina2.setCidade("Recife");
        oficina2.setComplemento("bloco A");
        oficina2.setEstado("PE");
        oficina2.setNumero("255");
        oficina2.setRua("Avenida Beberibe");
        oficina2.setTelefone("81 3555 3555");
        BaseEstabelecimento oficina3=new BaseEstabelecimento();
        oficina3.setNome("Perfet Tecnologia");
        oficina3.setRankingServico(4.75);
        oficina3.setBairro("Dois Unidos");
        oficina3.setCep("52-000000");
        oficina3.setCidade("Recife");
        oficina3.setComplemento("bloco A");
        oficina3.setEstado("PE");
        oficina3.setNumero("255");
        oficina3.setRua("Avenida Beberibe");
        oficina3.setTelefone("81 3555 3555");
        BaseEstabelecimento oficina4=new BaseEstabelecimento();
        oficina4.setNome("Oficina Calango");
        oficina4.setRankingServico(4.24);
        oficina4.setBairro("Dois Unidos");
        oficina4.setCep("52-000000");
        oficina4.setCidade("Recife");
        oficina4.setComplemento("bloco A");
        oficina4.setEstado("PE");
        oficina4.setNumero("255");
        oficina4.setRua("Avenida Beberibe");
        oficina4.setTelefone("81 3555 3555");
        BaseEstabelecimento oficina5=new BaseEstabelecimento();
        oficina5.setNome("Oficina do Amigo");
        oficina5.setRankingServico(3.86);
        oficina5.setBairro("Dois Unidos");
        oficina5.setCep("52-000000");
        oficina5.setCidade("Recife");
        oficina5.setComplemento("bloco A");
        oficina5.setEstado("PE");
        oficina5.setNumero("255");
        oficina5.setRua("Avenida Beberibe");
        oficina5.setTelefone("81 3555 3555");
        BaseEstabelecimento oficina6=new BaseEstabelecimento();
        oficina6.setNome("Oficina do Manuca");
        oficina6.setRankingServico(3.80);
        oficina6.setBairro("Dois Unidos");
        oficina6.setCep("52-000000");
        oficina6.setCidade("Recife");
        oficina6.setComplemento("bloco A");
        oficina6.setEstado("PE");
        oficina6.setNumero("255");
        oficina6.setRua("Avenida Beberibe");
        oficina6.setTelefone("81 3555 3555");
        BaseEstabelecimento oficina7=new BaseEstabelecimento();
        oficina7.setNome("Lojão do Petróleo");
        oficina7.setRankingServico(3.23);
        oficina6.setBairro("Dois Unidos");
        oficina6.setCep("52-000000");
        oficina6.setCidade("Recife");
        oficina6.setComplemento("bloco A");
        oficina6.setEstado("PE");
        oficina6.setNumero("255");
        oficina6.setRua("Avenida Beberibe");
        oficina6.setTelefone("81 3555 3555");
        BaseEstabelecimento oficina8=new BaseEstabelecimento();
        oficina8.setNome("Biauto");
        oficina8.setRankingServico(2.45);
        oficina8.setBairro("Dois Unidos");
        oficina8.setCep("52-000000");
        oficina8.setCidade("Recife");
        oficina8.setComplemento("bloco A");
        oficina8.setEstado("PE");
        oficina8.setNumero("255");
        oficina8.setRua("Avenida Beberibe");
        oficina8.setTelefone("81 3555 3555");
        BaseEstabelecimento oficina9=new BaseEstabelecimento();
        oficina9.setNome("Oficina Auto Center Casa Forte");
        oficina9.setRankingServico(2.33);
        oficina9.setBairro("Dois Unidos");
        oficina9.setCep("52-000000");
        oficina9.setCidade("Recife");
        oficina9.setComplemento("bloco A");
        oficina9.setEstado("PE");
        oficina9.setNumero("255");
        oficina9.setRua("Avenida Beberibe");
        oficina9.setTelefone("81 3555 3555");
        listaOficinas.add(oficina1);
        listaOficinas.add(oficina2);
        listaOficinas.add(oficina3);
        listaOficinas.add(oficina4);
        listaOficinas.add(oficina5);
        listaOficinas.add(oficina6);
        listaOficinas.add(oficina7);
        listaOficinas.add(oficina8);
        listaOficinas.add(oficina9);


        this.estabelecimentos=listaOficinas;
    }
}
