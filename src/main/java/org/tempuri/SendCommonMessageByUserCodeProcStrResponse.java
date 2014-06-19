
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
 *         &lt;element name="SendCommonMessageByUserCodeProcStrResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendCommonMessageByUserCodeProcStrResult"
})
@XmlRootElement(name = "SendCommonMessageByUserCodeProcStrResponse")
public class SendCommonMessageByUserCodeProcStrResponse {

    @XmlElement(name = "SendCommonMessageByUserCodeProcStrResult")
    protected String sendCommonMessageByUserCodeProcStrResult;

    /**
     * Gets the value of the sendCommonMessageByUserCodeProcStrResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendCommonMessageByUserCodeProcStrResult() {
        return sendCommonMessageByUserCodeProcStrResult;
    }

    /**
     * Sets the value of the sendCommonMessageByUserCodeProcStrResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendCommonMessageByUserCodeProcStrResult(String value) {
        this.sendCommonMessageByUserCodeProcStrResult = value;
    }

}
