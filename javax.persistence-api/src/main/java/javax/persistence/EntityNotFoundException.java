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
 * {@link EntityManager#getReference EntityManager.getReference}で取得されたエンティティへの参照にアクセスされたがエンティティが存在しない場合に永続性プロバイダによって投げられます。
 * 
 * {@link EntityManager#refresh EntityManager.refresh}が呼び出され、オブジェクトがデータベースに存在しなくなったときに投げられます。
 *  悲観的ロックで{@link EntityManager#lock EntityManager.lock}が使用され、エンティティがデータベースに存在しなくなったときに投げられます。
 * 
 * <p>現在のトランザクション(アクティブな状態で永続化コンテキストに結合されている場合)はロールバックがマークされます。
 * 
 * @see EntityManager#getReference(Class,Object)
 * @see EntityManager#refresh(Object)
 * @see EntityManager#refresh(Object, LockModeType)
 * @see EntityManager#refresh(Object, java.util.Map)
 * @see EntityManager#refresh(Object, LockModeType, java.util.Map)
 * @see EntityManager#lock(Object, LockModeType)
 * @see EntityManager#lock(Object, LockModeType, java.util.Map)
 * 
 * @since Java Persistence 1.0
 */
public class EntityNotFoundException extends PersistenceException {

	/**
	 * 新しい<code>EntityNotFoundException</code>例外を<code>null</code>を詳細メッセージとして生成します。
	 */
	public EntityNotFoundException() {
		super();
	}

	/**
	 * 新しい<code>EntityNotFoundException</code>例外を指定された詳細メッセージで生成します。
	 * 
	 * @param message 詳細メッセージ
	 */
	public EntityNotFoundException(String message) {
		super(message);
	}

}
