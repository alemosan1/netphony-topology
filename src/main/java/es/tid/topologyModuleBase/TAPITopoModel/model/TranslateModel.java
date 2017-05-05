package es.tid.topologyModuleBase.TAPITopoModel.model;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

import es.tid.tedb.DomainTEDB;
import es.tid.tedb.IntraDomainEdge;
import es.tid.tedb.Node_Info;

import java.util.Random;
import java.util.UUID;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;




public class TranslateModel {
	
	// Variables for testing
	private static boolean printingLink = true;
	private static boolean printingNode = true;
	
	// Hashtable of key:value -> nodeIP:nodeUUID
	private static Hashtable<String,String> nodes_uuid = new Hashtable<String,String>();
	// Hashtable of key:value -> nodeIP/portNumber:portUUID
	private static Hashtable<String,String> ports_uuid = new Hashtable<String,String>();
	// Hashtable mapping nodes and ports, of key:value -> NodeAndEdges(nodeIP,port):portUUID
	private static Hashtable<NodeAndEdges,String> nodes_ports = new Hashtable<NodeAndEdges,String>();
	
	
	public static Topology translateTopology(String topId, DomainTEDB ted){
		Topology topo = new Topology();

		System.out.println("TopoUUID = " +topId);
		
		// If there is no UUID, create one or take it from attributes?? If "generate" is chosen, review "get/set EncapTopology"
		// calls in method "translateNodeIp". 
		if (topo.getUuid() == null) {
//			topo.setUuid(UUID_generate());	// generate one
			topo.setUuid(topId);			// take id from attributes
		}
		topo.getName();
		topo.getLabel();
		topo.getLayerProtocolName();
		
		  for(IntraDomainEdge link : ted.getIntraDomainLinks()){
			  String src_L = ((java.net.Inet4Address)link.getSource()).getHostAddress();
			  String dst_L = ((java.net.Inet4Address)link.getTarget()).getHostAddress();
			  String srcEdge = src_L + "/" + Long.toString(link.getSrc_if_id()).toString();
			  String dstEdge = dst_L + "/" + Long.toString(link.getDst_if_id()).toString();
			  
			  nodes_uuid.putIfAbsent(src_L, UUID_generate());
			  nodes_uuid.putIfAbsent(dst_L, UUID_generate());
			  ports_uuid.putIfAbsent(srcEdge, UUID_generate());
			  ports_uuid.putIfAbsent(dstEdge, UUID_generate());
			  
			  NodeAndEdges src_nodeEdges = new NodeAndEdges(src_L, link.getSrc_if_id());
			  NodeAndEdges dst_nodeEdges = new NodeAndEdges(dst_L, link.getDst_if_id());
			  nodes_ports.putIfAbsent(src_nodeEdges, ports_uuid.get(srcEdge));
			  nodes_ports.putIfAbsent(dst_nodeEdges, ports_uuid.get(dstEdge));
			  
			  topo.getLink().add(translateEdge(ted, link));
		  }
		  /*
		  FOR TESTING nodes_ports HASHTABLE (SEE KEYS:VALUES)
		  for(Map.Entry entry: nodes_ports.entrySet()) {
			  System.out.println("NODE_IP = " + ((NodeAndEdges)entry.getKey()).getNodeIP() + " PORT = " + ((NodeAndEdges)entry.getKey()).getPort() + " UUID = " + nodes_ports.get((NodeAndEdges)entry.getKey()));
		  }
		  */
		  
		  List<Node> nodes = new ArrayList<Node>();
		  for(Object node : ted.getIntraDomainLinksvertexSet()){
//			 // System.out.println("DEBUG nodeInTopology, (class "+node.getClass()+"):" +node );
//			  if(node instanceof es.tid.tedb.elements.Node){
//				  nodes.add(translateNode(ted, (es.tid.tedb.elements.Node)node));
//			  }
//			  else if(node instanceof java.net.Inet4Address){
				  nodes.add(translateNodeIp(ted, (java.net.Inet4Address)node, ted.getNodeTable().get(node)));
		  }
		  System.out.println("No de nodos = "+nodes.size());
		  topo.setNode(nodes);
		return topo;
	}
		  
	public static Link translateEdge(DomainTEDB ted, IntraDomainEdge e){
		Link link = new Link();
		
		link.getUuid();
		if (link.getUuid() == null) {
			link.setUuid(UUID_generate());
		}
		
		link.getName();
		
		link.getLabel();
		
		String src_L = ((java.net.Inet4Address)e.getSource()).getHostAddress();
		String dst_L = ((java.net.Inet4Address)e.getTarget()).getHostAddress();
		link.addNodeItem(nodes_uuid.get(src_L));
		link.addNodeItem(nodes_uuid.get(dst_L));
		/*
		FOR TESTING LIST OF NODES
	 	for (int i=0; i < link.getNode().size(); i++) {
			System.out.println("NODOS_L_"+i+" = " +link.getNode().get(i));
			if (i == link.getNode().size()-1) {System.out.println("\n");}
		}*/
		
		// Every src/dst port (node-edge-point) as nodeUUID/portUUID
		String srcEdge = nodes_uuid.get(src_L) + "/" + ports_uuid.get(src_L + "/" + Long.toString(e.getSrc_if_id()).toString());
		link.getNodeEdgePoint().add(srcEdge);
		String dstEdge = nodes_uuid.get(dst_L) + "/" + ports_uuid.get(dst_L + "/" + Long.toString(e.getDst_if_id()).toString());
		link.getNodeEdgePoint().add(dstEdge);
		
		link.getState();
		AdminStatePac stateL = new AdminStatePac();
		if (link.getState() == null) {
			stateL.setAdministrativeState(AdminStatePac.AdministrativeStateEnum.UNLOCKED);
			stateL.setLifecycleState(AdminStatePac.LifecycleStateEnum.INSTALLED);		
			stateL.setOperationalState(AdminStatePac.OperationalStateEnum.ENABLED);
			link.setState(stateL);
		}
		
		link.getTransferCapacity();
        link.getTransferCost();
		link.getTransferIntegrity();
		link.getTransferTiming();
		
		link.getRiskParameter();
		link.getValidation();
		link.getLpTransition();
		link.getLayerProtocolName();
		
		link.getDirection();
		if (link.getDirection() == null) {
			link.setDirection(Link.DirectionEnum.BIDIRECTIONAL);
		}
		
		
		// FOR TESTING (PRINTING A LINK IN MAIN CONSOLE)
		if (printingLink) {
//			System.out.println("Direction = " + link.getDirection());
//			System.out.println("EDGE = " + link.getNodeEdgePoint());
//			System.out.println("LINK = " + link.toString());	
			printingLink = false;
		}
		
		
//		  Object src = e.getSrc_Numif_id();
//		  if( e.getSource() instanceof  java.net.Inet4Address){
//			  Node node = TranslateModel.getNodeById( db, ((java.net.Inet4Address)e.getSource()).getHostAddress());
//			  edge.setSource(node);
//			  for(EdgeEnd end : node.getEdgeEnd()){
//				  if(end.getName().equals(e.getSrc_if_id())){
//					  edge.setLocalIfid(end);
//				  }
//			  }
//		  }else if( src instanceof es.tid.tedb.elements.EndPoint ){
//			 
//			  Node node = TranslateModel.getNodeById( db, ((es.tid.tedb.elements.Node)e.getSource()).getNodeID());
//			  edge.setSource(node);
//			  for(EdgeEnd end : node.getEdgeEnd()){
//				  if(end.getName().equals(((es.tid.tedb.elements.EndPoint) src).getIntf()) ){
//					  edge.setLocalIfid(end);
//				  }
//			  }
//			  
//		  }
//		  
//		  Object dst = e.getDst_Numif_id();
//		  if( e.getTarget() instanceof  java.net.Inet4Address){
//			  Node node = TranslateModel.getNodeById( db, ((java.net.Inet4Address)e.getTarget()).getHostAddress());
//			  edge.setTarget(node);
//			  for(EdgeEnd end : node.getEdgeEnd()){
//				  if(end.getName().equals(e.getDst_if_id())){
//					  edge.setRemoteIfid(end);
//				  }
//			  }
//		  }else if( dst instanceof es.tid.tedb.elements.EndPoint ){
//			  Node node = TranslateModel.getNodeById( db, ((es.tid.tedb.elements.EndPoint) dst).getNode());
//			  edge.setTarget(node);
//			  for(EdgeEnd end : node.getEdgeEnd()){
//				  if(end.getName().equals(((es.tid.tedb.elements.EndPoint) dst).getIntf()) ){
//					  edge.setRemoteIfid(end);
//				  }
//			  }
//		  }	  
//		
//		link.
//		String src = edge.
//				
//				domain_src_router.toString();
//				+":"+e.domain_dst_router.toString();
//		link.getNode().add(e)
//		  edge.setName();
//		  edge.setEdgeId("0");
//		  edge.setEdgeType(EdgeTypeEnum.eth_edge);
//		  if (e.getTE_info()!=null) {
//			  if (e.getTE_info().getDefaultTEMetric()!=null) {
//				  edge.setMetric(e.getTE_info().getDefaultTEMetric().toString());
//			  } 
//			  if (e.getTE_info().getMaximumBandwidth()!=null) {
//				  edge.setMaxResvBw(e.getTE_info().getMaximumBandwidth().toString());
//			  } 
//			  if (e.getTE_info().getUnreservedBandwidth()!=null) {
//				  edge.setMaxResvBw(e.getTE_info().getUnreservedBandwidth().toString());
//			  } 
//			  
//			 
//		  }
//		  
////		  edge.s
//		  Node src_node = new Node();
//		  if (e.getDomain_src_router() instanceof Inet4Address) {
//			  src_node.setDomain(((Inet4Address)(e.getDomain_src_router())).getHostName());
//		  } else {
//			  src_node.setDomain(e.getDomain_src_router().toString());
//
//		  }
//		  if (e.getSrc_router_id() instanceof Inet4Address) {
//			  src_node.setNodeId(((Inet4Address)(e.getSrc_router_id())).getHostName());
//		  }else {
//			  src_node.setNodeId(e.getSrc_router_id().toString());
//		  }
//		 
//		  edge.setSource(src_node);
//		  Node dst_node = new Node();
//		  dst_node.setDomain(e.getDomain_dst_router().toString());
//		  dst_node.setNodeId(e.getDst_router_id().toString());
//		  edge.setTarget(dst_node);
		return link;
		
	}
	
	public static Node translateNodeIp(DomainTEDB ted, Inet4Address n, Node_Info nodeInfo) {
		Node node = new Node();
		
		System.out.println("DEBUG translateNodeIP, nodeInfo (of node "+n+"):"+nodeInfo);
		
		node.getUuid();
		if (node.getUuid() == null) {
			node.setUuid(nodes_uuid.get(n.getHostAddress())); // Get UUID from hastable
		}
		
		node.getName();
		// For naming nodes
		NameAndValue nameN = new NameAndValue();
		if (node.getName().size() == 0) {
			nameN.setValue(n.getHostAddress().toString());
			nameN.setValueName("name");
			node.addNameItem(nameN);
			node.setName(node.getName());
		}
		
		node.getLabel();
		
		// Add nodeIP/port as name for each port (owned-node-edge-point)
		for(Map.Entry entry: nodes_ports.entrySet()) {
			NodeEdgePoint e = new NodeEdgePoint();
			// For naming node-edge-points
			NameAndValue nameNE = new NameAndValue();
			
			String nodeIP = ((NodeAndEdges)entry.getKey()).getNodeIP();
			String port = Long.toString(((NodeAndEdges)entry.getKey()).getPort()).toString();
			if ( nodeIP.equals(n.getHostAddress()) ) {
				node.getOwnedNodeEdgePoint().add(e);
				
				e.getUuid();
				if (e.getUuid() == null) {
					e.setUuid(nodes_ports.get((NodeAndEdges)entry.getKey()));
				}
				
				e.getName();
				if (e.getName().size() == 0) {
					nameNE.setValue(nodeIP + "/" + port);
					nameNE.setValueName("name");
					e.addNameItem(nameNE);
					e.setName(e.getName());
				}
				
				e.getLabel();
				e.getLayerProtocol();
				e.getAggregatedNodeEdgePoint();
				e.getMappedServiceInterfacePoint();
				e.getState();
				
				e.getTerminationDirection();
				if (e.getTerminationDirection() == null) {
					e.setTerminationDirection(NodeEdgePoint.TerminationDirectionEnum.BIDIRECTIONAL);
				}
				
				e.getLinkPortDirection();
				if (e.getLinkPortDirection() == null) {
					e.setLinkPortDirection(NodeEdgePoint.LinkPortDirectionEnum.BIDIRECTIONAL);
				}
				
				e.getLinkPortRole();
				if (e.getLinkPortRole() == null) {
					e.setLinkPortRole(NodeEdgePoint.LinkPortRoleEnum.ROOT);
				}
			}
			// FOR TESTING RECOVERY OF nodeIP/port, AND UUID OF THAT PORT
//			System.out.println("NODE_IP = " + ((NodeAndEdges)entry.getKey()).getNodeIP() + " PORT = " + ((NodeAndEdges)entry.getKey()).getPort() + " UUID = " + nodes_ports.get((NodeAndEdges)entry.getKey()));
		}
		
		node.getAggregatedNodeEdgePoint();
		
		node.getEncapTopology();
		if (node.getEncapTopology() == null) {
			node.setEncapTopology(ted.getDomainID().getHostAddress());
//			Topology getUuid() --> non-static method... think about how to do it
		}
		
		node.getState();
		// For adding default state
		AdminStatePac stateN = new AdminStatePac();
		if (node.getState() == null) {
			stateN.setAdministrativeState(AdminStatePac.AdministrativeStateEnum.UNLOCKED);
			stateN.setLifecycleState(AdminStatePac.LifecycleStateEnum.INSTALLED);		
			stateN.setOperationalState(AdminStatePac.OperationalStateEnum.ENABLED);
			node.setState(stateN);
		}
		
		node.getTransferCapacity();
        node.getTransferCost();
        node.getTransferIntegrity();
        node.getTransferTiming();
        node.getLayerProtocolName();
		
        // FOR TESTING (PRINTING A NODE IN MAIN CONSOLE)
		if (printingNode) {
//			System.out.println("NODE = " +node.toString());
//			System.out.println("-----END OF NODE-----");
			printingNode = false;
		}
		
//		node.setName(n.getHostAddress());
//		node.setNodeId(n.getHostAddress());
//		if(nodeInfo==null){
//			System.out.println("No node Info: DomainID:"+ted.getDomainID());
//			node.setDomain(ted.getDomainID().getHostAddress());
//		}else{
//			node.setDomain(nodeInfo.getAs_number().getHostAddress());
//		}
//		node.setEdgeEnd(new ArrayList<EdgeEnd>());
		return node;
	}
	
	/**
	 * Generates a UUID following the default pattern of the UUID Java class 
	 * Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}
	 * Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6
	 */
	public static synchronized String UUID_generate() {
		String uuid = UUID.randomUUID().toString();
		if ( nodes_uuid.containsValue(uuid) || ports_uuid.containsValue(uuid) ) {
			uuid = UUID.randomUUID().toString();
		}
		return uuid;
		
	}
	
	/*
	FOR MAKING INVERSE SEARCH IN A HASHTABLE
	
	System.out.println("MAPPING = " +nodes_uuid.get(n.getHostAddress()) + " IP = "+n.getHostAddress());
			
	String key = null;
    String value = node.getUuid();
    for(Map.Entry entry: nodes_uuid.entrySet()){
        if(value.equals(entry.getValue())){
            key = entry.getKey().toString();
            break; //breaking because its one to one map
        }
    }
    System.out.println("Got key from value in hashtable key:  "+ key +" value: " + value);
    */
	

}
