package com.projectlite2.android.ui;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.projectlite2.android.app.MyApplication;

public class SublimePickerDialogFragment extends DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        SublimeListenerAdapter mListener  = new SublimeListenerAdapter() {
            @Override
            public void onDateTimeRecurrenceSet(SublimePicker sublimeMaterialPicker, SelectedDate selectedDate, int hourOfDay, int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) { }
            @Override
            public void onCancelled() { }
        };

        SublimePicker sublimePicker = new  SublimePicker(MyApplication.getContext());
        SublimeOptions sublimeOptions = new SublimeOptions(); // This is optional
        sublimeOptions.setPickerToShow(SublimeOptions.Picker.REPEAT_OPTION_PICKER); // I want the recurrence picker to show.
        sublimeOptions.setDisplayOptions(SublimeOptions.ACTIVATE_RECURRENCE_PICKER); // I only want the recurrence picker, not the date/time pickers.
        sublimePicker.initializePicker(sublimeOptions,mListener );

        return super.onCreateDialog(savedInstanceState);
    }
}
