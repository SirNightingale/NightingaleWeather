package com.dmitrys.nightingaleweather;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import static com.dmitrys.nightingaleweather.activity.MainActivity.getDBHelper;

public class DBCity {
    private List<String> listOfNames, listOfCountries, listOfDates, listOfDescriptions,
            listOfHumidity, listOfPressure, listOfTemperature;

    DBCityHelper dbHelper = getDBHelper();

    public List<String> getListOfNames() {
        return listOfNames;
    }

    public List<String> getListOfCountries() {
        return listOfCountries;
    }

    public List<String> getListOfDates() {
        return listOfDates;
    }

    public List<String> getListOfDescriptions() {
        return listOfDescriptions;
    }

    public List<String> getListOfHumidity() {
        return listOfHumidity;
    }

    public List<String> getListOfPressure() {
        return listOfPressure;
    }

    public List<String> getListOfTemperature() {
        return listOfTemperature;
    }

    public void loadData() {
        listOfNames = new LinkedList<>();
        listOfCountries = new LinkedList<>();
        listOfDates = new LinkedList<>();
        listOfDescriptions = new LinkedList<>();
        listOfHumidity = new LinkedList<>();
        listOfPressure = new LinkedList<>();
        listOfTemperature = new LinkedList<>();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // делаем запрос всех данных из таблицы hero_information, получаем Cursor
        Cursor c = db.query("cities", null, null, null, null, null, null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int idColumnIndex = c.getColumnIndex("id");
            int nameColumnIndex = c.getColumnIndex("name");
            int countryColumnIndex = c.getColumnIndex("country");
            int dateColumnIndex = c.getColumnIndex("date");
            int descriptionColumnIndex = c.getColumnIndex("description");
            int humidityColumnIndex = c.getColumnIndex("humidity");
            int pressureColumnIndex = c.getColumnIndex("pressure");
            int temperatureColumnIndex = c.getColumnIndex("temperature");

            do {
                listOfNames.add(c.getString(nameColumnIndex));
                listOfCountries.add(c.getString(countryColumnIndex));
                listOfDates.add(c.getString(dateColumnIndex));
                listOfDescriptions.add(c.getString(descriptionColumnIndex));
                listOfHumidity.add(c.getString(humidityColumnIndex));
                listOfPressure.add(c.getString(pressureColumnIndex));
                listOfTemperature.add(c.getString(temperatureColumnIndex));
            } while (c.moveToNext());
        } else {
            // TODO: если в таблице нет записей
            c.close();
        }

        // закрываем подключение к БД
        dbHelper.close();
    }

    public void saveData(String name, String country, String date, String description,
                         String humidity, String pressure, String temperature) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // подготовим данные для вставки в виде пар: наименование столбца - значение
        cv.put("name", name);
        cv.put("country", country);
        cv.put("date", date);
        cv.put("description", description);
        cv.put("humidity", humidity);
        cv.put("pressure", pressure);
        cv.put("temperature", temperature);

        // вставляем запись и получаем ее ID
        long rowID = db.insert("cities", null, cv);

        // закрываем подключение к БД
        dbHelper.close();
    }

    public void deleteData(int viewId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        long delCount = db.delete("customers", "fio=?", null);
        db.execSQL("DELETE FROM " + "cities" + " WHERE "+"name"+"='"+listOfNames.get(viewId)+"'");
        db.close();

        // закрываем подключение к БД
        dbHelper.close();
    }
}
