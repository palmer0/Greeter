package es.ulpgc.eite.da.greeter;

import android.app.Application;

import es.ulpgc.eite.da.greeter.hello.HelloState;

public class AppMediator extends Application {

  private HelloState helloState;
  //private HelloState helloState = new HelloState();

  @Override
  public void onCreate() {
    super.onCreate();

    helloState = new HelloState();
  }

//  private static AppMediator INSTANCE;
//
//  private AppMediator() {
//    helloState = new HelloState();
//  }
//
//  public static AppMediator getInstance() {
//    if(INSTANCE == null) {
//      INSTANCE = new AppMediator();
//    }
//
//    return INSTANCE;
//  }

  public void setHelloState(HelloState state) {
    helloState=state;
  }

  public HelloState getHelloState() {
    return helloState;
  }

}
