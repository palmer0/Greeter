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
  HelloContract.Router mockRouter;

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
    when(mockRouter.getDataFromPreviousScreen()).thenReturn(null);
    HelloContract.Presenter presenter = new HelloPresenter(new HelloState());
    String message = mockContext.getString(R.string.hello_message);
    HelloContract.Model model = new HelloModel(message);
    presenter.injectModel(model);
    presenter.injectRouter(mockRouter);
    presenter.injectView(new WeakReference<>(mockView));

    /*
    doAnswer(new Answer() {

      @Override
      public HelloViewModel answer(InvocationOnMock invocation) {

        Object[] args = invocation.getArguments();
        HelloViewModel viewModel = (HelloViewModel) args[0];
        return viewModel;
      }
    }).when(mockView).displayData(any(HelloViewModel.class));
    */

    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    verify(mockRouter, times(1))
        .getDataFromPreviousScreen();
    /*
    verify(mockView, times(1))
        .displayData(any(HelloViewModel.class));
    */
    verify(mockView, times(1))
        .displayData(vmCaptor.capture());
    String data = vmCaptor.getValue().data;
    assertEquals(HELLO_MSG, data);
  }


  @Test
  public void presenterCleanedData() {

    // Given
    when(mockContext.getString(R.string.empty_message)).thenReturn(EMPTY_MSG);
    when(mockRouter.getDataFromPreviousScreen()).thenReturn(null);
    HelloContract.Presenter presenter = new HelloPresenter(new HelloState());
    HelloContract.Model model =
        new HelloModel(mockContext.getString(R.string.empty_message));
    presenter.injectModel(model);
    presenter.injectRouter(mockRouter);
    presenter.injectView(new WeakReference<>(mockView));


    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    verify(mockRouter, times(1))
        .getDataFromPreviousScreen();
    verify(mockView, times(1))
        .displayData(vmCaptor.capture());
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
    when(mockRouter.getDataFromPreviousScreen()).thenReturn(state);
    HelloContract.Presenter presenter = new HelloPresenter(new HelloState());
    HelloContract.Model model =
        new HelloModel(mockContext.getString(R.string.hello_message));
    presenter.injectModel(model);
    presenter.injectRouter(mockRouter);
    presenter.injectView(new WeakReference<>(mockView));


    // When
    presenter.onStart();
    presenter.onResume();

    // Then
    verify(mockRouter, times(1))
        .getDataFromPreviousScreen();
    verify(mockView, times(1))
        .displayData(vmCaptor.capture());
    String data = vmCaptor.getValue().data;
    assertEquals(BYE_MSG, data);
  }

}
