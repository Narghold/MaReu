package com.dubois.yann.mareu;

import android.content.res.Resources;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.ViewPagerActions;
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
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.dubois.yann.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class FilterDateTest {

    Resources resource = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
    MeetingApiService service = DI.getNewInstanceMeetingApiService();

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<>(ListMeetingActivity.class, true, true);

    @Before
    public void setup() {
        ListMeetingActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        service.getMeetingList().clear();

        Meeting today = new Meeting("Today", resource.getString(R.string.place_spinner_mario), LocalDateTime.now(), 1, new ArrayList<>());
        Meeting tomorrow = new Meeting("Tomorrow", resource.getString(R.string.place_spinner_mario), LocalDateTime.now().plusDays(1), 1, new ArrayList<>());
        Meeting nextWeek = new Meeting("Next Week", resource.getString(R.string.place_spinner_mario), LocalDateTime.now().plusDays(8), 1, new ArrayList<>());
        Meeting nextMonth = new Meeting("Next Month", resource.getString(R.string.place_spinner_mario), LocalDateTime.now().plusMonths(1).plusDays(1), 1, new ArrayList<>());

        service.addNewMeeting(today);
        service.addNewMeeting(tomorrow);
        service.addNewMeeting(nextWeek);
        service.addNewMeeting(nextMonth);
    }


    public void createNewMeeting(String title, int month, int day){
        onView(withId(R.id.activity_list_meeting_fab_add)).perform(click());
        onView(withId(R.id.add_new_title_input)).perform(typeText(title));
        onView(withId(R.id.add_new_date_picker)).perform(PickerActions.setDate(2020, month, day));
        onView(withId(R.id.add_new_meeting_vp)).perform(ViewPagerActions.scrollRight());
        onView(withId(R.id.add_new_participant_input)).perform(typeText("aaa@mail.com"));
        onView(withId(R.id.add_new_participant_btn)).perform(click());
        onView(withId(R.id.add_new_meeting_vp)).perform(ViewPagerActions.scrollLeft());
        onView(isRoot()).perform(pressBack());
        onView(withId(R.id.confirm_new_meeting_btn)).perform(scrollTo());
        onView(withId(R.id.confirm_new_meeting_btn)).perform(click());
        onView(withId(R.id.activity_list_meeting_fab_add)).check(matches(isDisplayed()));
    }

    @Test
    public void dateFilterAction_today(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Today"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is(resource.getString(R.string.date_spinner_today))).perform(click());
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
        onData(is("Demain")).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
        onView(withText("Tomorrow")).check(matches(isDisplayed()));
        onView(withText("Today")).check(doesNotExist());
        onView(withText("Next Week")).check(doesNotExist());
        onView(withText("Next Month")).check(doesNotExist());
    }

    @Test
    public void dateFilterAction_thisWeek() {
        //Create meeting list
        //ROUE DE SECOURS
        createNewMeeting("Today", LocalDateTime.now().getMonthOfYear(), LocalDateTime.now().getDayOfMonth());
        createNewMeeting("Tomorrow", LocalDateTime.now().getMonthOfYear(), LocalDateTime.now().getDayOfMonth() + 1);
        createNewMeeting("Next Week", LocalDateTime.now().getMonthOfYear(), LocalDateTime.now().getDayOfMonth() + 8);
        createNewMeeting("Next Month", LocalDateTime.now().getMonthOfYear() + 1, LocalDateTime.now().getDayOfMonth() + 1);

        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "This Week"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is("Cette semaine")).perform(click());
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
        onData(is("La semaine prochaine")).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
        onView(withText("Next Week")).check(matches(isDisplayed()));
        onView(withText("Today")).check(doesNotExist());
        onView(withText("Tomorrow")).check(doesNotExist());
        onView(withText("Next Month")).check(doesNotExist());
    }

    @Test
    public void dateFilterAction_thisMonth() {
        //Create meeting list
        createNewMeeting("Today", LocalDateTime.now().getMonthOfYear(), LocalDateTime.now().getDayOfMonth());
        createNewMeeting("Tomorrow", LocalDateTime.now().getMonthOfYear(), LocalDateTime.now().getDayOfMonth() + 1);
        createNewMeeting("Next Week", LocalDateTime.now().getMonthOfYear(), LocalDateTime.now().getDayOfMonth() + 8);
        createNewMeeting("Next Month", LocalDateTime.now().getMonthOfYear() + 1, LocalDateTime.now().getDayOfMonth() + 1);

        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "This Month"
        onView(withId(R.id.date_spinner)).perform(click());
        onData(is("Ce mois-ci")).perform(click());
        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(3));
        onView(withText("Today")).check(matches(isDisplayed()));
        onView(withText("Tomorrow")).check(matches(isDisplayed()));
        onView(withText("Next Week")).check(matches(isDisplayed()));
        onView(withText("Next Month")).check(doesNotExist());
    }
}
