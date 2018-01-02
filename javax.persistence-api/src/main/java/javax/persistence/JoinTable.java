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
 * 関連のマッピングを指定します。
 * 
 * これは関連の所有側に適用されます。
 * 
 * <p>通常は結合テーブルは多対多および単方向の1対多の関連付けのマッピングで使用されます。
 * ただし、双方向多対1や1対多の関連付け、単方向多対1関係および1対1関連(双方向および単方向の両方)をマッピングするためでも使用できます。
 * 
 * <p>関係の所有側で組み込みクラスとの関連をマッピングする際に結合テーブルを使用した場合、
 * 組み込みクラスではなく組み込みクラスを保持するエンティティが関係の所有者とみなされます。
 * 
 * <p><code>JoinTable</code>アノテーションがない場合、アノテーションの要素のデフォルト値が適用されます。
 * 結合テーブルの名前は、関連するプライマリーテーブルの表名が(所有側が最初で)アンダースコアを使用して結合されたものであると見なされます。
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
