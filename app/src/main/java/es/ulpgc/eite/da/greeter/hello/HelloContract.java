package es.ulpgc.eite.da.greeter.hello;

import java.lang.ref.WeakReference;

public interface HelloContract {

  interface View {
    void injectPresenter(Presenter presenter);
    void displayData(HelloViewModel viewModel);
  }

  interface Presenter {
    //void updateData(String data);
    void injectView(WeakReference<View> view);
    void injectModel(Model model);
    void injectRouter(Router router);
    //void getData();
    //void resetData();
    void onStart();
    void onRestart();
    void onResume();
    void onGreetButtonClicked(String data);
  }

  interface Model {
    String getData();
    //void updateData(String data);
  }

  interface Router {
    void navigateToNextScreen();
    void passDataToNextScreen(HelloState state);
    HelloState getDataFromPreviousScreen();
  }
}
