package by.epam.task5.logic.parser;

import by.epam.task5.bean.Computer;
import by.epam.task5.bean.type.Hardware;
import by.epam.task5.bean.type.Port;
import by.epam.task5.bean.type.Type;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DevicesStaxParser {

    public static List<Computer> parse(XMLStreamReader reader) throws XMLStreamException {
        List<Computer> computers = new ArrayList<>();

        Computer computer = null;
        Type type = null;
        Hardware hardware = null;
        String elementName = "";
        while (reader.hasNext()) {

            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT : {
                    elementName = reader.getLocalName();

                    switch (elementName) {
                        case "computer" : {
                            computer = new Computer();
                            computer.setName(reader.getAttributeValue(null, "name"));
                            computer.setId(reader.getAttributeValue(null, "id"));
                            computer.setOrigin(reader.getAttributeValue(null, "origin"));
                            computer.setPrice(Integer.parseInt(reader.getAttributeValue(null, "price")));
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
                    break;
                }

                case XMLStreamConstants.CHARACTERS : {
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case "peripheral" : {
                            type.setIsPeripheral(Boolean.valueOf(text));
                            break;
                        }
                        case "power" : {
                            type.setPower(Integer.parseInt(text));
                            break;
                        }
                        case "keyboard" : {
                            hardware.setHasKeyboard(Boolean.valueOf(text));
                            break;
                        }
                        case "mouse" : {
                            hardware.setHasMouse(Boolean.valueOf(text));
                            break;
                        }
                        case "speakers" : {
                            hardware.setHasSpeakers(Boolean.valueOf(text));
                            break;
                        }
                        case "ports" : {
                            if (!text.contains(" ")) {
                                Port port = new Port();
                                port.setPortName(text);
                                List<Port> tempPorts = new ArrayList<>();
                                tempPorts.add(port);
                                type.setPorts(tempPorts);
                            }
                            else {
                                List<String> portsStr = Arrays.asList(text.split(" "));
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
                        case "critical" : {
                            computer.setIsCritical(Boolean.valueOf(text));
                            break;
                        }
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT : {
                    elementName = reader.getLocalName();

                    switch (elementName) {
                        case "computer" : {
                            computers.add(computer);
                            computer = null;
                            break;
                        }
                        case "type" : {
                            computer.setType(type);
                            type = null;
                            break;
                        }
                        case "hardware" : {
                            type.setHardware(hardware);
                            hardware = null;
                            break;
                        }
                    }
                }
            }
        }
        return computers;
    }
}
