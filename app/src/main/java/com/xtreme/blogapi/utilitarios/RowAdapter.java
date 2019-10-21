package com.xtreme.blogapi.utilitarios;

import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtreme.blogapi.R;
import com.xtreme.blogapi.modelos.City;
import com.xtreme.blogapi.modelos.Post;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolderDatos> {

    /*------- Variables ---------*/
    private ArrayList<Post> listPost;

    public RowAdapter(ArrayList<Post> listPost) {
        this.listPost = listPost;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.array_item,null,false);
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

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView detalle;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.array_item_txt_titulo);
            detalle=itemView.findViewById(R.id.array_item_txt_detalle);
        }

        public void asignarDatos(Post post) {
            titulo.setText(post.getTitulo());
            detalle.setText(post.getText());
        }
    }
}
