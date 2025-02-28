//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.10.04 a las 11:12:24 AM CEST 
//


package ManejoDeFicheros;

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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="Libro">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Autor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ISBN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Numerodeejemplares" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Editorial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Numerodepaginas" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Añodeedicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "libro"
})
@XmlRootElement(name = "Libros")
public class Libros {

    @XmlElement(name = "Libro")
    protected List<Libros.Libro> libro;

    /**
     * Gets the value of the libro property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libro property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibro().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Libros.Libro }
     * 
     * 
     */
    public List<Libros.Libro> getLibro() {
        if (libro == null) {
            libro = new ArrayList<Libros.Libro>();
        }
        return this.libro;
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
     *         &lt;element name="Autor" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ISBN" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Numerodeejemplares" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Editorial" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Numerodepaginas" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Añodeedicion" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="Titulo" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "autor",
        "isbn",
        "numerodeejemplares",
        "editorial",
        "numerodepaginas",
        "a\u00f1odeedicion"
    })
    public static class Libro {

        @XmlElement(name = "Autor", required = true)
        protected String autor;
        @XmlElement(name = "ISBN", required = true)
        protected String isbn;
        @XmlElement(name = "Numerodeejemplares", required = true)
        protected String numerodeejemplares;
        @XmlElement(name = "Editorial", required = true)
        protected String editorial;
        @XmlElement(name = "Numerodepaginas", required = true)
        protected String numerodepaginas;
        @XmlElement(name = "A\u00f1odeedicion", required = true)
        protected String añodeedicion;
        @XmlAttribute(name = "Titulo")
        protected String titulo;

        /**
         * Obtiene el valor de la propiedad autor.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAutor() {
            return autor;
        }

        /**
         * Define el valor de la propiedad autor.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAutor(String value) {
            this.autor = value;
        }

        /**
         * Obtiene el valor de la propiedad isbn.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getISBN() {
            return isbn;
        }

        /**
         * Define el valor de la propiedad isbn.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setISBN(String value) {
            this.isbn = value;
        }

        /**
         * Obtiene el valor de la propiedad numerodeejemplares.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumerodeejemplares() {
            return numerodeejemplares;
        }

        /**
         * Define el valor de la propiedad numerodeejemplares.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumerodeejemplares(String value) {
            this.numerodeejemplares = value;
        }

        /**
         * Obtiene el valor de la propiedad editorial.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEditorial() {
            return editorial;
        }

        /**
         * Define el valor de la propiedad editorial.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEditorial(String value) {
            this.editorial = value;
        }

        /**
         * Obtiene el valor de la propiedad numerodepaginas.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumerodepaginas() {
            return numerodepaginas;
        }

        /**
         * Define el valor de la propiedad numerodepaginas.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumerodepaginas(String value) {
            this.numerodepaginas = value;
        }

        /**
         * Obtiene el valor de la propiedad añodeedicion.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAñodeedicion() {
            return añodeedicion;
        }

        /**
         * Define el valor de la propiedad añodeedicion.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAñodeedicion(String value) {
            this.añodeedicion = value;
        }

        /**
         * Obtiene el valor de la propiedad titulo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitulo() {
            return titulo;
        }

        /**
         * Define el valor de la propiedad titulo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitulo(String value) {
            this.titulo = value;
        }

    }

}
