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

/**
 * エンティティの主キーを指定します。
 * 
 * <code>Id</code>アノテーションが適用されたフィールドまたはプロパティは以下のいずれかの型である必要があります。
 * <ul>
 * <li>Javaプリミティブ型のいずれか</li>
 * <li>Javaプリミティブ型のラッパークラスのいずれか</li>
 * <li><code>String</code></li>
 * <li><code>java.util.Date</code></li>
 * <li><code>java.math.BigDecimal</code></li>
 * <li><code>java.math.BigInteger</code></li>
 * </ul>
 *
 * <p>エンティティの主キーがマッピングされるカラムはプライマリーテーブルの主キーであるとみなされます。
 * <code>Column</code>アノテーションが指定されていない場合、主キーのカラムの名前は主キープロパティもしくはフィールドの名前とみなされます。
 *
 * <pre>
 *   Example:
 *
 *   &#064;Id
 *   public Long getId() { return id; }
 * </pre>
 *
 * @see Column
 * @see GeneratedValue
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)

public @interface Id {}
