package a1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PhdTest {

	/** testGroupA */
	@Test
	public void testConstructor1() {
		// Create a Phd instance
		Phd p = new Phd("Max", 2021, 5);

		// Test getter
		assertEquals("Max", p.name());
		assertEquals("5/2021", p.date());
		assertEquals(null, p.advisor1());
		assertEquals(null, p.advisor2());
		assertEquals(0, p.nAdvisees());
	}

	@Test
	public void testGroupB() {
		// Create Phd instances
		Phd p = new Phd("Max", 2021, 5);
		Phd adv1 = new Phd("David", 1966, 5);
		Phd adv2 = new Phd("Curran", 2014, 5);

		assertEquals(null, p.advisor1());
		assertEquals(null, p.advisor2());
		assertEquals(0, adv1.nAdvisees());
		assertEquals(0, adv1.nAdvisees());

		// 1st advisor added
		p.setAdvisor1(adv1);
		assertEquals(adv1, p.advisor1());
		assertEquals(null, p.advisor2());
		assertEquals(1, adv1.nAdvisees());

		// 2nd advisor added
		p.setAdvisor2(adv2);
		assertEquals(adv1, p.advisor1());
		assertEquals(adv2, p.advisor2());
		assertEquals(1, adv2.nAdvisees());
	}

	@Test
	public void testGroupC() {
		// Create Phd instances
		Phd adv1 = new Phd("David", 1966, 5);
		Phd adv2 = new Phd("Curran", 2014, 5);
		Phd p = new Phd("Max", 2021, 5, adv1, adv2);

		// Test getter
		assertEquals("Max", p.name());
		assertEquals("5/2021", p.date());
		assertEquals(adv1, p.advisor1());
		assertEquals(adv2, p.advisor2());
		assertEquals(0, p.nAdvisees());

		// Test setter
		assertEquals(1, adv1.nAdvisees());
		assertEquals(1, adv2.nAdvisees());
		// Add another Phd instance
		Phd dummy = new Phd("Apple", 2021, 5, adv1, adv2);
		assertEquals(2, adv1.nAdvisees());
		assertEquals(2, adv2.nAdvisees());
		assertEquals(dummy, dummy);
	}

	@Test
	public void testGroupD() {
		// Create Phd instances
		Phd a1 = new Phd("a1", 1966, 5);
		Phd a2 = new Phd("a2", 2014, 5);
		Phd a3 = new Phd("a3", 2021, 5);

		// Test hasNoAdvisees()
		assertEquals(true, a1.hasNoAdvisees());
		a3.setAdvisor1(a1);
		assertEquals(false, a1.hasNoAdvisees());
		assertEquals(true, a2.hasNoAdvisees());
		assertEquals(true, a3.hasNoAdvisees());
		a3.setAdvisor2(a2);
		assertEquals(false, a2.hasNoAdvisees());

		// Test gotBefore(Phd p)
		Phd a4 = new Phd("a4", 2021, 6);
		assertEquals(true, a1.gotBefore(a2));
		assertEquals(true, a2.gotBefore(a3));
		assertEquals(false, a3.gotBefore(a2));
		assertEquals(true, a3.gotBefore(a4));

		// Test areSibs(Phd p)
		/**
		 * a3: 1stAdv = a1, 2stAdv = a2 <br>
		 * a4: 1stAdv = a1, 2stAdv = null <br>
		 * a5: 1stAdv = xx, 2stAdv = a2 <br>
		 * a6: 1stAdv = a2, 2stAdv = null <br>
		 * a7: 1stAdv = xx, 2stAdv = a1 <br>
		 * a8: 1stAdv = a6, 2stAdv = a7 <br>
		 * xx is a4, which is distinct with a3's advisors
		 */
		a4.setAdvisor1(a1);
		Phd a5 = new Phd("a5", 2021, 6, a4, a2);
		Phd a6 = new Phd("a6", 2021, 6);
		a6.setAdvisor1(a2);
		Phd a7 = new Phd("a7", 2021, 6, a4, a1);
		Phd a8 = new Phd("a8", 2021, 6);
		a8.setAdvisor1(a7);
		Phd a9 = new Phd("a8", 2021, 6, a7, a8);

		// Neither A nor B has an advisor
		assertEquals(false, a1.areSibs(a2));

		// A and B are the same object and they have the same non-null first advisor.
		assertEquals(false, a3.areSibs(a3));

		// A and B are different objects and they have the same first advisor.
		assertEquals(true, a3.areSibs(a4));

		// A and B are different objects and they have the same second advisor.
		assertEquals(true, a3.areSibs(a5));

		// A and B are different objects and the first advisor of one is the second
		// advisor of the other.
		assertEquals(true, a3.areSibs(a6)); // a3.2ndAdv = a6.1stAdv
		assertEquals(true, a3.areSibs(a7)); // a3.1stAdv = a7.2ndAdv

		// A and B are different objects and they have different first advisors.
		assertEquals(false, a3.areSibs(a8));

		// A and B are different objects and they have different second advisors.
		assertEquals(false, a3.areSibs(a9));
	}

}