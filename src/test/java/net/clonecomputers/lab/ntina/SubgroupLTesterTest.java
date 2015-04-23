package net.clonecomputers.lab.ntina;

import static org.junit.Assert.*;
import static net.clonecomputers.lab.ntina.SubgroupLTester.getLValue;

import org.junit.Test;

public class SubgroupLTesterTest {

	@Test
	public void testGetLValue() {
		assertEquals(1, getLValue(1));
		assertEquals(-5, getLValue(0xF));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetLValueZeroExecption() {
		getLValue(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetLValueNegExecption() {
		getLValue(-1);
	}

}
