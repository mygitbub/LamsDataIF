
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
 *         &lt;element name="wmCheckUserByTokenResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "wmCheckUserByTokenResult",
    "errMessage"
})
@XmlRootElement(name = "wmCheckUserByTokenResponse")
public class WmCheckUserByTokenResponse {

    protected int wmCheckUserByTokenResult;
    protected String errMessage;

    /**
     * Gets the value of the wmCheckUserByTokenResult property.
     * 
     */
    public int getWmCheckUserByTokenResult() {
        return wmCheckUserByTokenResult;
    }

    /**
     * Sets the value of the wmCheckUserByTokenResult property.
     * 
     */
    public void setWmCheckUserByTokenResult(int value) {
        this.wmCheckUserByTokenResult = value;
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
