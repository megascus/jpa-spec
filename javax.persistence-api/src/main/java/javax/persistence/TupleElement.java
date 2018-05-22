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

/**
 * <code>TupleElement</code>インターフェースはクエリーの結果タプルから返される要素を定義します。
 * @param <X> 要素の型
 *
 * @see Tuple
 *
 * @since Java Persistence 2.0
 */
public interface TupleElement<X> {
    
    /**
     * タプル要素のJavaの型を返します。
     * @return タプル要素のJavaの型
     */
    Class<? extends X> getJavaType();

    /**
     * タプル要素に割り当てられたエイリアスを返します。nullの場合はエイリアスが割り当てられてません。
     * @return エイリアス
     */
    String getAlias();
}
