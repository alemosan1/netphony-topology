package es.tid.topologyModuleBase.TAPITopoModel.model;

/**
 * Class to match a port number to the IP of a node.
 * @author Javier Soler
 *
 */
public class NodeAndEdges {

	private String nodeIP = null;
	private Long port = null;
	
	public NodeAndEdges(String nodeIP, Long port) {
		this.nodeIP = nodeIP;
		this.port = port;
	}

	public String getNodeIP() {
		return nodeIP;
	}

	public void setNodeIP(String nodeIP) {
		this.nodeIP = nodeIP;
	}

	public Long getPort() {
		return port;
	}

	public void setPort(Long port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeIP == null) ? 0 : nodeIP.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeAndEdges other = (NodeAndEdges) obj;
		if (nodeIP == null) {
			if (other.nodeIP != null)
				return false;
		} else if (!nodeIP.equals(other.nodeIP))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		return true;
	}

}
