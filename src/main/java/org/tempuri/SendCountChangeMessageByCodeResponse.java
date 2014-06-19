
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
 *         &lt;element name="SendCountChangeMessageByCodeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendCountChangeMessageByCodeResult"
})
@XmlRootElement(name = "SendCountChangeMessageByCodeResponse")
public class SendCountChangeMessageByCodeResponse {

    @XmlElement(name = "SendCountChangeMessageByCodeResult")
    protected String sendCountChangeMessageByCodeResult;

    /**
     * Gets the value of the sendCountChangeMessageByCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendCountChangeMessageByCodeResult() {
        return sendCountChangeMessageByCodeResult;
    }

    /**
     * Sets the value of the sendCountChangeMessageByCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendCountChangeMessageByCodeResult(String value) {
        this.sendCountChangeMessageByCodeResult = value;
    }

}
