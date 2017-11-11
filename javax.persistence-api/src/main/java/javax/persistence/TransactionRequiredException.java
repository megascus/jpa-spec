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
 * トランザクションが必要であるがアクティブでないときに永続性プロバイダによってスローされます。
 * 
 * @since Java Persistence 1.0
 */
public class TransactionRequiredException extends PersistenceException {

	/**
	 * 詳細なメッセージとして<code>null</code>を指定して新しい<code>TransactionRequiredException</code>例外を生成します。
	 */
	public TransactionRequiredException() {
		super();
	}

	/**
	 * 指定された詳細なメッセージで新しい<code>TransactionRequiredException</code>例外を生成します。
	 * 
	 * @param message 詳細なメッセージ
	 */
	public TransactionRequiredException(String message) {
		super(message);
	}
}
