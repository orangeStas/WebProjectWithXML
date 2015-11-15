package by.epam.task5.logic.parser;

import by.epam.task5.bean.Computer;
import by.epam.task5.bean.type.Hardware;
import by.epam.task5.bean.type.Port;
import by.epam.task5.bean.type.Type;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DeviceDomParser {
    public static List<Computer> parse(InputStream inputStream) throws IOException, SAXException {
        DOMParser domParser = new DOMParser();
        domParser.parse(new InputSource(inputStream));
        Document document = domParser.getDocument();

        Element root = document.getDocumentElement();

        List<Computer> computers = new ArrayList<>();

        NodeList computerNodes = root.getElementsByTagName("computer");
        Computer computer = null;

        for (int i = 0 ; i < computerNodes.getLength(); i++) {
            computer = new Computer();
            Element computerElement = (Element) computerNodes.item(i);

            computer.setName(computerElement.getAttribute("name"));
            computer.setId(computerElement.getAttribute("id"));
            computer.setOrigin(computerElement.getAttribute("origin"));
            computer.setPrice(Integer.parseInt(computerElement.getAttribute("price")));

            computer.setIsCritical(Boolean.valueOf(getSingleChild(computerElement, "critical").getTextContent().trim()));
            computer.setType(getComputerType(computerElement));

            computers.add(computer);
        }

        return computers;
    }

    private static Type getComputerType(Element computerElement) {
        Type type = new Type();

        NodeList typeNodes = computerElement.getElementsByTagName("type");
        Element typeElement = (Element) typeNodes.item(0);

        type.setIsPeripheral(Boolean.valueOf(getSingleChild(typeElement, "peripheral").getTextContent()));
        type.setPower(Integer.parseInt(getSingleChild(typeElement, "power").getTextContent()));
        type.setHardware(getHardware(typeElement));

        String ports = getSingleChild(typeElement, "ports").getTextContent();
        if (!ports.contains(" ")) {
            Port port = new Port();
            port.setPortName(ports);
            List<Port> tempPorts = new ArrayList<>();
            tempPorts.add(port);
            type.setPorts(tempPorts);
        }
        else {
            List<String> portsStr = Arrays.asList(ports.split(" "));
            List<Port> portList = new ArrayList<>();
            for (String port : portsStr) {
                Port port1 = new Port();
                port1.setPortName(port);
                portList.add(port1);
            }
            type.setPorts(portList);
        }

        return type;
    }

    private static Hardware getHardware(Element typeElement) {
        Hardware hardware = new Hardware();

        Element hardwareElement = (Element) typeElement.getElementsByTagName("hardware").item(0);
        hardware.setHasKeyboard(Boolean.valueOf(getSingleChild(hardwareElement, "keyboard").getTextContent()));
        hardware.setHasSpeakers(Boolean.valueOf(getSingleChild(hardwareElement, "speakers").getTextContent()));
        hardware.setHasMouse(Boolean.valueOf(getSingleChild(hardwareElement, "mouse").getTextContent()));

        return hardware;
    }

    private static Element getSingleChild(Element parentElement, String childName) {
        return (Element) parentElement.getElementsByTagName(childName).item(0);
    }
}
