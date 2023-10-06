package com.example.communicationapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageSlideActivity extends AppCompatActivity {

    Button forward, backward;
    ImageView imageView;
    int index = 0;

    int[] images = {
            R.drawable.archite,
            R.drawable.dog,
            R.drawable.hacker
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slide);

        forward = findViewById(R.id.btnforward); // Corrected findViewById assignments
        backward = findViewById(R.id.btnback);
        imageView = findViewById(R.id.imageView);

        // Set the initial image
        imageView.setImageResource(images[index]);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index == images.length) index = 0;
                imageView.setImageResource(images[index]);
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if (index < 0) index = images.length - 1;
                imageView.setImageResource(images[index]);
            }
        });
    }
}
