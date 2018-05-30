package com.example.franciscoandrade.geniusplazachallenge;

import com.example.franciscoandrade.geniusplazachallenge.data.model.Data;

import java.util.List;

public interface UsersContract {

    interface View{
        void setRecyclerView(List<Data> matchList);
        void showMessage();
        void ableToReload(boolean reload, int offset);
    }

    interface Presenter{
        void getMatchList(int offset);

    }

}
