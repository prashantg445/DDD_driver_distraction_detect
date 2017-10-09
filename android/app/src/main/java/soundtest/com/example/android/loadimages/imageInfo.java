package soundtest.com.example.android.loadimages;

import android.graphics.Bitmap;

/**
 * Created by This Pc on 10/8/2017.
 */

public class imageInfo {

    private String imageUrl;
    //private Bitmap imageBitmap;
    public imageInfo(String imageUrl) {
        this.imageUrl = imageUrl;
        //this.imageBitmap=imageBitmap;
    }
    /*public Bitmap getImageBitmap() {
        return imageBitmap;
    }
    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }*/
        public String getImageUrl() {
            return imageUrl;
        }
        public void setImageUrl(String songUrl) {
            this.imageUrl = imageUrl;
        }
    }


