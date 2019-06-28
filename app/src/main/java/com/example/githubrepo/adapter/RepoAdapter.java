package com.example.githubrepo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.githubrepo.R;
import com.example.githubrepo.model.UserRepo;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    ArrayList<UserRepo> itemlist;
    Context context;

    public RepoAdapter(ArrayList<UserRepo> itemlist, Context context){
        this.itemlist = itemlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RepoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_userrepo, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.ViewHolder viewHolder, int position) {
        UserRepo item = itemlist.get(position);

        viewHolder.tv_name.setText(item.getName());
        viewHolder.tv_description.setText(item.getDescription());
        viewHolder.tv_language.setText(item.getLanguage());
        viewHolder.tv_updated.setText(item.getUpdated_at());

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_description, tv_language, tv_updated;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_language = itemView.findViewById(R.id.tv_language);
            tv_updated = itemView.findViewById(R.id.tv_updated);
        }
    }
}
