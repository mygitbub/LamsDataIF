
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
 *         &lt;element name="SendPopupMessage_stdExResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendPopupMessageStdExResult"
})
@XmlRootElement(name = "SendPopupMessage_stdExResponse")
public class SendPopupMessageStdExResponse {

    @XmlElement(name = "SendPopupMessage_stdExResult")
    protected String sendPopupMessageStdExResult;

    /**
     * Gets the value of the sendPopupMessageStdExResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendPopupMessageStdExResult() {
        return sendPopupMessageStdExResult;
    }

    /**
     * Sets the value of the sendPopupMessageStdExResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendPopupMessageStdExResult(String value) {
        this.sendPopupMessageStdExResult = value;
    }

}
