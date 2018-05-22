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
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * アノテーションの付いたエンティティクラスのセカンダリテーブルを指定します。
 * 
 * ひとつ以上のセカンダリテーブルを指定することでエンティティクラスのデータが複数のテーブルにまたがって保管されることを示します。
 *
 * <p> <code>SecondaryTable</code>が指定されてない場合は、エンティティのすべての永続化フィールドもしくはプロパティはプライマリテーブルにマップされるものとみなされます。
 * 主キー結合カラムが指定されていない場合、結合カラムはプライマリテーブルの主キーのカラムを参照し、プライマリテーブルで参照された主キーのカラムと同じ名前と型を持つと見なされます。
 *
 * <pre>
 *    Example 1: 単一の主キーのカラムの単一のセカンダリテーブル
 *
 *    &#064;Entity
 *    &#064;Table(name="CUSTOMER")
 *    &#064;SecondaryTable(name="CUST_DETAIL", 
 *        pkJoinColumns=&#064;PrimaryKeyJoinColumn(name="CUST_ID"))
 *    public class Customer { ... } 
 *
 *
 *    Example 2: 複合主キーを使用した単一のセカンダリテーブル
 *
 *    &#064;Entity
 *    &#064;Table(name="CUSTOMER")
 *    &#064;SecondaryTable(name="CUST_DETAIL",
 *        pkJoinColumns={
 *            &#064;PrimaryKeyJoinColumn(name="CUST_ID"),
 *            &#064;PrimaryKeyJoinColumn(name="CUST_TYPE")})
 *    public class Customer { ... }
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Repeatable(SecondaryTables.class)
@Target(TYPE) 
@Retention(RUNTIME)

public @interface SecondaryTable {

    /** (必須) テーブルの名前。 */
    String name();

    /**
     * (オプション) テーブルの含まれるカタログ。
     * 
     * <p> デフォルトでは既定のカタログです。
     */
    String catalog() default "";

    /**
     * (オプション) テーブルの含まれるスキーマ。
     * 
     * <p> デフォルトではユーザーにとっての規定のスキーマです。
     */
    String schema() default "";

    /** 
     * (オプション) プライマリテーブルとの結合に使われるカラム。
     * 
     * <p> デフォルトではプライマリテーブルの(複数単数問わず)主キーのカラムと同じ名前のカラムになります。 
     */
    PrimaryKeyJoinColumn[] pkJoinColumns() default {};

    /**
     *  (オプション) テーブルの生成が有効なときに<code>pkJoinColumns</code>要素に対応するカラムの外部キー制約の生成を指定または制御するために使用されます。
     * 
     * この要素と<code>pkJoinColumns</code>要素のいずれかにおいての<code>foreignKey</code>要素が同時に指定されている場合の動作は未定義です。
     * いずれの場所にも外部キーアノテーション要素が指定されていない場合、永続化プロバイダのデフォルトの外部キー方式が適用されるでしょう。
     *
     *  @since Java Persistence 2.1
     */
    ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);

    /**
     * (オプション) テーブルに配置されるユニーク制約。
     * 
     * これらは通常、テーブルの生成が有効な場合にのみ使用されます。
     * これらの制約は、<code>Column</code>および<code>JoinColumn</code> アノテーションで指定された制約および主のマッピングによって作られる制約とは別に適用されます。
     * 
     * <p>デフォルトでは追加の制約はありません。
     */
    UniqueConstraint[] uniqueConstraints() default {};

    /**
     * (オプション) テーブルのインデックス。
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。
     *
     * @since Java Persistence 2.1 
     */
    Index[] indexes() default {};
}
