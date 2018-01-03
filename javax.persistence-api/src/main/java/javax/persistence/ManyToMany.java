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
import javax.persistence.CascadeType;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.FetchType.LAZY;

/**
 * 多対多の多重度を持つ複数値の関連付けを指定します。
 *
 * <p>すべての多対多関連は二つの側を持ちます。所有側と被所有側(もしくは逆側)です。
 * 結合テーブルは所有側で指定されます。
 * 関係が双方向である場合、被所有側は<code>ManyToMany</code>アノテーションの<code>mappedBy</code>要素を使用して、
 * 所有側の関係フィールドまたはプロパティを指定する必要があります。
 *
 * <p> 関係の結合テーブルは、デフォルトでない場合は所有側で指定します。
 *
 * <p> <code>ManyToMany</code>アノテーションはエンティティクラス内に含まれる組み込みクラス内で使用されたエンティティのコレクションへの関係を指定することができます。
 * 関係が双方向であり、組み込みクラスを含むエンティティが関係の所有側である場合、被所有側は組み込みクラスの関係フィールドまたはプロパティを指定するために<code>ManyToMany</code>
 * アノテーションの<code>mappedBy</code>要素を使用する必要があります。
 * 組み込み属性内の関係属性を示すには、<code>mappedBy</code>要素でドット(".")表記を使用する必要があります。
 * ドット表記で使用される各識別子の値は、それぞれの組み込みフィールドまたはプロパティの名前です。
 *
 * <pre>
 *
 *    Example 1:
 *
 *    // In Customer class:
 *
 *    &#064;ManyToMany
 *    &#064;JoinTable(name="CUST_PHONES")
 *    public Set&#060;PhoneNumber&#062; getPhones() { return phones; }
 *
 *    // In PhoneNumber class:
 *
 *    &#064;ManyToMany(mappedBy="phones")
 *    public Set&#060;Customer&#062; getCustomers() { return customers; }
 *
 *    Example 2:
 *
 *    // In Customer class:
 *
 *    &#064;ManyToMany(targetEntity=com.acme.PhoneNumber.class)
 *    public Set getPhones() { return phones; }
 *
 *    // In PhoneNumber class:
 *
 *    &#064;ManyToMany(targetEntity=com.acme.Customer.class, mappedBy="phones")
 *    public Set getCustomers() { return customers; }
 *
 *    Example 3:
 *
 *    // In Customer class:
 *
 *    &#064;ManyToMany
 *    &#064;JoinTable(name="CUST_PHONE",
 *        joinColumns=
 *            &#064;JoinColumn(name="CUST_ID", referencedColumnName="ID"),
 *        inverseJoinColumns=
 *            &#064;JoinColumn(name="PHONE_ID", referencedColumnName="ID")
 *        )
 *    public Set&#060;PhoneNumber&#062; getPhones() { return phones; }
 *
 *    // In PhoneNumberClass:
 *
 *    &#064;ManyToMany(mappedBy="phones")
 *    public Set&#060;Customer&#062; getCustomers() { return customers; }
 * </pre>
 *
 * @see JoinTable
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface ManyToMany {

    /**
     * (オプション) 関連付けの対象のエンティティクラス。collectionプロパティがJavaジェネリクスを使用して定義されている場合は指定する必要はありません。そうでない場合は指定する必要があります。
     * 
     * <p>ジェネリクスを使用して定義されている場合、デフォルトはコレクションのパラメタライズドタイプです。
     */
    Class targetEntity() default void.class;

    /** 
     * (オプション) 関連付けの対象にカスケードする必要のある操作。
     *
     * <p> 対象のコレクションが{@link java.util.Map java.util.Map}である場合、
     * <code>cascade</code>属性はMapの値に適用されます。
     *
     * <p> デフォルトではすべての操作はカスケードされません。
     */
    CascadeType[] cascade() default {};

    /** 
     * (オプション) 関連付けを遅延ロード(LAZY)するか、即座に取得(EAGER)する必要があるかどうか。
     * 
     * EAGER戦略は永続性プロバイダーの実行時に関連エンティティを即座に取得するべきだとする要件です。
     * LAZY戦略は持続性プロバイダーの実行時のヒントです。
     * 
     * <p>訳注：つまりEAGERは必ず有効になり、LAZYが有効になるかは実装に依存します。
     */
    FetchType fetch() default LAZY;

    /** 
     * 関係を所有するフィールド。
     * 
     * 関係が一方向でない場合は必須です。
     */
    String mappedBy() default "";
}
