package es.tid.topologyModuleBase.TAPITopoModel.model;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

import es.tid.tedb.DomainTEDB;
import es.tid.tedb.IntraDomainEdge;
import es.tid.tedb.Node_Info;


public class TranslateModel {
	public static Topology translateTopology(String topId, DomainTEDB ted){
		Topology topo = new Topology();
		
		  for(IntraDomainEdge link : ted.getIntraDomainLinks()){
			  topo.getLink().add(translateEdge(ted, link));
		  }
		  
		  List<Node> nodes = new ArrayList<Node>();
		  for(Object node : ted.getIntraDomainLinksvertexSet()){
//			 // System.out.println("DEBUG nodeInTopology, (class "+node.getClass()+"):" +node );
//			  if(node instanceof es.tid.tedb.elements.Node){
//				  nodes.add(translateNode(ted, (es.tid.tedb.elements.Node)node));
//			  }
//			  else if(node instanceof java.net.Inet4Address){
				  nodes.add(translateNodeIp(ted, (java.net.Inet4Address)node, ted.getNodeTable().get(node)));
		  }
		return topo;
	}
		  
	public static Link translateEdge(DomainTEDB ted,IntraDomainEdge e ){
		Link link = new Link();
		link.getNode().add(((java.net.Inet4Address)e.getSource()).getHostAddress());
		link.getNode().add(((java.net.Inet4Address)e.getTarget()).getHostAddress());
		
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
		NodeEdgePoint e= new NodeEdgePoint();

		node.getOwnedNodeEdgePoint().add(e);
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
	

}
