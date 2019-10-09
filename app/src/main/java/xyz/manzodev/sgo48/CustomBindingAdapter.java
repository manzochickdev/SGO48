package xyz.manzodev.sgo48;

import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class CustomBindingAdapter {
    @BindingAdapter("setImageUrl")
    public static void setImageUrl(ImageView view , String url){
        if (url!=null && url.trim().length()>0){

            Glide.with(view.getContext()).load(url).placeholder(R.mipmap.ic_launcher).into(view);
        }
        else Glide.with(view.getContext()).load(R.mipmap.ic_launcher).into(view);
    }

    @BindingAdapter("setFont")
    public static void setFont(TextView view, String path) {
        Typeface typeface = Typeface.createFromAsset(view.getContext().getAssets(),path);
        view.setTypeface(typeface);
    }
}
