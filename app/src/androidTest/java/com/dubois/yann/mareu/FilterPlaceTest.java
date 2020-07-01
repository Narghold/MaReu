package com.dubois.yann.mareu;

import androidx.test.espresso.contrib.ViewPagerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.dubois.yann.mareu.controller.listMeeting.ListMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.dubois.yann.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class FilterPlaceTest {

    @Rule
    public ActivityTestRule<ListMeetingActivity> mActivityRule =
            new ActivityTestRule<>(ListMeetingActivity.class);

    @Before
    public void setUp() {
        ListMeetingActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    public void createNewList(){
        //Create new meeting list
        List<String> place = new ArrayList<>();
        place.add("Mario");
        place.add("Luigi");
        place.add("Peach");
        place.add("Bowser");
        place.add("Wario");
        place.add("Waluigi");
        place.add("Daisy");
        place.add("Donkey Kong");
        place.add("Yoshi");
        place.add("Toad");

        for (int i = 0; i < place.size(); i++) {
            onView(withId(R.id.activity_list_meeting_fab_add)).perform(click());
            onView(withId(R.id.add_new_title_input)).perform(typeText(place.get(i)));
            onView(isRoot()).perform(pressBack());
            onView(withId(R.id.add_new_meeting_place_sp)).perform(click());
            onData(is(place.get(i))).perform(click());
            onView(withId(R.id.add_new_meeting_vp)).perform(ViewPagerActions.scrollRight());
            onView(withId(R.id.add_new_participant_input)).perform(typeText("aaa@mail.com"));
            onView(withId(R.id.add_new_participant_btn)).perform(click());
            onView(withId(R.id.add_new_meeting_vp)).perform(ViewPagerActions.scrollLeft());
            onView(withId(R.id.confirm_new_meeting_btn)).perform(scrollTo());
            onView(withId(R.id.confirm_new_meeting_btn)).perform(click());
            onView(withId(R.id.activity_list_meeting_fab_add)).check(matches(isDisplayed()));
        }

        //Check if list displayed 10 meeting
        onView(withId(R.id.list_meeting_container)).check(withItemCount(10));
    }

    @Test
    public void placeFilterAction_mario(){
        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Mario"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is("Mario")).perform(click());

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
        onData(is("Luigi")).perform(click());

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
        onData(is("Peach")).perform(click());

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
        onData(is("Bowser")).perform(click());

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
        onData(is("Wario")).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }

    @Test
    public void placeFilterAction_waluigi(){
        createNewList();

        //Click on filter tab item
        onView(withId(R.id.action_filter)).perform(click());
        onView(withId(R.id.card_filter)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        //Click on spinner item "Waluigi"
        onView(withId(R.id.place_spinner)).perform(click());
        onData(is("Waluigi")).perform(click());

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
        onData(is("Daisy")).perform(click());

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
        onData(is("Donkey Kong")).perform(click());

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
        onData(is("Yoshi")).perform(click());

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
        onData(is("Toad")).perform(click());

        //Check if the correct meeting is displayed
        onView(withId(R.id.list_meeting_container)).check(withItemCount(1));
    }
}