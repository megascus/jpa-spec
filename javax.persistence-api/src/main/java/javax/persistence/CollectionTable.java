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
 * 基本型もしくは組み込み型のコレクションのマッピングに使用されるテーブルを指定します。
 * 
 * 値がコレクションのフィールドもしくはプロパティに適用します。
 * 
 * <p>デフォルトでは組み込みクラスまたは基本型に対応するコレクションテーブルの列は組み込みクラスの属性または<code>Column</code>アノテーションのデフォルト値に従って基本型から派生します。
 * 基本型の場合は列名は値がコレクションのフィールドまたはプロパティの名前から派生します。
 * 組み込みクラスの場合は列名は組み込みクラスのフィールドまたはプロパティ名から派生します。
 * <ul>
 * <li> 基本型に使用される列でデフォルトの設定を上書きするためには値がコレクションの属性で<code>ElementCollection</code>アノテーションに加えて<code>Column</code>アノテーションを使用します。
 *
 * <li> 組み込みクラスのデフォルト値を上書きするためには<code>ElementCollection</code>アノテーションに加えて<code>AttributeOverride</code>や<code>AttributeOverrides</code>アノテーションを使用することができます。
 * 組み込みクラスにほかのエンティティへの参照が含まれている場合、それらの参照に関連する列のデフォルト値は<code>AssociationOverride</code>や<code>AssociationOverrides</code>を使用することで上書きすることができます。
 * </ul>
 *
 * <p><code>CollectionTable</code>アノテーションがない場合は<code>CollectionTable</code>アノテーションの要素のデフォルト値が適用されます。
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
 *       &#064;ElementCollection  // デフォルトのテーブルを使用 (PERSON_NICKNAMES)
 *       &#064;Column(name="name", length=50)
 *       protected Set&#060;String&#062; nickNames = new HashSet();
 *       ...
 *    }
 *
 *    &#064;Entity public class WealthyPerson extends Person {
 *       &#064;ElementCollection
 *       &#064;CollectionTable(name="HOMES") // デフォルトの結合列名を使用
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
     *  (オプション) コレクションテーブルの名前。
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
     * (オプション)エンティティのプライマリーテーブルを参照するコレクションテーブルの外部キー列。
     * デフォルト値は単一の結合列が使用されている場合のみ適用されます。
     * デフォルト値は<code>JoinColumn</code>と同じです。
     * (つまり、エンティティの名前 + "_" + 参照された主キー列の名前)
     * しかしながら、複数の結合列が存在する場合は<code>JoinColumns</code>アノテーションを使用して各結合列に対して
     * <code>JoinColumn</code>アノテーションを指定する必要があります。
     * この場合、それぞれの<code>JoinColumn</code>アノテーションの<code>name</code>要素と<code>referencedColumnName</code>要素の両方を指定する必要があります。
     */
     JoinColumn[] joinColumns() default {};

    /**
     * (オプション) テーブルの生成が有効な場合に<code>joinColumns</code>要素に関係する列のための外部キー制約の生成を指定または制御するために使用されます。
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
