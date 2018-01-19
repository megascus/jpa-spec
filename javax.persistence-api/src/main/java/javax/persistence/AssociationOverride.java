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
 *     Linda DeMichiel - Java Persistence 2.0 - Version 2.0 (October 1 - 2013)
 *     Specification available from http://jcp.org/en/jsr/detail?id=317
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
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * Used to override a mapping for an entity relationship.
 *
 * <p> May be applied to an entity that extends a mapped superclass to
 * override a relationship mapping defined by the mapped
 * superclass. If not specified, the association is mapped the same as
 * in the original mapping. When used to override a mapping defined by
 * a mapped superclass, <code>AssociationOverride</code> is applied to
 * the entity class.
 *
 * <p> May be used to override a relationship mapping from an
 * embeddable within an entity to another entity when the embeddable
 * is on the owning side of the relationship. When used to override a
 * relationship mapping defined by an embeddable class (including an
 * embeddable class embedded within another embeddable class),
 * <code>AssociationOverride</code> is applied to the field or
 * property containing the embeddable.
 *
 * <p> When <code>AssociationOverride</code> is used to override a
 * relationship mapping from an embeddable class, the
 * <code>name</code> element specifies the referencing relationship
 * field or property within the embeddable class. To override mappings
 * at multiple levels of embedding, a dot (".") notation syntax must
 * be used in the <code>name</code> element to indicate an attribute
 * within an embedded attribute.  The value of each identifier used
 * with the dot notation is the name of the respective embedded field
 * or property.
 * 
 * <p> When <code>AssociationOverride</code> is applied to override
 * the mappings of an embeddable class used as a map value,
 * "<code>value.</code>" must be used to prefix the name of the
 * attribute within the embeddable class that is being overridden in
 * order to specify it as part of the map value.
 *
 * <p> If the relationship mapping is a foreign key mapping, the
 * <code>joinColumns</code> element is used.  If the relationship
 * mapping uses a join table, the <code>joinTable</code> element must
 * be specified to override the mapping of the join table and/or its
 * join columns.
 *
 * <pre>
 *    Example 1: マップドスーパークラスで定義された関係のマッピングを上書きする
 *
 *    &#064;MappedSuperclass
 *    public class Employee {
 *        ...
 *        &#064;ManyToOne
 *        protected Address address;
 *        ...
 *    }
 *    
 *    &#064;Entity 
 *        &#064;AssociationOverride(name="address", 
 *                             joinColumns=&#064;JoinColumn(name="ADDR_ID"))
 *        // address field mapping overridden to ADDR_ID foreign key
 *    public class PartTimeEmployee extends Employee {
 *        ...
 *    }
 * </pre>
 *
 * <pre>
 *    Example 2: ContactInfoクラスで定義されたphoneNumbersのマッピングを上書きする
 *
 *    &#064;Entity
 *    public class Employee {
 *        &#064;Id int id;
 *        &#064;AssociationOverride(
 *          name="phoneNumbers",
 *          joinTable=&#064;JoinTable(
 *             name="EMPPHONES",
 *             joinColumns=&#064;JoinColumn(name="EMP"),
 *             inverseJoinColumns=&#064;JoinColumn(name="PHONE")
 *          )
 *        )
 *        &#064;Embedded ContactInfo contactInfo;
 *       ...
 *    }
 * 
 *    &#064;Embeddable
 *    public class ContactInfo {
 *        &#064;ManyToOne Address address; // 単方向
 *        &#064;ManyToMany(targetEntity=PhoneNumber.class) List phoneNumbers;
 *    }
 * 
 *    &#064;Entity
 *    public class PhoneNumber {
 *        &#064;Id int number;
 *        &#064;ManyToMany(mappedBy="contactInfo.phoneNumbers")
 *        Collection&#060;Employee&#062; employees;
 *     }
 *    </pre>
 *
 * @see Embedded
 * @see Embeddable
 * @see MappedSuperclass
 * @see AttributeOverride
 *
 * @since Java Persistence 1.0 
 */
@Repeatable(AssociationOverrides.class)
@Target({TYPE, METHOD, FIELD}) 
@Retention(RUNTIME)

public @interface AssociationOverride {

    /**
     *  (必須) プロパティベースのアクセスが使用されている場合にはマッピングが上書きされるプロパティの名前、またはフィールドベースのアクセスが使用されている場合はフィールドの名前。
     */
    String name();

    /**
     * 永続属性にマッピングされる結合列。
     * 
     * 関係のマッピングの上書きで外部キーマッピングを使用する場合は<code>joinColumns</code>要素を指定する必要があります。
     * 関係の上書きに結合テーブルが使用される場合は<code>joinColumns</code>要素を指定してはいけません。
     */
    JoinColumn[] joinColumns() default {};

    /**
     * (オプション) 表の生成が有効な場合に<code>joinColumns</code>要素に関係する列のための外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素と<code>joinColumns</code>要素のいずれかの<code>foreignKey</code>要素の両方が指定されていた場合の挙動は未定義です。
     * いずれの場所にも外部キーのアノテーション要素が指定されていない場合は、永続化プロバイダのデフォルトの外部キー戦略が適用されます。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

    /**
     * 関係のマッピングをされる結合テーブル。
     * 
     * 関係のマッピングの上書きで結合テーブルを使用する場合は<code>joinTable</code> 要素を指定する必要があります。
     * 関係の上書きに外部キーマッピングが使用される場合は<code>joinTable</code>要素を指定してはいけません。
     *
     * @since Java Persistence 2.0
     */
    JoinTable joinTable() default @JoinTable;
}
