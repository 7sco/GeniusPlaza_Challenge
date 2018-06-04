package com.example.franciscoandrade.geniusplazachallenge.presentation;

import com.example.franciscoandrade.geniusplazachallenge.data.model.Data;

import java.util.List;

public interface UsersContract {

    interface View{
        void setRecyclerView(List<Data> matchList);
        void showMessage();
        void ableToReload(boolean reload, int offset);
        void createUserMessage(String message);
    }

    interface Presenter{
        void getMatchList(int offset);
        void createUser(String name, String job);
    }

}
