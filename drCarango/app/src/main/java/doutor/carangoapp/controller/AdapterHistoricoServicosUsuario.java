package doutor.carangoapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseServicosRealizados;

public class AdapterHistoricoServicosUsuario  extends RecyclerView.Adapter<AdapterHistoricoServicosUsuario.ViewHolderHistoricoServicos>{

    private ArrayList<BaseServicosRealizados> servicosRealizados;

    public ArrayList<BaseServicosRealizados> getServicosRealizados() {
        return servicosRealizados;
    }

    public void setServicosRealizados(ArrayList<BaseServicosRealizados> servicosRealizados) {
        this.servicosRealizados = servicosRealizados;
    }

    @Override

    public ViewHolderHistoricoServicos onCreateViewHolder(ViewGroup parent, int viewType) {
        View result= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_historico_servicos,parent,false);
        return new ViewHolderHistoricoServicos(result);
    }

    @Override
    public void onBindViewHolder(ViewHolderHistoricoServicos holder, int position) {
            BaseServicosRealizados servico=servicosRealizados.get(position);
            holder.mNotaServico.setText(servico.getNotaServico());
            holder.mDataServico.setText(servico.getDataServico());
            holder.mNomeOficina.setText(servico.getNomeOficina());
            holder.mCategoriaServico.setText(servico.getCategoriaServico());
    }

    @Override
    public int getItemCount() {
        return servicosRealizados.size();
    }

    public class ViewHolderHistoricoServicos extends RecyclerView.ViewHolder{

    private TextView mNomeOficina;
    private TextView mCategoriaServico;
    private TextView mDataServico;
    private TextView mNotaServico;
    private View mIcEstrela;



    public ViewHolderHistoricoServicos(View itemView) {
        super(itemView);
        this.mCategoriaServico=itemView.findViewById(R.id.tv_perfil_usuario_categoria_servico);
        this.mNomeOficina=itemView.findViewById(R.id.tv_perfil_usuario_nome_oficina);
        this.mDataServico=itemView.findViewById(R.id.tv_data_servico);
        this.mNotaServico=itemView.findViewById(R.id.tv_nota_servico_perfil_usuario);
        this.mIcEstrela=itemView.findViewById(R.id.ic_estrela_perfil_usuario);
    }
}

}
