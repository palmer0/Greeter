package es.ulpgc.eite.da.greeter.hello;

public class HelloModel implements HelloContract.Model {

  public static String TAG = HelloModel.class.getSimpleName();

  private String data;

  public HelloModel(String message) {
    data=message;
  }

  @Override
  public String getData() {
    //Log.e(TAG, "getData()");
    return data;
  }

}
