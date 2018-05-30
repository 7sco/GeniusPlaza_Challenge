package com.example.franciscoandrade.geniusplazachallenge.presentation;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.franciscoandrade.geniusplazachallenge.R;
import com.example.franciscoandrade.geniusplazachallenge.data.api.ClientService;
import com.example.franciscoandrade.geniusplazachallenge.data.api.User;
import com.example.franciscoandrade.geniusplazachallenge.data.api.UsersApi;
import com.example.franciscoandrade.geniusplazachallenge.data.model.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersPresenter implements UsersContract.Presenter{

        private static final String TAG= UsersPresenter.class.getSimpleName();
        private UsersContract.View viewImpl;
        private UsersApi matchApi;
        private Resources resources;

        public UsersPresenter(@NonNull UsersContract.View viemImpl,
                              @NonNull ClientService clientService,
                              @NonNull Resources resources) {
            this.viewImpl = viemImpl;
            this.resources=resources;
            matchApi= clientService.getUserApi();
        }

        @Override
        public void getMatchList(final int offset) {
            Call<UserResponse> userCall= matchApi.getUsersList(10,offset);
            userCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    viewImpl.ableToReload(true, offset);
                    viewImpl.setRecyclerView(response.body().getData());
                }
                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    viewImpl.showMessage();
                    viewImpl.ableToReload(true, offset);
                }
            });
        }

    @Override
    public void createUser(String name, String job) {
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(job)) {
            Call<User> userCall = matchApi.createUser(name, job);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    viewImpl.createUserMessage(resources.getString(R.string.user_created_text)+response.body().getId());
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    viewImpl.createUserMessage(resources.getString(R.string.failed_connection_text));
                }
            });
        }
        else {
            viewImpl.createUserMessage(resources.getString(R.string.empty_text));
        }
    }
}

