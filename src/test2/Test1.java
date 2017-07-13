package test2;

public class Test1 {
	public static void main(String[] args) {
		Double a = 144439d;
		String b = Long.toHexString(a.longValue());
		String c = "00000000".substring(b.length()) + b;
		Integer num = Integer.parseInt(c.substring(6, 8), 16) ;
		Double temp = Double.parseDouble(""+num)/2;
		Integer wet = Integer.parseInt(c.substring(4, 6), 16);
		Integer staus = Integer.parseInt(c.substring(2, 4), 16);
		System.out.println(temp + " " + wet + " " + staus);
	}

}
