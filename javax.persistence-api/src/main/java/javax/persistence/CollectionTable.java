/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * Specifies the table that is used for the mapping of
 * collections of basic or embeddable types.  Applied
 * to the collection-valued field or property.
 * 
 * <p>By default, the columns of the collection table that correspond
 * to the embeddable class or basic type are derived from the
 * attributes of the embeddable class or from the basic type according
 * to the default values of the <code>Column</code> annotation. In the case
 * of a basic type, the column name is derived from the name of the
 * collection-valued field or property. In the case of an embeddable
 * class, the column names are derived from the field or property
 * names of the embeddable class.
 * <ul>
 * <li> To override the default properties of the column used for a
 * basic type, the <code>Column</code> annotation is used on the
 * collection-valued attribute in addition to the
 * <code>ElementCollection</code> annotation. 
 *
 * <li> To override these defaults for an embeddable class, the
 * <code>AttributeOverride</code> and/or
 * <code>AttributeOverrides</code> annotations can be used in
 * addition to the <code>ElementCollection</code> annotation. If the
 * embeddable class contains references to other entities, the default
 * values for the columns corresponding to those references may be
 * overridden by means of the <code>AssociationOverride</code> and/or
 * <code>AssociationOverrides</code> annotations.  
 * </ul>
 *
 * <p> If the <code>CollectionTable</code> annotation is missing, the
 * default values of the <code>CollectionTable</code> annotation
 * elements apply.
 *
 * <pre>
 *    Example:
 *
 *    &#064;Embeddable public class Address {
 *       protected String street;
 *       protected String city;
 *       protected String state;
 *       ... 
 *     }
 *
 *    &#064;Entity public class Person {
 *       &#064;Id protected String ssn;
 *       protected String name;
 *       protected Address home;
 *       ...
 *       &#064;ElementCollection  // use default table (PERSON_NICKNAMES)
 *       &#064;Column(name="name", length=50)
 *       protected Set&#060;String&#062; nickNames = new HashSet();
 *       ...
 *    }
 *
 *    &#064;Entity public class WealthyPerson extends Person {
 *       &#064;ElementCollection
 *       &#064;CollectionTable(name="HOMES") // use default join column name
 *       &#064;AttributeOverrides({
 *          &#064;AttributeOverride(name="street", 
 *                             column=&#064;Column(name="HOME_STREET")),
 *          &#064;AttributeOverride(name="city", 
 *                             column=&#064;Column(name="HOME_CITY")),
 *          &#064;AttributeOverride(name="state", 
 *                             column=&#064;Column(name="HOME_STATE"))
 *        })
 *       protected Set&#060;Address&#062; vacationHomes = new HashSet();
 *       ...
 *    }
 * </pre>
 *
 * @see ElementCollection
 * @see AttributeOverride
 * @see AssociationOverride
 * @see Column
 *
 * @since Java Persistence 2.0
 */

@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface CollectionTable {

    /** 
     *  (オプション) コレクション表の名前。
     * 
     * 指定されていない場合はこれが含まれるエンティティの名前とコレクションの属性の名前がアンダースコアで分けられて結合されたものがデフォルトです。
     */
    String name() default "";

    /**
     *  (オプション) テーブルのカタログ。
     * 
     * 指定されていない場合はデフォルトカタログが使用されます。
     */
    String catalog() default "";

    /**
     * (オプション) テーブルのスキーマ。
     * 
     * 指定されていない場合はユーザーにとっての規定のスキーマが使用されます。
     */
    String schema() default "";

    /**
     * (オプション)エンティティのプライマリーテーブルを参照するコレクション表の外部キー列。
     * デフォルト値は単一の結合列が使用されている場合のみ適用されます。
     * デフォルト値は<code>JoinColumn</code>と同じです。
     * (つまり、エンティティの名前 + "_" + 参照された主キー列の名前)
     * しかしながら、複数の結合列が存在する場合は<code>JoinColumns</code>アノテーションを使用して各結合列に対して
     * <code>JoinColumn</code>アノテーションを指定する必要があります。
     * この場合、それぞれの<code>JoinColumn</code>アノテーションの<code>name</code>要素と<code>referencedColumnName</code>要素の両方を指定する必要があります。
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
     * (オプション) テーブルに設置されるユニーク制約。
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。
     */
    UniqueConstraint[] uniqueConstraints() default {};

    /**
     * (オプション) テーブルのためのインデックス。
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。
     *
     * @since Java Persistence 2.1 
     */
    Index[] indexes() default {};
}
