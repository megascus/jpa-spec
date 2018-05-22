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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * エンティティクラスやマップドスーパークラス、組み込みクラス、もしくはそれらのようなクラスの特定の属性に適用されるアクセス型を指定するために使用されます。
 * 
 * @since Java Persistence 2.0
 */
@Target( { TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public @interface Access {

    /**
     * (必須)  フィールドベースもしくはプロパティーベースのアクセスの指定。
     */
    AccessType value();
}
