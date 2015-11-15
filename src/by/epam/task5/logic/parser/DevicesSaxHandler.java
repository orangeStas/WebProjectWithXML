package by.epam.task5.logic.parser;

import by.epam.task5.bean.Computer;
import by.epam.task5.bean.type.Hardware;
import by.epam.task5.bean.type.Port;
import by.epam.task5.bean.type.Type;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stas- on 10/16/2015.
 */
public class DevicesSaxHandler extends DefaultHandler {
    private List<Computer> devices = new ArrayList<>();
    private Computer computer;
    private Type type;
    private Hardware hardware;
    private StringBuilder text;

    public List<Computer> getDevices() {
        return devices;
    }

    public void startDocument() {
        System.out.println("Parsing started");
    }

    public void endDocument() {
        System.out.println("Parsing ended");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        text = new StringBuilder();
        switch (qName) {
            case "computer" : {
                computer = new Computer();
                computer.setId(attributes.getValue("id"));
                computer.setName(attributes.getValue("name"));
                computer.setOrigin(attributes.getValue("origin"));
                computer.setPrice(Integer.parseInt(attributes.getValue("price")));
                break;
            }
            case "type" : {
                type = new Type();
                break;
            }

            case "hardware" : {
                hardware = new Hardware();
                break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws org.xml.sax.SAXException {
        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws org.xml.sax.SAXException {
        switch (qName) {
            case "keyboard" : {
                hardware.setHasKeyboard(Boolean.valueOf(text.toString()));
                break;
            }
            case "mouse" : {
                hardware.setHasMouse(Boolean.valueOf(text.toString()));
                break;
            }
            case "speakers" : {
                hardware.setHasSpeakers(Boolean.valueOf(text.toString()));
                break;
            }
            case "hardware" : {
                type.setHardware(hardware);
                break;
            }
            case "peripheral" : {
                type.setIsPeripheral(Boolean.valueOf(text.toString()));
                break;
            }
            case "power" : {
                type.setPower(Integer.parseInt(text.toString()));
                break;
            }
            case "ports" : {
                if (!text.toString().contains(" ")) {
                    Port port = new Port();
                    port.setPortName(text.toString());
                    List<Port> tempPorts = new ArrayList<>();
                    tempPorts.add(port);
                    type.setPorts(tempPorts);
                }
                else {
                    List<String> portsStr = Arrays.asList(text.toString().split(" "));
                    List<Port> ports = new ArrayList<>();
                    for (String port : portsStr) {
                        Port port1 = new Port();
                        port1.setPortName(port);
                        ports.add(port1);
                    }
                    type.setPorts(ports);
                }
                break;
            }
            case "type" : {
                computer.setType(type);
                type = null;
                break;
            }
            case "critical" : {
                computer.setIsCritical(Boolean.valueOf(text.toString()));
                break;
            }
            case "computer" : {
                devices.add(computer);
                computer = null;
                break;
            }
        }
    }

    @Override
    public void warning(SAXParseException e) throws org.xml.sax.SAXException {
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws org.xml.sax.SAXException {
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws org.xml.sax.SAXException {
        super.fatalError(e);
    }
}
