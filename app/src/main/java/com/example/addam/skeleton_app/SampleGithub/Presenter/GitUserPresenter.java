package com.example.addam.skeleton_app.SampleGithub.Presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.addam.skeleton_app.SampleGithub.Models.GitUserResponse;
import com.example.addam.skeleton_app.SampleGithub.RetroRest.GitUserREST;
import com.example.addam.skeleton_app.SampleGithub.View.GitUserView;
import com.example.addam.skeleton_app.core.Presenters;

import rx.Subscriber;

/**
 * Created by addam on 8/3/17.
 */

public class GitUserPresenter implements Presenters<GitUserView> {
    GitUserView gitUserView;
    GitUserREST gitUserREST;

    public GitUserPresenter(GitUserREST gitUserREST) {
        this.gitUserREST = gitUserREST;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void attachView(@NonNull GitUserView view) {
        gitUserView = view;
    }

    public void getGitUserDetails(String username){
        gitUserREST.execute(new Subscriber<GitUserResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("Error",e.getMessage());
            }

            @Override
            public void onNext(GitUserResponse gitUserResponse) {
                gitUserView.displayResponse(gitUserResponse);
            }
        });
    }
}
