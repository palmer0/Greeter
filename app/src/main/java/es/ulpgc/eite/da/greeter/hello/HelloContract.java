package es.ulpgc.eite.da.greeter.hello;

import java.lang.ref.WeakReference;

public interface HelloContract {

  interface View {
    void injectPresenter(Presenter presenter);
    void displayData(HelloViewModel viewModel);
    void navigateToNextScreen();

  }

  interface Presenter {
    void injectView(WeakReference<View> view);
    void injectModel(Model model);
    //void injectRouter(Router router);

    void onStart();
    void onRestart();
    void onResume();
    void onGreetButtonClicked(String data);
  }

  interface Model {
    String getData();
  }

  /*
  interface Router {
    void passDataToNextScreen(HelloState state);
    HelloState getDataFromPreviousScreen();
  }
  */
}
