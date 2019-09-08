package tk.pratanumandal.expr4j;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tk.pratanumandal.expr4j.ShuntingYard;
import tk.pratanumandal.expr4j.ShuntingYardDualStack;

public class ShuntingYardDualStackTest extends TestCase {
	
	private ShuntingYard sy;
	
    public ShuntingYardDualStackTest(String testName) {
        super(testName);
        sy = new ShuntingYardDualStack();
    }
    
    public static Test suite() {
        return new TestSuite(ShuntingYardDualStackTest.class);
    }
    
    public void test1() {
    	double expected = 8.0298136373;
    	double actual = sy.evaluate("5+3/cos(sin(-6))^0.25");
        assertEquals(expected, actual);
    }
    
    public void test2() {
    	double expected = 99.99;
    	double actual = sy.evaluate("1e+2 - 1e-2");
        assertEquals(expected, actual);
    }
    
    public void test3() {
    	double expected = 1.0;
    	double actual = sy.evaluate("ceil(rand)");
        assertEquals(expected, actual);
    }
    
    public void test4() {
    	double expected = -1.0;
    	double actual = sy.evaluate("floor(-rand)");
        assertEquals(expected, actual);
    }
    
    public void test5() {
    	double expected = 30.0;
    	double actual = sy.evaluate("deg(asin(sin(rad(30))))");
        assertEquals(expected, actual);
    }
    
    public void test6() {
    	double expected = 0.3722236412;
    	double actual = sy.evaluate("log(2, (ln(2 + 3) * 4))");
        assertEquals(expected, actual);
    }
    
}