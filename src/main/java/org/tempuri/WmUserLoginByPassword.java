
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
 *         &lt;element name="AUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="APassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "aUserCode",
    "aPassword",
    "errMessage"
})
@XmlRootElement(name = "wmUserLoginByPassword")
public class WmUserLoginByPassword {

    @XmlElement(name = "AUserCode")
    protected String aUserCode;
    @XmlElement(name = "APassword")
    protected String aPassword;
    protected String errMessage;

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
     * Gets the value of the aPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPassword() {
        return aPassword;
    }

    /**
     * Sets the value of the aPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPassword(String value) {
        this.aPassword = value;
    }

    /**
     * Gets the value of the errMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMessage() {
        return errMessage;
    }

    /**
     * Sets the value of the errMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMessage(String value) {
        this.errMessage = value;
    }

}
