/**
 * 
 */
package com.ytdev.utils.jnet;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 * @author Thirumalaivasan Rajasekaran
 * 
 */
public class NetworkInterface {

	public static HashMap<String, List<String>> getInformation() {
		HashMap<String, List<String>> nwInterfaceInfoMap = new HashMap<>();
		Enumeration<java.net.NetworkInterface> networkInterfaceEnum = null;
		try {
			networkInterfaceEnum = (Enumeration<java.net.NetworkInterface>) java.net.NetworkInterface
					.getNetworkInterfaces();
			int unknownInterfaceCount = 1;
			while (networkInterfaceEnum.hasMoreElements()) {
				java.net.NetworkInterface networkInterface = networkInterfaceEnum
						.nextElement();
				String displayName = networkInterface.getDisplayName();
				if(displayName == null || displayName.isEmpty()){
					displayName = "Unknown Interface #"+unknownInterfaceCount;
					unknownInterfaceCount++;
				}
				String name = networkInterface.getName();
				final int mtu = networkInterface.getMTU();
				final java.net.NetworkInterface parent = networkInterface.getParent();
				boolean isLoopback = networkInterface.isLoopback();
				boolean isPointToPoint = networkInterface.isPointToPoint();
				boolean isUp = networkInterface.isUp();
				boolean isVirtual = networkInterface.isVirtual();
				boolean supportsMulticast = networkInterface.supportsMulticast();
				final List<InterfaceAddress> listInterfaceAddresses = networkInterface.getInterfaceAddresses();
				final byte[] macbytes = networkInterface.getHardwareAddress();
				Enumeration<InetAddress> inetAddressEnum = networkInterface.getInetAddresses();
				List<String> infoList = new ArrayList<>();
				infoList.add("Name: " 	+ name);
				infoList.add("MTU: " 	+ mtu);
//				infoList.add("Parent Interface	:"	+parent != null?parent.getDisplayName():"-");
				infoList.addAll(parseInetAddressEnumeration(inetAddressEnum));
				infoList.add("Hardware Address(MAC): " 	+ parseMACAddress(macbytes));
				infoList.add("Loopback: " 	+ isLoopback);
				infoList.add("Point To Point: " 	+ isPointToPoint);
				infoList.add("Up: " 	+ isUp);
				infoList.add("Virtual: " 	+ isVirtual);
				infoList.add("Supports Muliticast: " 	+ supportsMulticast);
				infoList.add("InterfaceAddresses: " 	+ parseInterfaceAddresses(listInterfaceAddresses));
				nwInterfaceInfoMap.put(displayName, infoList);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return nwInterfaceInfoMap;

	}

	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			return "UNKNOWN";
		}
	}

	 private static List<String> parseInetAddressEnumeration(Enumeration<InetAddress> enumeration){
		 List<String> inetAddressList =  new ArrayList<>();
		 while(enumeration.hasMoreElements()){
			 InetAddress inetAddress = enumeration.nextElement();
			 if(inetAddress != null){
				 String hostName = inetAddress.getCanonicalHostName();
				 String hostAddress = inetAddress.getHostAddress();
				 inetAddressList.add("Host Name/IP: "+hostName+" / "+hostAddress);
			 }
		 }
		 return inetAddressList;
	 }
	 
	 private static String parseInterfaceAddresses(List<InterfaceAddress> list){
		 if(list == null || list.isEmpty()){
			 return "-";
		 }
		 String parsedListToString =  "";
		 for(InterfaceAddress interfaceAddress : list){
			 if(interfaceAddress.getAddress() != null){
				 if(!parsedListToString.isEmpty()){
					 parsedListToString = parsedListToString+" , "+interfaceAddress.getAddress().getHostName()+" / "+interfaceAddress.getAddress().getHostAddress();
				 }else{
					 parsedListToString = interfaceAddress.getAddress().getHostName()+" / "+interfaceAddress.getAddress().getHostAddress();
				 }
			 }
		 }
		 return parsedListToString;
	 }
	 
	 private static String parseMACAddress(byte[] macbytes){
		 if(macbytes == null || macbytes.length <= 0){
			 return "-";
		 }
		 String mac = "";
		 for(byte macbyte : macbytes){
			 mac += String.format("%02X ", macbyte).toUpperCase().toString();
		 }
		 return mac;
		 
	 }
}
