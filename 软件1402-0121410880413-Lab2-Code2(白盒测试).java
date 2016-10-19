import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/***********************°×ºÐ²âÊÔ*************************/
public class GameLogicTest {
	private static GameLogic logic= new GameLogic();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGet_neighbor_count() {
		assertEquals(1,logic.get_neighbor_count(2,5));
	}

	@Test
	public void testGet() {
		assertEquals(false,logic.get(1,1));
		assertEquals(true,logic.get(2,2));
	}

	@Test
	public void testSet() {
		assertEquals(false,logic.set(2,5));
		assertEquals(true,logic.set(2,8));
		assertEquals(true,logic.set(5,2));
	}

}
