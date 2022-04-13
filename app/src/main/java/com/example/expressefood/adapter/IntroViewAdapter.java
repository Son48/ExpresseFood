package com.example.expressefood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.expressefood.R;
import com.example.expressefood.model.ScreenItem;

import java.util.List;

public class IntroViewAdapter extends PagerAdapter {
    Context context;
    List<ScreenItem> screenItemList;
    TextView title, des;
    ConstraintLayout constraintLayout;

    public IntroViewAdapter(Context context, List<ScreenItem> screenItemList) {
        this.context = context;
        this.screenItemList = screenItemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutscreen = inflater.inflate(R.layout.layout_screen, null);


        title = layoutscreen.findViewById(R.id.txtTitleScreen);
        des = layoutscreen.findViewById(R.id.txtDescriptionScreen);
        constraintLayout = layoutscreen.findViewById(R.id.imgScreenLayout);
        title.setText(screenItemList.get(position).getTitle());
        des.setText(screenItemList.get(position).getDescription());
        constraintLayout.setBackgroundResource(screenItemList.get(position).getUrl_img());
        container.addView(layoutscreen);
        return layoutscreen;

    }

    @Override
    public int getCount() {
        return screenItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}

