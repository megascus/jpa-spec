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
 * L2キャッシュと対話するために使用されるインターフェースです。
 * 
 * キャッシュが使用されていない場合、このインターフェースのメソッドはfalseを返す<code>contains</code>を除いて何も効果を及ぼしません。
 * @since Java Persistence 2.0
 */
public interface Cache {

    /**
     * キャッシュに与えられたエンティティのためのデータが含まれているかどうか。
     * @param cls  エンティティクラス
     * @param primaryKey  プライマリーキー
     * @return エンティティがキャッシュに含まれているかどうかを示すboolean
     */
    public boolean contains(Class cls, Object primaryKey);

    /**
     * キャッシュから与えられたエンティティのためのデータを削除します。
     * @param cls  エンティティクラス
     * @param primaryKey  プライマリーキー
     */
    public void evict(Class cls, Object primaryKey);

    /**
     * 指定されたクラス(とそのサブクラス)のエンティティのデータをキャッシュから削除します。
     * @param cls  エンティティクラス
     */
    public void evict(Class cls);

    /**
     * キャッシュをクリアします。
     */
    public void evictAll();

    /**
     * プロバイダ固有のAPIにアクセスできるように、指定された型のオブジェクトを返します。
     * 
     * プロバイダのキャッシュ実装が指定されたクラスをサポートしていない場合、 <code>PersistenceException</code>が投げられます。
     * @param cls  返されるオブジェクトのクラス、通常だと基礎となるキャッシュの実装クラスか実装するインターフェイスのいずれかです。
     * @return 指定されたクラスのインスタンス
     * @throws PersistenceException プロバイダが呼び出しをサポートしない場合
     * @since Java Persistence 2.1
     */
    public <T> T unwrap(Class<T> cls);
}
