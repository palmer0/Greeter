package es.ulpgc.eite.da.greeter;

import org.junit.Test;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.greeter.hello.HelloContract;
import es.ulpgc.eite.da.greeter.hello.HelloModel;
import es.ulpgc.eite.da.greeter.hello.HelloPresenter;
import es.ulpgc.eite.da.greeter.hello.HelloState;
import es.ulpgc.eite.da.greeter.hello.HelloViewModel;

import static org.junit.Assert.assertEquals;

public class HelloUnitTests {

  private static final String EMPTY_MSG = "???";
  private static final String HELLO_MSG = "Hello";
  private static final String BYE_MSG = "Bye";

  @Test
  public void modelFetchData() {

    // Given
    HelloContract.Model model = new HelloModel(HELLO_MSG);

    // When
    String data = model.getData();

    // Then
    assertEquals(HELLO_MSG, data);
  }

  @Test
  public void modelFetchCleanedData() {

    class MockHelloModel implements HelloContract.Model {

      private String data;

      public void setData(String data) {
        this.data = data;
      }

      @Override
      public String getData() {
        return data;
      }
    }

    // Given
    HelloContract.Model model = new MockHelloModel();
    ((MockHelloModel) model).setData(EMPTY_MSG);

    // When
    String data = model.getData();

    // Then
    assertEquals(EMPTY_MSG, data);
  }

  @Test
  public void presenterFetchData() {

    class MockHelloActivity implements HelloContract.View{

      private String data;

      public String getData() {
        return data;
      }

      @Override
      public void injectPresenter(HelloContract.Presenter presenter) {

      }

      @Override
      public void displayData(HelloViewModel viewModel) {
        data=viewModel.data;
      }

      @Override
      public void navigateToNextScreen() {

      }
    }

    // Given
    AppMediator mediator = AppMediator.getInstance();
    HelloContract.Presenter presenter = new HelloPresenter(mediator);
    HelloContract.Model model = new HelloModel(HELLO_MSG);
    presenter.injectModel(model);
    HelloContract.View view = new MockHelloActivity();
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    String data = ((MockHelloActivity) view).getData();
    assertEquals(HELLO_MSG, data);
  }

  @Test
  public void presenterFetchCleanedData() {

    class MockHelloModel implements HelloContract.Model {

      private String data;

      public void setData(String data) {
        this.data = data;
      }

      @Override
      public String getData() {
        return data;
      }
    }

    class MockHelloActivity implements HelloContract.View{

      private String data;

      public String getData() {
        return data;
      }

      @Override
      public void injectPresenter(HelloContract.Presenter presenter) {

      }

      @Override
      public void displayData(HelloViewModel viewModel) {
        data=viewModel.data;
      }

      @Override
      public void navigateToNextScreen() {

      }
    }

    // Given
    AppMediator mediator = AppMediator.getInstance();
    HelloContract.Presenter presenter = new HelloPresenter(mediator);
    HelloContract.Model model = new MockHelloModel();
    ((MockHelloModel) model).setData(EMPTY_MSG);
    presenter.injectModel(model);
    HelloContract.View view = new MockHelloActivity();
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    String data = ((MockHelloActivity) view).getData();
    assertEquals(EMPTY_MSG, data);
  }

  @Test
  public void presenterFetchSavedData() {

    class MockHelloActivity implements HelloContract.View{

      private String data;

      public String getData() {
        return data;
      }

      @Override
      public void injectPresenter(HelloContract.Presenter presenter) {

      }

      @Override
      public void displayData(HelloViewModel viewModel) {
        data=viewModel.data;
      }

      @Override
      public void navigateToNextScreen() {

      }
    }

    // Given
    AppMediator mediator = AppMediator.getInstance();
    HelloContract.Presenter presenter = new HelloPresenter(mediator);
    HelloContract.Model model = new HelloModel(HELLO_MSG);
    presenter.injectModel(model);
    HelloState savedState = new HelloState();
    savedState.data=BYE_MSG;
    mediator.setHelloState(savedState);
    HelloContract.View view = new MockHelloActivity();
    presenter.injectView(new WeakReference<>(view));

    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    String data = ((MockHelloActivity) view).getData();
    assertEquals(BYE_MSG, data);
  }
}