/*#Utsav
TEST DOUBLES <>
DEFINITION : They shield the test class from the external dependencies.

*/

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NewsletterSenderTest {


    /*     MOCK    */
    @Test
    public void constructorAssignsDatabase(){


       /* #Utsav mock(.class) => returns a nullified version of the object for that class.*/
        MessagingEngine messagingEngine = mock(MessagingEngine.class); /*#Utsav We use mock here because the MessagingEngine is just passed a param and not showing any behavior */
        SubscribersDatabase subscribersDatabase = new SubscribersDatabase();
        NewsletterSender newsletterSender = new NewsletterSender(subscribersDatabase, messagingEngine);

        assertEquals(subscribersDatabase, newsletterSender.getSubscribersDatabase());
    }


    /*    STUB    */
    /* STUB is MOCK + [instructions on how to respond certain method calls]
    /* STUB v/s MOCK => MOCK returns simplyh the nullified values whereas the STUB returns the certain values defined for method calls */
    @Test
    public void numberOfSubscribers(){

        SubscribersDatabase subscribersDatabaseMock = mock(SubscribersDatabase.class);
        MessagingEngine messagingEngineMock = mock(MessagingEngine.class);

        NewsletterSender sender = new NewsletterSender(subscribersDatabaseMock, messagingEngineMock);

        List<String> subscribersList = Arrays.asList("email1", "email2", "email3");
        when(subscribersDatabaseMock.getSubscribers()).thenReturn(subscribersList);

        assertEquals(3, sender.numberOfSubscribers());
    }

    /*    SPY    */

    /* represents the real object 
    SPY v/s REGULAR INSTANCE
    spy can define values for certain method calls */

    @Test(expected = ZeroSubscribersException.class)
    public void zeroSubscribersThrown(){

        NewsletterSender newsletterSender = new NewsletterSender(new SubscribersDatabase(), new MessagingEngine());
        NewsletterSender newsletterSenderSpy = spy(newsletterSender);

        when(newsletterSenderSpy.numberOfSubscribers()).thenReturn(0);

        newsletterSenderSpy.sendNewsletter("SUBJECT");

    }
}





NOTES:
MOCK = nullfied instance which does not have any role to play in the test
STUB = MOCK + defined values on certain method calls
SPY = STUB + regualr instace behaviour

