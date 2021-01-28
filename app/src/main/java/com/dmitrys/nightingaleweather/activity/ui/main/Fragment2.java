package com.dmitrys.nightingaleweather.activity.ui.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.dmitrys.nightingaleweather.DBCity;
import com.dmitrys.nightingaleweather.R;

/**
 * The {@link Fragment} responsible
 * For displaying the search
 * History.
 */
public class Fragment2 extends Fragment implements View.OnClickListener {

    private static DBCity dbCity;

    private int[] elementsId;
    private int[] backgrounds = new int[2];

    private LinearLayout linLayout;
    private LayoutInflater ltInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbCity = new DBCity();
    }

    @Override
    public void onResume() {
        super.onResume();
        linLayout = (LinearLayout) getActivity().findViewById(R.id.listOfCities);

        ltInflater = getLayoutInflater();

        backgrounds[0] = R.color.bg_login;
        backgrounds[1] = R.color.bg_main;

        loadList();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }

    @Override
    public void onClick(View view) {
        String lastDate = getString(R.string.last_date);
        String humidity = getString(R.string.humidity);
        String pressure = getString(R.string.pressure);
        String pressureUnit = getString(R.string.pressure_unit);
        final int viewId = view.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(dbCity.getListOfNames().get(view.getId()) +
                ", " + dbCity.getListOfCountries().get(view.getId()))
                .setMessage(
                        dbCity.getListOfDescriptions().get(view.getId()) + "\n" +
                                dbCity.getListOfTemperature().get(view.getId()) + "\n" +
                                lastDate + ": " + dbCity.getListOfDates().get(view.getId()) + "\n" +
                                humidity + ": " + dbCity.getListOfHumidity().get(view.getId()) + "\n" +
                                pressure + ": " + dbCity.getListOfPressure().get(view.getId()) + " " + pressureUnit
                )
                //.setIcon(R.drawable.logo)
                .setCancelable(true);
        AlertDialog alert = builder.create();
        alert.show();


    }

    private void loadList() {
        linLayout.removeAllViews();
        dbCity.loadData();

        elementsId = new int[dbCity.getListOfNames().size()];

        for (int i = 0; i < elementsId.length; i++) {
            elementsId[i] = i;
        }

        elementsId = new int[dbCity.getListOfNames().size()];

        for (int i = 0; i < elementsId.length; i++) {
            elementsId[i] = i;
        }

        for (int i = dbCity.getListOfNames().size() - 1; i > 0; i--) {
            View item = ltInflater.inflate(R.layout.city, linLayout, false);
            item.setId(elementsId[i]);
            item.setOnClickListener(this);
            TextView tvName = (TextView) item.findViewById(R.id.tvCityList);
            tvName.setText(dbCity.getListOfNames().get(i));
            TextView tvTemperature = (TextView) item.findViewById(R.id.tvDegreesList);
            tvTemperature.setText(dbCity.getListOfTemperature().get(i));
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundResource(backgrounds[i % 2]);
            linLayout.addView(item);
        }
    }
}