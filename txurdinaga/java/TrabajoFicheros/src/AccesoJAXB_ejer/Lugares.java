//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.09.29 a las 01:19:36 PM CEST 
//


package AccesoJAXB_ejer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Sitio" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Parking" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Duchas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Hotel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sitio"
})
@XmlRootElement(name = "Lugares")
public class Lugares {

    @XmlElement(name = "Sitio")
    protected List<Lugares.Sitio> sitio;

    /**
     * Gets the value of the sitio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sitio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSitio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Lugares.Sitio }
     * 
     * 
     */
    public List<Lugares.Sitio> getSitio() {
        if (sitio == null) {
            sitio = new ArrayList<Lugares.Sitio>();
        }
        return this.sitio;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Parking" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Duchas" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Hotel" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "parking",
        "duchas",
        "hotel"
    })
    public static class Sitio {

        @XmlElement(name = "Parking", required = true)
        protected String parking;
        @XmlElement(name = "Duchas", required = true)
        protected String duchas;
        @XmlElement(name = "Hotel", required = true)
        protected String hotel;
        @XmlAttribute(name = "nombre")
        protected String nombre;
        @XmlAttribute(name = "provincia")
        protected String provincia;

        /**
         * Obtiene el valor de la propiedad parking.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParking() {
            return parking;
        }

        /**
         * Define el valor de la propiedad parking.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParking(String value) {
            this.parking = value;
        }

        /**
         * Obtiene el valor de la propiedad duchas.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDuchas() {
            return duchas;
        }

        /**
         * Define el valor de la propiedad duchas.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDuchas(String value) {
            this.duchas = value;
        }

        /**
         * Obtiene el valor de la propiedad hotel.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHotel() {
            return hotel;
        }

        /**
         * Define el valor de la propiedad hotel.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHotel(String value) {
            this.hotel = value;
        }

        /**
         * Obtiene el valor de la propiedad nombre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Define el valor de la propiedad nombre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombre(String value) {
            this.nombre = value;
        }

        /**
         * Obtiene el valor de la propiedad provincia.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProvincia() {
            return provincia;
        }

        /**
         * Define el valor de la propiedad provincia.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProvincia(String value) {
            this.provincia = value;
        }

    }

}
