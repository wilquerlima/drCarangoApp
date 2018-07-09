package doutor.carangoapp.controller;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseDistancia;
import doutor.carangoapp.base.BaseEstabelecimento;


public class AdapterOficinas extends RecyclerView.Adapter<AdapterOficinas.OficinasViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private ArrayList<BaseEstabelecimento> mOficinas;

    private final OnItemClickListener mOnClickListener;

    public ArrayList<BaseEstabelecimento> getmOficinas() {
        return mOficinas;
    }

    public void setmOficinas(ArrayList<BaseEstabelecimento> mOficinas) {
        this.mOficinas = mOficinas;
    }


    public AdapterOficinas(OnItemClickListener listener) {
        this.mOnClickListener = listener;
    }

    @Override
    public OficinasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View result = inflater.inflate(R.layout.layout_viewholder_oficina, parent, false);
        return new OficinasViewHolder(result);
    }

    @Override
    public void onBindViewHolder(OficinasViewHolder holder, int position) {

        Random r = new Random();

        BaseEstabelecimento oficina = mOficinas.get(position);
        if(oficina.getNumeroPromocoes()==0){
            holder.mPromocao.setVisibility(View.GONE);
        }
        if(!oficina.isParceira()){
            holder.mOficinaParceira.setVisibility(View.GONE);
        }

        holder.getmNomeOficina().setText(oficina.getNome());
        holder.getmMediaAvaliacoes().setText(Double.toString(oficina.getRankingServico()));
        holder.getmNumeroComentarios().setText(Double.toString(oficina.getNumeroComentarios()));
        holder.getmNumeroAvaliacoes().setText(Double.toString(oficina.getNumeroAvaliacoes()));
        holder.mDistancia.setText(Double.toString(BaseDistancia.distancias[oficina.getId()])+" KM");
        //gera randomicamente cor para circulo na view
        GradientDrawable background = (GradientDrawable) holder.getmCircleOficinaList().getBackground();
        background.setColor(Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256)));


    }

    @Override
    public int getItemCount() {
        return mOficinas != null ? mOficinas.size() : 0;
    }

    public class OficinasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNomeOficina;
        private TextView mCircleOficinaList;
        private TextView mNumeroAvaliacoes;
        private TextView mNumeroComentarios;
        private TextView mMediaAvaliacoes;
        private View mPromocao;
        private View mOficinaParceira;
        private TextView mDistancia;


        public OficinasViewHolder(View itemView) {

            super(itemView);
            mNomeOficina = itemView.findViewById(R.id.tv_nome_oficina);
            mCircleOficinaList = itemView.findViewById(R.id.circle_oficina_list);
            mNumeroAvaliacoes = itemView.findViewById(R.id.tv_numero_avaliacoes);
            mNumeroComentarios = itemView.findViewById(R.id.tv_numero_comentarios);
            mPromocao = itemView.findViewById(R.id.v_promocao);
            mMediaAvaliacoes = itemView.findViewById(R.id.tv_media_avaliacoes);
            mOficinaParceira = itemView.findViewById(R.id.ic_parceira_oficina_lista);
            mDistancia=itemView.findViewById(R.id.tv_distancia);
            itemView.setOnClickListener(this);

        }

        public View getmPromocao() {
            return mPromocao;
        }

        public void setmPromocao(View mPromocao) {
            this.mPromocao = mPromocao;
        }

        public View getmOficinaParceira() {
            return mOficinaParceira;
        }

        public void setmOficinaParceira(View mOficinaParceira) {
            this.mOficinaParceira = mOficinaParceira;
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


        public TextView getmCircleOficinaList() {
            return mCircleOficinaList;
        }


        public TextView getmNumeroAvaliacoes() {
            return mNumeroAvaliacoes;
        }

        public TextView getmNumeroComentarios() {
            return mNumeroComentarios;
        }


        @Override
        public void onClick(View v) {
            mOnClickListener.onItemClick(getAdapterPosition());
        }
    }
}
