package example.devtips.senddatatoactivity;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
@RunWith(RobolectricTestRunner.class)
public class SplashScreenTest {

       @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule =
            new ActivityTestRule<SplashScreen>(SplashScreen.class); //launch activity

    private SplashScreen mSplashScreen = null;

    @Before
    public void setUp()  {
        mSplashScreen = mActivityTestRule.getActivity(); //get the corresponding activity of SplashScreen
    }

    @After
    public void tearDown(){
        mSplashScreen = null; //teardown the splashScreen
    }

    //if views are shown (not Null) tests pass
    @Test
    public void testJourneyPlannerButton_isShown() {
        View journeyPlannerButton = mSplashScreen.findViewById(R.id.journey_planner_btn);
        assertNotNull(journeyPlannerButton);
    }

    @Test
    public void testGreenTipsButton_isShown(){
        View greenTipsButton = mSplashScreen.findViewById(R.id.green_tips_btn);
        assertNotNull(greenTipsButton);
    }

    @Test
    public void testFriendListButton_isShown() {
        View friendsButton = mSplashScreen.findViewById(R.id.friends_btn);
        assertNotNull(friendsButton);
    }
    @Test
    public void testAboutButton_isShown() {
        View aboutButton = mSplashScreen.findViewById(R.id.about);
        assertNotNull(aboutButton);
    }

}