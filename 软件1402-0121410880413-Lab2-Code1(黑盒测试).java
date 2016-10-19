package Game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
//import org.juint.Ignore;
//import org.juint.internal.runners.TestClassRunner;
//import org.juint.runner.RunWith;
/************************ºÚºÐ²âÊÔ*************************/
public class GameLogicTest {
	private static GameLogic logic= new GameLogic();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGet_neighbor_count() {
		assertEquals(0,logic.get_neighbor_count(2,2));
		assertEquals(1,logic.get_neighbor_count(2,5));
		assertEquals(2,logic.get_neighbor_count(2,8));
		assertEquals(3,logic.get_neighbor_count(5,2));
		assertEquals(4,logic.get_neighbor_count(5,5));
		assertEquals(5,logic.get_neighbor_count(5,8));
		assertEquals(6,logic.get_neighbor_count(8,2));
		assertEquals(7,logic.get_neighbor_count(8,5));
		assertEquals(8,logic.get_neighbor_count(8,8));
	}

	@Test
	public void testGet() {
		assertEquals(false,logic.get(1,1));
		assertEquals(true,logic.get(2,2));
	}

	@Test
	public void testSet() {
		assertEquals(true,logic.set(2,8));
		assertEquals(false,logic.set(3,2));
		assertEquals(true,logic.set(5,2));
		assertEquals(false,logic.set(5,5));
	}

}
