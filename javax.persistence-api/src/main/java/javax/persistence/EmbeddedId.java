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
 * エンティティクラスまたはマップドスーパークラスの永続化フィールドまたはプロパティに適用され、組み込みクラスである複合主キーを示します。
 * 組み込みクラスには{@link Embeddable}アノテーションを付ける必要があります。
 * 
 * <p>　<code>EmbeddedId</code>アノテーションが使用されている場合は、
 * <code>EmbeddedId</code>アノテーションは一つのみであり、<code>Id</code>アノテーションは使用してはいけません。
 * 
 * <p> {@link AttributeOverride}アノテーションを使用することで組み込みクラス内で宣言された列マッピングを上書きすることができます。
 * 
 * <p> {@link MapsId}アノテーションは派生主キーを指定するために<code>EmbeddedId</code>アノテーションとともに使用できます。
 * 
 * <p> エンティティに派生主キーがある場合、<code>AttributeOverride</code>アノテーションは親エンティティとの関係に対応しない組み込みIDの属性を上書きする場合のみ使用できます。
 * 
 * <p> 組み込みIDクラス内で定義された関係マッピングはサポートされていません。
 *
 * <pre>
 *    Example 1:
 *
 *    &#064;EmbeddedId
 *    protected EmployeePK empPK;
 *
 *
 *    Example 2:
 *
 *    &#064;Embeddable
 *    public class DependentId {
 *       String name;
 *       EmployeeId empPK;   // Employeeの主キーに対応
 *    }
 *
 *    &#064;Entity
 *    public class Dependent {
 *       // default column name for "name" attribute is overridden
 *       &#064;AttributeOverride(name="name", &#064;Column(name="dep_name"))
 *       &#064;EmbeddedId DependentId id;
 *       ...
 *       &#064;MapsId("empPK")
 *       &#064;ManyToOne Employee emp;
 *    }
 * </pre>
 *
 * @see Embeddable
 * @see MapsId
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)

public @interface EmbeddedId {}
