//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.11 at 07:49:59 AM CET 
//


package eu.europa.esig.dss.jaxb.detailedreport;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (eu.europa.esig.dss.jaxb.parsers.DateParser.parse(value));
    }

    public String marshal(Date value) {
        return (eu.europa.esig.dss.jaxb.parsers.DateParser.print(value));
    }

}
