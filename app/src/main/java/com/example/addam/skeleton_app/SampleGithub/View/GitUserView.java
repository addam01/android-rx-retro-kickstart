package com.example.addam.skeleton_app.SampleGithub.View;

import com.example.addam.skeleton_app.SampleGithub.Models.GitUserResponse;
import com.example.addam.skeleton_app.core.View;

/**
 * Created by addam on 8/3/17.
 */

public interface GitUserView extends View {
    void displayResponse(GitUserResponse gitUserResponse);
}
