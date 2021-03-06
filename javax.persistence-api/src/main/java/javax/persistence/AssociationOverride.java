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
 * エンティティのリレーションシップのマッピングを上書きするために使用します。
 *
 * <p>マップドスーパークラスで定義されたリレーションシップのマッピングをマップドスーパークラスを拡張したエンティティに適用して上書きすることができます。
 * 指定されていない場合、関連はオリジナルのマッピングと同じものがマッピングされます。
 * マップドスーパークラスで定義されたマッピングを上書きする時は<code>AssociationOverride</code>はエンティティクラスに適用されます。
 *
 * <p>組み込みクラスがリレーションシップの所有者側の場合は組み込みクラスの中から他のエンティティへのリレーションシップのマッピングを上書きするために使用できます。
 * 組み込みクラス(他の組み込みクラスに含まれる組み込みクラスの場合を含みます)で定義されたリレーションシップのマッピングを上書きする場合は
 * <code>AssociationOverride</code>はその組み込みクラスを含むフィールドもしくはプロパティに適用されます。
 *
 * <p> <code>AssociationOverride</code>が組み込みクラス内のリレーションシップを参照したフィールドもしくはプロパティを上書きするために使用された場合、
 * <code>name</code>要素は組み込みクラス内のリレーションシップを参照したフィールドもしくはプロパティを指定します。
 * 複数の埋め込みレベルのマッピングを上書きするには、組み込み属性内の属性を示すために<code>name</code>要素にドット(".")表記形式を使用する必要があります。
 * ドット表記で使用される各識別子の値は、それぞれの組み込みフィールドもしくはプロパティの名前です。
 * 
 * <p> <code>AssociationOverride</code> がMapの値として使用されている組み込みクラスのマッピングを上書きするために適用されている場合は
 * マップの値の一部として指定するために上書きされる組み込みクラスの含まれる属性の名前のプレフィックスとして<code>value.</code>"を使用する必要があります。
 *
 * <p> リレーションシップが外部キーマッピングの場合は<code>joinColumns</code>要素が使用されます。
 * リレーションシップのマッピングが結合テーブルを使用する場合は<code>joinTable</code>要素を結合テーブルやその結合カラムのマッピングを上書きするために指定する必要があります。
 *
 * <pre>
 *    Example 1: マップドスーパークラスで定義されたリレーションシップのマッピングを上書きする
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
     *  (必須) プロパティベースのアクセスが使用されている場合にはマッピングが上書きされるリレーションシップのプロパティの名前、
     * またはフィールドベースのアクセスが使用されている場合はリレーションシップのフィールドの名前。
     */
    String name();

    /**
     * 永続属性にマッピングされる結合カラム。
     * 
     * リレーションシップのマッピングの上書きで外部キーマッピングを使用する場合は<code>joinColumns</code>要素を指定する必要があります。
     * リレーションシップの上書きに結合テーブルが使用される場合は<code>joinColumns</code>要素を指定してはいけません。
     */
    JoinColumn[] joinColumns() default {};

    /**
     * (オプション) テーブルの生成が有効な場合に<code>joinColumns</code>要素に関係するカラムのための外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素と<code>joinColumns</code>要素のいずれかの<code>foreignKey</code>要素の両方が指定されていた場合の挙動は未定義です。
     * いずれの場所にも外部キーのアノテーション要素が指定されていない場合は、永続化プロバイダのデフォルトの外部キー戦略が適用されます。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

    /**
     * リレーションシップのマッピングをされる結合テーブル。
     * 
     * リレーションシップのマッピングの上書きで結合テーブルを使用する場合は<code>joinTable</code> 要素を指定する必要があります。
     * リレーションシップの上書きに外部キーマッピングが使用される場合は<code>joinTable</code>要素を指定してはいけません。
     *
     * @since Java Persistence 2.0
     */
    JoinTable joinTable() default @JoinTable;
}
