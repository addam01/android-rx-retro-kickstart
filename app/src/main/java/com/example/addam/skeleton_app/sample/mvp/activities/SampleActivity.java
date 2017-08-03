package com.example.addam.skeleton_app.sample.mvp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.addam.skeleton_app.AppPreferences;
import com.example.addam.skeleton_app.R;
import com.example.addam.skeleton_app.core.Rest.RestRepository;
import com.example.addam.skeleton_app.sample.mvp.model.SampleResponse;
import com.example.addam.skeleton_app.sample.mvp.presenter.SamplePresenter;
import com.example.addam.skeleton_app.sample.mvp.retroRest.GetSample;
import com.example.addam.skeleton_app.sample.mvp.view.SampleView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SampleActivity extends AppCompatActivity implements SampleView {
    AppPreferences mAppPreferences;
    SamplePresenter mSamplePresenter;
    GetSample mGetSample;

    @BindView(R.id.t_hello)
    TextView tHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);

        initializePresenters();
    }

    void initializePresenters(){
        /**
         * This will grab the current preferences,
         * if you want to add session ID, place it within the apppreference
         * then add param in the RestRepository
         */
        mAppPreferences = (AppPreferences) getApplicationContext();
        RestRepository repository = new RestRepository(this, "Some Token");
        mGetSample = new GetSample(repository);
        mSamplePresenter = new SamplePresenter(mGetSample);
        /**
         * This officially binds this view to the presenters so that all your widgets and
         * logics can be handled there
         */
        mSamplePresenter.attachView(this);
    }

    @Override
    public void doSomething(SampleResponse sampleResponse) {
        Toast.makeText(getBaseContext(),sampleResponse.getToken(),Toast.LENGTH_SHORT).show();
    }
}
