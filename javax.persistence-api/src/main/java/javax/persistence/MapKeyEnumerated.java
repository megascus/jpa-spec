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
import static javax.persistence.EnumType.ORDINAL;

/**
 * 基本型が列挙型であるマップキーのEnumの型を指定します。
 * 
 * <code>MapKeyEnumerated</code>アノテーションは<code>ElementCollection</code>もしくは<code>OneToMany</code>、<code>ManyToMany</code>アノテーションと共に、
 * <code>java.util.Map</code>型の要素コレクションや関係に適用できます。
 * 列挙型が指定されていないか、<code>MapKeyEnumerated</code>アノテーションが使用されていない場合、列挙型は<code>ORDINAL</code>であるとみなされます。
 *
 * <pre>
 *   Example:
 *
 *   public enum ProjectStatus {COMPLETE, DELAYED, CANCELLED, IN_PROGRESS}
 *
 *   public enum SalaryRate {JUNIOR, SENIOR, MANAGER, EXECUTIVE}
 *
 *   &#064;Entity public class Employee {
 *       &#064;ManyToMany
 *       public Projects&#060;ProjectStatus, Project&#062; getProjects() {...}
 *       
 *       &#064;OneToMany
 *       &#064;MapKeyEnumerated(STRING)
 *       public Map&#060;SalaryRate, Employee&#062; getEmployees() {...}
 *       ...
 *   }
 * </pre>
 *
 * @see ElementCollection
 * @see OneToMany
 * @see ManyToMany
 *
 * @since Java Persistence 2.0
 */
@Target({METHOD, FIELD}) @Retention(RUNTIME)
public @interface MapKeyEnumerated {
    
    /** (オプション) マップキーのEnumの型のマッピングに使用される型。 */
    EnumType value() default ORDINAL;
}
