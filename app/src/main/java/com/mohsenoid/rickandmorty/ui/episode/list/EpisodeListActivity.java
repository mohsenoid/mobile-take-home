package com.mohsenoid.rickandmorty.ui.episode.list;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.mohsenoid.rickandmorty.R;
import com.mohsenoid.rickandmorty.injection.DependenciesProvider;
import com.mohsenoid.rickandmorty.ui.base.BaseActivity;

public class EpisodeListActivity extends BaseActivity {

    static String TAG = EpisodeListActivity.class.getSimpleName();
    static String TAG_EPISODE_LIST_FRAGMENT = "episodeListFragment";

    private EpisodeListFragment episodeListFragment;

    @Override
    public void injectDependencies(@Nullable Bundle savedInstanceState, DependenciesProvider dependenciesProvider) {
        if (savedInstanceState == null) {
            episodeListFragment = dependenciesProvider.getEpisodeListFragment();
        } else {
            episodeListFragment = (EpisodeListFragment) getSupportFragmentManager().findFragmentByTag(TAG_EPISODE_LIST_FRAGMENT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == savedInstanceState) {
            attachFragments();
        }

        Log.i(TAG, "EpisodeListActivity created");
    }

    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, episodeListFragment, TAG_EPISODE_LIST_FRAGMENT);
        fragmentTransaction.commit();
    }
}