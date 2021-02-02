package es.ulpgc.eite.da.greeter.hello;

import es.ulpgc.eite.da.greeter.AppMediator;

public class HelloRouter implements HelloContract.Router {

  public static String TAG = HelloRouter.class.getSimpleName();

  private AppMediator mediator;

  public HelloRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

//  @Override
//  public void navigateToNextScreen() {
//    Context context = mediator.getApplicationContext();
//    Intent intent = new Intent(context, HelloActivity.class);
//    context.startActivity(intent);
//  }

  @Override
  public void passDataToNextScreen(HelloState state) {
    mediator.setHelloState(state);
  }

  @Override
  public HelloState getDataFromPreviousScreen() {
    return null;
  }

}
