package com.steven.tabbedactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.webkit.WebSettings;
import android.webkit.WebView;

public class SurveyActivity extends AppCompatActivity {

    private Spinner spinnerPersonajeSelect;
    private SeekBar seekBarPersonajeRango;
    private TextView textViewPersonajeRango;
    private static TextView textViewPersonajeDOBYears;
    private ImageButton imageButtonCalendar;
    private RadioGroup radioGroupGender;
    private RadioButton genderSelected;
    private Integer characterRankValue = 0;
    private static Integer day;
    private static Integer month;
    private static Integer year;
    private static String stringDOB;
    private static String stringYearsOld;
    private FirebaseDatabase dbFirebase;
    private DatabaseReference dbFirebaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        spinnerPersonajeSelect = (Spinner) findViewById(R.id.spinnerPersonajeSelect);

        seekBarPersonajeRango = (SeekBar) findViewById(R.id.seekBarPersonajeRango);

        textViewPersonajeRango = (TextView) findViewById(R.id.textViewPersonajeRango);

        textViewPersonajeDOBYears = (TextView) findViewById(R.id.textViewPersonajeDOBYears);

        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButtonCalendar);

        radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.personaje_spinner, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinnerPersonajeSelect.setAdapter(adapter);

        seekBarPersonajeRango.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {

                characterRankValue = progressValue;

                Toast.makeText(getApplicationContext(),"Changing Seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                Toast.makeText(getApplicationContext(),"Started tracking seekbar", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                textViewPersonajeRango.setText(getString(R.string.lbl_personaje_rango) + " [ " + characterRankValue + " ] ");

                Toast.makeText(getApplicationContext(),"Seekbar Value Rank = " + characterRankValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void sendSurveyData(View view)
    {
        //get selected radio button from RadioGroup
        int selectedId = radioGroupGender.getCheckedRadioButtonId();

        //find the radiobutton by returned id
        genderSelected = (RadioButton) findViewById(selectedId);

        dbFirebase = FirebaseDatabase.getInstance();
        dbFirebaseReference = dbFirebaseReference.getRef();

        String surveyID = dbFirebaseReference.push().getKey();

        HashMap<String, Object> surveyDataHashMap = new HashMap<>();

        surveyDataHashMap.put("character", spinnerPersonajeSelect.getSelectedItem().toString());

        surveyDataHashMap.put("rank", characterRankValue.toString());

        surveyDataHashMap.put("dob", stringDOB);

        surveyDataHashMap.put("gender", genderSelected.getText().toString());

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put(surveyID, surveyDataHashMap);

        dbFirebaseReference.updateChildren(childUpdates);

        Toast.makeText(this, getResources().getString(R.string.msg_send_survey_data), Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishAfterTransition(){

        super.finishAfterTransition();
    }

    private void setupWindowAnimations(){

        Explode explode = new Explode();
        explode.setDuration(1000);
        getWindow().setEnterTransition(explode);
    }

    public static class DatePickerFragment  extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            //Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
        }


        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay){

            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;

            String dayString = day.toString();
            String monthString = month.toString();

            if(day < 10) {
                dayString = "0" + dayString;
            }

            if(month < 10) {
                monthString = "0" + monthString;
            }

            Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);

            Integer yearsOld = currentYear - year;

            stringYearsOld = getResources().getString(R.string.lbl_personaje_years_old);

            textViewPersonajeDOBYears.setText(stringDOB + " (" + yearsOld.toString() + " " + stringYearsOld + ")");
            }
        }

    }
