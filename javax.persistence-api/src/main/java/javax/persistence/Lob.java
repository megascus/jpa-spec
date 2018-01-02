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
 * 永続化プロパティまたはフィールドを、データベースがサポートするラージオブジェクト型のラージオブジェクトとして永続化する必要があることを指定します。
 * 
 * <p> ポータブルアプリケーションでは、データベースのLOB型にマッピングするときには<code>Lob</code>アノテーションを使用する必要があります。
 * <code>Lob</code>アノテーションは{@link Basic}アノテーションまたは要素コレクションの値が基本型である場合の{@link ElementCollection}アノテーションと共に使用できます。
 * <code>Lob</code>は、バイナリまたは文字型のいずれかです。
 *
 * <p> <code>Lob</code>型は永続化フィールドまたはプロパティの型から推論されますが、文字列および文字型を除いて既定値はBlobになります。
 * <pre>
 *
 *   Example 1:
 *
 *   &#064;Lob &#064;Basic(fetch=LAZY)
 *   &#064;Column(name="REPORT")
 *   protected String report;
 *
 *   Example 2:
 *
 *   &#064;Lob &#064;Basic(fetch=LAZY)
 *   &#064;Column(name="EMP_PIC", columnDefinition="BLOB NOT NULL")
 *   protected byte[] pic;
 *
 * </pre>
 *
 * @see Basic
 * @see ElementCollection
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Lob {
}
