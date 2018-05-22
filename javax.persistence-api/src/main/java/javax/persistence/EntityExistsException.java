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
 * {@link EntityManager#persist(Object) EntityManager.persist(Object)}が呼び出された時にすでにそのエンティティが存在する場合に永続化プロバイダによって投げられます。
 * 
 * 現在のトランザクションがアクティブな場合、ロールバックがマークされます。
 * <p>
 * エンティティがすでに存在する場合、永続化操作が実行された場合に<code>EntityExistsException</code>が投げられるか、
 * <code>EntityExistsException</code>もしくはほかの<code>PersistenceException</code>がフラッシュもしくはコミット時に投げられる場合があります。
 * <p> 現在のトランザクションがアクティブで永続化コンテキストがそれに参加している場合、ロールバックがマークされます。
 *
 * @see javax.persistence.EntityManager#persist(Object)
 * 
 * @since Java Persistence 1.0
 */
public class EntityExistsException extends PersistenceException {

    /**
     * 新しい<code>EntityExistsException</code>例外を<code>null</code>を詳細メッセージとして生成します。
     */
    public EntityExistsException() {
        super();
    }

    /**
     * 新しい<code>EntityExistsException</code>例外を指定された詳細メッセージで生成します。
     * 
     * @param message   詳細メッセージ
     */
    public EntityExistsException(String message) {
        super(message);
    }

    /**
     * 新しい<code>EntityExistsException</code>例外を指定された詳細メッセージと原因で生成します。
     * 
     * @param message   詳細メッセージ
     * @param cause     原因
     */
    public EntityExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 新しい<code>EntityExistsException</code>例外を指定された原因で生成します。
     * 
     * @param cause     原因
     */
    public EntityExistsException(Throwable cause) {
        super(cause);
    }
}
