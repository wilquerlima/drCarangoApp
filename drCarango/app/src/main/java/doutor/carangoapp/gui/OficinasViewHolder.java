package doutor.carangoapp.gui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import doutor.carangoapp.R;

public class OficinasViewHolder extends RecyclerView.ViewHolder {

    private TextView mNomeOficina;
    private TextView mCircleOficinaList;
    private TextView mNumeroAvaliacoes;
    private TextView mNumeroComentarios;
    private TextView mNumeroAmigosEmComum;
    private TextView mMediaAvaliacoes;





    public OficinasViewHolder(View itemView) {
        super(itemView);
        mNomeOficina=itemView.findViewById(R.id.tv_nome_oficina);
        mCircleOficinaList=itemView.findViewById(R.id.circle_oficina_list);
        mNumeroAvaliacoes=itemView.findViewById(R.id.tv_numero_avaliacoes);
        mNumeroComentarios=itemView.findViewById(R.id.tv_numero_comentarios);
        mNumeroAmigosEmComum=itemView.findViewById(R.id.tv_numero_amigos_em_comum);
        mMediaAvaliacoes=itemView.findViewById(R.id.tv_media_avaliacoes);

    }
    public TextView getmMediaAvaliacoes() {
        return mMediaAvaliacoes;
    }

    public void setmMediaAvaliacoes(TextView mMediaAvaliacoes) {
        this.mMediaAvaliacoes = mMediaAvaliacoes;
    }


    public TextView getmNomeOficina() {
        return mNomeOficina;
    }



    public void setmNomeOficina(TextView mNomeOficina) {
        this.mNomeOficina = mNomeOficina;
    }

    public void setmCircleOficinaList(TextView mCircleOficinaList) {
        this.mCircleOficinaList = mCircleOficinaList;
    }



    public void setmNumeroAvaliacoes(TextView mNumeroAvaliacoes) {
        this.mNumeroAvaliacoes = mNumeroAvaliacoes;
    }

    public void setmNumeroComentarios(TextView mNumeroComentarios) {
        this.mNumeroComentarios = mNumeroComentarios;
    }

    public void setmNumeroAmigosEmComum(TextView mNumeroAmigosEmComum) {
        this.mNumeroAmigosEmComum = mNumeroAmigosEmComum;
    }

    public TextView getmCircleOficinaList() {
        return mCircleOficinaList;
    }



    public TextView getmNumeroAvaliacoes() {
        return mNumeroAvaliacoes;
    }

    public TextView getmNumeroComentarios() {
        return mNumeroComentarios;
    }

    public TextView getmNumeroAmigosEmComum() {
        return mNumeroAmigosEmComum;
    }


}
