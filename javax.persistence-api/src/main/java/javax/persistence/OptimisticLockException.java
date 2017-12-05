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
 * 楽観ロックで競合が発生したときに永続化プロバイダによって投げられます。
 * この例外はフラッシュやコミット時のAPI呼び出しの一部として投げられます。
 * 現在のトランザクションがアクティブである場合はロールバックするようにマークされます。
 * 
 * @see EntityManager#find(Class, Object, LockModeType)
 * @see EntityManager#find(Class, Object, LockModeType, java.util.Map)
 * @see EntityManager#lock(Object, LockModeType)
 * @see EntityManager#lock(Object, LockModeType, java.util.Map)
 * 
 * @since Java Persistence 1.0
 */
public class OptimisticLockException extends PersistenceException {

	/**
	 * このオブジェクトの原因となった例外
	 */
	Object entity;

	/**
	 * 新しい<code>OptimisticLockException</code>例外を<code>null</code>を詳細メッセージとして生成します。
	 * Constructs a new <code>OptimisticLockException</code> exception with
	 * <code>null</code> as its detail message.
	 */
	public OptimisticLockException() {
		super();
	}

	/**
     * 新しい<code>OptimisticLockException</code>例外を指定された詳細メッセージで生成します。
     * @param   message   詳細メッセージ
	 */
	public OptimisticLockException(String message) {
		super(message);
	}

	/**
     * 新しい<code>OptimisticLockException</code>例外を指定された詳細メッセージと原因で生成します。
     * @param   message   詳細メッセージ
     * @param   cause     原因
	 */
	public OptimisticLockException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
     * 新しい<code>OptimisticLockException</code>例外を指定された原因で生成します。
     * @param   cause     原因
	 */
	public OptimisticLockException(Throwable cause) {
		super(cause);
	}

	/**
     * 新しい<code>OptimisticLockException</code>例外を指定されたエンティティで生成します。
     * @param   entity     エンティティ
	 */
	public OptimisticLockException(Object entity) {
		this.entity = entity;
	}

	/**
     * 新しい<code>OptimisticLockException</code>例外を指定された詳細メッセージと原因とエンティティで生成します。
     * @param   message   詳細メッセージ
     * @param   cause     原因
     * @param   entity     エンティティ
	 */
	public OptimisticLockException(String message, Throwable cause, Object entity) {
		super(message, cause);
		this.entity = entity;
	}

	/**
     * この例外の原因となったエンティティを返します。
     * @return エンティティ
	 */
	public Object getEntity() {
		return this.entity;
	}

}
