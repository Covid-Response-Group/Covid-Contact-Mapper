package com.wordingly.covidcontacttracer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class TravelTrackActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTrainNumber, etTrainCoach, etTrainSeat, etBusNumber, etBusSeat, etFlightNumber, etFlightSeat, etOtherVehicleNumber, etOtherVehicleInfo;
    LinearLayout llSelectTransport, llSurveyTrain, llSurveyBus, llSurveyFlight, llSurveyOthers;
    CardView cvSelectTrain, cvSelectBus, cvSelectFlight, cvSelectOthers, cvSubmit;
    private static int mCurrentEdit = -1;
    private CardView[] mSelectorViews;
    private LinearLayout[] mFormViews;

    private EditText[] mTrainViews, mBusViews, mFlightViews, mOthersViews;
    private EditText[][] mAllFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_track);
        setupIncludedLayouts();
    }

    private void setupIncludedLayouts() {
        llSelectTransport = findViewById(R.id.include_select_transport);
        llSurveyTrain = findViewById(R.id.include_survey_train);
        llSurveyBus = findViewById(R.id.include_survey_bus);
        llSurveyFlight = findViewById(R.id.include_survey_flight);
        llSurveyOthers = findViewById(R.id.include_survey_others);
        callSetupInOrder();
    }

    private void callSetupInOrder() {
        mFormViews = new LinearLayout[] {llSurveyTrain, llSurveyBus, llSurveyFlight, llSurveyOthers};
        setupSelectTransportViews();
        setupTrainViews();
        setupBusViews();
        setupFlightViews();
        setupOthersViews();
        mAllFields = new EditText[][]{mTrainViews, mBusViews, mFlightViews, mOthersViews};
        setAllFieldsFocusListener();
    }

    private void setupSelectTransportViews() {
        cvSelectTrain = findViewById(R.id.cv_train);
        cvSelectBus = findViewById(R.id.cv_bus);
        cvSelectFlight = findViewById(R.id.cv_air);
        cvSelectOthers = findViewById(R.id.cv_other);
        cvSubmit = findViewById(R.id.cv_submit);

        mSelectorViews = new CardView[]{cvSelectTrain, cvSelectBus, cvSelectFlight, cvSelectOthers};

        cvSelectTrain.setOnClickListener(this);
        cvSelectBus.setOnClickListener(this);
        cvSelectFlight.setOnClickListener(this);
        cvSelectOthers.setOnClickListener(this);
        cvSubmit.setOnClickListener(this);
    }

    private void setupTrainViews() {
        etTrainNumber = findViewById(R.id.et_train_number);
        etTrainCoach = findViewById(R.id.et_coach_number);
        etTrainSeat = findViewById(R.id.et_train_seat_number);
        mTrainViews = new EditText[]{etTrainNumber, etTrainCoach, etTrainSeat};
    }

    private void setupBusViews() {
        etBusNumber = findViewById(R.id.et_bus_number);
        etBusSeat = findViewById(R.id.et_bus_seat_number);
        mBusViews = new EditText[]{etBusNumber, etBusSeat};
    }

    private void setupFlightViews() {
        etFlightNumber = findViewById(R.id.et_flight_number);
        etFlightSeat = findViewById(R.id.et_flight_seat_number);
        mFlightViews = new EditText[]{etFlightNumber, etFlightSeat};
    }

    private void setupOthersViews() {
        etOtherVehicleNumber = findViewById(R.id.et_vehicle_number);
        etOtherVehicleInfo = findViewById(R.id.et_vehicle_details);
        mOthersViews = new EditText[]{etOtherVehicleNumber, etOtherVehicleInfo};
    }

    private void setAllFieldsFocusListener() {
        for (int i = 0; i < mAllFields.length; i++) {
            for (int j = 0; j < mAllFields[i].length; j++) {
                mAllFields[i][j].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {
                            view.setBackground(getResources().getDrawable(R.drawable.borders_all));
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_train:
                mCurrentEdit = 0;
                fadeViews();
                break;
            case R.id.cv_bus:
                mCurrentEdit = 1;
                fadeViews();
                break;
            case R.id.cv_air:
                mCurrentEdit = 2;
                fadeViews();
                break;
            case R.id.cv_other:
                mCurrentEdit = 3;
                fadeViews();
                break;
            case R.id.cv_submit:
                validateAndSubmit();
                break;
        }
    }

    private void validateAndSubmit() {

        ArrayList<Integer> errorIndices = new ArrayList<>();
        if (mCurrentEdit == 1) {
            if (mAllFields[mCurrentEdit][0].getText().toString().trim().isEmpty()) {
                errorIndices.add(1);
            }
        } else {
            for (int i = 0; i < mAllFields[mCurrentEdit].length; i++) {
                if (mAllFields[mCurrentEdit][i].getText().toString().trim().isEmpty()) {
                    errorIndices.add(i);
                }
            }
        }

        if (!errorIndices.isEmpty()) {
            for (int i = 0; i < errorIndices.size(); i++) {
                mAllFields[mCurrentEdit][errorIndices.get(i)].setBackground(getResources().getDrawable(R.drawable.borders_all_error));
            }
        } else {
            //Submit depending on CurrentEditedField
        }

    }

    private void fadeViews() {
        for (int i = 0; i < mSelectorViews.length; i++) {
            if (i != mCurrentEdit) {
                mSelectorViews[i].setCardBackgroundColor(getResources().getColor(R.color.estimote_faded_purple));
                mFormViews[i].setVisibility(View.GONE);
            } else {
                mSelectorViews[i].setCardBackgroundColor(getResources().getColor(R.color.estimote_purple));
                mFormViews[i].setVisibility(View.VISIBLE);
            }
        }
        cvSubmit.setVisibility(View.VISIBLE);
    }

}
