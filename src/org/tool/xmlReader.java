package org.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xmlReader {
	/**
	 * xml读取类，用于读取struts.xml文件里面的action列表，
	 * 并返回action的List<String>
	 */
	public List<String> xRead(String xmlPath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document document = builder.parse(new File("src/struts.xml"));
			Document document = builder.parse(new File(xmlPath));
			Element root = document.getDocumentElement();	//获取element根节点
//			System.out.println(root.getNodeName());	//打印根节点名
			NodeList secondNodesList = root.getChildNodes();		//获取二级节点列表
			
			List<String> actionList = new ArrayList<String>();
			for(int i=0;i<secondNodesList.getLength();i++){
				Node node2 = secondNodesList.item(i);	//二级节点
				if("package".equals(node2.getNodeName())){
//					System.out.println("  "+node2.getNodeName());
					
					NodeList thirdNodesList = node2.getChildNodes();//三级节点列表
					for(int j=0;j<thirdNodesList.getLength();j++){
						Node node3 = thirdNodesList.item(j);	//三级节点
						if("action".equals(node3.getNodeName())){
//							System.out.println("    "+node3.getNodeName());	//三级节点节点名称
							String value = ""+node3.getAttributes().getNamedItem("name");
							value = value.replace("\"", "");
							value = value.replace("name=", "");
//							System.out.println(value);	//三级节点属性值
							actionList.add(value);
						}
					}
				}
			}
			return actionList;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	} 
}
