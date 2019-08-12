package com.example.android.tflitecamerademo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {

        this.context = context;
    }
    // img Array
    public int[] image_slide ={
            R.drawable.t01,
            R.drawable.t02,
            R.drawable.t03,
            R.drawable.t04,
            R.drawable.t10,
            R.drawable.t07,
            R.drawable.t05,
            R.drawable.t06,
            R.drawable.t09,
            R.drawable.t08
    };


    @Override
    public int getCount() {
        return image_slide.length;
    }

    @Override
    public boolean isViewFromObject(View view,Object o) {
        return view == o;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
        container.addView(view);

        ImageView slide_imageView = view.findViewById(R.id.imageView);

        slide_imageView.setImageResource(image_slide[position]);

        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
