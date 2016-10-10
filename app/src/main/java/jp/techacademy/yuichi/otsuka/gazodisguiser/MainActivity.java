package jp.techacademy.yuichi.otsuka.gazodisguiser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Uri mFileUri;
    byte[] buffer1 = new byte[1048576];//読み込んだファイルを格納する配列
    byte[] buffer2 = new byte[1048576];//↑を変換した配列を格納する配列


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonFS = (Button) findViewById(R.id.fileSelectorButton);
        buttonFS.setOnClickListener(this);

        Button buttonGD = (Button) findViewById(R.id.fileDisguisingButton);
        buttonGD.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fileSelectorButton) {
            //ファイル選択ボタンが押されたときの動作
            fileSelectorIntentOpenAndGetFilePath();
        }
        if (v.getId() == R.id.fileDisguisingButton) {
            //ファイル変装ボタンが押されたときの動作
            fileDisguisingProcessStart();
        }
    }

    public void fileSelectorIntentOpenAndGetFilePath() {
        Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
        intent1.setType("*/*");
        startActivityForResult(intent1, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == 1111) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("UriEcho", "Uri: " + uri.toString());
                mFileUri = uri;
            }
        }
    }

    public void fileDisguisingProcessStart() {
        Log.d("FILEURI", mFileUri.toString());


        //ファイルの読み込み
        try {
            readDataFromUri(mFileUri);
        } catch (IOException e) {
        }


        //形態変換


        //ファイルへの書き込み
    }

    private void readDataFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);

        readAll(inputStream);
    }

    public byte[] readAll(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        while (true) {
            int len = inputStream.read(buffer1);
            if (len < 0) {
                break;
            }
            bout.write(buffer1, 0, len);
        }
        return bout.toByteArray();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}