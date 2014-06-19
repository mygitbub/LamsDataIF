
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SendCountChangeMessage_stdResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sendCountChangeMessageStdResult"
})
@XmlRootElement(name = "SendCountChangeMessage_stdResponse")
public class SendCountChangeMessageStdResponse {

    @XmlElement(name = "SendCountChangeMessage_stdResult")
    protected String sendCountChangeMessageStdResult;

    /**
     * Gets the value of the sendCountChangeMessageStdResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendCountChangeMessageStdResult() {
        return sendCountChangeMessageStdResult;
    }

    /**
     * Sets the value of the sendCountChangeMessageStdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendCountChangeMessageStdResult(String value) {
        this.sendCountChangeMessageStdResult = value;
    }

}
