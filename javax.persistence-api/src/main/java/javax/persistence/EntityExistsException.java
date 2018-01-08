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
 * Thrown by the persistence provider when {@link EntityManager#persist(Object)
 * EntityManager.persist(Object)} is called and the entity already exists. The
 * current transaction, if one is active, will be marked for rollback.
 * <p>
 * If the entity already exists, the <code>EntityExistsException</code> may be thrown when
 * the persist operation is invoked, or the <code>EntityExistsException</code> or another
 * <code>PersistenceException</code> may be thrown at flush or commit time.
 * <p> The current transaction, if one is active and the persistence context
 * has been joined to it, will be marked for rollback.
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
