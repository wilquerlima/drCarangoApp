package doutor.carangoapp.gui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;

public class AdapterOficinas extends RecyclerView.Adapter<OficinasViewHolder> {

    private ArrayList<BaseEstabelecimento> mOficinas;

    public AdapterOficinas(ArrayList<BaseEstabelecimento> estabelecimentos){
        this.mOficinas=estabelecimentos;
    }

    @Override
    public OficinasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View result=inflater.inflate(R.layout.layout_viewholder_oficina,parent,false);
        return new OficinasViewHolder(result);
    }

    @Override
    public void onBindViewHolder(OficinasViewHolder holder, int position) {

        Random r=new Random();
        BaseEstabelecimento oficina=mOficinas.get(position);
        holder.getmNomeOficina().setText(oficina.getNome());
        holder.getmMediaAvaliacoes().setText(Double.toString(oficina.getRankingServico()));
        holder.getmNumeroAmigosEmComum().setText( (r.nextInt(100))+" amigos em comum");
        holder.getmNumeroComentarios().setText( " ("+ r.nextInt(100)+ ")");

        holder.getmNumeroAvaliacoes().setText(" ("+Integer.toString (r.nextInt(100))+")");

        GradientDrawable background=(GradientDrawable) holder.getmCircleOficinaList().getBackground();
        background.setColor(Color.argb(255,r.nextInt(256),r.nextInt(256),r.nextInt(256)));


    }

    @Override
    public int getItemCount() {
       return mOficinas!=null?mOficinas.size():0;
    }
}
