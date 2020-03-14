package es.ulpgc.eite.da.greeter.hello;

import java.lang.ref.WeakReference;

public class HelloPresenter implements HelloContract.Presenter {

  public static String TAG = HelloPresenter.class.getSimpleName();

  private WeakReference<HelloContract.View> view;
  private HelloState state;
  private HelloContract.Model model;
  private HelloContract.Router router;

  public HelloPresenter(HelloState state) {
    this.state = state;
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
      state.data = model.getData();;
    }

  }

  @Override
  public void onResume() {

    // use passed state if is necessary
    HelloState savedState = router.getDataFromPreviousScreen();
    if (savedState != null) {

      // update the state
      updateState(savedState.data);

      return;
    }

    // update the state
    updateState(state.data);
  }

//  @Override
//  public void resetData() {
//    //Log.e(TAG, "resetData()");
//
//    // call the model
//    state.data = model.getData();
//  }
//
//  @Override
//  public void getData() {
//    //Log.e(TAG, "getData()");
//
//    // use passed state if is necessary
//    HelloState savedState = router.getDataFromPreviousScreen();
//    if (savedState != null) {
//
//      /*
//      // update view and model state
//      state.data = savedState.data;
//
//      // update the view
//      view.get().displayData(state);
//      */
//
//      // update the state
//      updateState(savedState.data);
//
//      return;
//    }
//
//    // initialize the state if is necessary
//    if (state.data == null) {
//
//      // call the model
//      state.data = model.getData();;
//    }
//
//    // update the state
//    updateState(state.data);
//
//    /*
//    // set view state
//    state.data = data;
//
//    // update the view
//    view.get().displayData(state);
//    */
//  }
//
//
//  @Override
//  public void updateData(String data) {
//    // Log.e(TAG, "updateData()");
//
//    // call the model
//    //model.updateData(data);
//
//    // update the state
//    updateState(data);
//  }

  @Override
  public void onGreetButtonClicked(String data) {
    // Log.e(TAG, "onGreetButtonClicked()");

    // update the state
    updateState(data);
  }

  private void updateState(String data) {

    // set view state
    state.data = data;

    // update the view
    view.get().displayData(state);
  }


  @Override
  public void injectView(WeakReference<HelloContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(HelloContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(HelloContract.Router router) {
    this.router = router;
  }


}
