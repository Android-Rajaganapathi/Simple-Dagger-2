package ganapathi.raja.simpledagger;

import android.app.Application;

import ganapathi.raja.simpledagger.di.components.ApplicationComponent;
import ganapathi.raja.simpledagger.di.components.DaggerApplicationComponent;
import ganapathi.raja.simpledagger.di.module.ApplicationModule;

public class MyApplication extends Application {

    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }
}
