package tech.android.tcmp13.camerademo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.camera:
                boolean hasCamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
                if (!hasCamera) {
                    Toast.makeText(this, "You have no camera", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) == null) {
                    Toast.makeText(this, "You have no camera APP", Toast.LENGTH_SHORT).show();
                    return false;
                }
                startActivityForResult(intent, 169);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //DONT DARE TO DO DIS 2222 production
        if (requestCode != 169)
            return;
        if (resultCode != RESULT_OK)
            return;
        //"data" is the key provided by the android native camera app, maybe other apps use different key!!!
        Bundle extras = data.getExtras();
        Bitmap resultImage = (Bitmap) extras.get("data");
        imageView.setImageBitmap(resultImage);
    }
}
