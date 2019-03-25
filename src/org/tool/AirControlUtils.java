package org.tool;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AirControlUtils {
	private static final int TIMEOUT = 10 * 1000; // 设置接收数据的超时时间
	private static final int MAXNUM = 5; // 设置重发数据的最多次数

	/**
	 * 获取异或值
	 */
	private static byte getXORValue(byte[] bytes) {
		byte result = bytes[1];
		for (int i = 2; i <= 15; i++) {
			result = (byte) (result ^ bytes[i]);
		}
		return result;
	}

	/**
	 * 获取传输数据
	 */
	private static byte[] getControllByte(int ctl, int temp, String displayname) {
		byte[] buf = new byte[18];
		buf[0] = (byte) 0x7F;
		buf[1] = (byte) 0x01;
		buf[2] = (byte) 0x0D;
		buf[3] = (byte) 0x64;
		buf[4] = (byte) 0x57;
		if (displayname.replaceAll("[^\\d]+", "").equals("2")) {
			buf[5] = (byte) 0x09;
		} else {
			buf[5] = (byte) 0x08;
		}
		buf[6] = (byte) 0xE0;
		buf[7] = (byte) 0x00;
		switch (ctl) {
		case 0:// 关空调
			buf[8] = (byte) 0x00;
			buf[9] = (byte) 0x00;
			buf[10] = (byte) 0x80;
			buf[11] = (byte) 0x00;
			buf[12] = (byte) 0x00;
			buf[13] = (byte) 0x00;
			buf[14] = (byte) 0x00;
			buf[15] = (byte) 0x00;
			buf[16] = getXORValue(buf);
			buf[17] = (byte) 0xEF;
			break;
		case 1:
			buf[8] = (byte) 0x00;
			buf[9] = (byte) 0x00;
			buf[10] = (byte) 0x81;
			buf[11] = (byte) 0x00;
			buf[12] = (byte) 0x00;
			buf[13] = (byte) 0x00;
			buf[14] = (byte) 0x00;
			buf[15] = (byte) 0x00;
			buf[16] = getXORValue(buf);
			buf[17] = (byte) 0xEF;
			break;
		case 2:// 控制空调温度
			buf[8] = (byte) Integer.parseInt(
					Integer.toHexString(temp * 2 + 128), 16);
			buf[9] = (byte) 0x00;
			buf[10] = (byte) 0x00;
			buf[11] = (byte) 0x00;
			buf[12] = (byte) 0x00;
			buf[13] = (byte) 0x00;
			buf[14] = (byte) 0x00;
			buf[15] = (byte) 0x00;
			buf[16] = getXORValue(buf);
			buf[17] = (byte) 0xEF;
			break;
		default:
			break;
		}
		return buf;
	}

	/**
	 * 控制精密空调
	 * 
	 * @param ctl
	 *            0关空调，1开空调，2设置温度
	 * @param temp
	 *            温度
	 * @param host
	 *            环控主机IP
	 * @return
	 * @throws IOException
	 */
	public static boolean control(String host, int ctl, int temp,
			String displayname) throws IOException {
		boolean result = false;
		// 7F 01 05 64 6D 01 30 00 3C EF
		byte[] buf = getControllByte(ctl, temp, displayname);
		// 客户端在3152端口监听接收到的数据
		DatagramSocket ds = new DatagramSocket(3152);
		InetAddress loc = InetAddress.getByName(host);
		// 定义用来发送数据的DatagramPacket实例
		DatagramPacket dp_send = new DatagramPacket(buf, buf.length, loc, 3152);
		// 定义用来接收数据的DatagramPacket实例
		byte[] recBuf = new byte[15];
		DatagramPacket dp_receive = new DatagramPacket(recBuf, 15);
		// 数据发向本地3152端口
		ds.setSoTimeout(TIMEOUT); // 设置接收数据时阻塞的最长时间
		int tries = 0; // 重发数据的次数
		boolean receivedResponse = false; // 是否接收到数据的标志位
		// 直到接收到数据，或者重发次数达到预定值，则退出循环
		while (!receivedResponse && tries < MAXNUM) {
			// 发送数据
			ds.send(dp_send);
			try {
				// 接收从服务端发送回来的数据
				ds.receive(dp_receive);
				// 如果接收到的数据不是来自目标地址，则抛出异常
				if (!dp_receive.getAddress().equals(loc)) {
					throw new IOException(
							"Received packet from an umknown source");
				}
				// 如果接收到数据。则将receivedResponse标志位改为true，从而退出循环
				receivedResponse = true;
			} catch (InterruptedIOException e) {
				// 如果接收数据时阻塞超时，重发并减少一次重发的次数
				tries += 1;
				System.out.println("Time out," + (MAXNUM - tries)
						+ " more tries...");
			}
		}
		if (receivedResponse) {
			// 如果收到数据，则打印出来
			// 7F 64 0A 01 72 F3 00 02 35 0C 0F 03 11 C8 EF
			// 7f 64 0a 01 72 f4 00 02 36 14 0f 03 11 d4 ef
			// System.out.println(recBuf[4] == (byte) 0x72);
			// System.out.println(Utils.byteArrayToHexStr(recBuf));
			if (recBuf[4] == (byte) 0x72) {// 判断操作是否成功
				result = true;
			}
			// 由于dp_receive在接收了数据之后，其内部消息长度值会变为实际接收的消息的字节数，
			// 所以这里要将dp_receive的内部消息长度重新置为1024
			dp_receive.setLength(15);
		} else {
			// 如果重发MAXNUM次数据后，仍未获得服务器发送回来的数据，则打印如下信息
			System.out.println("No response -- give up.");
		}
		ds.close();
		return result;
	}

	// public static void main(String args[]) {
	// try {
	// // System.out.println(control(0, 26));
	// // System.out.println(control(1, 26));
	// System.out.println(control(2, 24));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
