package ganapathi.raja.simpledagger.di.components;

import dagger.Component;
import ganapathi.raja.simpledagger.di.ActivityScope;
import ganapathi.raja.simpledagger.di.module.ActivityModule;
import ganapathi.raja.simpledagger.view.DaggerActivity;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(DaggerActivity activity);

}
