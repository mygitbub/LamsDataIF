
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
 *         &lt;element name="wmUserLoginByPasswordResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "wmUserLoginByPasswordResult",
    "errMessage"
})
@XmlRootElement(name = "wmUserLoginByPasswordResponse")
public class WmUserLoginByPasswordResponse {

    protected int wmUserLoginByPasswordResult;
    protected String errMessage;

    /**
     * Gets the value of the wmUserLoginByPasswordResult property.
     * 
     */
    public int getWmUserLoginByPasswordResult() {
        return wmUserLoginByPasswordResult;
    }

    /**
     * Sets the value of the wmUserLoginByPasswordResult property.
     * 
     */
    public void setWmUserLoginByPasswordResult(int value) {
        this.wmUserLoginByPasswordResult = value;
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
