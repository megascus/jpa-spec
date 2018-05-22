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
package javax.persistence.criteria;

import java.util.Map;
import javax.persistence.metamodel.MapAttribute;

/**
 * <code>MapJoin</code>インターフェイスは<code>java.util.Map</code>として指定された関連や要素コレクションを介してコレクションに結合した結果の型です。
 *
 * @param <Z> 結合のソースの型
 * @param <K> ターゲットMapのキーの型
 * @param <V> ターゲットMapの値の型
 *
 * @since Java Persistence 2.0
 */
public interface MapJoin<Z, K, V> 
		extends PluralJoin<Z, Map<K, V>, V> {

    /**
     * 結合を変更して指定されたON条件に従って結果を制限した結合オブジェクトを戻します。
     * 
     * 以前のON条件があれば置き換えます。
     *  @param restriction  単純な、もしくは複合したブール式
     *  @return 変更された結合オブジェクト
     *  @since Java Persistence 2.1
     */
    MapJoin<Z, K, V> on(Expression<Boolean> restriction);

    /**
     * 結合を変更して指定されたON条件に従って結果を制限した結合オブジェクトを戻します。
     * 
     * 以前のON条件があれば置き換えます。
     *  @param restrictions  0個以上の制限の述語
     *  @return 変更された結合オブジェクト
     *  @since Java Persistence 2.1
     */
    MapJoin<Z, K, V> on(Predicate... restrictions);

    /**
     * マップ属性のメタモデル表現を返します。
     * @return 結合のターゲットである<code>Map</code>を表すメタモデル型
     */
    MapAttribute<? super Z, K, V> getModel();
    
    /**
     * マップのキーに対応するパス式を作ります。
     * @return マップのキーに対応するパス
     */
    Path<K> key();
    
    /**
     * マップの値に対応するパス式を作ります。
     * 
     * このメソッドは形式的な使用のためのみに使われます。それはこれそのものを返します。
     * @return マップの値に対応するパス
     */
    Path<V> value(); 
    
    /**
     * マップのエントリーに対応する式を作ります。
     * @return マップのエントリーに対応する式
     */
    Expression<Map.Entry<K, V>> entry();
}
