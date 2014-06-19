
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="nSenderPlatID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nSenderUserID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sReceiverPlatUserIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strAppCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strCustomInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "nSenderPlatID",
    "nSenderUserID",
    "sReceiverPlatUserIds",
    "strAppCode",
    "strCustomInfo"
})
@XmlRootElement(name = "SendCountChangeMessage_std")
public class SendCountChangeMessageStd {

    protected long nSenderPlatID;
    protected long nSenderUserID;
    protected String sReceiverPlatUserIds;
    protected String strAppCode;
    protected String strCustomInfo;

    /**
     * Gets the value of the nSenderPlatID property.
     * 
     */
    public long getNSenderPlatID() {
        return nSenderPlatID;
    }

    /**
     * Sets the value of the nSenderPlatID property.
     * 
     */
    public void setNSenderPlatID(long value) {
        this.nSenderPlatID = value;
    }

    /**
     * Gets the value of the nSenderUserID property.
     * 
     */
    public long getNSenderUserID() {
        return nSenderUserID;
    }

    /**
     * Sets the value of the nSenderUserID property.
     * 
     */
    public void setNSenderUserID(long value) {
        this.nSenderUserID = value;
    }

    /**
     * Gets the value of the sReceiverPlatUserIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSReceiverPlatUserIds() {
        return sReceiverPlatUserIds;
    }

    /**
     * Sets the value of the sReceiverPlatUserIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSReceiverPlatUserIds(String value) {
        this.sReceiverPlatUserIds = value;
    }

    /**
     * Gets the value of the strAppCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAppCode() {
        return strAppCode;
    }

    /**
     * Sets the value of the strAppCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAppCode(String value) {
        this.strAppCode = value;
    }

    /**
     * Gets the value of the strCustomInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCustomInfo() {
        return strCustomInfo;
    }

    /**
     * Sets the value of the strCustomInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCustomInfo(String value) {
        this.strCustomInfo = value;
    }

}
