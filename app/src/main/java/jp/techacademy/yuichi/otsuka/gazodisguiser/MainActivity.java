package jp.techacademy.yuichi.otsuka.gazodisguiser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFS = (Button) findViewById(R.id.fileSelectorButton);
        buttonFS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}