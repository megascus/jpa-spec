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

/**
 * 永続化コンテキストが現在のトランザクションと常に自動的に同期するかどうか、
 * または永続化コンテキストを{@link EntityManager#joinTransaction}メソッドを使用して現在のトランザクションに明示的に参加する必要があるかどうかを指定します。
 *
 * @since Java Persistence 2.1
 */
public enum SynchronizationType {

    /** 永続化コンテキストは現在のトランザクションと常に自動的に同期します。 */
    SYNCHRONIZED,

    /** 永続化コンテキストは現在のトランザクションに明示的に参加する必要があります。 */
    UNSYNCHRONIZED,
}
