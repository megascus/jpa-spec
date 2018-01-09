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
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.FetchType.LAZY;

/**
 * 1対多の多重度を持つ複数値の関連付けを指定します。
 * 
 * <p> ジェネリクスを使用してコレクションが定義されている場合には要素の型を指定するために関連する対象のエンティティタイプを指定する必要はありません。
 * そうでない場合は対象のエンティティクラスを指定する必要があります。
 * エンティティ間の関係が双方向である場合、<code>mappedBy</code>要素を使用して関係の所有者側のエンティティの関係フィールドまたはプロパティを指定する必要があります。
 * 
 * <p> <code>OneToMany</code>アノテーションをエンティティクラス内に含まれる組み込みクラス内で、
 * エンティティのコレクションへの関係を指定するために使用できます。
 * エンティティ間の関係が双方向である場合、<code>mappedBy</code>要素を使用して関係の所有者側のエンティティの関係フィールドまたはプロパティを指定する必要があります。
 * 
 * コレクションが<code>java.util.Map</code>の場合、<code>cascade</code>要素と<code>orphanRemoval</code>要素はマップの値に適用されます。
 *
 * <pre>
 *
 *    Example 1: ジェネリクスを使用した1対多の関連付け
 *
 *    // In Customer class:
 *
 *    &#064;OneToMany(cascade=ALL, mappedBy="customer")
 *    public Set&#060;Order&#062; getOrders() { return orders; }
 *
 *    In Order class:
 *
 *    &#064;ManyToOne
 *    &#064;JoinColumn(name="CUST_ID", nullable=false)
 *    public Customer getCustomer() { return customer; }
 *
 *
 *    Example 2: ジェネリクスを使用しない1対多の関連付け
 *
 *    // In Customer class:
 *
 *    &#064;OneToMany(targetEntity=com.acme.Order.class, cascade=ALL,
 *                mappedBy="customer")
 *    public Set getOrders() { return orders; }
 *
 *    // In Order class:
 *
 *    &#064;ManyToOne
 *    &#064;JoinColumn(name="CUST_ID", nullable=false)
 *    public Customer getCustomer() { return customer; }
 *
 *
 *    Example 3:外部キーマッピングを使用する1方向の1対多の関連付け
 *
 *    // In Customer class:
 *
 *    &#064;OneToMany(orphanRemoval=true)
 *    &#064;JoinColumn(name="CUST_ID") // join column is in table for Order
 *    public Set&#060;Order&#062; getOrders() {return orders;}
 *    
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)

public @interface OneToMany {

    /**
     * (オプション) 関連付けの対象のエンティティクラス。collectionプロパティがJavaジェネリクスを使用して定義されている場合は指定する必要はありません。そうでない場合は指定する必要があります。
     * 
     * <p>ジェネリクスを使用して定義されている場合、デフォルトはコレクションのパラメタライズドタイプです。
     */
    Class targetEntity() default void.class;

    /** 
     * (オプション) 関連付けの対象にカスケードする必要のある操作。
     *
     * <p> デフォルトではすべての操作はカスケードされません。
     *
     * <p> 対象のコレクションが{@link java.util.Map java.util.Map}である場合、
     * <code>cascade</code>属性はMapの値に適用されます。
     */
    CascadeType[] cascade() default {};

    /** 
     * (オプション) 関連付けを遅延ロード(LAZY)するか、即座に取得(EAGER)する必要があるかどうか。
     * 
     * EAGER戦略は永続化プロバイダの実行時に関連エンティティを即座に取得するべきだとする要件です。
     * LAZY戦略は永続化プロバイダの実行時のヒントです。
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

    /**
     * (オプション) 関係から削除されたエンティティに対し削除操作を適用し削除操作をそれらのエンティティにカスケードするかどうか。
     * @since Java Persistence 2.0
     */
    boolean orphanRemoval() default false;
}
