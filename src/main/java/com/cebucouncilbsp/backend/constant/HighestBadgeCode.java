/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum HighestBadgeCode {

	MEMBERSHIP(0, "00"), YOUNG_USA(1, "10"), GROWING_USA(1, "11"), LEAPING_USA(1, "12"), TENDERFOOT(2, "20"),
	SECOND_CLASS(2, "21"), FIRST_CLASS(2, "22"), EXPLORER(3, "30"), PATHFINDER(3, "31"), OUTDOORSMAN(3, "32"),
	VENTURER(3, "33"), EAGLE(3, "34"), YELLOW_QUADRANT(4, "40"), GREEN_QUADRANT(4, "41"), RED_QUADRANT(4, "42"),
	BLUE_QUADRANT(4, "43"), NATION_BUILDER(4, "44");

	private final int section;
	private final String code;

	HighestBadgeCode(int section, String code) {
		this.section = section;
		this.code = code;
	}

	public int getSection() {
		return section;
	}

	public String getCode() {
		return code;
	}
}
