/*******************************************************************************
 * Copyright (c) 2008 - 2014 Oracle Corporation. All rights reserved.
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
import static javax.persistence.EnumType.ORDINAL;

/**
 * 永続化プロパティまたはフィールドを列挙型として永続化することを指定します。
 * 
 * <code>Enumerated</code>アノテーションは<code>Basic</code>アノテーションとともに、
 * または値が基本型である要素コレクションで<code>ElementCollection</code>アノテーションとともに使用できます。
 * <code>EnumType</code>の値が指定されていないか、または<code>Enumerated</code>アノテーションが使用されていない場合、 
 * <code>EnumType</code>の値は<code>ORDINAL</code>とみなされます。
 *
 * <pre>
 *   Example:
 *
 *   public enum EmployeeStatus {FULL_TIME, PART_TIME, CONTRACT}
 *
 *   public enum SalaryRate {JUNIOR, SENIOR, MANAGER, EXECUTIVE}
 *
 *   &#064;Entity public class Employee {
 *       public EmployeeStatus getStatus() {...}
 *       ...
 *       &#064;Enumerated(STRING)
 *       public SalaryRate getPayScale() {...}
 *       ...
 *   }
 * </pre>
 *
 * @see Basic
 * @see ElementCollection
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Enumerated {

    /** (オプション) Enum型のマッピングに使用される型。 */
    EnumType value() default ORDINAL;
}
