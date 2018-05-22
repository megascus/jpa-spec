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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

/**
 * エンティティクラスの階層で使用される継承戦略を指定します。
 * 
 * これはエンティティクラス階層の最上位のエンティティクラスで指定されます。
 * <code>Inheritance</code>アノテーションが指定されてない場合、もしくはエンティティクラス階層で継承型が指定されていない場合は、
 * <code>SINGLE_TABLE</code>マッピングが使用されます。
 *
 * <pre>
 *
 *   Example:
 *
 *   &#064;Entity
 *   &#064;Inheritance(strategy=JOINED)
 *   public class Customer { ... }
 *
 *   &#064;Entity
 *   public class ValuedCustomer extends Customer { ... }
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target({TYPE})
@Retention(RUNTIME)

public @interface Inheritance {

    /** エンティティの継承階層に使用される戦略。 */
    InheritanceType strategy() default SINGLE_TABLE;
}
