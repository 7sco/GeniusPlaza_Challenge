package com.example.franciscoandrade.geniusplazachallenge.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.franciscoandrade.geniusplazachallenge.R;
import com.example.franciscoandrade.geniusplazachallenge.data.model.Data;

import java.util.ArrayList;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListViewHolder> {
    private List<Data> listUsers= new ArrayList<>();


    @NonNull
    @Override
    public UsersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_viewholder, parent, false);
        return new UsersListViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListViewHolder holder, int position) {
        holder.bind(listUsers.get(position));

    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public void addUsers(List<Data> listUsers) {
        //this.listUsers.clear();
        this.listUsers.addAll(listUsers);
        notifyDataSetChanged();
    }
}
