package es.ulpgc.eite.da.greeter.hello;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.greeter.AppMediator;

public class HelloPresenter implements HelloContract.Presenter {

  public static String TAG = HelloPresenter.class.getSimpleName();

  private AppMediator mediator;
  private WeakReference<HelloContract.View> view;
  private HelloState state;
  private HelloContract.Model model;
  //private HelloContract.Router router;

  /*
  public HelloPresenter(HelloState state) {
    this.state = state;
  }
  */

  public HelloPresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getHelloState();
  }

  @Override
  public void onStart() {

    // call the model
    state.data = model.getData();

  }

  @Override
  public void onRestart() {

    // initialize the state if is necessary
    if (state.data == null) {

      // call the model
      state.data = model.getData();
    }

  }

  @Override
  public void onResume() {

    // use passed state if is necessary
    HelloState savedState = getDataFromPreviousScreen();
    //HelloState savedState = router.getDataFromPreviousScreen();
    if (savedState != null) {

      // update the state
      state.data = savedState.data;
    }

    // update the view
    view.get().displayData(state);
  }

  @Override
  public void onGreetButtonClicked(String data) {
    // Log.e(TAG, "onGreetButtonClicked()");

    // set view state
    state.data = data;

    // update the view
    view.get().displayData(state);
  }

  private void passDataToNextScreen(HelloState state) {
    mediator.setHelloState(state);
  }

  private HelloState getDataFromPreviousScreen() {
    return null;
  }

  @Override
  public void injectView(WeakReference<HelloContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(HelloContract.Model model) {
    this.model = model;
  }

  /*
  @Override
  public void injectRouter(HelloContract.Router router) {
    this.router = router;
  }
  */

}
