package me.bayupaoh.screeningtest.view.guest;

import java.util.List;

import me.bayupaoh.screeningtest.api.dao.GuestDao;
import me.bayupaoh.screeningtest.api.service.ScreeningTestService;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dsantren on 09/08/2017.
 */

public class GuestPresenter implements GuestContract.Presenter {
    private GuestContract.View guestView;
    private CompositeSubscription subscription;

    @Override
    public void onAttach(GuestContract.View view) {
        subscription = new CompositeSubscription();
        guestView = view;
    }

    @Override
    public void onDetach() {
        subscription.clear();
        guestView = null;
    }

    @Override
    public void loadDataGuest() {
        guestView.showProgress();
        subscription.clear();
        Observable<List<GuestDao>> call = ScreeningTestService.factory.create().getListPeople();
        Subscription subscription = call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<GuestDao>>() {
            @Override
            public void onCompleted() {
                guestView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                guestView.showErrorMessage(e.getMessage());
            }

            @Override
            public void onNext(List<GuestDao> guestDaos) {
                if(guestDaos == null){
                    guestView.showErrorMessage("Data tidak tersedia");
                }else{
                    guestView.showGuestData(guestDaos);
                }
            }
        });

        this.subscription.add(subscription);
    }
}
