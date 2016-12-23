package com.tahirietrit.imagecompression;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Path of the selected photo
        //Todo change this to your dir.
        String photoPath = Environment.getExternalStorageDirectory() + "/gjirafa.JPG";
        String compressedDir = Environment.getExternalStorageDirectory()+"/";
        String compressedName= "compressedImage.JPG";
        compressImage(photoPath, compressedDir, compressedName, 90);


    }
    // photoPath -> path of the image you want to compress
    // compressedDir -> directory where you want to save the compressed image
    // compressedName -> name of the compressed image
    // quality -> quality of the compressed image 0-100;
    private void compressImage(final String photoPath, final String compressedDir, final String compressedName, final int quality) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
        File dir = new File(compressedDir);
        if (!dir.exists())
            dir.mkdirs();
        File reportPicture = new File(dir, compressedName);
        try {
            FileOutputStream fOut = new FileOutputStream(reportPicture);

            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
