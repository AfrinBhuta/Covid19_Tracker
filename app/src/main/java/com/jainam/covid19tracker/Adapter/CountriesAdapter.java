package com.jainam.covid19tracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jainam.covid19tracker.Activities.TrackCountriesActivity;
import com.jainam.covid19tracker.Model.Countries;
import com.jainam.covid19tracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends ArrayAdapter<Countries> {
    private Context context;
    private List<Countries> countryModelsList;
    private List<Countries> countryModelsListFiltered;

    public CountriesAdapter(@NonNull Context context, List<Countries> countryModelsList) {
        super(context, R.layout.affected_countries_layout, countryModelsList);
        this.context = context;
        this.countryModelsList = countryModelsList;
        this.countryModelsListFiltered = countryModelsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.affected_countries_layout, parent, false);


        TextView countriesnameTextView = convertView.findViewById(R.id.country_name_textview);
        ImageView countriesflagImageView = convertView.findViewById(R.id.country_flag_imageview);

        countriesnameTextView.setText(countryModelsListFiltered.get(position).getCountriesname());
        Picasso.get().load(countryModelsListFiltered.get(position).getCountriesflagurl()).into(countriesflagImageView);

        return convertView;

    }

    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Nullable
    @Override
    public Countries getItem(int position) {
        return countryModelsListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelsList.size();
                    filterResults.values = countryModelsList;

                }else{
                    List<Countries> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Countries itemsModel:countryModelsList){
                        if(itemsModel.getCountriesname().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelsListFiltered = (List<Countries>) results.values;
                TrackCountriesActivity.countryModelsList = (List<Countries>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}