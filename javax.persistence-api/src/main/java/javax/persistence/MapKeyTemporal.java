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
 * このアノテーションは{@link java.util.Date}型と{@link java.util.Calendar}型の永続化マップキーに対して指定する必要があります。
 * 
 * このアノテーションはこれらのタイプのマップキーに対してのみ指定できます。
 * 
 * <p> <code>MapKeyTemporal</code>アノテーションは、<code>ElementCollection</code>もしくは<code>OneToMany</code>、<code>ManyToMany</code>アノテーションとともに、 
 * <code>java.util.Map</code>型の要素コレクションもしくは関係に適用できます。
 *
 * <pre>
 *     Example:
 * 
 *     &#064;OneToMany
 *     &#064;MapKeyTemporal(DATE)
 *     protected java.util.Map&#060;java.util.Date, Employee&#062; employees;
 * </pre>
 *
 * @since Java Persistence 2.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface MapKeyTemporal {

    /** (必須) マッピングに使用する型、
     * <code>java.util.Date</code>もしくは
     * <code>java.util.Calendar</code>。 
     */
    TemporalType value();
}

