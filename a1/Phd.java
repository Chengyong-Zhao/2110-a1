
/**
 * NetId: cz473. Time spent: 3 hours, 15 minutes <br>
 * What I thought about this assignment: need to think very comprehensively <br>
 * <br>
 * An instance maintains info about the Phd of a person.
 */
package a1;

/** An instance (i.e. object) maintains info about a Phd */
public class Phd {

	/** Name of the person , String length > 0 */
	private String name;
	/** year Phd was awarded. year must > 1000 */
	private int year;
	/** month Phd was awarded. In 1..12, with 1 meaning January, etc. */
	private int month;
	/** first advisor, null if unknown */
	private Phd firstAdvisor;
	/** second advisor, null if unknown or only one */
	private Phd secondAdvisor;
	/** count of advisees */
	private int numberOfAdvisees;

	/**
	 * Constructor: an instance for a Phd with name n, Phd year y, Phd month m. Its
	 * advisors are unknown, and it has no advisees. <br>
	 * Precondition: n has at least 1 char, y > 1000, and m is in 1..12
	 */
	public Phd(String n, int y, int m) {
		// Check Precondition
		assert n != null && n.length() > 0;
		assert y > 1000;
		assert m >= 1 && m <= 12;

		this.name = n;
		this.year = y;
		this.month = m;
		this.firstAdvisor = null;
		this.secondAdvisor = null;
		this.numberOfAdvisees = 0;
	}

	/**
	 * Constructor: an instance for a Phd with name n, Phd year y, Phd month m,
	 * first advisor a1, and second advisor a2. <br>
	 * Precondition: n has at least 1 char, y > 1000, m is in 1..12, <br>
	 * a1 and a2 are not null, and a1 and a2 are different.
	 */
	public Phd(String n, int y, int m, Phd a1, Phd a2) {
		// Check Precondition
		assert n != null && n.length() > 0;
		assert y > 1000;
		assert m >= 1 && m <= 12;
		assert a1 != null && a2 != null;
		assert a1 != a2;

		this.name = n;
		this.year = y;
		this.month = m;
		this.firstAdvisor = a1;
		this.secondAdvisor = a2;
		this.numberOfAdvisees = 0;

		a1.numberOfAdvisees++;
		a2.numberOfAdvisees++;
	}

	/**
	 * = the name of this person.
	 */
	public String name() {
		return this.name;
	}

	/**
	 * = the date on which this person got the Phd. <br>
	 * In the form "month/year" with no blanks, e.g. "6/2007"
	 */
	public String date() {
		return this.month + "/" + this.year;
	}

	/**
	 * = the first advisor of this Phd (null if unknown).
	 */
	public Phd advisor1() {
		return this.firstAdvisor;
	}

	/**
	 * = the second advisor of this Phd (null if unknown or non-existent).
	 */
	public Phd advisor2() {
		return this.secondAdvisor;
	}

	/**
	 * = the number of Phd advisees of this person.
	 */
	public int nAdvisees() {
		return this.numberOfAdvisees;
	}

	/**
	 * Make p the first advisor of this person. <br>
	 * Precondition: the first advisor is unknown and p is not null.
	 */
	public void setAdvisor1(Phd p) {
		assert this.firstAdvisor == null;
		assert p != null;

		this.firstAdvisor = p;
		p.numberOfAdvisees++;
	}

	/**
	 * Make p the second advisor of this person. <br>
	 * Precondition: The first advisor (of this person) is known, the second advisor
	 * is unknown, p is not null, and p is different from the first advisor.
	 */
	public void setAdvisor2(Phd p) {
		assert this.firstAdvisor != null;
		assert this.secondAdvisor == null;
		assert p != null;
		assert p != this.firstAdvisor;

		this.secondAdvisor = p;
		p.numberOfAdvisees++;
	}

	/**
	 * = "this Phd has no advisees", <br>
	 * i.e. true if this Phd has no advisees and false otherwise.
	 */
	public boolean hasNoAdvisees() {
		return this.numberOfAdvisees == 0;
	}

	/**
	 * = "p is not null and this person got the Phd before p.”
	 */
	public boolean gotBefore(Phd p) {
		assert p != null;
		return (this != p) && ((this.year < p.year) || (this.year == p.year && this.month < p.month));
	}

	/**
	 * = "this person and p are intellectual siblings." <br>
	 * Precondition: p is not null.
	 */
	public boolean areSibs(Phd p) {
		assert p != null;
		return (this != p) && ((this.firstAdvisor != null && p.firstAdvisor != null
				&& this.firstAdvisor == p.firstAdvisor)
				|| (this.secondAdvisor != null && p.secondAdvisor != null && this.secondAdvisor == p.secondAdvisor)
				|| (this.firstAdvisor != null && p.secondAdvisor != null && this.firstAdvisor == p.secondAdvisor)
				|| (this.secondAdvisor != null && p.firstAdvisor != null && this.secondAdvisor == p.firstAdvisor));
	}

}
