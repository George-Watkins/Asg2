package git.comgeorge_watkinsasg2.httpsgithub.asg2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class Asg2 extends AppCompatActivity {

    ImageView newPicture;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asg2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        newPicture = (ImageView)findViewById(R.id.newPicture);
        ImageView cameraButton = (ImageView)findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File file = new File(path, "DemoPicture.jpg");
                startActivityForResult(intent, 0);
            }
        });
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_asg2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.settings:
                Toast.makeText(this, "settings goes here", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.reset:
                Toast.makeText(this, "reset goes here", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                Toast.makeText(this, "share goes here", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                doHelp();
                return true;
            case R.id.action_edit:
                Toast.makeText(this, "edit goes here", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_colorize:
                Toast.makeText(this, "colorize the picure here", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doHelp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Version One.  This app was created by Travis Smith and George Watkins");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Asg2.this, "clicked OK in Help", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Asg2 Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://git.comgeorge_watkinsasg2.httpsgithub.asg2/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }



//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Asg2 Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
////                 TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://git.comgeorge_watkinsasg2.httpsgithub.asg2/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }

    //makes the captured image show up on the screen after picture is taken
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        newPicture.setVisibility(View.VISIBLE);
        if(requestCode == 0) {
            Bitmap theImage = (Bitmap) data.getExtras().get("data");
            newPicture.setImageBitmap(theImage);
        }
    }

    //this will colorize the photo after it is taken
    public void doColorize(MenuItem item) {
    }

    public void doReset(MenuItem item) {
        newPicture.setVisibility(View.INVISIBLE);
    }

    //this method will invoke app to share picture
    public void doShare(MenuItem item) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpg");
        File photo = new File(getFilesDir(), "foo.jpg");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photo));
        startActivity(Intent.createChooser(share, "Share image using"));
    }

    //will change the picture to black and white
    public void doEdit(MenuItem item) {
    }
}
