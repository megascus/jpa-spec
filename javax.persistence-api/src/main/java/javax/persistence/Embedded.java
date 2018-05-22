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
 * エンティティのフィールドもしくはプロパティの値が組み込みクラスのインスタンスであることを指定します。
 * 
 * 組み込みクラスは{@link Embeddable}アノテーションをつける必要があります。
 *
 * <p> <code>AttributeOverride</code>や<code>AttributeOverrides</code>、<code>AssociationOverride</code>、
 * <code>AssociationOverrides</code>アノテーションを組み込みクラスで宣言された、もしくはデフォルトの値を上書きマッピングするために使用できます。
 *
 * <pre>
 *   Example:
 *
 *   &#064;Embedded
 *   &#064;AttributeOverrides({
 *       &#064;AttributeOverride(name="startDate", column=&#064;Column("EMP_START")),
 *       &#064;AttributeOverride(name="endDate", column=&#064;Column("EMP_END"))
 *   })
 *   public EmploymentPeriod getEmploymentPeriod() { ... }
 * </pre>
 *
 * @see Embeddable
 * @see AttributeOverride
 * @see AttributeOverrides
 * @see AssociationOverride
 * @see AssociationOverrides
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)

public @interface Embedded {
}
