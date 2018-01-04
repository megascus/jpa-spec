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
 * 問題が発生したときに永続化プロバイダによって投げれます。
 * 
 * {@link NoResultException}と{@link NonUniqueResultException}、{@link LockTimeoutException}、{@link QueryTimeoutException}を除いた
 * <code>PersistenceException</code>のインスタンスは現在のトランザクションがアクティブで、永続化コンテキストがそれに参加している場合、
 * ロールバックのマークが付けられる原因となります。
 *
 * @since Java Persistence 1.0
 */
public class PersistenceException extends RuntimeException {

        /** 
         * 新しい<code>PersistenceException</code>例外を<code>null</code>を詳細メッセージとして生成します。
         */
	public PersistenceException() {
		super();
	}

        /**
         * 新しい<code>PersistenceException</code>例外を指定された詳細メッセージで生成します。
         * @param   message   詳細メッセージ
         */
	public PersistenceException(String message) {
		super(message);
	}

        /**
         * 新しい<code>PersistenceException</code>例外を指定された詳細メッセージと原因で生成します。
         * @param   message   詳細メッセージ
         * @param   cause     原因
         */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
	
        /** 
         * 新しい<code>PersistenceException</code>例外を指定されたと原因で生成します。
         * @param   cause     原因
         */
	public PersistenceException(Throwable cause) {
		super(cause);
	}
}

