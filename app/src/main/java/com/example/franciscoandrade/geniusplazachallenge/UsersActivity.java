package com.example.franciscoandrade.geniusplazachallenge;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.franciscoandrade.geniusplazachallenge.data.api.ClientService;
import com.example.franciscoandrade.geniusplazachallenge.data.model.Data;
import com.example.franciscoandrade.geniusplazachallenge.recyclerView.UsersListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity extends AppCompatActivity implements UsersContract.View{

    @BindView(R.id.user_rv)
    RecyclerView userRv;
    private UsersListAdapter adapter ;
    private ArrayList<Data> savedMatchList;
    private GridLayoutManager gridLayoutManager;
    private boolean ableToReload;
    private int offset;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        ClientService clientService = new ClientService(getString(R.string.WW_Domain));
        final UsersContract.Presenter presenter = new UsersPresenter(this, clientService);





        userRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    //int visibleItemCount = gridLayoutManager.getChildCount();
                    int visibleItemCount=userRv.getLayoutManager().getChildCount();
                    //int totalItemCount = gridLayoutManager.getItemCount();
                    int totalItemCount=userRv.getLayoutManager().getItemCount();
                    //int pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();
                    int pastVisibleItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();


                    if (ableToReload) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i("END==", " Llegamos al final.");

                            ableToReload = false;
                            offset += 1;
                            Log.d("CALL2==", "onScrolled: ");
                            presenter.getMatchList(offset);
                        }
                    }
                }
            }
        });

        ableToReload = true;
        offset = 1;

        if(savedInstanceState!=null){
            savedMatchList=(ArrayList<Data>) savedInstanceState.getSerializable("listBeforeRotation");
            adapter.addUsers(savedMatchList);
            userRv.setAdapter(adapter);
        }
        else {
            presenter.getMatchList(offset);
        }

        //getRotation();
        setRecycler();
    }


    private void setRecycler() {
        adapter = new UsersListAdapter();
        userRv.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("listBeforeRotation",  savedMatchList);
        super.onSaveInstanceState(outState);
    }



    @Override
    public void setRecyclerView(List<Data> matchList) {
        adapter.addUsers(matchList);
        savedMatchList= new ArrayList<>();
        savedMatchList.addAll(matchList);
       // userRv.setAdapter(adapter);
    }

    @Override
    public void showMessage() {
        String message=this.getString(R.string.error_no_internet);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void ableToReload(boolean reload, int offset) {
        ableToReload =reload;
        this.offset=offset;
    }

    public void getRotation() {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            userRv.setLayoutManager(new GridLayoutManager(this, 4));
        }
    }
}
