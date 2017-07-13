package org.util;

public class SpeedUtils {
	public static String getState(Integer stateNum) {
		String state = "";
		switch (stateNum) {
		case 0:
			state = "空调关";
			break;
		case 1:
			state = "通风";
			break;
		case 2:
			state = "制冷";
			break;
		case 3:
			state = "制热";
			break;
		case 4:
			state = "加湿";
			break;
		case 5:
			state = "减湿";
			break;
		default:
			break;
		}
		return state;
	}
}
