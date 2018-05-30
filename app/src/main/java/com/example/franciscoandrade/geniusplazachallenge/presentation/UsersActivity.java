package com.example.franciscoandrade.geniusplazachallenge.presentation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.franciscoandrade.geniusplazachallenge.R;
import com.example.franciscoandrade.geniusplazachallenge.data.api.ClientService;
import com.example.franciscoandrade.geniusplazachallenge.data.model.Data;
import com.example.franciscoandrade.geniusplazachallenge.recyclerView.UsersListAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersActivity extends AppCompatActivity implements UsersContract.View {

    @BindView(R.id.user_rv)
    RecyclerView userRv;
    @BindView(R.id.add_user)
    FloatingActionButton addUser;
    @BindView(R.id.linearLayout)
    ConstraintLayout linearLayout;
    UsersContract.Presenter presenter;
    private UsersListAdapter adapter;
    private ArrayList<Data> savedMatchList;
    private boolean ableToReload;
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ClientService clientService = new ClientService(getString(R.string.WW_Domain));
        presenter = new UsersPresenter(this, clientService, getResources());
        userRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = userRv.getLayoutManager().getChildCount();
                    int totalItemCount = userRv.getLayoutManager().getItemCount();
                    int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (ableToReload) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            ableToReload = false;
                            offset += 1;
                            presenter.getMatchList(offset);
                        }
                    }
                }
            }
        });
        ableToReload = true;
        savedMatchList = new ArrayList<>();
        setRecycler();
        if (savedInstanceState != null) {
            savedMatchList = (ArrayList<Data>) savedInstanceState.getSerializable(getResources().getText(R.string.rotation_text).toString());
            adapter.addUsers(savedMatchList);
            offset=savedInstanceState.getInt(getResources().getText(R.string.offset_text).toString());
        } else {
            offset = 1;
        }
            presenter.getMatchList(offset);
    }

    private void setRecycler() {
        adapter = new UsersListAdapter();
        userRv.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(getResources().getText(R.string.rotation_text).toString(), savedMatchList);
        outState.putInt(getResources().getText(R.string.offset_text).toString(), offset+=1);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setRecyclerView(List<Data> matchList) {
        adapter.addUsers(matchList);
        savedMatchList.addAll(matchList);
    }

    @Override
    public void showMessage() {
        String message = this.getString(R.string.error_no_internet);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void ableToReload(boolean reload, int offset) {
        ableToReload = reload;
        this.offset = offset;
    }

    @Override
    public void createUserMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.add_user)
    public void onViewClicked() {
        setAlertDialog();
    }

    private void setAlertDialog() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);
        final EditText userName = promptsView.findViewById(R.id.user_name);
        final EditText userJob = promptsView.findViewById(R.id.user_job);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(getResources().getText(R.string.add_text),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                presenter.createUser(userName.getText().toString(), userJob.getText().toString());
                            }
                        })
                .setNegativeButton(getResources().getText(R.string.cancel_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
