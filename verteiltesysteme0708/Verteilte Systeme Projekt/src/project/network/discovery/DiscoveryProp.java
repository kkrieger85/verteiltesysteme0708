package project.network.discovery;

/**
 *
 *
 * @author Andreas Kuntz
 * 
 * @version 0.1 (20.03.2008)
 *
 */
public final class DiscoveryProp {
	/*
	System properties required
	jip.rmi.multicast.address
	jip.rmi.multicast.port
	jip.rmi.unicast.port
	jip.rmi.unicast.portRange
	jip.rmi.protocol.header
	jip.rmi.protocol.delim
	*/
	
	private static java.util.Properties _props;
    public static final String ANY="any";
    
    private DiscoveryProp(){
    }
    
    public static void setProperties(java.util.Properties props){
        _props=props;
    }
    
    public static void setProperties(String fileName) throws java.io.IOException {
        java.io.FileInputStream fis = new java.io.FileInputStream(fileName);
        _props = new java.util.Properties();
        _props.load(fis);
        fis.close();
        _props.list(System.out);
    }
    
    public static java.net.InetAddress getMulticastAddress() throws java.net.UnknownHostException {
        String multicastAddress=_props.getProperty("project.network.discovery.multicast.address");
        return java.net.InetAddress.getByName(multicastAddress); 
    }
    
    public static int getMulticastPort() {
        return getIntProperty("project.network.discovery.multicast.port");
    }
    
    public static String getProtocolDelim() {
        return _props.getProperty("project.network.discovery.protocol.delim"); 
    }
    
    public static String getProtocolHeader() {
        return _props.getProperty("project.network.discovery.protocol.header");
    }
    
    public static int getUnicastPort() {
          return getIntProperty("project.network.discovery.unicast.port");
    }
    
    public static int getUnicastPortRange() {
          return getIntProperty("project.network.discovery.unicast.portRange");
    }
    
    public static String getRegistyUrlPrefix() {
        return _props.getProperty("project.network.discovery.registry.urlPrefix");
    }
    
    private static int getIntProperty(String propertyName) {
        String str=_props.getProperty(propertyName);
        return Integer.parseInt(str);
    }
}
