package me.bayupaoh.screeningtest;

/**
 * Created by dsantren on 09/08/2017.
 */

public interface BasePresenter <T extends BaseView> {
    void onAttach(T view);

    void onDetach();
}
