package test;

import org.action.GetIPCListAction;

public class test09 {
	public static void main(String[] args) {
		int k = 0;
		String deviceAlarm = "";
		String Avalue = "移动侦测报警-2016/09/30 14:28:54-1475216934-1;遮挡报警-2016/09/30 14:28:58-1475216938-1";
		String[] s = Avalue.split(";");
		for (String s1 : s) {
			String[] s2 = s1.split("-");
			if (s2[3].equals("" + 1)) {// 通道号符合要求
				if (k == 0) {
					deviceAlarm = s2[0];
				} else {
					deviceAlarm = deviceAlarm + "," + s2[0];
				}
				k++;
			}
		}
		System.out.println(deviceAlarm);
		
	}
}
