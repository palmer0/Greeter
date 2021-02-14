package es.ulpgc.eite.da.greeter;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.greeter.hello.HelloContract;
import es.ulpgc.eite.da.greeter.hello.HelloModel;
import es.ulpgc.eite.da.greeter.hello.HelloPresenter;
import es.ulpgc.eite.da.greeter.hello.HelloState;
import es.ulpgc.eite.da.greeter.hello.HelloViewModel;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloMockitoTests {

  private static final String EMPTY_MSG = "???";
  private static final String HELLO_MSG = "Hello";
  private static final String BYE_MSG = "Bye";

  @Mock
  Context mockContext;

  @Mock
  HelloContract.View mockView;

  @Captor
  ArgumentCaptor<HelloViewModel> vmCaptor;


  @Test
  public void modelFetchData() {

    // Given
    when(mockContext.getString(R.string.hello_message)).thenReturn(HELLO_MSG);
    HelloContract.Model model =
        new HelloModel(mockContext.getString(R.string.hello_message));

    // When
    String data = model.getData();

    // Then
    assertThat(data, is(HELLO_MSG));
  }

  @Test
  public void presenterFetchData() {

    // Given
    when(mockContext.getString(R.string.hello_message)).thenReturn(HELLO_MSG);
    AppMediator mediator = AppMediator.getInstance();
    HelloContract.Presenter presenter = new HelloPresenter(mediator);
    String message = mockContext.getString(R.string.hello_message);
    HelloContract.Model model = new HelloModel(message);
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(mockView));

    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    verify(
        mockView, times(1)
    ).displayData(vmCaptor.capture());
    String data = vmCaptor.getValue().data;
    assertEquals(HELLO_MSG, data);
  }


  @Test
  public void presenterCleanedData() {

    // Given
    when(mockContext.getString(R.string.empty_message)).thenReturn(EMPTY_MSG);
    AppMediator mediator = AppMediator.getInstance();
    HelloContract.Presenter presenter = new HelloPresenter(mediator);
    HelloContract.Model model =
        new HelloModel(mockContext.getString(R.string.empty_message));
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(mockView));


    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    verify(
        mockView, times(1)
    ).displayData(vmCaptor.capture());
    String data = vmCaptor.getValue().data;
    assertEquals(EMPTY_MSG, data);
  }

  @Test
  public void presenterSavedData() {

    // Given
    when(mockContext.getString(R.string.hello_message)).thenReturn(HELLO_MSG);
    when(mockContext.getString(R.string.bye_message)).thenReturn(BYE_MSG);
    HelloState state = new HelloState();
    state.data= mockContext.getString(R.string.bye_message);
    AppMediator mediator = AppMediator.getInstance();
    HelloContract.Presenter presenter = new HelloPresenter(mediator);
    HelloContract.Model model =
        new HelloModel(mockContext.getString(R.string.hello_message));
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(mockView));
    mediator.setHelloState(state);

    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    verify(
        mockView, times(1)
    ).displayData(vmCaptor.capture());
    String data = vmCaptor.getValue().data;
    assertEquals(BYE_MSG, data);
  }

}
