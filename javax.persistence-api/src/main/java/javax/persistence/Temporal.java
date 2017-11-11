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
 * このアノテーションは<code>java.util.Date</code>型および<code>java.util.Calendar</code>型の永続フィールドまたはプロパティに対して指定する必要があります。
 * 
 * これらの型のフィールドまたはプロパティに対してのみ指定できます。
 * 
 * <p><code>Temporal</code>アノテーションは{@link Basic}アノテーションや{@link Id}アノテーション、
 * {@link ElementCollection}アノテーション(要素のコレクションの値がそのような時制の型である場合)と同時に使用できます。
 *
 * <pre>
 *     Example:
 * 
 *     &#064;Temporal(DATE)
 *     protected java.util.Date endDate;
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface Temporal {

    /** <code>java.util.Date</code> や <code>java.util.Calendar</code>のマッピングに使用される型。 */
    TemporalType value();
}
