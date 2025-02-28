//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.09.29 a las 02:53:49 PM CEST 
//


package AccesoJAXB;

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
 *         &lt;element name="Alumno">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Grupo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="AnoNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "alumno"
})
@XmlRootElement(name = "Alumnos")
public class Alumnos {

    @XmlElement(name = "Alumno")
    protected List<Alumnos.Alumno> alumno;

    /**
     * Gets the value of the alumno property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alumno property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlumno().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Alumnos.Alumno }
     * 
     * 
     */
    public List<Alumnos.Alumno> getAlumno() {
        if (alumno == null) {
            alumno = new ArrayList<Alumnos.Alumno>();
        }
        return this.alumno;
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
     *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Apellido" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Grupo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="AnoNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nombre",
        "apellido",
        "grupo"
    })
    public static class Alumno {

        @XmlElement(name = "Nombre", required = true)
        protected String nombre;
        @XmlElement(name = "Apellido", required = true)
        protected String apellido;
        @XmlElement(name = "Grupo", required = true)
        protected String grupo;
        @XmlAttribute(name = "AnoNacimiento")
        protected String anoNacimiento;

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
         * Obtiene el valor de la propiedad apellido.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApellido() {
            return apellido;
        }

        /**
         * Define el valor de la propiedad apellido.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApellido(String value) {
            this.apellido = value;
        }

        /**
         * Obtiene el valor de la propiedad grupo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGrupo() {
            return grupo;
        }

        /**
         * Define el valor de la propiedad grupo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGrupo(String value) {
            this.grupo = value;
        }

        /**
         * Obtiene el valor de la propiedad anoNacimiento.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAnoNacimiento() {
            return anoNacimiento;
        }

        /**
         * Define el valor de la propiedad anoNacimiento.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAnoNacimiento(String value) {
            this.anoNacimiento = value;
        }

    }

}
