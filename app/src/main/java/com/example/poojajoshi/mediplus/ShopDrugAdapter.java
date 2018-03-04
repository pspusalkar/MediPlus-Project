package com.example.poojajoshi.mediplus;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopDrugAdapter extends BaseAdapter {
    String [] drugNames;

    Context context;
    private static LayoutInflater inflater=null;

    // Create Custom Adapter
    public ShopDrugAdapter(ShopDrugActivity shopDrugActivity, String[] drugNameList) {
        // TODO Auto-generated constructor stub
        context = shopDrugActivity;
        drugNames = drugNameList;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return drugNames.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView textView_drugName;
        ImageView img_shop;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Create the each Row Item and fill with the name and number list

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.shop_drug_item, null);
        rowView.setMinimumHeight(200);
        rowView.setPadding(20, 5, 20, 5);

        holder.textView_drugName =(TextView) rowView.findViewById(R.id.textView);
        holder.img_shop = (ImageView) rowView.findViewById(R.id.imageView);

        holder.textView_drugName.setText(drugNames[position]);
        holder.img_shop.setImageResource(R.drawable.shop);

        holder.img_shop.setMaxHeight(5);
        holder.img_shop.setMaxWidth(5);
        holder.img_shop.setMinimumWidth(5);
        holder.img_shop.setMinimumHeight(5);

        return rowView;
    }
}
