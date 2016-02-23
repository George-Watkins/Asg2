package git.comgeorge_watkinsasg2.httpsgithub.asg2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class Asg2 extends AppCompatActivity {

    ImageView newPicture;
    private GoogleApiClient client;
    ImageView cameraButton;
    File file;
    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asg2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        newPicture = (ImageView)findViewById(R.id.newPicture);
        cameraButton = (ImageView)findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraButton.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                file = new File(path, "foo.jpg");
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
                doReset();
                return true;
            case R.id.share:
                doShare();
                return true;
            case R.id.about:
                doHelp();
                return true;
            case R.id.action_edit:
                doEdit();
                return true;
            case R.id.action_colorize:
                doColorize();
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

    //makes the captured image show up on the screen after picture is taken
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        newPicture.setVisibility(View.VISIBLE);
        if(requestCode == 0) {
            Bitmap theImage = (Bitmap) data.getExtras().get("data");
            newPicture.setImageBitmap(theImage);
        }
    }

    //this will colorize the photo after it is taken
    public void doColorize() {
    }

    public void doReset() {
        cameraButton.setVisibility(View.VISIBLE);
        newPicture.setVisibility(View.INVISIBLE);
    }

    //this method will invoke app to share picture
    public void doShare() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpg");
        File photo = new File(path, "foo.jpg");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photo));
        startActivity(Intent.createChooser(share, "What application would you like to use?"));
    }

    //will change the picture to black and white
    public void doEdit() {
    }
}