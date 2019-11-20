package com.xtreme.blogapi.utilitarios;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.Post;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolderDatos> implements View.OnClickListener {

    /*------- Variables ---------*/
    private List<Post> listPost;
    private View.OnClickListener listener;
    private static final String TAG="XXXXXXX - RowAdapter";

    public RowAdapter(List<Post> listPost) {
        this.listPost = listPost;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.array_item,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listPost.get(position));
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener !=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView id;
        TextView titulo;
        TextView detalle;
        TextView view;
        TextView commenterios;
        TextView like;
        TextView date;
        Switch likebtn;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.array_item_txt_titulo);
            detalle=itemView.findViewById(R.id.array_item_txt_detalle);
            like = itemView.findViewById(R.id.view_post_txt_like);
            commenterios=itemView.findViewById(R.id.view_post_txt_comentarios);
            view = itemView.findViewById(R.id.view_post_txt_view);
            date = itemView.findViewById(R.id.view_post_txt_date);
            likebtn = itemView.findViewById(R.id.array_item_like);
        }

        public void asignarDatos(Post post) {
            titulo.setText(post.getTitle());

            if(post.getText()==null || post.getText().length() <=40) {
                detalle.setText("\n"+post.getText()+"\n\n");
            }else{
                detalle.setText("\n"+post.getText().substring(0,40)+" ... ver mas \n\n");
            }


            like.setText(String.valueOf(post.getLikes()));
            commenterios.setText(String.valueOf(post.getComments()));
            view.setText(String.valueOf(post.getViews()));
            likebtn.setChecked(post.isLiked());
            long fechaint = Long.parseLong(post.getCreatedAt());
            Date fecha = new Date(fechaint);
            date.setText("Fecha: "+new SimpleDateFormat("dd-MM-yyyy").format(fecha));

        }
    }


}
