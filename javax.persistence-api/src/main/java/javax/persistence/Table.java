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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * アノテーションをつけられたエンティティのためのプライマリテーブルを指定します。
 * 
 * 追加のテーブルは{@link SecondaryTable} や{@link SecondaryTables}のアノテーションを使用することで指定することができます。
 * 
 * <p>エンティティクラスに<code>Table</code>アノテーションが指定されない場合はデフォルトの値が適用されます。
 *
 * <pre>
 *    Example:
 *
 *    &#064;Entity
 *    &#064;Table(name="CUST", schema="RECORDS")
 *    public class Customer { ... }
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target(TYPE) 
@Retention(RUNTIME)
public @interface Table {

    /**
     * (オプション) テーブルの名前。
     * 
     * <p> デフォルトの値はエンティティの名前です。
     */
    String name() default "";

    /** (オプション) テーブルの含まれるカタログ。
     * 
     * <p> デフォルトでは既定のカタログです。
     */
    String catalog() default "";

    /** (オプション) テーブルの含まれるスキーマ。
     * 
     * <p> デフォルトではユーザーにとっての規定のスキーマです。
     */
    String schema() default "";

    /**
     * (オプション) テーブルに配置される固有の制約。 
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。
     * これらの制約は<code>Column</code>および<code>JoinColumn</code>アノテーションで指定された制約や、
     * 主キーのマッピングによって制約される制約とは別に適用されます。
     * 
     * <p>デフォルトでは追加の制約はありません。
     */
    UniqueConstraint[] uniqueConstraints() default {};

    /**
     * (オプション) テーブルのためのインデックス。
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。
     * 主キー索引は自動的に作成されるため、主キーの索引を指定する必要はないことに注意してください。
     *
     * @since Java Persistence 2.1 
     */
    Index[] indexes() default {};
}
