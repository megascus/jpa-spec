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
 * 楽観ロック値として機能するエンティティクラスの<code>Version</code>フィールドまたはプロパティを指定します。
 * この<code>Version</code>は、マージ操作を実行する際の整合性と楽観ロックによる並行性制御のために使用されます。
 * 
 * <p>クラスごとに1つの<code>Version</code>プロパティまたはフィールドのみ使用できます。複数の<code>Version</code>プロパティまたはフィールドを使用するアプリケーションは移植できないでしょう。
 * 
 * <p><code>Version</code>プロパティは、エンティティクラスのプライマリテーブルに設置する必要があります。
 * <code>Version</code>プロパティをプライマリテーブル以外のテーブルに設置するアプリケーションは移植できないでしょう。
 * 
 * <p><code>Version</code>プロパティでサポートされる型は<code>int</code>、<code>Integer</code>、<code>short</code>、<code>Short</code>、<code>long</code>、<code>Long</code>、<code>java.sql.Timestamp</code>です。
 *
 * <pre>
 *    Example:
 *
 *    &#064;Version
 *    &#064;Column(name="OPTLOCK")
 *    protected int getVersionNum() { return versionNum; }
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Version {}
