import org.junit.Test;

import static example.devtips.senddatatoactivity.SplashScreen.about;
import static example.devtips.senddatatoactivity.SplashScreen.friendList;
import static example.devtips.senddatatoactivity.SplashScreen.greenTips;
import static example.devtips.senddatatoactivity.SplashScreen.journeyPlan;

import static org.junit.Assert.assertNotNull;

public class ExampleUnitTest {
    @Test
    public void testGreenTipsNotNull() {
        assertNotNull(greenTips);
    }

    @Test
    public void testFriendsNotNull() {
        assertNotNull(friendList);
    }

    @Test
    public void testAboutNotNull() {
        assertNotNull(about);
    }

    @Test
    public void testJourneyPlannerNotNull() {
        assertNotNull(journeyPlan);
    }
}
