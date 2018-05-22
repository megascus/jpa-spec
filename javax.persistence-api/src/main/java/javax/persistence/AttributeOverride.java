/*******************************************************************************
 * Copyright (c) 2008 - 2015 Oracle Corporation. All rights reserved.
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
 *     Linda DeMichiel - Java Persistence 2.0
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
 * (明示的もしくはデフォルトの)<code>Basic</code>プロパティもしくはフィールド、<code>Id</code>プロパティもしくはフィールドのマッピングを上書きするために使用します。
 *
 * <p> マップドスーパークラスを継承したエンティティや組み込みフィールドもしくはプロパティを持つエンティティのマップドスーパークラスや組み込みクラス
 * (もしくはその属性のひとつで定義された組み込みクラス)で定義された基本マッピングやIDマッピングを上書きするために適用できます。
 * 
 * <p>組み込みクラスのインスタンスを含む要素コレクションやキーもしくは値が組み込みクラスであるMapコレクションに適用することができます。
 * Mapに<code>AttributeOverride</code>を適用するときはマップのキーもしくはマップの値の一部として指定するために、
 * 上書きされる属性の名前のプレフィックスとして"<code>key.</code>"もしくは"<code>value.</code>"を使用する必要があります。
 *
 * <p>複数の埋め込みレベルのマッピングを上書きするには、組み込み属性内の属性を示すために<code>name</code>要素にドット(".")表記形式を使用する必要があります。
 * ドット表記で使用される各識別子の値は、それぞれの組み込みフィールドもしくはプロパティの名前です。
 *
 * <p><code>AttributeOverride</code>が指定されていない場合は、カラムにはオリジナルのマッピングと同じマッピングがされます。
 *
 * <pre>
 *    Example 1:
 *
 *    &#064;MappedSuperclass
 *    public class Employee {
 *        &#064;Id protected Integer id;
 *        &#064;Version protected Integer version;
 *        protected String address;
 *        public Integer getId() { ... }
 *        public void setId(Integer id) { ... }
 *        public String getAddress() { ... }
 *        public void setAddress(String address) { ... }
 *    }
 *
 *    &#064;Entity
 *    &#064;AttributeOverride(name="address", column=&#064;Column(name="ADDR"))
 *    public class PartTimeEmployee extends Employee {
 *        // address field mapping overridden to ADDR
 *        protected Float wage();
 *        public Float getHourlyWage() { ... }
 *        public void setHourlyWage(Float wage) { ... }
 *    }
 * 
 *
 *    Example 2:
 *
 *    &#064;Embeddable public class Address {
 *        protected String street;
 *        protected String city;
 *        protected String state;
 *        &#064;Embedded protected Zipcode zipcode;
 *    }
 *
 *    &#064;Embeddable public class Zipcode {
 *        protected String zip;
 *        protected String plusFour;
 *    }
 *
 *    &#064;Entity public class Customer {
 *        &#064;Id protected Integer id;
 *        protected String name;
 *        &#064;AttributeOverrides({
 *            &#064;AttributeOverride(name="state",
 *                               column=&#064;Column(name="ADDR_STATE")),
 *            &#064;AttributeOverride(name="zipcode.zip",
 *                               column=&#064;Column(name="ADDR_ZIP"))
 *        })
 *        &#064;Embedded protected Address address;
 *        ...
 *    }
 *
 *
 *    Example 3:
 *
 *    &#064;Entity public class PropertyRecord {
 *        &#064;EmbeddedId PropertyOwner owner;
 *        &#064;AttributeOverrides({
 *            &#064;AttributeOverride(name="key.street", 
 *                               column=&#064;Column(name="STREET_NAME")),
 *            &#064;AttributeOverride(name="value.size", 
 *                               column=&#064;Column(name="SQUARE_FEET")),
 *            &#064;AttributeOverride(name="value.tax", 
 *                               column=&#064;Column(name="ASSESSMENT"))
 *        })
 *       &#064;ElementCollection
 *       Map&#060;Address, PropertyInfo&#062; parcels;
 *    }
 *
 *    &#064;Embeddable public class PropertyInfo {
 *        Integer parcelNumber;
 *        Integer size;
 *        BigDecimal tax;
 *    }
 *
 * </pre>
 *
 * @see Embedded
 * @see Embeddable
 * @see MappedSuperclass
 * @see AssociationOverride
 *
 * @since Java Persistence 1.0
 */
@Repeatable(AttributeOverrides.class)
@Target({TYPE, METHOD, FIELD}) 
@Retention(RUNTIME)

public @interface AttributeOverride {

    /**
     * (必須) プロパティベースのアクセスが使用されている場合にはマッピングが上書きされるプロパティの名前、またはフィールドベースのアクセスが使用されている場合はフィールドの名前。
     */
    String name();

    /**
     * (必須) 永続化属性にマッピングされているカラム。
     * 
     * マッピング型は、組み込みクラスまたはマップドスーパークラスで定義されているものと同じままです。
     */
    Column column();
}
