package soundtest.com.example.android.loadimages;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG =MainActivity.this.getClass().toString() ;
    private ArrayList<imageInfo> _images=new ArrayList<>();
    imageAdapter imageAdapter;
    RecyclerView recyclerView;
    //File file;
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    public static File dir;
    public static List<Classifier> mClassifiers = new ArrayList<>();
    private static final int PIXEL_WIDTH = 28;
    static TextView resultfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        resultfinal=(TextView)findViewById(R.id.result);
        loadModel();
         dir = new File( "/sdcard/pictures" );
        //Log.i(TAG, "onCreate: "+dir.exists());
        String[] fileNames = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (new File(dir,name).isDirectory())
                    return false;
                return name.toLowerCase().endsWith(".jpg");
            }
        });
        for(String bitmapFileName : fileNames) {

            imageInfo s1=new imageInfo(bitmapFileName);
            _images.add(s1);

        }
        imageAdapter=new imageAdapter(this,_images);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    private void loadModel() {
        //The Runnable interface is another way in which you can implement multi-threading other than extending the
        // //Thread class due to the fact that Java allows you to extend only one class. Runnable is just an interface,
        // //which provides the method run.
        // //Threads are implementations and use Runnable to call the method run().
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //add 2 classifiers to our classifier arraylist
                    //the tensorflow classifier and the keras classifier
                    mClassifiers.add(
                            TensorFlowClassifier.create(getAssets(), "Keras",
                                    "opt_mnist_convnet.pb","label.txt", PIXEL_WIDTH,
                                    "conv2d_1_input", "dense_2/Softmax", false));
                } catch (final Exception e) {
                    //if they aren't found, throw an error!
                    throw new RuntimeException("Error initializing classifiers!", e);
                }
            }
        }).start();
    }


}
