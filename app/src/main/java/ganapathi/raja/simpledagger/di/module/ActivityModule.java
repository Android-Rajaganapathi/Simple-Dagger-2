package ganapathi.raja.simpledagger.di.module;

import dagger.Module;
import dagger.Provides;
import ganapathi.raja.simpledagger.model.Model;

@Module
public class ActivityModule {

    @Provides
    public Model provideModel() {
        return new Model();
    }

}
