package com.xtreme.blogapi.utilitarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.Post;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolderDatos> implements View.OnClickListener {

    /*------- Variables ---------*/
    private ArrayList<Post> listPost;
    private View.OnClickListener listener;

    public RowAdapter(ArrayList<Post> listPost) {
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

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.array_item_txt_id);
            titulo=itemView.findViewById(R.id.array_item_txt_titulo);
            detalle=itemView.findViewById(R.id.array_item_txt_detalle);
        }

        public void asignarDatos(Post post) {
            id.setText(String.valueOf(post.getId()));
            titulo.setText(post.getTitle());
            detalle.setText(post.getText());
        }
    }


}
