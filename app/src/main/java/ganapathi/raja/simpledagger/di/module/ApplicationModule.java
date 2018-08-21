package ganapathi.raja.simpledagger.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ganapathi.raja.simpledagger.MyApplication;

@Module
public class ApplicationModule {

    private MyApplication mApplication;

    public ApplicationModule(MyApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApplication;
    }
}
