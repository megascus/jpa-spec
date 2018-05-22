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
 * マップキー自体が主キーである場合に{@link java.util.Map java.util.Map}型の関連付けのためのマップキーを指定するか、
 * マップの値であるエンティティの永続化フィールドまたはプロパティを指定します。
 * 
 * <p> 主キー以外の永続化フィールドまたはプロパティがマップキーとして使用される場合でもそれに関連付けられた一意制約があることが予期されます。
 *
 * <p>{@link MapKeyClass}アノテーションと<code>MapKey</code>アノテーションは同時に使われることはありません。
 *
 * <pre>
 *
 *    Example 1:
 *
 *    &#064;Entity
 *    public class Department {
 *        ...
 *        &#064;OneToMany(mappedBy="department")
 *        &#064;MapKey  // マップキーは主キー
 *        public Map&#060;Integer, Employee&#062; getEmployees() {... }
 *        ...
 *    }
 *
 *    &#064;Entity
 *    public class Employee {
 *        ...
 *        &#064;Id Integer getEmpId() { ... }
 *        &#064;ManyToOne
 *        &#064;JoinColumn(name="dept_id")
 *        public Department getDepartment() { ... }
 *        ...
 *    }
 *
 *    Example 2:
 *
 *    &#064;Entity
 *        public class Department {
 *        ...
 *        &#064;OneToMany(mappedBy="department")
 *        &#064;MapKey(name="name")
 *        public Map&#060;String, Employee&#062; getEmployees() {... }
 *        ...
 *    }
 *
 *    &#064;Entity
 *        public class Employee {
 *        &#064;Id public Integer getEmpId() { ... }
 *        ...
 *        &#064;ManyToOne
 *        &#064;JoinColumn(name="dept_id")
 *        public Department getDepartment() { ... }
 *        ...
 *    }
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface MapKey {

    /**
     * (オプション) マップキーとして使用される関連エンティティの永続化フィールドまたはプロパティの名前。
     * 
     * <p> デフォルト： <code>name</code>要素が指定されていない場合は関連エンティティの主キーがマップキーとして使用されます。
     * 主キーが複合主キーであり、<code>IdClass</code>としてマッピングされている場合は主キークラスのインスタンスがキーとして使用されます。
     */
    String name() default "";
}
