
package com.epam.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epam.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RandomNumberGeneratorResponse_QNAME = new QName("http://service.epam.com/", "randomNumberGeneratorResponse");
    private final static QName _RandomNumberGenerator_QNAME = new QName("http://service.epam.com/", "randomNumberGenerator");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RandomNumberGenerator }
     * 
     */
    public RandomNumberGenerator createRandomNumberGenerator() {
        return new RandomNumberGenerator();
    }

    /**
     * Create an instance of {@link RandomNumberGeneratorResponse }
     * 
     */
    public RandomNumberGeneratorResponse createRandomNumberGeneratorResponse() {
        return new RandomNumberGeneratorResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RandomNumberGeneratorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.epam.com/", name = "randomNumberGeneratorResponse")
    public JAXBElement<RandomNumberGeneratorResponse> createRandomNumberGeneratorResponse(RandomNumberGeneratorResponse value) {
        return new JAXBElement<RandomNumberGeneratorResponse>(_RandomNumberGeneratorResponse_QNAME, RandomNumberGeneratorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RandomNumberGenerator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.epam.com/", name = "randomNumberGenerator")
    public JAXBElement<RandomNumberGenerator> createRandomNumberGenerator(RandomNumberGenerator value) {
        return new JAXBElement<RandomNumberGenerator>(_RandomNumberGenerator_QNAME, RandomNumberGenerator.class, null, value);
    }

}
