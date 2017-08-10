package me.bayupaoh.screeningtest.view.guest;

import java.util.List;

import me.bayupaoh.screeningtest.BasePresenter;
import me.bayupaoh.screeningtest.BaseView;
import me.bayupaoh.screeningtest.api.dao.GuestDao;

/**
 * Created by dsantren on 09/08/2017.
 */

public class GuestContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showErrorMessage(String message);

        void showGuestData(List<GuestDao> guestDao);
    }

    interface Presenter extends BasePresenter<View> {
        void loadDataGuest();
    }
}
