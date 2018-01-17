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
 * <p><code>Convert</code>アノテーションは基本属性または基本タイプの要素コレクションに適用できます。(この場合コンバーターはコレクションの要素に適用されます)
 * このような使用方法の場合は<code>attributeName</code>要素を指定してはいけません。
 * 
 * <p><code>Convert</code>アノテーションは組み込み属性やキーまたは値が組み込み型のMapのコレクション属性に適用できます。
 * (この場合コンバーターはコレクションに含まれる組み込みインスタンスの指定された属性に適用されます)
 * このような使用方法の場合は<code>attributeName</code>要素を指定する必要があります。
 * 
 * <p>複数の組み込みレベルで変換マッピングを上書きするためには、組み込み属性内の属性を示すために<code>attributeName</code>要素にドット(".")表記を使用する必要があります。
 * ドット表記で使用される各識別子の値はそれぞれの組み込みフィールドまたはプロパティの名前です。
 *
 * <p><code>Convert</code>アノテーションを組み込みクラスのインスタンスに含まれるMapに適用する場合は<code>attributeName</code>要素は指定されなければならず、
 * マップのキーまたはマップの値の一部として指定するために<code>"key."</code>もしくは<code>"value."</code>を変換される属性の名前にプレフィックスとして使用する必要があります。
 *
 * <p><code>Convert</code>アノテーションを基本型のMapのキーの変換を指定するためにMapに適用する場合は<code>attributeName</code>要素の値として<code>"key"</code>を使用して変換されるのはマップのキーであることを指定する必要があります。
 * 
 * <p><code>Convert</code>アノテーションはマップドスーパークラスを拡張したエンティティクラスに対して継承された基本属性または組み込み属性の変換マッピングを指定または上書きするために適用できます。
 *
 *  <pre>
 *     Example 1:  基本属性の変換
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
 *     Example 2: 基本属性の変換の自動適用
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
 *     Example 3:  自動適用コンバーターがある場合の変換の無効化
 *
 *     &#064;Convert(disableConversion=true)
 *     EmployeeDate lastReview;
 *
 *
 *     Example 4:  基本型の要素コレクションへのコンバーターの適用
 *
 *     &#064;ElementCollection
 *     // applies to each element in the collection
 *     &#064;Convert(converter=NameConverter.class) 
 *     List&#060;String&#062; names;
 *
 *
 *     Example 5:  Mapもしくは基本値である要素コレクションへのコンバーターの適用。  
 *                 コンバーターはMapの値に適用される。
 *
 *     &#064;ElementCollection
 *     &#064;Convert(converter=EmployeeNameConverter.class)
 *     Map&#060;String, String&#062; responsibilities;
 *
 *
 *     Example 6:  基本型のMapのキーへのコンバーターの適用
 *
 *     &#064;OneToMany
 *     &#064;Convert(converter=ResponsibilityCodeConverter.class, 
 *              attributeName="key")
 *     Map&#060;String, Employee&#062; responsibilities;
 *
 *
 *     Example 7:  組み込み属性へのコンバーターの適用
 *
 *     &#064;Embedded
 *     &#064;Convert(converter=CountryConverter.class, 
 *              attributeName="country")
 *     Address address;
 * 
 *
 *     Example 8:  ネストした組み込み属性へのコンバーターの適用
 * 
 *     &#064;Embedded
 *     &#064;Convert(converter=CityConverter.class, 
 *              attributeName="region.city")
 *     Address address;
 *
 *
 *     Example 9:  要素コレクションのMapのキーとしてネストした組み込みクラスへのコンバーターの適用
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
 *     Example 10: 関連のマップのキーである組み込みクラスへのコンバーターの適用
 *
 *     &#064;OneToMany
 *     &#064;Convert(attributeName="key.jobType", 
 *              converter=ResponsibilityTypeConverter.class)
 *     Map&#060;Responsibility, Employee&#062; responsibilities;
 *
 *
 *     Example 11: マップドクラスから継承した属性の変換マッピングの上書き
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
   * 適用するコンバーターを指定します。
   * 
   * 複数のコンバーターが他で適用されるならば、この値は指定する必要があります。
   */
  Class converter() default void.class;

  /**
   * <code>Convert</code>アノテーションが基本型や基本型の要素コレクションの属性に適用されている場合を除き<code>attributeName</code>要素は指定する必要があります。
   * 
   * それらの場合では<code>attributeName</code>要素は指定してはいけません。
   */
  String attributeName() default "";

  /**
   * 自動適用されるか継承されたコンバーターを無効化するのに使用します。
   * 
   * disableConversionがtrueの場合、<code>converter</code>要素を指定する必要があります。
   */
  boolean disableConversion() default false;
}
