/*******************************************************************************
 * Copyright (c) 2011 - 2013 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/
package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <code>Convert</code>アノテーションをまとめるために使用します。
 * 
 * 複数のコンバーターを同じ基本属性に適用してはいけません。
 *
 * @see Convert
 * @since Java Persistence 2.1
 */
@Target({METHOD, FIELD, TYPE})
@Retention(RUNTIME)
public @interface Converts {

  /**
   * 適用される<code>Convert</code>マッピング。
   *
   */
  Convert[] value();
}
