package es.ulpgc.eite.da.greeter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.da.greeter.hello.HelloActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HelloInstrumentedTests {

  private static final String EMPTY_MSG = "???";
  private static final String HELLO_MSG = "Hello";
  private static final String BYE_MSG = "Bye";

  private static final long DELAY = 2000;


  @Rule
  public ActivityTestRule<HelloActivity> activityTestRule =
      new ActivityTestRule(HelloActivity.class );

  @Test
  public void modelUpdateData() {

    // given
    delayTest(DELAY);
    (onView(withId(R.id.greetTextView))).check(matches(withText(HELLO_MSG)));

    // when
    (onView(withId(R.id.greetButton))).perform(click());

    // then
    delayTest(DELAY);
    (onView(withId(R.id.greetTextView))).check(matches(withText(BYE_MSG)));
  }

  @Test
  public void modelUpdateDataWithRotation() {

    // given
    delayTest(DELAY);
    (onView(withId(R.id.greetTextView))).check(matches(withText(HELLO_MSG)));

    // when
    (onView(withId(R.id.greetButton))).perform(click());
    delayTest(DELAY);
    rotateScreen();

    // then
    delayTest(DELAY);
    (onView(withId(R.id.greetTextView))).check(matches(withText(BYE_MSG)));
  }

  private void rotateScreen() {
    Context context = ApplicationProvider.getApplicationContext();
    int orientation = context.getResources().getConfiguration().orientation;
    Activity activity = activityTestRule.getActivity();

    if(orientation  == Configuration.ORIENTATION_PORTRAIT) {
      activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    } else {
      activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
  }

  private void delayTest(long msecs){

    try {
      Thread.sleep(msecs);
    } catch (InterruptedException e) {

    }
  }
}
