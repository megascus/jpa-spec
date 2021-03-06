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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 複数の名前付きJPQL(Java Persistence query language)クエリーを指定します。
 * 
 * クエリーの名前は永続化ユニット内で共有されます。
 * <code>NamedQueries</code>アノテーションはエンティティ({@link Entity}の付いたクラス)やマップドスーパークラス({@link MappedSuperclass}の付いたクラス)に適用できます。
 *
 * @see NamedQuery
 * 
 * @since Java Persistence 1.0
 */
@Target({TYPE})  
@Retention(RUNTIME) 
public @interface NamedQueries { 

    /** (必須) <code>NamedQuery</code> アノテーションの配列。 */
     NamedQuery [] value (); 
}
