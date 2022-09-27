import com.example.application.calculator.Calculator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CalculatorTest {

    @Test
    public void calcBasicAddition(){
        Calculator calculator = new Calculator();
        ArrayList<String> testStringArray = new ArrayList<>();
        testStringArray.add("2");
        testStringArray.add("+");
        testStringArray.add("2");

        assertEquals(4.0, calculator.getResult(testStringArray));
    }
}
