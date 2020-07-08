package com.dubois.yann.mareu;

import android.content.res.Resources;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.dubois.yann.mareu.controller.listMeeting.ListMeetingActivity;
import com.dubois.yann.mareu.model.Meeting;
import com.dubois.yann.mareu.service.DI;
import com.dubois.yann.mareu.service.MeetingApiService;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.dubois.yann.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class FilterPlaceTest {

    Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
    MeetingApiService service = DI.getMeetingApiService();

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<>(ListMeetingActivity.class);

    @Before
    public void setup() {
        ListMeetingActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        service.getMeetingList().clear();

        Meeting mario = new Meeting(res.getString(R.string.place_spinner_mario), res.getString(R.string.place_spinner_mario), LocalDateTime.now(), 1, new ArrayList<>());
        Meeting luigi = new Meeting(res.getString(R.string.place_spinner_luigi), res.getString(R.string.place_spinner_luigi), LocalDateTime.now(), 2, new ArrayList<>());
        Meeting peach = new Meeting(res.getString(R.string.place_spinner_peach), res.getString(R.string.place_spinner_peach), LocalDateTime.now(), 3, new ArrayList<>());
        Meeting bowser = new Meeting(res.getString(R.string.place_spinner_bowser), res.getString(R.string.place_spinner_bowser), LocalDateTime.now(), 4, new ArrayList<>());
        Meeting wario = new Meeting(res.getString(R.string.place_spinner_wario), res.getString(R.string.place_spinner_wario), LocalDateTime.now(), 5, new ArrayList<>());
        Meeting waluigi = new Meeting(res.getString(R.string.place_spinner_waluigi), res.getString(R.string.place_spinner_waluigi), LocalDateTime.now(), 6, new ArrayList<>());
        Meeting daisy = new Meeting(res.getString(R.string.place_spinner_daisy), res.getString(R.string.place_spinner_daisy), LocalDateTime.now(), 7, new ArrayList<>());
        Meeting donkeyKong = new Meeting(res.getString(R.string.place_spinner_donkey_kong), res.getString(R.string.place_spinner_donkey_kong), LocalDateTime.now(), 8, new ArrayList<>());
        Meeting yoshi = new Meeting(res.getString(R.string.place_spinner_yoshi), res.getString(R.string.place_spinner_yoshi), LocalDateTime.now(), 9, new ArrayList<>());
        Meeting toad = new Meeting(res.getString(R.string.place_spinner_toad), res.getString(R.string.place_spinner_toad), LocalDateTime.now(), 10, new ArrayList<>());

        service.addNewMeeting(mario);
        service.addNewMeeting(luigi);
        service.addNewMeeting(peach);
        service.addNewMeeting(bowser);
        service.addNewMeeting(wario);
        service.addNewMeeting(waluigi);
        service.addNewMeeting(daisy);
        service.addNewMeeting(donkeyKong);
        service.addNewMeeting(yoshi);
        service.addNewMeeting(toad);
    }

    @Test
    public void placeFilterAction_mario(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Mario"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_mario))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_luigi(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Luigi"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_luigi))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_peach(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Peach"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_peach))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }
    @Test
    public void placeFilterAction_bowser(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Bowser"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_bowser))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_wario(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Wario"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_wario))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_waluigi(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Waluigi"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_waluigi))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_daisy(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Daisy"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_daisy))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_donkeyKong(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Donkey Kong"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_donkey_kong))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_yoshi(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Yoshi"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_yoshi))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_toad(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Toad"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is(res.getString(R.string.place_spinner_toad))).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }
}