package com.jainam.covid19tracker.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.jainam.covid19tracker.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class CountriesDetailsActivity extends AppCompatActivity {
    private int positionCountry;
    TextView tvCountry, tvCases, tvRecovered, tvCritical, tvActive, tvTodayCases, tvTotalDeaths, tvTodayDeaths, tvcasesPerOneMillion, tvdeathsPerOneMillion, tvtests;
    PieChart countryPieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_details);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position", 0);

        getSupportActionBar().setTitle("Details of " + TrackCountriesActivity.countryModelsList.get(positionCountry).getCountriesname());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        countryPieChart = findViewById(R.id.country_piechart);
        tvCountry = findViewById(R.id.country_textview);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvcasesPerOneMillion = findViewById(R.id.tvcasesPerOneMillion);
        tvdeathsPerOneMillion = findViewById(R.id.tvdeathsPerOneMillion);
        tvtests = findViewById(R.id.tvtests);


        tvCountry.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getCountriesname());
        tvCases.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getCases());
        tvRecovered.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getRecovered());
        tvCritical.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getCritical());
        tvActive.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getActive());
        tvTodayCases.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getTodayDeaths());
        tvcasesPerOneMillion.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getCasesPerOneMillion());
        tvdeathsPerOneMillion.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getDeathsPerOneMillion());
        tvtests.setText(TrackCountriesActivity.countryModelsList.get(positionCountry).getTests());


        countryPieChart.addPieSlice(new PieModel("cases", Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
        countryPieChart.addPieSlice(new PieModel("recovered", Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
        countryPieChart.addPieSlice(new PieModel("deaths", Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
        countryPieChart.addPieSlice(new PieModel("active", Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
        countryPieChart.startAnimation();



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
