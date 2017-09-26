package cn.zdsoft.common;

import static org.junit.Assert.*;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XmlUtilTest {

	
	public void testGetXmlElement() throws DocumentException {
		SAXReader reader = new SAXReader();
		File f = new File("d:\\config.xml");
		Document document = reader.read(f);
		Element ele = document.getRootElement();
		String str = XmlUtil.GetXmlElement(ele, "Failed", "null");
		boolean con = XmlUtil.GetXmlElement(ele, "IsMatchAllFilter", false);
		System.out.println(str);
		System.out.println(con);

	}

	
	public void testGetXmlAttr() throws DocumentException {
		SAXReader reader = new SAXReader();
		File f = new File("d:\\config.xml");
		Document document = reader.read(f);
		Element ele = document.getRootElement();

		Element element = ele.element("IsMatchAllFilter");
		int id = XmlUtil.GetXmlAttr(element, "id", 0);
		System.out.println("id=" + id);
	}

	
	public void testGetXmlPath() throws DocumentException {
		SAXReader reader = new SAXReader();
		File f = new File("d:\\config.xml");
		Document document = reader.read(f);
		Element ele = document.getRootElement();

		Element element = ele.element("Filters").element("Filter");
		String level = XmlUtil.GetXmlPath(element);
		System.out.println(level);
	}

}
