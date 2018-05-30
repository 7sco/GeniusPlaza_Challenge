package com.example.franciscoandrade.geniusplazachallenge.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franciscoandrade.geniusplazachallenge.R;
import com.example.franciscoandrade.geniusplazachallenge.data.model.Data;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_image)
    ImageView userImage;
    @BindView(R.id.user_full_name)
    TextView userFullNameTV;
    @BindView(R.id.user_id)
    TextView userIdTV;

    public UsersListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bind(Data data){
        String userFullName= data.getFirst_name()+" "+data.getLast_name();
        String userId= itemView.getResources().getString(R.string.id_text)+ data.getId();
        String imageUrl= data.getAvatar();

        userFullNameTV.setText(userFullName);
        userIdTV.setText(userId);
        Picasso.get().load(imageUrl).
                fit().
                centerCrop().
                placeholder(R.drawable.ic_load).
                error(R.drawable.error_image).
                into(userImage);
    }
}
