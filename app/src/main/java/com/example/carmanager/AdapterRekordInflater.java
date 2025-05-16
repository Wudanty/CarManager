package com.example.carmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class AdapterRekordInflater extends ArrayAdapter<AdapterRekordRaports> {

    private int resourceLayout;
    private Context mContext;

    public AdapterRekordInflater(@NonNull Context context, int resource, @NonNull List<AdapterRekordRaports> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(resourceLayout, null);
        }

        AdapterRekordRaports adaperRekord = getItem(position);

        if (adaperRekord != null) {
            if(adaperRekord.getDataType()==1){
                TextView tvAdapterMileage = (TextView) view.findViewById(R.id.tvAdapterWeight);
                TextView tvAdaperMileageDiff = (TextView) view.findViewById(R.id.tvAdapterDifference);
                TextView tvAdapterDate = (TextView) view.findViewById(R.id.tvAdapterDate);
                if (tvAdapterMileage != null) {
                    tvAdapterMileage.setText(adaperRekord.getData1());
                }
                if (tvAdaperMileageDiff != null) {
                    tvAdaperMileageDiff.setText(adaperRekord.getDiff1());
                }
                if (tvAdapterDate != null) {
                    tvAdapterDate.setText(adaperRekord.getDate());
                }
            }
            else if(adaperRekord.getDataType()==0){
                TextView tvAdapterData1 = (TextView) view.findViewById(R.id.tvAdapterData1);
                TextView tvAdapterData2 = (TextView) view.findViewById(R.id.tvAdapterData2);
                //TextView tvAdapterData3 = (TextView) view.findViewById(R.id.tvAdapterData3);
                TextView tvAdapterDate = (TextView) view.findViewById(R.id.tvAdapterDate);
                if (tvAdapterData1 != null) {
                    tvAdapterData1.setText(adaperRekord.getData1());
                }
                if (tvAdapterData2 != null) {
                    tvAdapterData2.setText(adaperRekord.getData2());
                }
                /*if (tvAdapterData3 != null) {
                    tvAdapterData3.setText(adaperRekord.getData3());
                }*/
                if (tvAdapterDate != null) {
                    tvAdapterDate.setText(adaperRekord.getDate());
                }
            }
            else if(adaperRekord.getDataType()==2) {

                TextView tvAdapterData1 = (TextView) view.findViewById(R.id.tvAdapterData1);
                TextView tvAdapterData2 = (TextView) view.findViewById(R.id.tvAdapterData2);
                TextView tvAdapterDate = (TextView) view.findViewById(R.id.tvAdapterDate);
                if (tvAdapterData1 != null) {
                    tvAdapterData1.setText(adaperRekord.getData1());
                }
                if (tvAdapterData2 != null) {
                    tvAdapterData2.setText(adaperRekord.getData2());
                }
                if (tvAdapterDate != null) {
                    tvAdapterDate.setText(adaperRekord.getDate());
                }

            }
            else if(adaperRekord.getDataType()==3) {
                TextView tvAdapterData1 = (TextView) view.findViewById(R.id.tvAdapterData1);
                TextView tvAdapterData2 = (TextView) view.findViewById(R.id.tvAdapterData2);
                TextView tvAdapterDate = (TextView) view.findViewById(R.id.tvAdapterDate);
                if (tvAdapterData1 != null) {
                    tvAdapterData1.setText(adaperRekord.getData1());
                }
                if (tvAdapterData2 != null) {
                    tvAdapterData2.setText(adaperRekord.getData2());
                }
                if (tvAdapterDate != null) {
                    tvAdapterDate.setText(adaperRekord.getDate());
                }
            }
            else if(adaperRekord.getDataType()==4) {
                TextView tvAdapterData1 = (TextView) view.findViewById(R.id.tvAdapterData1);
                TextView tvAdapterData2 = (TextView) view.findViewById(R.id.tvAdapterData2);
                TextView tvAdapterDate = (TextView) view.findViewById(R.id.tvAdapterDate);
                if (tvAdapterData1 != null) {
                    tvAdapterData1.setText(adaperRekord.getData1());
                }
                if (tvAdapterData2 != null) {
                    tvAdapterData2.setText(adaperRekord.getData2());
                }
                if (tvAdapterDate != null) {
                    tvAdapterDate.setText(adaperRekord.getDate());
                }
            }
        }
        return view;
    }
}
