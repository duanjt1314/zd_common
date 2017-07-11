package cn.zdsoft.common;

import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * XML公共类
 * 
 * @author 段江涛
 * @date 2017-07-10
 */
public class XmlUtil {

	/**
	 * 根据指定xml节点获取指定的xml子节点，如果不存在则返回默认值，如果存在就返回节点内容
	 * @param element	xml节点
	 * @param eleName	xml子节点名称
	 * @param defaultValue	默认值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T GetXmlElement(Element element, String eleName, T defaultValue) {
		if (element.element(eleName) == null) {
			return defaultValue;
		}
		return (T) ConvertUtil.Convert(element.element(eleName).getText(), defaultValue.getClass());
	}
	
	/**
	 * 从xml节点中获取指定属性的数据，如果不存在该属性则返回默认值
	 * @param element xml节点
	 * @param attName 属性名称
	 * @param defaultValue 默认值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T GetXmlAttr(Element element, String attName, T defaultValue) {
		if (element.attribute(attName) == null) {
			return defaultValue;
		}
		return (T) ConvertUtil.Convert(element.attribute(attName).getText(), defaultValue.getClass());
	}
	
	/**
	 * 获得xml下的Element节点,必须存在,如果不存在则抛出异常
	 * @param xElement xml节点
	 * @param eleName 子节点名称
	 * @return
	 * @throws Exception 
	 */
	public static Element GetXmlElement(Element element, String eleName) throws Exception{
		Element ele = element.element(eleName);
        if (ele == null)
            throw new Exception("节点:" + GetXmlPath(element) + "不存在子节点" + eleName);
        return ele;
	}
	
	/**
	 * 获取xml节点下的属性对象,必须存在,如果不存在则抛出异常
	 * @param element xml节点
	 * @param attrName 属性名称
	 * @return
	 * @throws Exception
	 */
	public static Attribute GetXmlAttr(Element element, String attrName) throws Exception{
		Attribute attribute= element.attribute( attrName);
		if(attribute==null){
			throw new Exception("节点:" + GetXmlPath(element) + "不存在属性[" + attrName+"]");
		}
		return attribute;
	}
	
	/**
	 * 获取xml节点的路径
	 * @param element
	 * @return
	 */
	 public static String GetXmlPath(Element element)
     {
         if (element.getParent() == null)
         {
             return element.getName();
         }
         else
         {
             return GetXmlPath(element.getParent()) + "/" + element.getName();
         }
     }

}
