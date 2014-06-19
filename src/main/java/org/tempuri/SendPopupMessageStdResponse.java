
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
 *         &lt;element name="SendPopupMessage_stdResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendPopupMessageStdResult"
})
@XmlRootElement(name = "SendPopupMessage_stdResponse")
public class SendPopupMessageStdResponse {

    @XmlElement(name = "SendPopupMessage_stdResult")
    protected String sendPopupMessageStdResult;

    /**
     * Gets the value of the sendPopupMessageStdResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendPopupMessageStdResult() {
        return sendPopupMessageStdResult;
    }

    /**
     * Sets the value of the sendPopupMessageStdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendPopupMessageStdResult(String value) {
        this.sendPopupMessageStdResult = value;
    }

}
