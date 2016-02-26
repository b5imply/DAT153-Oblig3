package com.example.testing.dat153_oblig3.classes;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;

/**
 * This class is for inputs and outputs. Usually to get data from outside or write data of the app.
 */
public class IOclass {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    /**
     * Gets a list of ImgeItems objects in which the images is tumbnails.
     * @param activity : for getting the resolver and contexts.
     * @return returns the array of image-objects.
     */
    public static ArrayList<ImageItem> getListOfImages(Activity activity) {

        Uri uri;
        ArrayList<ImageItem> listOfAllImages = new ArrayList<ImageItem>();
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        String PathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        File sd = Environment.getExternalStorageDirectory();
        File image;
        while (cursor.moveToNext()) {
            PathOfImage = cursor.getString(column_index_data);
            image = setImagePath(PathOfImage, sd);
            Bitmap bitmap = setBitmap(image);

            listOfAllImages.add(new ImageItem(bitmap, PathOfImage, image.getName()));
        }
        return listOfAllImages;
    }

    private static Bitmap setBitmap(File image) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
        bmOptions.inSampleSize = calculateInSampleSize(bmOptions, WIDTH, HEIGHT);
        bmOptions.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
    }

    @NonNull
    private static File setImagePath(String pathOfImage, File sd) {
        File image;
        if (Environment.getExternalStorageState().equals("mounted"))
            image = new File(pathOfImage);
        else
            image = new File(sd + pathOfImage);
        return image;
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
