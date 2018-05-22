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
 * 永続化プロバイダにより
 * {@link EntityTransaction#commit() EntityTransaction.commit()} が失敗したときに投げられます。
 *
 * @see javax.persistence.EntityTransaction#commit()
 *
 * @since Java Persistence 1.0
 */
public class RollbackException extends PersistenceException {
	
        /** 
         * 新しい<code>RollbackException</code>例外を<code>null</code>を詳細なメッセージとして生成します。
         */
	public RollbackException() {
		super();
	}

        /** 
         * 新しい<code>RollbackException</code>例外を指定された詳細メッセージで生成します。
         * @param   message   詳細メッセージ
         */
	public RollbackException(String message) {
		super(message);
	}

        /** 
         * 新しい<code>RollbackException</code>例外を指定された詳細メッセージと原因例外で生成します。
         * @param   message   詳細メッセージ
         * @param   cause     原因例外
         */
	public RollbackException(String message, Throwable cause) {
		super(message, cause);
	}
	
        /** 
         * 新しい<code>RollbackException</code>例外を指定された原因例外で生成します。
         * @param   cause     原因例外
         */
	public RollbackException(Throwable cause) {
		super(cause);
	}
}


