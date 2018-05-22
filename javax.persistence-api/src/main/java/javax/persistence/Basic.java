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
import static javax.persistence.FetchType.EAGER;

/**
 * データベースのカラムへのマッピングのもっとも単純な型です。
 * 
 * <code>Basic</code>アノテーションは次の型のフィールドもしくはプロパティに適用することができます。:
 * Javaプリミティブ型,、プリミティブ型のラッパー、 <code>String</code>、 
 * <code>java.math.BigInteger</code>、
 * <code>java.math.BigDecimal</code>、
 * <code>java.util.Date</code>、
 * <code>java.util.Calendar</code>、 
 * <code>java.sql.Date</code>、 
 * <code>java.sql.Time</code>、
 * <code>java.sql.Timestamp</code>、 <code>byte[]</code>、 <code>Byte[]</code>、
 * <code>char[]</code>、 <code>Character[]</code>、列挙型、<code>java.io.Serializable</code>を実装したほかのすべての型。
 * 
 * <p> これらの型の永続化フィールドもしくはプロパティに対する<code>Basic</code>アノテーションの使用はオプションです。
 * <code>Basic</code>アノテーションがそのようなフィールドもしくはプロパティに指定されていない場合は<code>Basic</code>アノテーションのデフォルトの値が適用されます。
 *
 * <pre>
 *    Example 1:
 *
 *    &#064;Basic
 *    protected String name;
 *
 *    Example 2:
 *
 *    &#064;Basic(fetch=LAZY)
 *    protected String getName() { return name; }
 *
 * </pre>
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Basic {

    /**
     * (オプション) フィールドもしくはプロパティを遅延ロード(LAZY)すべきか、即座に取得(EAGER)する必要があるかどうか。
     * 
     * <code>EAGER</code>戦略は永続化プロバイダの実行時に関連エンティティを即座に取得するべきだとする要件です。
     * <code>LAZY</code> 戦略は永続化プロバイダの実行時のヒントです。
     * 指定されていない場合のデフォルトは<code>EAGER</code>です。
     */
    FetchType fetch() default EAGER;

    /**
     * (オプション) フィールドもしくはプロパティの値がnullになりえるかどうかを定義します。
     * 
     * これはヒントであり、プリミティブ型では無視されます。これはスキーマ生成で使用されます。
     * 指定されていない場合のデフォルト値は<code>true</code>です。
     */
    boolean optional() default true;
}
