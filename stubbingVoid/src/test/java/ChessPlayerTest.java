import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ChessPlayerTest {

    @Test
    public void testAssignCategorySpy() throws Exception{

        ChessPlayer chessPlayer = new ChessPlayer("Magnus", 30);
        ChessPlayer chessPlayerSpy = Mockito.spy(chessPlayer); /* #Utsav using SPY to test VOID mehtods */

        doNothing().when(chessPlayerSpy).assignCategory(); /* #Utsav instructs the method to do nothing on this method call */
        chessPlayerSpy.assignCategory();

        assertEquals("Senior", chessPlayerSpy.getCategory());

    }

    @Test
    public void testAssignCategoryMock() throws Exception{

        ChessPlayer chessPlayerMock = Mockito.mock(ChessPlayer.class);

        when(chessPlayerMock.getAge()).thenReturn(30);
        doCallRealMethod().when(chessPlayerMock).assignCategory(); /* #Utsav used to call real methods on the mock objet */
        doCallRealMethod().when(chessPlayerMock).getCategory();
        chessPlayerMock.assignCategory();

        assertEquals("Senior", chessPlayerMock.getCategory());
    }

    @Test(expected = Exception.class)
    public void testAssignCategoryMockException() throws Exception{

        ChessPlayer chessPlayerMock = Mockito.mock(ChessPlayer.class);
        doThrow(Exception.class).when(chessPlayerMock).assignCategory(); /* #Utsav used to throw an Exception forcefully */
        chessPlayerMock.assignCategory();

    }

    @Test
    public void testAssignScoreStats(){

        ChessPlayer chessPlayer = new ChessPlayer("Magnus", 30);
        ChessPlayer chessPlayerSpy = Mockito.spy(chessPlayer);

        doAnswer((chessP) -> {
            ChessPlayer player = (ChessPlayer)chessP.getMock();
            player.setWins(3);
            player.setPoints(9);
            return null;
        }).when(chessPlayerSpy).assignScoreStats();

        chessPlayerSpy.assignScoreStats();
        assertSame(3, chessPlayerSpy.getWins());
        assertSame(9, chessPlayerSpy.getPoints());

    }

}
