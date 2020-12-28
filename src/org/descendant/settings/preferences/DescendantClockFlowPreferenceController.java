/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.descendant.settings.preferences;

import android.content.Context;
import android.content.ContentResolver;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settingslib.core.AbstractPreferenceController;

import java.util.ArrayList;
import java.util.List;


public class DescendantClockFlowPreferenceController extends AbstractPreferenceController implements
        Preference.OnPreferenceChangeListener {

    private static final String DESCENDANT_CLOCK_FLOW_SELECTOR = "descendant_clock_flow_selector";
    private ListPreference mClockFlowStyle;

    public DescendantClockFlowPreferenceController(Context context) {
        super(context);
    }

    @Override
    public String getPreferenceKey() {
        return DESCENDANT_CLOCK_FLOW_SELECTOR;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        mClockFlowStyle = (ListPreference) screen.findPreference(DESCENDANT_CLOCK_FLOW_SELECTOR);
        int ClockFlowStyle = Settings.System.getInt(mContext.getContentResolver(),
                Settings.System.DESCENDANT_CLOCK_FLOW_SELECTOR, 0);
        int valueIndex = mClockFlowStyle.findIndexOfValue(String.valueOf(ClockFlowStyle));
        mClockFlowStyle.setValueIndex(valueIndex >= 0 ? valueIndex : 0);
        mClockFlowStyle.setSummary(mClockFlowStyle.getEntry());
        mClockFlowStyle.setOnPreferenceChangeListener(this);
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mClockFlowStyle) {
            String value = (String) newValue;
            Settings.System.putInt(mContext.getContentResolver(), Settings.System.DESCENDANT_CLOCK_FLOW_SELECTOR, Integer.valueOf(value));
            int valueIndex = mClockFlowStyle.findIndexOfValue(value);
            mClockFlowStyle.setSummary(mClockFlowStyle.getEntries()[valueIndex]);
        }
        return true;
    }
}
