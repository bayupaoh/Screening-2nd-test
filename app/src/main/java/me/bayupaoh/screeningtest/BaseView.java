package me.bayupaoh.screeningtest;

import android.content.Context;

/**
 * Created by dsantren on 09/08/2017.
 */

public interface BaseView {
    Context getContext();

    void onAttachView();

    void onDetachView();
}
