package socket;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InternetAddressExample {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> interFaceList = NetworkInterface
					.getNetworkInterfaces();
			if (interFaceList == null) {
				System.out.println("no interfaces found");
			} else {
				while (interFaceList.hasMoreElements()) {
					NetworkInterface networkInterface = (NetworkInterface) interFaceList
							.nextElement();
					System.out.println("interface:"
							+ networkInterface.getName());
					Enumeration<InetAddress> addrList = networkInterface
							.getInetAddresses();
					if (!addrList.hasMoreElements()) {
						System.out.println("\t no address for this interface");
					} else {
						while (addrList.hasMoreElements()) {
							InetAddress inetAddress = (InetAddress) addrList
									.nextElement();
							System.out
									.print("\t address"
											+ ((inetAddress instanceof Inet4Address) ? "(v4)"
													: (inetAddress instanceof Inet6Address ? "(v6)"
															: "(?)")));
							System.out.println(": "
									+ inetAddress.getHostAddress());
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("Error getting network interfaces:"
					+ e.getMessage());
		}

		for (String host : args) {
			System.out.println(host + ":");
			InetAddress[] addressList;
			try {
				addressList = InetAddress.getAllByName(host);
				for (InetAddress inetAddress : addressList) {
					System.out.println("\t" + inetAddress.getHostAddress()
							+ "/" + inetAddress.getHostAddress());
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.out.println("\t Unable to find address for " + host);
			}
		}
	}

}
