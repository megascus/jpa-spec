/*******************************************************************************
 * Copyright (c) 2011 - 2015 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Petros Splinakis - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *
 ******************************************************************************/
package javax.persistence;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 基本フィールドまたはプロパティの変換を指定します。
 * 
 * 基本型を指定するのに<code>Basic</code>アノテーションや対応するXML要素を使用する必要はありません。
 * 
 * <p><code>Convert</code>アノテーションはId属性、バージョン属性、関係属性、列挙型または時制が明示的に指定された属性の変換を指定するために使用すべきではありません。
 * そのような変換を指定するアプリケーションはポータブルではありません。
 * 
 * <p><code>Convert</code>アノテーションは、基本属性または基本タイプの要素コレクションに適用できます。(この場合コンバーターはコレクションの要素に適用されます)
 * このような使用方法の場合は<code>attributeName</code>要素を指定してはいけません。
 * 
 * <p><code>Convert</code>アノテーションは、組み込み属性やキーまたは値が組み込み型のMapのコレクション属性に適用できます。
 * (この場合コンバーターはコレクションに含まれる組み込みインスタンスの指定された属性に適用されます)
 * このような使用方法の場合は<code>attributeName</code>要素を指定する必要があります。
 * 
 * <p>複数の組み込みレベルで変換マッピングを上書きするためには、組み込み属性内の属性を示すために、<code>attributeName</code>要素にドット(".")表記を使用する必要があります。
 * ドット表記で使用される各識別子の値は、それぞれの組み込みフィールドまたはプロパティの名前です。
 *
 *  <p>When the <code>Convert</code> annotation is applied to a map containing
 *  instances of embeddable classes, the <code>attributeName</code> element
 *  must be specified, and <code>"key."</code> or <code>"value."</code>
 *  must be used to prefix the name of the attribute that is to be converted
 *  in order to specify it as part of the map key or map value.
 *
 *  <p>When the <code>Convert</code> annotation is applied to a map to specify
 *  conversion of a map key of basic type, <code>"key"</code> must be used
 *  as the value of the <code>attributeName</code> element to specify that
 *  it is the map key that is to be converted.
 *  
 *  <p>The <code>Convert</code> annotation may be applied to an entity class
 *  that extends a mapped superclass to specify or override a conversion
 *  mapping for an inherited basic or embedded attribute.
 *
 *  <pre>
 *     Example 1:  Convert a basic attribute
 *
 *     &#064;Converter
 *     public class BooleanToIntegerConverter 
 *        implements AttributeConverter&#060;Boolean, Integer&#062; {  ... }
 *
 *     &#064;Entity
 *     public class Employee {
 *         &#064;Id long id;
 *
 *         &#064;Convert(converter=BooleanToIntegerConverter.class)
 *          boolean fullTime;
 *          ...
 *     }
 *
 *
 *     Example 2:  Auto-apply conversion of a basic attribute
 *
 *     &#064;Converter(autoApply=true)
 *     public class EmployeeDateConverter 
 *        implements AttributeConverter&#060;com.acme.EmployeeDate, java.sql.Date&#062; {  ... }
 *
 *     &#064;Entity
 *     public class Employee {
 *         &#064;Id long id;
 *         ...
 *         // EmployeeDateConverter is applied automatically
 *         EmployeeDate startDate;
 *     }
 *
 *
 *     Example 3:  Disable conversion in the presence of an autoapply converter
 *
 *     &#064;Convert(disableConversion=true)
 *     EmployeeDate lastReview;
 *
 *
 *     Example 4:  Apply a converter to an element collection of basic type
 *
 *     &#064;ElementCollection
 *     // applies to each element in the collection
 *     &#064;Convert(converter=NameConverter.class) 
 *     List&#060;String&#062; names;
 *
 *
 *     Example 5:  Apply a converter to an element collection that is a map or basic values.  
 *                 The converter is applied to the map value.
 *
 *     &#064;ElementCollection
 *     &#064;Convert(converter=EmployeeNameConverter.class)
 *     Map&#060;String, String&#062; responsibilities;
 *
 *
 *     Example 6:  Apply a converter to a map key of basic type
 *
 *     &#064;OneToMany
 *     &#064;Convert(converter=ResponsibilityCodeConverter.class, 
 *              attributeName="key")
 *     Map&#060;String, Employee&#062; responsibilities;
 *
 *
 *     Example 7:  Apply a converter to an embeddable attribute
 *
 *     &#064;Embedded
 *     &#064;Convert(converter=CountryConverter.class, 
 *              attributeName="country")
 *     Address address;
 * 
 *
 *     Example 8:  Apply a converter to a nested embeddable attribute
 * 
 *     &#064;Embedded
 *     &#064;Convert(converter=CityConverter.class, 
 *              attributeName="region.city")
 *     Address address;
 *
 *
 *     Example 9:  Apply a converter to a nested attribute of an embeddable that is a map key 
 *                 of an element collection
 *
 *     &#064;Entity public class PropertyRecord {
 *          ...
 *         &#064;Convert(attributeName="key.region.city", 
 *                  converter=CityConverter.class)
 *         &#064;ElementCollection
 *         Map&#060;Address, PropertyInfo&#062; parcels;
 *     }
 *
 *
 *     Example 10: Apply a converter to an embeddable that is a map key for a relationship
 *
 *     &#064;OneToMany
 *     &#064;Convert(attributeName="key.jobType", 
 *              converter=ResponsibilityTypeConverter.class)
 *     Map&#060;Responsibility, Employee&#062; responsibilities;
 *
 *
 *     Example 11: Override conversion mappings for attributes inherited from a mapped superclass
 *
 *     &#064;Entity
 *         &#064;Converts({
 *            &#064;Convert(attributeName="startDate", 
 *                     converter=DateConverter.class),
 *            &#064;Convert(attributeName="endDate", 
 *                     converter=DateConverter.class)})
 *     public class FullTimeEmployee extends GenericEmployee { ... }
 *  </pre>
 *
 *  @see Converter
 *  @see Converts
 *  @see Basic
 *
 *  @since Java Persistence 2.1
 */
@Repeatable(Converts.class)
@Target({METHOD, FIELD, TYPE}) @Retention(RUNTIME)
public @interface Convert {

  /**
   * Specifies the converter to be applied.  A value for this
   * element must be specified if multiple converters would
   * otherwise apply.
   */
  Class converter() default void.class;

  /**
   * The <code>attributeName</code> element must be specified unless the 
   * <code>Convert</code> annotation is on an attribute of basic type 
   * or on an element collection of basic type.  In these cases, the
   * <code>attributeName</code> element  must not be specified.
   */
  String attributeName() default "";

  /**
   * Used to disable an auto-apply or inherited converter.
   * If disableConversion is true, the <code>converter</code> element should
   * not be specified.
   */
  boolean disableConversion() default false;
}
