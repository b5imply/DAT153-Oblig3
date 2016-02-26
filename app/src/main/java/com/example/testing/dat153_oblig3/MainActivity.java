package com.example.testing.dat153_oblig3;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.testing.dat153_oblig3.classes.IOclass;
import com.example.testing.dat153_oblig3.classes.ImageItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private boolean connectedToChromecast = false;
    private static ArrayList<ImageItem> listOfImages = new ArrayList<ImageItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (listOfImages.isEmpty())
            listOfImages = IOclass.getListOfImages(this);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, listOfImages);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                if (connectedToChromecast) {

                } else {
                    Toast.makeText(getApplicationContext(), "Picked item:" + item.getTitle(), Toast.LENGTH_LONG).show();
                    //Create intent for when a image is clicked.
                    //Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    //intent.putExtra("title", item.getTitle());
                    //intent.putExtra("image", item.getImage());

                    //Start details activity
                    //startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Prepare some dummy data for gridview
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            Drawable myDrawable = getResources().getDrawable(R.mipmap.blank_person);
            Bitmap bitmap = ((BitmapDrawable) myDrawable).getBitmap();
            imageItems.add(new ImageItem(bitmap, "internal", "Image#" + i));
        }
        return imageItems;
    }

}
