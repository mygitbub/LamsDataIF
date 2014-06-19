
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="sProcStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nSenderPlatID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nSenderUserID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sReceiverPlatUserIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strAppCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strAppTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nOnlineOnly" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="nReserveDays" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="strFromApp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strFromUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sProcStr",
    "nSenderPlatID",
    "nSenderUserID",
    "sReceiverPlatUserIds",
    "strUrl",
    "strAppCode",
    "strAppTitle",
    "nOnlineOnly",
    "nReserveDays",
    "strFromApp",
    "strFromUserName",
    "strCustomInfo"
})
@XmlRootElement(name = "SendPopupMessageByProcStr")
public class SendPopupMessageByProcStr {

    protected String sProcStr;
    protected long nSenderPlatID;
    protected long nSenderUserID;
    protected String sReceiverPlatUserIds;
    protected String strUrl;
    protected String strAppCode;
    protected String strAppTitle;
    @XmlSchemaType(name = "unsignedByte")
    protected short nOnlineOnly;
    @XmlSchemaType(name = "unsignedByte")
    protected short nReserveDays;
    protected String strFromApp;
    protected String strFromUserName;
    protected String strCustomInfo;

    /**
     * Gets the value of the sProcStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSProcStr() {
        return sProcStr;
    }

    /**
     * Sets the value of the sProcStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSProcStr(String value) {
        this.sProcStr = value;
    }

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
     * Gets the value of the strUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrUrl() {
        return strUrl;
    }

    /**
     * Sets the value of the strUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrUrl(String value) {
        this.strUrl = value;
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
     * Gets the value of the strAppTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrAppTitle() {
        return strAppTitle;
    }

    /**
     * Sets the value of the strAppTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrAppTitle(String value) {
        this.strAppTitle = value;
    }

    /**
     * Gets the value of the nOnlineOnly property.
     * 
     */
    public short getNOnlineOnly() {
        return nOnlineOnly;
    }

    /**
     * Sets the value of the nOnlineOnly property.
     * 
     */
    public void setNOnlineOnly(short value) {
        this.nOnlineOnly = value;
    }

    /**
     * Gets the value of the nReserveDays property.
     * 
     */
    public short getNReserveDays() {
        return nReserveDays;
    }

    /**
     * Sets the value of the nReserveDays property.
     * 
     */
    public void setNReserveDays(short value) {
        this.nReserveDays = value;
    }

    /**
     * Gets the value of the strFromApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFromApp() {
        return strFromApp;
    }

    /**
     * Sets the value of the strFromApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFromApp(String value) {
        this.strFromApp = value;
    }

    /**
     * Gets the value of the strFromUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFromUserName() {
        return strFromUserName;
    }

    /**
     * Sets the value of the strFromUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFromUserName(String value) {
        this.strFromUserName = value;
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
