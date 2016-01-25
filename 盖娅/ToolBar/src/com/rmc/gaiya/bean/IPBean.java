package com.rmc.gaiya.bean;

public class  IPBean{	
	private String HostMac;
    private String HostName;
    private String IsConneted;
    private String ip;
	
	public String getHostMac() {
		return HostMac;
	}

	public void setHostMac(String hostMac) {
		HostMac = hostMac;
	}

	public String getHostName() {
		return HostName;
	}

	public void setHostName(String hostName) {
		HostName = hostName;
	}

	public String getIsConneted() {
		return IsConneted;
	}

	public void setIsConneted(String isConneted) {
		IsConneted = isConneted;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "InforBean[HostMac="+HostMac+",HostName	="+HostName+",IsConneted="+IsConneted+",ip="+ip+"]";
	}




	

}
