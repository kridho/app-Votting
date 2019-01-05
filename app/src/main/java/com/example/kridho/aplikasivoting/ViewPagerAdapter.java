package com.example.kridho.aplikasivoting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.pelantikan1,R.drawable.pelantikan2,R.drawable.pelantikan3,R.drawable.pelantikan4};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_dem, null);
        ImageView imageView =(ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Toast.makeText(context, "slide 1 Cliked",Toast.LENGTH_SHORT).show();
                } else  if (position == 1){
                    Toast.makeText(context, "slide 2 Cliked",Toast.LENGTH_SHORT).show();
                } else if(position == 2) {
                    Toast.makeText(context, "slide 3 Cliked",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "slide 4 Cliked",Toast.LENGTH_SHORT).show();
                }

            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View)object;
        vp.removeView(view);
    }
}


