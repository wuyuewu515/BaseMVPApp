package gzfns.com.inventoryregulation.base;


/**
 * Created by Administrator on 2016/9/13.
 */
public abstract class BasePresenter<T, E> {
    protected T mView;
    protected E mModel;

    public void setVM(T baseView,E baseModel) {
        this.mView = baseView;
        this.mModel = baseModel;
        this.onStart();
    }

    protected abstract void onStart();

    protected void onDestroy() {
        if (mModel != null)
            mModel = null;
        if (mView != null)
            mView = null;
    }
}
