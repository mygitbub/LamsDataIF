
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
 *         &lt;element name="wmUserInfoExtResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AUserID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AUserDepID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AUserDepCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AUserDepName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AUserLongDepName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "wmUserInfoExtResult",
    "aUserID",
    "aUserCode",
    "aUserName",
    "aUserDepID",
    "aUserDepCode",
    "aUserDepName",
    "aUserLongDepName"
})
@XmlRootElement(name = "wmUserInfoExtResponse")
public class WmUserInfoExtResponse {

    protected int wmUserInfoExtResult;
    @XmlElement(name = "AUserID")
    protected long aUserID;
    @XmlElement(name = "AUserCode")
    protected String aUserCode;
    @XmlElement(name = "AUserName")
    protected String aUserName;
    @XmlElement(name = "AUserDepID")
    protected long aUserDepID;
    @XmlElement(name = "AUserDepCode")
    protected String aUserDepCode;
    @XmlElement(name = "AUserDepName")
    protected String aUserDepName;
    @XmlElement(name = "AUserLongDepName")
    protected String aUserLongDepName;

    /**
     * Gets the value of the wmUserInfoExtResult property.
     * 
     */
    public int getWmUserInfoExtResult() {
        return wmUserInfoExtResult;
    }

    /**
     * Sets the value of the wmUserInfoExtResult property.
     * 
     */
    public void setWmUserInfoExtResult(int value) {
        this.wmUserInfoExtResult = value;
    }

    /**
     * Gets the value of the aUserID property.
     * 
     */
    public long getAUserID() {
        return aUserID;
    }

    /**
     * Sets the value of the aUserID property.
     * 
     */
    public void setAUserID(long value) {
        this.aUserID = value;
    }

    /**
     * Gets the value of the aUserCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUserCode() {
        return aUserCode;
    }

    /**
     * Sets the value of the aUserCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUserCode(String value) {
        this.aUserCode = value;
    }

    /**
     * Gets the value of the aUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUserName() {
        return aUserName;
    }

    /**
     * Sets the value of the aUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUserName(String value) {
        this.aUserName = value;
    }

    /**
     * Gets the value of the aUserDepID property.
     * 
     */
    public long getAUserDepID() {
        return aUserDepID;
    }

    /**
     * Sets the value of the aUserDepID property.
     * 
     */
    public void setAUserDepID(long value) {
        this.aUserDepID = value;
    }

    /**
     * Gets the value of the aUserDepCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUserDepCode() {
        return aUserDepCode;
    }

    /**
     * Sets the value of the aUserDepCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUserDepCode(String value) {
        this.aUserDepCode = value;
    }

    /**
     * Gets the value of the aUserDepName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUserDepName() {
        return aUserDepName;
    }

    /**
     * Sets the value of the aUserDepName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUserDepName(String value) {
        this.aUserDepName = value;
    }

    /**
     * Gets the value of the aUserLongDepName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUserLongDepName() {
        return aUserLongDepName;
    }

    /**
     * Sets the value of the aUserLongDepName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUserLongDepName(String value) {
        this.aUserLongDepName = value;
    }

}
