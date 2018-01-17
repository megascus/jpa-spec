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
 * データがデータベースから読み取られたときとデータがデータベースにコミットされたときのふるまいを指定するために<code>javax.persistence.cache.storeMode</code>プロパティの値として使用されます。
 *
 * @since Java Persistence 2.0
 */
public enum CacheStoreMode {

    /**
     * エンティティデータをデータベースから読み込み、データベースにコミットしたときにキャッシュに挿入/更新します、これはデフォルトの動作です。
     * 
     * データベースから読み取られた項目がすでにキャッシュされている場合の更新は強制しません。
     */
    USE,

    /**
     * キャッシュに挿入しません。
     */
    BYPASS,

    /**
     * エンティティデータをデータベースから読み込み、データベースにコミットしたときにキャッシュに挿入/更新します。
     * 
     * データベースから読み取られた項目のキャッシュを強制的に更新します。
     */
    REFRESH
}
