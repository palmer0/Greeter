package es.ulpgc.eite.da.greeter;

import es.ulpgc.eite.da.greeter.hello.HelloState;

public class AppMediator {

  private HelloState helloState;

  private static AppMediator INSTANCE;

  private AppMediator() {
    helloState = new HelloState();
  }

  public static AppMediator getInstance() {
    if(INSTANCE == null) {
      INSTANCE = new AppMediator();
    }

    return INSTANCE;
  }

  public static void resetInstance() {
    INSTANCE = null;
  }

  public void setHelloState(HelloState state) {
    helloState=state;
  }

  public HelloState getHelloState() {
    return helloState;
  }

}
