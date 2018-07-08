package doutor.carangoapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseComentario;

public class AdapterComentsOficina extends RecyclerView.Adapter<AdapterComentsOficina.ViewHolderComentariosOficina> {


    private ArrayList<BaseComentario> comentarios;

    public AdapterComentsOficina(ArrayList<BaseComentario> coments){
        this.comentarios=coments;
    }

    @Override
    public ViewHolderComentariosOficina onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate=LayoutInflater.from(parent.getContext());
        View result=inflate.inflate(R.layout.view_holder_comentarios_oficina,parent,false);
        return new ViewHolderComentariosOficina(result);
    }

    @Override
    public void onBindViewHolder(ViewHolderComentariosOficina holder, int position) {

        BaseComentario coment=comentarios.get(position);
        holder.getmComentario().setText(coment.getmConteudo());
        holder.getmNomeUsuario().setText(coment.getmNomeUsuario());
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public ArrayList<BaseComentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<BaseComentario> comentarios) {
        this.comentarios = comentarios;
    }





    public class ViewHolderComentariosOficina extends RecyclerView.ViewHolder {

        private TextView mNomeUsuario;
        private TextView mComentario;


        public TextView getmNomeUsuario() {
            return mNomeUsuario;
        }

        public void setmNomeUsuario(TextView mNomeUsuario) {
            this.mNomeUsuario = mNomeUsuario;
        }

        public TextView getmComentario() {
            return mComentario;
        }

        public void setmComentario(TextView mComentario) {
            this.mComentario = mComentario;
        }


        public ViewHolderComentariosOficina(View itemView) {
            super(itemView);
            mNomeUsuario=itemView.findViewById(R.id.tv_nome_comentario_oficina);
            mComentario=itemView.findViewById(R.id.tv_conteudo_comentario);
        }
    }

}
