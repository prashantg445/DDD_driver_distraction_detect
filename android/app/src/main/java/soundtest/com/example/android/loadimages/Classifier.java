package soundtest.com.example.android.loadimages;

/**
 * Created by This Pc on 10/8/2017.
 */
    public interface Classifier {
        String name();

        Classification recognize(final int[] pixels);
    }

