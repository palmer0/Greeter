package es.ulpgc.eite.da.greeter;

import android.app.Activity;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import es.ulpgc.eite.da.greeter.hello.HelloActivity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(RobolectricTestRunner.class)
public class HelloRoboTests {

  private static final String EMPTY_MSG = "???";
  private static final String HELLO_MSG = "Hello";
  private static final String BYE_MSG = "Bye";

  @Test
  public void modelFetchData() {

    Activity activity = Robolectric.setupActivity(HelloActivity.class);
    TextView dataTextView = activity.findViewById(R.id.greetTextView);
    assertThat(dataTextView.getText().toString(), equalTo(HELLO_MSG));
  }

  @Test
  public void modelUpdateData() {

    // Given
    HelloActivity activity = Robolectric.setupActivity(HelloActivity.class);

    // When
    activity.greetButtonClicked(null);

    // Then
    TextView dataTextView = activity.findViewById(R.id.greetTextView);
    assertThat(dataTextView.getText().toString(), equalTo(BYE_MSG));
  }
}
