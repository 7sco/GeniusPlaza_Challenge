package com.example.franciscoandrade.geniusplazachallenge;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.franciscoandrade.geniusplazachallenge.data.api.ClientService;
import com.example.franciscoandrade.geniusplazachallenge.data.api.UsersApi;
import com.example.franciscoandrade.geniusplazachallenge.data.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersPresenter implements UsersContract.Presenter{

        private static final String TAG= UsersPresenter.class.getSimpleName();
        private UsersContract.View viewImpl;
        private ClientService clientService;
        private UsersApi matchApi;

        public UsersPresenter(@NonNull UsersContract.View viemImpl,
                              @NonNull ClientService clientService) {
            this.viewImpl = viemImpl;
            this.clientService = clientService;
            matchApi= clientService.getUserApi();
        }

        @Override
        public void getMatchList(final int offset) {
            Call<UserResponse> matchCall= matchApi.getUsersList(10,offset);
            matchCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    Log.d(TAG, "onResponse: "+ response.toString());
                    Log.d(TAG, "onResponse: "+ response.body().toString());
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
    }

