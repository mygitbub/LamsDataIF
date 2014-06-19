
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
 *         &lt;element name="SendPopupMessageByUserCodeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendPopupMessageByUserCodeResult"
})
@XmlRootElement(name = "SendPopupMessageByUserCodeResponse")
public class SendPopupMessageByUserCodeResponse {

    @XmlElement(name = "SendPopupMessageByUserCodeResult")
    protected String sendPopupMessageByUserCodeResult;

    /**
     * Gets the value of the sendPopupMessageByUserCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendPopupMessageByUserCodeResult() {
        return sendPopupMessageByUserCodeResult;
    }

    /**
     * Sets the value of the sendPopupMessageByUserCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendPopupMessageByUserCodeResult(String value) {
        this.sendPopupMessageByUserCodeResult = value;
    }

}
