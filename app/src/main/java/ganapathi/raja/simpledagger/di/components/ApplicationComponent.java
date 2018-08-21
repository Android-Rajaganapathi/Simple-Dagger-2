package ganapathi.raja.simpledagger.di.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ganapathi.raja.simpledagger.MyApplication;
import ganapathi.raja.simpledagger.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApplication application);

    Context getContext();

    Application getApplication();
}
