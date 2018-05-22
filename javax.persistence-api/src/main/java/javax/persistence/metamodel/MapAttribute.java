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
package javax.persistence.metamodel;

/**
 * <code>MapAttribute</code>型のインスタンスは永続化された<code>java.util.Map</code>の値の属性を表します。
 *
 * @param <X> 表されるマップが属する型
 * @param <K> 表されるマップのキーの型
 * @param <V> 表されるマップの値の型
 *
 * @since Java Persistence 2.0
 *
 */
public interface MapAttribute<X, K, V> 
	extends PluralAttribute<X, java.util.Map<K, V>, V> {

    /**
     * マップのキーのJava型を返します。
     * @return Javaのキー型
     */
    Class<K> getKeyJavaType();

    /**
     * マップのキーの型を表す型を返します。
     * @return キー型を表す型
     */
    Type<K> getKeyType();
}
