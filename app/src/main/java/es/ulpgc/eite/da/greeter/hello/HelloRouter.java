package es.ulpgc.eite.da.greeter.hello;

import android.content.Context;
import android.content.Intent;

import es.ulpgc.eite.da.greeter.AppMediator;

public class HelloRouter implements HelloContract.Router {

  public static String TAG = HelloRouter.class.getSimpleName();

  //private final Context context;

  private AppMediator mediator;

  public HelloRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

//  public HelloRouter(Context context ) {
//    this.context = context;
//  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, HelloActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(HelloState state) {
    //AppMediator mediator = AppMediator.getInstance();
    mediator.setHelloState(state);
  }

  @Override
  public HelloState getDataFromPreviousScreen() {
    return null;
  }

  /*
  @Override
  public HelloState getDataFromPreviousScreen() {
    HelloState state = mediator.getHelloState();
    return state;
  }
  */
}
