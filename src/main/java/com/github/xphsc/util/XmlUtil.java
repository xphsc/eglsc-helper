package com.github.xphsc.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Created by ${huipei.x} on 2017-6-1.
 */
public class XmlUtil {

    public XmlUtil() {
    }

    public static <T> T parseObject(String xml, Class<T> clazz) {
        Object obj;
        try {
            JAXBContext e = JAXBContext.newInstance(new Class[]{clazz});
            Unmarshaller um = e.createUnmarshaller();
            obj = um.unmarshal(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        } catch (Exception var5) {
            obj = null;
        }

        return (T) obj;
    }

    public static <T> String toXml(Object obj, Class<T> clazz) {
        String ret;
        try {
            JAXBContext e = JAXBContext.newInstance(new Class[]{clazz});
            Marshaller marshaller = e.createMarshaller();
            marshaller.setProperty("jaxb.encoding", "UTF-8");
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            ret = writer.toString();
        } catch (JAXBException var6) {
            ret = "";
        }

        return ret;
    }

    public static String getText(String data, String element) {
        String result = "";
        Pattern pattern = Pattern.compile(makePattern(element));
        Matcher matcher = pattern.matcher(data);
        if(matcher.find()) {
            result = getXmlClearText(matcher.group(1));
        }

        return result;
    }

    public static int getInt(String data, String element) {
        String result = "";
        Pattern pattern = Pattern.compile(makePattern(element));
        Matcher matcher = pattern.matcher(data);
        if(matcher.find()) {
            result = getXmlClearText(matcher.group(1));
        }

        return NumberUtil.getInt(result);
    }

    public static String getTextX(String data, String path) {
        String result = "";
        String[] elements = path.split("/");

        for(int index = 0; index < elements.length; ++index) {
            if(index != elements.length - 1) {
                data = getText(data, elements[index]);
            } else {
                result = getText(data, elements[index]);
            }
        }

        return result;
    }

    public static int getIntX(String data, String path) {
        String result = "";
        String[] elements = path.split("/");

        for(int index = 0; index < elements.length; ++index) {
            if(index != elements.length - 1) {
                data = getText(data, elements[index]);
            } else {
                result = getText(data, elements[index]);
            }
        }

        return NumberUtil.getInt(result);
    }

    public static List<String> getTexts(String data, String element) {
        ArrayList textList = new ArrayList();
        Pattern pattern = Pattern.compile(makePattern(element));
        Matcher matcher = pattern.matcher(data);

        while(matcher.find()) {
            textList.add(getXmlClearText(matcher.group(1)));
        }

        return textList;
    }

    public static List<String> getTextsX(String data, String path) {
        Object textList = new ArrayList();
        String[] elements = path.split("/");

        for(int index = 0; index < elements.length; ++index) {
            if(index != elements.length - 1) {
                data = getText(data, elements[index]);
            } else {
                textList = getTexts(data, elements[index]);
            }
        }

        return (List)textList;
    }

    private static String makePattern(String param) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(param);
        sb.append(">");
        sb.append("(.*?)");
        sb.append("</");
        sb.append(param);
        sb.append(">");
        return sb.toString();
    }

    private static String getXmlClearText(String xml) {
        return xml.replaceAll("\\<\\!\\[CDATA\\[", "").replaceAll("]]>", "");
    }

    public static Map<String, String> convert(String xml) throws Exception {
        xml = getXmlClearText(xml);
        ByteArrayInputStream is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(is);
        return createMap(document.getDocumentElement());
    }

    public static Map<String, String> createMap(Node node) {
        HashMap map = new HashMap();
        NodeList nodeList = node.getChildNodes();

        for(int i = 0; i < nodeList.getLength(); ++i) {
            Node currentNode = nodeList.item(i);
            if(currentNode.hasAttributes()) {
                for(int j = 0; j < currentNode.getAttributes().getLength(); ++j) {
                    Node item = currentNode.getAttributes().item(i);
                    map.put(item.getNodeName(), item.getTextContent());
                }
            }

            if(node.getFirstChild() != null && node.getFirstChild().getNodeType() == 1) {
                map.putAll(createMap(currentNode));
            } else if(node.getFirstChild().getNodeType() == 3) {
                map.put(node.getLocalName(), node.getTextContent());
            }
        }

        return map;
    }

    public static void main(String[] args) {
        String xmlText = "<A><B>1</B></A>";
        System.err.println("--->" + getTextX(xmlText, "A/B"));
        String xmlTexts = "<items><item>1</item><item>2</item></items>";
        System.err.println("--->" + getTextsX(xmlTexts, "items/item"));
    }
}
