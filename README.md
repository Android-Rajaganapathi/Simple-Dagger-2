# SimpleDagger

A simple dagger 2 integration app. 

The base flow of this project is to store some user's name and phone number which is available in the model class and print them.
This Model.java will be acting as an dependent to be injected in the activity.
 
 * ### Step One: 
   >Add respective gradle dependencies(Check for versions when you add). 
   ```
   implementation 'com.google.dagger:dagger:2.16'
   annotationProcessor 'com.google.dagger:dagger-compiler:2.16'
   ``` 

 * ### Then create Modules: 
   Create the ApplicationModule.java and annotate with @Module.
   This class will be the projects main module, which will be providing the application's context and the application's instance. @Provides annotation is used for providing instances inside a module.
   
   >Your ApplicationModule.java will be something like this
   ```java
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

   ```
   
   Same as ApplicationModule create ActivityModule.java Module
   This class provides the required entities for an activity.
   Here we are in need of a model, so provide the model here.
   
   >And ActivityModule.java class will be like this
   ```java
    @Module
    public class ActivityModule {

      @Provides
      public Model provideModel() {
        return new Model();
      }

    }
   ```
 * ### Now go for creating Components: 
   The main job of the components is to connect modules with the activity.
   So this should be an interface class.

   Create an ApplicationComponent.java interface.
   Annotate with @Singleton, because this will be our application's main instance to be travelled untill our app dies.
   Then declare the respective module with this component annotation. 

   > The ApplicationComponent will be like this
   ```java
    @Singleton
    @Component(modules = ApplicationModule.class)
    public interface ApplicationComponent {

        void inject(MyApplication application);

        Context getContext();

        Application getApplication();
    }
    ```

   Now create ActivityComponent.java interface.
   This should be annotated with some custom scope, because this is our sub module and which is similar like sub-singleton.
   So it has to be annotated with custom scope. I have used @ActivityScope here.
   
   > Your ActivityComponent will be like this
   ```java
    @ActivityScope
    @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
    public interface ActivityComponent {

        void inject(DaggerActivity activity);

    }
   ```
   _ApplicationComponent.class is the main dependencies, so it is declared as dependencies, and the ActivityModule is declared as modules_


 * ### Now lets start injecting stuffs:
   _Before building stuffs dont forget to rebuild your project. Only then you'll get your dagger generated codes_

   >In your application class initialize your ApplicationModule.
   ```java
    ApplicationComponent mComponent = DaggerApplicationComponent
          .builder()
          .applicationModule(new ApplicationModule(this))
          .build();
   ```
   >In your Activity class initialize your ActivityModule.
   ```java
    ActivityComponent mComponent = DaggerActivityComponent
            .builder()
            .applicationComponent(((MyApplication) getApplication()).getComponent())
            .activityModule(new ActivityModule())
            .build();
    mComponent.inject(this);
   ```
    By injecting your `Model` class, those stuffs can be used.
    ```java
      @Inject Model model;
    ```
