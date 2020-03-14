package es.ulpgc.eite.da.greeter.hello;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.greeter.AppMediator;
import es.ulpgc.eite.da.greeter.R;

public class HelloScreen {

  public static void configure(HelloContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    String message = context.get().getString(R.string.hello_message);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    HelloState state = mediator.getHelloState();

    HelloContract.Router router = new HelloRouter(mediator);
    HelloContract.Presenter presenter = new HelloPresenter(state);
    HelloContract.Model model = new HelloModel(message);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
