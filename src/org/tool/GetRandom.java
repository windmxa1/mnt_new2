package org.tool;

public class GetRandom {
	private final static String[] array = {"南山区","宝安区","福田区","罗湖区","福田区","南山区","盐田区","龙华新区","龙岗区","宝安区","福田区"};
	public static String get2random() {
		Integer random = (int) (Math.random() * 89 + 10);
		if(random==80){
			random = (int) (Math.random() * 89 + 10);
		}
		return "" + random;
	}
	public static String get1random() {
		Integer random = (int) (Math.random() * 10 );
		return "" + random;
	}
	public static String getString(){
		Integer random = (int) (Math.random() * 10 );
		return array[random];
	}
	public static String get4random() {
		Integer random = (int) (Math.random() * 3000 + 1000);
		return "" + random;
	}
}
