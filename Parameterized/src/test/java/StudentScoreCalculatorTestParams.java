import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class) /*#Utsav Step-1: annotation used for paramtzd tests*/
public class StudentScoreCalculatorTestParams {

    /*#Utsav Step-3: Create a methods which returns an array of Objects which can be passed to the test method.
    This can be passed as a param to @Parameters annotation.
    NOTE- this is a generic pattern shown so you can re-use it.*/
    private static Object[] testValues(){

        return new Object[]{
                new Object[]{50, 50,  2500},
                new Object[]{-10, 50, -1},
                new Object[]{50, -1, -1},
                new Object[]{-1, -1, -1},
                new Object[]{150, 50, -1},
                new Object[]{50, 150, -1},
                new Object[]{150, 150, -1},
                new Object[]{0, 0, 0},
                new Object[]{100, 100, 10000}
        };

    }

    @Test
    @Parameters(method = "testValues")/*#Utsav Step-4: This annotation is a must with a testValues() name*/
    /* #Utsav Step-2: Create a test method with required params of the original method and am extra expected param*/
    public void studentScoreCalculator(int mathsScore, int literacyScore, int expectedScore){

        StudentScoreCalculator sc = new StudentScoreCalculator();
        sc.calculateSATScore(mathsScore, literacyScore);
        assertEquals(expectedScore, sc.getSatScore());
    }
}
