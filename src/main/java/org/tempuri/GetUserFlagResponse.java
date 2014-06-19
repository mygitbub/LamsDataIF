
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
 *         &lt;element name="GetUserFlagResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sMSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getUserFlagResult",
    "smsg"
})
@XmlRootElement(name = "GetUserFlagResponse")
public class GetUserFlagResponse {

    @XmlElement(name = "GetUserFlagResult")
    protected int getUserFlagResult;
    @XmlElement(name = "sMSG")
    protected String smsg;

    /**
     * Gets the value of the getUserFlagResult property.
     * 
     */
    public int getGetUserFlagResult() {
        return getUserFlagResult;
    }

    /**
     * Sets the value of the getUserFlagResult property.
     * 
     */
    public void setGetUserFlagResult(int value) {
        this.getUserFlagResult = value;
    }

    /**
     * Gets the value of the smsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSMSG() {
        return smsg;
    }

    /**
     * Sets the value of the smsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSMSG(String value) {
        this.smsg = value;
    }

}
