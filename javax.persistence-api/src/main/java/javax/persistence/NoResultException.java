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
 * クエリーの{@link Query#getSingleResult Query.getSingleResult()}や{@link TypedQuery#getSingleResult TypedQuery.getSingleResult()}が実行され、
 * 結果が見つからなかった場合に永続化プロバイダによって投げられます。
 * この例外では現在のトランザクションがアクティブな場合にロールバックするというマークは付きません。
 * 
 * @see Query#getSingleResult()
 * @see TypedQuery#getSingleResult()
 * 
 * @since Java Persistence 1.0
 */
public class NoResultException extends PersistenceException {

	/**
	 * 新しい<code>NoResultException</code>例外を<code>null</code>を詳細メッセージとして生成します。
	 */
	public NoResultException() {
		super();
	}

	/**
	 * 新しい<code>NoResultException</code>例外を指定された詳細メッセージで生成します。
	 * 
	 * @param message 詳細メッセージ
	 */
	public NoResultException(String message) {
		super(message);
	}
}
