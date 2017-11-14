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
 * クエリーがタイムアウトし、ステートメントのみがロールバックされたときに永続化プロバイダから投げられます。
 * 
 * 現在のトランザクションがアクティブでもロールバックのマークは付けられません。
 *
 * @since Java Persistence 2.0
 */
public class QueryTimeoutException extends PersistenceException {

    /** この例外の原因となったクエリーオブジェクト */
    Query query;

    /** 
     * 新しい<code>QueryTimeoutException</code>例外を<code>null</code>を詳細なメッセージとして生成します。
     */
    public QueryTimeoutException() {
        super();
    }

    /** 
     * 新しい<code>QueryTimeoutException</code>例外を指定された詳細メッセージで生成します。
     * @param   message   詳細メッセージ
     */
    public QueryTimeoutException(String message) {
        super(message);
    }

    /** 
     * 新しい<code>QueryTimeoutException</code>例外を指定された詳細メッセージと原因例外で生成します。
     * @param   message   詳細メッセージ
     * @param   cause     原因例外
     */
    public QueryTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    /** 
     * 新しい<code>QueryTimeoutException</code>例外を指定された原因例外で生成します。
     * @param   cause     原因例外
     */
    public QueryTimeoutException(Throwable cause) {
        super(cause);
    }


    /** 
     * 新しい<code>QueryTimeoutException</code>例外を指定されたクエリーで生成します。
     * @param   query     クエリー
     */
    public QueryTimeoutException(Query query) {
        this.query = query;
    }

    /** 
     * 新しい<code>QueryTimeoutException</code>例外を指定された詳細メッセージと原因例外とクエリーで生成します。
     * @param   message   詳細メッセージ
     * @param   cause     原因例外
     * @param   query     クエリー
     */
    public QueryTimeoutException(String message, Throwable cause, Query query) {
        super(message, cause);
        this.query = query;
    }
    
    /**
     * この例外の原因となったクエリーを返します。
     * @return クエリー
     */
    public Query getQuery() {
        return this.query;
    }
}


