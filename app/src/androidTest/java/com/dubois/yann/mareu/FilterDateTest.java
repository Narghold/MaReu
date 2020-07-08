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
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.dubois.yann.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class FilterDateTest {

    Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
    MeetingApiService service = DI.getMeetingApiService();

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<>(ListMeetingActivity.class, true, true);

    @Before
    public void setup() {
        ListMeetingActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        service.getMeetingList().clear();

        Meeting today = new Meeting("Today", res.getString(R.string.place_spinner_mario), LocalDateTime.now(), 1, new ArrayList<>());
        Meeting tomorrow = new Meeting("Tomorrow", res.getString(R.string.place_spinner_mario), LocalDateTime.now().plusDays(1), 2, new ArrayList<>());
        Meeting nextWeek = new Meeting("Next Week", res.getString(R.string.place_spinner_mario), LocalDateTime.now().plusDays(8), 3, new ArrayList<>());
        Meeting nextMonth = new Meeting("Next Month", res.getString(R.string.place_spinner_mario), LocalDateTime.now().plusMonths(1).plusDays(1), 4, new ArrayList<>());

        service.addNewMeeting(today);
        service.addNewMeeting(tomorrow);
        service.addNewMeeting(nextWeek);
        service.addNewMeeting(nextMonth);
    }

    @Test
    public void dateFilterAction_today(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Today"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is(res.getString(R.string.date_spinner_today))).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
        onView(withText("Today")).check(matches(isDisplayed()));
        onView(withText("Tomorrow")).check(doesNotExist());
        onView(withText("Next Week")).check(doesNotExist());
        onView(withText("Next Month")).check(doesNotExist());
    }

    @Test
    public void dateFilterAction_tomorrow(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Tomorrow"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is(res.getString(R.string.date_spinner_tomorrow))).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
        onView(withText("Tomorrow")).check(matches(isDisplayed()));
        onView(withText("Today")).check(doesNotExist());
        onView(withText("Next Week")).check(doesNotExist());
        onView(withText("Next Month")).check(doesNotExist());
    }

    @Test
    public void dateFilterAction_thisWeek() {
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "This Week"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is(res.getString(R.string.date_spinner_this_week))).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(2));
        onView(withText("Today")).check(matches(isDisplayed()));
        onView(withText("Tomorrow")).check(matches(isDisplayed()));
        onView(withText("Next Week")).check(doesNotExist());
        onView(withText("Next Month")).check(doesNotExist());
    }

    @Test
    public void dateFilterAction_nextWeek() {
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Next Week"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is(res.getString(R.string.date_spinner_next_week))).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
        onView(withText("Next Week")).check(matches(isDisplayed()));
        onView(withText("Today")).check(doesNotExist());
        onView(withText("Tomorrow")).check(doesNotExist());
        onView(withText("Next Month")).check(doesNotExist());
    }

    @Test
    public void dateFilterAction_thisMonth() {
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "This Month"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is(res.getString(R.string.date_spinner_month))).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(3));
        onView(withText("Today")).check(matches(isDisplayed()));
        onView(withText("Tomorrow")).check(matches(isDisplayed()));
        onView(withText("Next Week")).check(matches(isDisplayed()));
        onView(withText("Next Month")).check(doesNotExist());
    }
}
