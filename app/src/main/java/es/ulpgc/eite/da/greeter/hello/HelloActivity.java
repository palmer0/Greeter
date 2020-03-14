package es.ulpgc.eite.da.greeter.hello;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.greeter.R;

public class HelloActivity
    extends AppCompatActivity implements HelloContract.View {

  public static String TAG = HelloActivity.class.getSimpleName();

  private HelloContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hello);
    //Log.e(TAG, "onCreate()");

    // do the setup
    HelloScreen.configure(this);

    if(savedInstanceState == null) {
      //presenter.resetData();
      presenter.onStart();

    } else {
      presenter.onRestart();
    }
  }


  @Override
  protected void onResume() {
    super.onResume();
    //Log.e(TAG, "onResume()");

    // load the data
    presenter.onResume();
    //presenter.getData();
  }

//  @Override
//  protected void onStart() {
//    super.onStart();
//    Log.e(TAG, "onStart()");
//  }
//
//  @Override
//  protected void onRestart() {
//    super.onRestart();
//    Log.e(TAG, "onRestart()");
//  }
//
//  @Override
//  protected void onDestroy() {
//    super.onDestroy();
//    Log.e(TAG, "onDestroy()");
//  }

  @Override
  public void displayData(HelloViewModel viewModel) {
    //Log.e(TAG, "displayData()");

    /*
    AppMediator mediator = (AppMediator) getApplication();
    HelloState state = mediator.getHelloState();
    if(state != null) {
      Log.e(TAG, "data=" + state.data);
    }
    */

    // deal with the data
    ((TextView) findViewById(R.id.greetTextView)).setText(viewModel.data);
  }

  @Override
  public void injectPresenter(HelloContract.Presenter presenter) {
    this.presenter = presenter;
  }

  public void greetButtonClicked(View view) {
    //presenter.updateData(getString(R.string.bye_message));
    presenter.onGreetButtonClicked(getString(R.string.bye_message));
  }
}
