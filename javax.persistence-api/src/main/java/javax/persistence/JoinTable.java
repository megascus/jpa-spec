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
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * Specifies the mapping of associations. It is applied to the
 * owning side of an association.  
 *
 * <p> A join table is typically used in the mapping of many-to-many
 * and unidirectional one-to-many associations. It may also be used to
 * map bidirectional many-to-one/one-to-many associations,
 * unidirectional many-to-one relationships, and one-to-one
 * associations (both bidirectional and unidirectional).
 *
 *<p>When a join table is used in mapping a relationship with an
 *embeddable class on the owning side of the relationship, the
 *containing entity rather than the embeddable class is considered the
 *owner of the relationship.
 *
 * <p> If the <code>JoinTable</code> annotation is missing, the 
 * default values of the annotation elements apply.  
 * The name of the join table is assumed to be the table names of the 
 * associated primary tables concatenated together (owning side 
 * first) using an underscore.
 *
 * <pre>
 *
 *    Example:
 *
 *    &#064;JoinTable(
 *        name="CUST_PHONE",
 *        joinColumns=
 *            &#064;JoinColumn(name="CUST_ID", referencedColumnName="ID"),
 *        inverseJoinColumns=
 *            &#064;JoinColumn(name="PHONE_ID", referencedColumnName="ID")
 *    )
 * </pre>
 * 
 * @see JoinColumn
 * @see JoinColumns
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)

public @interface JoinTable {

    /**
     * (オプション) 結合テーブルの名前。 
     * 
     * <p> デフォルトでは関連する二つのプライマリーエンティティテーブルの名前がアンダースコアで結合された名前が使用されます。
     */
    String name() default "";

    /** (オプション) テーブルのカタログ。
     *  
     * <p> デフォルトでは既定のカタログです。
     */
    String catalog() default "";

    /** (オプション) テーブルのスキーマ。
     * 
     * <p> デフォルトではユーザーの規定のスキーマです。
     */
    String schema() default "";

    /**
     * (オプション) 関連を所有するエンティティのプライマリテーブルを参照する結合テーブルの外部キー列。(つまり、関連の所有側)
     *
     * <p> {@link JoinColumn}と同じデフォルト値を使用します。
     */
    JoinColumn[] joinColumns() default {};

    /** 
     * (オプション) 関連を所有しないエンティティのプライマリテーブルを参照する結合テーブルの外部キー列。(つまり、関連の所有の逆側)
     *
     * <p> {@link JoinColumn}と同じデフォルト値を使用します。
     */
    JoinColumn[] inverseJoinColumns() default {};

    /**
     * (オプション) 表の生成が有効な場合に<code>joinColumns</code>要素に関係する列のための外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素と<code>joinColumns</code>要素のいずれかの<code>foreignKey</code>要素の両方が指定されていた場合の挙動は未定義です。
     * いずれの場所にも外部キーのアノテーション要素が指定されていない場合は、永続化プロバイダーのデフォルトの外部キー戦略が適用されます。
     *
     * @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

    /**
     * (オプション) 表の生成が有効な場合に<code>inverseJoinColumns</code>要素に関係する列のための外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素と<code>inverseJoinColumns</code>要素のいずれかの<code>foreignKey</code>要素の両方が指定されていた場合の挙動は未定義です。
     * いずれの場所にも外部キーのアノテーション要素が指定されていない場合は、永続化プロバイダーのデフォルトの外部キー戦略が適用されます。
     *
     * @since Java Persistence 2.1
     */
    ForeignKey inverseForeignKey() default @ForeignKey(PROVIDER_DEFAULT);

    /**
     * (オプション) テーブルに設置されるユニーク制約。
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。
     * 
     * <p> デフォルトでは追加の制約はありません。
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
