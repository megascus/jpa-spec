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
 * {@link PersistenceContext}でトランザクションスコープまたは拡張された永続化コンテキストを使用するかどうかを指定します。
 * 
 * 指定されていない場合はトランザクションスコープの永続化コンテキストが使用されます。
 *
 * @since Java Persistence 1.0
 */
public enum PersistenceContextType {

    /** トランザクションスコープの永続化コンテキスト */
    TRANSACTION,

    /** 拡張された永続化コンテキスト */
    EXTENDED
}
