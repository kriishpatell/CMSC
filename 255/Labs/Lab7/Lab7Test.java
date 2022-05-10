package Labs.Lab7;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Lab7Test {

	private String splitter = System.getProperty("line.separator");

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@Test
	public void greetingTest() {
		Lab7.greeting("Dr.", "Caroline", "Budwell");
		String rawOutput = outContent.toString();
		String[] outputLines = rawOutput.split(splitter);
		//assertEquals(" ", outputLines[0]);
		assertEquals("Dear Dr. Caroline Budwell,", rawOutput.trim());
	}

	@Test
	public void max10and2Test() {
		int max = Lab7.max(10, 2);
		assertEquals(10, max);
	}

	@Test
	public void max2and10Test() {
		int max = Lab7.max(2, 10);
		assertEquals(10, max);
	}

	@Test
	public void max50and50Test() {
		int max = Lab7.max(50, 50);
		assertEquals(50, max);
	}

	@Test
	public void maxMinus10andMinus2Test() {
		int max = Lab7.max(-10, -2);
		assertEquals(-2, max);
	}

	@Test
	public void maxMinus2andMinus10Test() {
		int max = Lab7.max(-2, -10);
		assertEquals(-2, max);
	}

	@Test
	public void max0and0Test() {
		int max = Lab7.max(0, 0);
		assertEquals(0, max);
	}

	@Test
	public void sumTo2and10Test() {
		int max = Lab7.sumTo(2, 10);
		assertEquals(54, max);
	}

	@Test
	public void sumTo10and2Test() {
		int max = Lab7.sumTo(10, 2);
		assertEquals(54, max);
	}

	@Test
	public void sumTo50and50Test() {
		int max = Lab7.sumTo(50, 50);
		assertEquals(50, max);
	}

	@Test
	public void sumToMinus10andMinus2Test() {
		int max = Lab7.sumTo(-10, -2);
		assertEquals(-54, max);
	}

	@Test
	public void sumToMinus2andMinus10Test() {
		int max = Lab7.sumTo(-2, -10);
		assertEquals(-54, max);
	}

	@Test
	public void sumTo0and0Test() {
		int max = Lab7.sumTo(0, 0);
		assertEquals(0, max);
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}
}
