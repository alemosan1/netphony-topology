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
//import es.tid.tedb.TE_Information;
//import es.tid.tedb.FileTEDBUpdater;
//import es.tid.tedb.TEDB;




public class TranslateModel {
	
	// Variable for testing
	private static boolean printingLink = true;
	private static boolean printingNode = true;
	private static NameAndValue nameL = new NameAndValue();
	private static NameAndValue labelL = new NameAndValue();
	private static AdminStatePac stateL = new AdminStatePac();
	private static NameAndValue labelN = new NameAndValue();
	private static AdminStatePac stateN = new AdminStatePac();
	

	
	// Hashtable of key:value -> nodeIP:nodeUUID
	private static Hashtable<String,String> nodes_uuid = new Hashtable<String,String>();
	// Hashtable of key:value -> nodeIP/portNumber:portUUID
	private static Hashtable<String,String> ports_uuid = new Hashtable<String,String>();
	// Hashtable mapping nodes and ports, of key:value -> portUUID:nodeIP
	private static Hashtable<String,String> ports_nodes = new Hashtable<String,String>();
	// Hashtable mapping nodes and ports, of key:value -> nodeIP:listOfPorts
//	private static Hashtable<String,List<NodeEdgePoint>> nodes_ports = new Hashtable<String,List<NodeEdgePoint>>();
	
	public static Topology translateTopology(String topId, DomainTEDB ted){
		Topology topo = new Topology();

		System.out.println("TopoUUID = " +topId);
		
		// If there is no UUID, create one or take it from attributes??
		if (topo.getUuid() == null) {
//			topo.setUuid(UUID_generate());	// generate one
			topo.setUuid(topId);			// take id from attributes
		}
		topo.getName();
		topo.getLabel();
		topo.getLayerProtocolName();
//		if (topo.getName().length == 0) {
//			NameAndValue = 
//		}
		
		  for(IntraDomainEdge link : ted.getIntraDomainLinks()){
			  String src_L = ((java.net.Inet4Address)link.getSource()).getHostAddress();
			  String dst_L = ((java.net.Inet4Address)link.getTarget()).getHostAddress();
			  String srcEdge = src_L + "/" + Long.toString(link.getSrc_if_id()).toString();
			  String dstEdge = dst_L + "/" + Long.toString(link.getDst_if_id()).toString();
			  
			  nodes_uuid.putIfAbsent(src_L, UUID_generate());
			  nodes_uuid.putIfAbsent(dst_L, UUID_generate());
			  ports_uuid.putIfAbsent(srcEdge, UUID_generate());
			  ports_uuid.putIfAbsent(dstEdge, UUID_generate());
			  
			  ports_nodes.putIfAbsent(ports_uuid.get(srcEdge), src_L);
			  ports_nodes.putIfAbsent(ports_uuid.get(dstEdge), dst_L);
//			  ports_nodes.putIfAbsent(src_L, nodeedge...)
//			  ports_nodes.putIfAbsent(dst_L, nodeedge...)
			  
			  topo.getLink().add(translateEdge(ted, link));
		  }
//		  System.out.println(nodes_uuid.toString());
		  
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
		
		// Set name, label and state
//		intializeLinkTest(link);
		
		link.getDirection();
		if (link.getDirection() == null) {
			link.setDirection(Link.DirectionEnum.UNDEFINED_OR_UNKNOWN);
		}
		link.getLabel();
		link.getLayerProtocolName();
		link.getLpTransition();
		
		String src_L = ((java.net.Inet4Address)e.getSource()).getHostAddress();// + " with IP: " +((java.net.Inet4Address)e.getSource()).getHostAddress());
		String dst_L = ((java.net.Inet4Address)e.getTarget()).getHostAddress();// + " with IP: " +((java.net.Inet4Address)e.getTarget()).getHostAddress());
		link.addNodeItem(nodes_uuid.get(src_L));
		link.addNodeItem(nodes_uuid.get(dst_L));
		/*for (int i=0; i < link.getNode().size(); i++) {
			System.out.println("NODOS_L_"+i+" = " +link.getNode().get(i));
			if (i == link.getNode().size()-1) {System.out.println("\n");}
		}*/
		
		link.getName();
		
		String srcEdge = src_L + "/" + Long.toString(e.getSrc_if_id()).toString();
		srcEdge += "/" + ports_uuid.get(srcEdge);
		link.getNodeEdgePoint().add(srcEdge);
		String dstEdge = dst_L + "/" + Long.toString(e.getDst_if_id()).toString();
		dstEdge += "/" + ports_uuid.get(dstEdge);
		link.getNodeEdgePoint().add(dstEdge);
		
		link.getRiskParameter();
		link.getState();
		link.getTransferCapacity();
        link.getTransferCost();
		link.getTransferIntegrity();
		link.getTransferTiming();
		link.getUuid();
		if (link.getUuid() == null) {
			link.setUuid(UUID_generate());
		}
		link.getValidation();
		
		if (printingLink) {
//			System.out.println("Direction = " + link.getDirection());
			System.out.println("EDGE = "+link.getNodeEdgePoint());
//			System.out.println("LINK = " + link.toString());	
//			printingLink = false;
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
		
		if (node.getUuid() == null) {
			node.setUuid(nodes_uuid.get(n.getHostAddress())); // Get UUID from hastable
		}
		
		List<String> key = new ArrayList<String>();
		String value = n.getHostAddress();
		for(Map.Entry entry: ports_nodes.entrySet()){
			if(value.equals(entry.getValue())){
				key.add(entry.getKey().toString());// = entry.getKey().toString();
//				break; //breaking because its one to one map
			}
		}
//		System.out.println("Got key (portUUID) from value (nodeIP) in hashtable key:  "+ key +" value: " + value);
		
		for (int i=0; i < key.size(); i++) {
			System.out.println("PORTS_UUID"+i+" = " +key.get(i) +" NODE: " + value);
			if (i == key.size()-1) {System.out.println("\n");}
		}
		
		
		// Hashtable mapping nodes and ports, of key:value -> portUUID:nodeIP
//		private static Hashtable<String,String> ports_nodes = new Hashtable<String,String>();
//		ports_nodes.putIfAbsent(ports_uuid.get(srcEdge), src_L)
		
//		System.out.println("MAPPING = " +ports_nodes.get(n.getHostAddress()) + " IP = "+n.getHostAddress());
//		
//		String key = null;
//        String value = node.getUuid();
//        for(Map.Entry entry: nodes_uuid.entrySet()){
//            if(value.equals(entry.getValue())){
//                key = entry.getKey().toString();
//                break; //breaking because its one to one map
//            }
//        }
//        System.out.println("Got key from value in hashtable key:  "+ key +" value: " + value);
		
		
		NodeEdgePoint e = new NodeEdgePoint();
		node.getOwnedNodeEdgePoint().add(e);
//		if (e.getUuid() == null) {
//			e.setUuid(ports_uuid.get(n.getHostAddress()));
//		}
		
		node.getLayerProtocolName();
		
		// For naming nodes and node-edge-points
		NameAndValue nameN = new NameAndValue();
		NameAndValue nameNE = new NameAndValue();
		
		if (node.getName().size() == 0) {
			nameN.setValue(n.getHostAddress().toString());
			nameN.setValueName("name");
			node.addNameItem(nameN);
			node.setName(node.getName());
		}
		node.getName();
		node.getLabel();
//		if (node.getLayerProtocolName() == null) {
//			node.setLayerProtocolName(Node.LayerProtocolNameEnum.UNDEFINED_OR_UNKNOWN);
//		}
		
		if (printingNode) {
//			System.out.println("NODE = " +node.toString());
//			printingNode = false;
			System.out.println(key + "\n");
			System.out.println("-----END OF NODE-----");
		}
		
		
//		System.out.println("NODES 1 = " +ted.getNodeTable());
//		System.out.println("NODES 2 = " +ted.getNodeTable().get(node));
		
//		System.out.println("NODES = " + FileTEDBUpdater.graph.vertexSet());
//		System.out.println("LABELS = "+TE_Information.getAvailableLabels());
//		System.out.println("TOPOLOGY = "+TEDB.printTopology());
		
		
//		System.out.println("NODE_NAME = "+nodeInfo.getName());
//		System.out.println("IP_ADDR = "+nodeInfo.getIpv4Address());
//		System.out.println("NODE_SID = "+nodeInfo.getSid());
		
//		System.out.println("IP = " + ((Node_Info)node).getName());
		
//		System.out.println("Node state = "+e.getState().toString());
//		System.out.println("n = " + n.toString());
//		System.out.println("aggregating = " + node.getAggregatedNodeEdgePoint().add(n.toString()));
//		System.out.println("aggregated = " + node.getAggregatedNodeEdgePoint());
//		System.out.println("clientNode = " + e.getClientNodeEdgePoint());
//		System.out.println("EncapTopology = " + node.getEncapTopology());
		//e.
		//owned-node-edge-point
		
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
		if ( nodes_uuid.containsValue(uuid) ) {
			uuid = UUID.randomUUID().toString();
		}
		return uuid;
		
/*		Random r = new Random();
		
		System.out.println("lastUUID_1 = "+lastUUID);
		
		String UUIDstr = "";
		String alphabet = "abcdefABCDEF0123456789";
		if (lastUUID == null) {
		    for (int i = 0; i < 36; i++) {
		    	if (i == 8 || i == 13 || i == 18 || i == 23) {
		    		UUIDstr += "-";
		    	} else {
		    		UUIDstr += alphabet.charAt(r.nextInt(alphabet.length()));
		    	}
		    }
		    lastUUID = UUIDstr;
		} else {
			System.out.println("lastUUID no null, sumo 1");
		}
//	    lastUUID = UUID;
	    System.out.println("lastUUID_2 = "+lastUUID);
	    return UUIDstr;
*/
	}
	
	private static synchronized void intializeLinkTest(Link link) {
		/*if (link.getName().size() == 0) {
			nameL.setValue("DEFAULT_NAME");
			nameL.setValueName("name");
			link.addNameItem(nameL);
			link.setName(link.getName());
		}*/
		
		if (link.getLabel().size() == 0) {
			labelL.setValue("DEFAULT_LABEL_NAME");
			labelL.setValueName("name");
			link.addLabelItem(labelL);
			link.setLabel(link.getLabel());
		}
		
		if (link.getState() == null) {
			AdminStatePac stateL = new AdminStatePac();
			stateL.setAdministrativeState(AdminStatePac.AdministrativeStateEnum.fromValue("null")/*UNLOCKED*/);
			stateL.setLifecycleState(AdminStatePac.LifecycleStateEnum.fromValue("null")/*INSTALLED*/);		
			stateL.setOperationalState(AdminStatePac.OperationalStateEnum.fromValue("null")/*ENABLED*/);
			link.setState(stateL);
		}

	}
	
	
	/*
	 * System.out.println("MAPPING = " +nodes_uuid.get(n.getHostAddress()) + " IP = "+n.getHostAddress());
			
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
