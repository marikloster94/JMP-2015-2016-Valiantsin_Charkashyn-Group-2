
package com.epam.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for randomNumberGenerator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="randomNumberGenerator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "randomNumberGenerator", propOrder = {
    "lowerBound",
    "upperBound"
})
public class RandomNumberGenerator {

    protected int lowerBound;
    protected int upperBound;

    /**
     * Gets the value of the arg0 property.
     * 
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     */
    public void setLowerBound(int value) {
        this.lowerBound = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     */
    public void setUpperBound(int value) {
        this.upperBound = value;
    }

}
