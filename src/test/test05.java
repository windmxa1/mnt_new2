package test;


public class test05 {
	public static void main(String[] args) throws Exception {
		
		
		String value = "192.168.1.70-asd-2016/09/09 15:54:15-1473407655-1-192.168.1.70-dfg-2016/09/09 15:54:19-1473407659-2".replace("3", "").replace("6", "").replace(" ","");
//		value.replace("3", "");
//		value.replace("6", "");
		String [] values = value.split("-");
		for (int i = 0; i < values.length; i=i+5) {
			String a = values[i];
			System.out.println(a);
		}
		System.out.println(values.length);
		
		String aString = "asdasd";
		if(aString.indexOf("d")!=-1){
			System.out.println("ok");
		}
		String asda = "         海康摄像头-1   ";
		System.out.println(asda.contains("海康摄像头") );
		if(asda.contains("海康摄像头") ){
			System.out.println(true);
		}
		
	}
	
}
