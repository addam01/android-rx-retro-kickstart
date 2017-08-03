package com.example.addam.skeleton_app.SampleGithub.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addam.skeleton_app.R;
import com.example.addam.skeleton_app.SampleGithub.Models.GitUserResponse;
import com.example.addam.skeleton_app.SampleGithub.Presenter.GitUserPresenter;
import com.example.addam.skeleton_app.SampleGithub.RetroRest.GitUserREST;
import com.example.addam.skeleton_app.SampleGithub.View.GitUserView;
import com.example.addam.skeleton_app.core.Rest.RestRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GitUserActivity extends AppCompatActivity implements GitUserView {
    GitUserPresenter presenter;

    @BindView(R.id.username)
    EditText tUsername;
    @BindView(R.id.Submit)
    Button button;

    @BindView(R.id.tResponse)
    TextView tResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_user);
        ButterKnife.bind(this);

        initializePresenter();
    }

    private void initializePresenter() {
        presenter = new GitUserPresenter(new GitUserREST(new RestRepository(this,"Some token")));
        presenter.attachView(this);
    }

    @OnClick(R.id.Submit)
    void OnClickSubmit(){
        Toast.makeText(this, "HEllo", Toast.LENGTH_LONG).show();
        presenter.getGitUserDetails(tUsername.getText().toString());
    }

    @Override
    public void displayResponse(GitUserResponse gitUserResponse) {
        tResponse.setText(gitUserResponse.getBio());
    }
}
