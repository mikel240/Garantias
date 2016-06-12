
import android.content.Intent;
import android.widget.LinearLayout;

import com.example.atmosfera.garantias.BuildConfig;
import com.example.atmosfera.garantias.R;
import com.example.atmosfera.garantias.controllers.activities.AddGarantiaActivity;
import com.example.atmosfera.garantias.controllers.activities.InfoActivity;
import com.example.atmosfera.garantias.controllers.activities.MainActivity;
import com.example.atmosfera.garantias.controllers.activities.MisGarantiasActivity;
import com.example.atmosfera.garantias.controllers.activities.SettingsActivity;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity activity;
    private LinearLayout linearPressed;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void checkCallsLinearMisGarantias() throws Exception {
        linearPressed = (LinearLayout) activity.findViewById(R.id.linearGarantias);
        checkIntentCalls(MisGarantiasActivity.class);
    }

    @Test
    public void checkCallsLinearAdd() throws Exception {
        linearPressed = (LinearLayout) activity.findViewById(R.id.linearAddGarantia);
        checkIntentCalls(AddGarantiaActivity.class);
    }

    @Test
    public void checkCallsLinearSettings() throws Exception {
        linearPressed = (LinearLayout) activity.findViewById(R.id.linearSettings);
        checkIntentCalls(SettingsActivity.class);
    }

    @Test
    public void checkCallsLinearInfo() throws Exception {
        linearPressed = (LinearLayout) activity.findViewById(R.id.linearInfo);
        checkIntentCalls(InfoActivity.class);
    }

    public void checkIntentCalls(Class classExpected) {
        linearPressed.performClick();
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Shadows.shadowOf(startedIntent);
        Assert.assertThat(shadowIntent.getComponent().getClassName(), IsEqual.equalTo(classExpected.getName()));
    }
}