package ganapathi.raja.simpledagger.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import ganapathi.raja.simpledagger.MyApplication;
import ganapathi.raja.simpledagger.R;
import ganapathi.raja.simpledagger.di.components.ActivityComponent;
import ganapathi.raja.simpledagger.di.components.DaggerActivityComponent;
import ganapathi.raja.simpledagger.di.module.ActivityModule;
import ganapathi.raja.simpledagger.model.Model;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    Model model;

    private ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mComponent = DaggerActivityComponent
                .builder()
                .applicationComponent(((MyApplication) getApplication()).getComponent())
                .activityModule(new ActivityModule())
                .build();
        mComponent.inject(this);

        model.bindUserList();

        String s = "";

        for (int i = 0; i < model.getUserList().size(); i++) {
            System.out.println("model = " + model.getUserList().get(i).getName());
            s = String.format("%s %s %s \n", s, model.getUserList().get(i).getName(), model.getUserList().get(i).getPhone());
        }

        TextView tv = findViewById(R.id.tv);
        tv.setText(s);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mComponent = null;
    }
}
