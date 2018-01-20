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

/**
 * 永続化プロパティもしくはフィールドのためにマッピングされたカラムを指定します。
 * 
 * <code>Column</code>アノテーションが指定されていない場合は、デフォルト値が適用されます。
 *
 * <blockquote><pre>
 *    Example 1:
 *
 *    &#064;Column(name="DESC", nullable=false, length=512)
 *    public String getDescription() { return description; }
 *
 *    Example 2:
 *
 *    &#064;Column(name="DESC",
 *            columnDefinition="CLOB NOT NULL",
 *            table="EMP_DETAIL")
 *    &#064;Lob
 *    public String getDescription() { return description; }
 *
 *    Example 3:
 *
 *    &#064;Column(name="ORDER_COST", updatable=false, precision=12, scale=2)
 *    public BigDecimal getCost() { return cost; }
 *
 * </pre></blockquote>
 *
 *
 * @since Java Persistence 1.0
 */ 
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Column {

    /**
     * (オプション) カラムの名前。
     * 
     * デフォルトではプロパティもしくはフィールドの名前になります。
     */
    String name() default "";

    /**
	 * (オプション) このプロパティがユニークキーかどうか。
	 * 
	 * これはテーブルレベルの<code>UniqueConstraint</code>アノテーションのショートカットであり、
	 * ユニークキー制約が単一のフィールドだけである場合に便利です。
	 * この制約は主キーのマッピングとテーブルレベルで指定された制約に伴う制約に加えて適用されます。
     */
    boolean unique() default false;

    /**
     * (オプション) データベースのカラムがNULLを許容するかどうか。
     */
    boolean nullable() default true;

    /**
     * (オプション) 永続化プロバイダによって生成されたSQL INSERTステートメントにカラムが含まれるかどうか。
     */
    boolean insertable() default true;

    /**
     * (オプション) 永続化プロバイダによって生成されたSQL UPDATEステートメントにカラムが含まれるかどうか。
     */
    boolean updatable() default true;

    /**
	 * (オプション) カラムのDDLを生成するときに使用されるSQLフラグメント。
	 * 
	 * <p>デフォルトでは型推論されたカラムを作成するSQLを生成します。
     */
    String columnDefinition() default "";

    /**
     * (オプション) このカラムが含まれるテーブルの名前。
     * 
     * 存在しない場合、カラムはプライマリテーブルにあるとみなされます。
     */
    String table() default "";

    /**
     * (オプション) カラムの長さ。 (文字ベースのカラムが使用された場合のみ適用されます。)
     */
    int length() default 255;

    /**
     * (オプション) 10進数(正確な数値)のカラムの精度。 (10進数のカラムが使用されている場合にのみ適用されます。)
     * 
     * 値は開発者によって設定される必要があります。
     */
    int precision() default 0;

    /**
     * (オプション) 10進数(正確な数値)のカラムの位取り。 (10進数のカラムが使用されている場合にのみ適用されます。)
     */
    int scale() default 0;
}
