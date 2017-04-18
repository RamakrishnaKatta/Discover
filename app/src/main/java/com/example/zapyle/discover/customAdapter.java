package com.example.zapyle.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by zapyle on 27/6/16.
 */
public class customAdapter extends BaseAdapter {

    ArrayList<String> image=new ArrayList<String>();
    ArrayList<Integer> id=new ArrayList<Integer>();
    Context context;
    LayoutInflater inflater;

    public customAdapter(Context context, ArrayList<String> imagearray, ArrayList<Integer> integerarray) {
        this.image=imagearray;
        this.id=integerarray;
        this.context=context;
        inflater=LayoutInflater.from(this.context);

    }


    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public class Holder
    {
        TextView textView;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View view;
        view=inflater.inflate(R.layout.discover,null);
        holder.imageView=(ImageView)view.findViewById(R.id.image);
        holder.textView=(TextView)view.findViewById(R.id.id1);
        holder.textView.setText(String.valueOf(id.get(position)));
        Picasso.with(context)
                .load(image.get(position))
                .into(holder.imageView);


        return null;
    }
}
