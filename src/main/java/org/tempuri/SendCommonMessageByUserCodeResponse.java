
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
 *         &lt;element name="SendCommonMessageByUserCodeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendCommonMessageByUserCodeResult"
})
@XmlRootElement(name = "SendCommonMessageByUserCodeResponse")
public class SendCommonMessageByUserCodeResponse {

    @XmlElement(name = "SendCommonMessageByUserCodeResult")
    protected String sendCommonMessageByUserCodeResult;

    /**
     * Gets the value of the sendCommonMessageByUserCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendCommonMessageByUserCodeResult() {
        return sendCommonMessageByUserCodeResult;
    }

    /**
     * Sets the value of the sendCommonMessageByUserCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendCommonMessageByUserCodeResult(String value) {
        this.sendCommonMessageByUserCodeResult = value;
    }

}
