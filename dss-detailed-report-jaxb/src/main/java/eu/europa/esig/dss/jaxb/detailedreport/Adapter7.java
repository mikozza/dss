//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.04 at 07:34:33 AM CEST 
//


package eu.europa.esig.dss.jaxb.detailedreport;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import eu.europa.esig.dss.validation.ValidationTime;

public class Adapter7
    extends XmlAdapter<String, ValidationTime>
{


    public ValidationTime unmarshal(String value) {
        return (eu.europa.esig.dss.jaxb.parsers.ValidationTimeParser.parse(value));
    }

    public String marshal(ValidationTime value) {
        return (eu.europa.esig.dss.jaxb.parsers.ValidationTimeParser.print(value));
    }

}
